package utils.xcommon.util.zk.queue;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedQueue {
	private static final Logger LOG = LoggerFactory.getLogger(DistributedQueue.class);
	private final String dir="/queueTest";
	private ZooKeeper zookeeper;
	private List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
	private final String prefix = "qn-";

	public DistributedQueue() throws IOException {
		this.zookeeper =  new ZooKeeper( "192.168.160.200:2181", 10000,new LatchChildWatcher());
		
	}

	/**
	 * Returns a Map of the children, ordered by id.
	 * 获取children 的Map集合, 根据ID来排序
	 * @param watcher optional watcher on getChildren() operation.
	 * @return map from id to child name for all children
	 */
	private TreeMap<Long, String> orderedChildren(Watcher watcher)
			throws KeeperException, InterruptedException {
		TreeMap<Long, String> orderedChildren = new TreeMap<Long, String>();

		List<String> childNames = null;
		try {
			childNames = zookeeper.getChildren(dir, watcher);
		} catch (KeeperException.NoNodeException e) {
			throw e;
		}

		for (String childName : childNames) {
			try {
				// Check format
				if (!childName.regionMatches(0, prefix, 0, prefix.length())) {
					LOG.warn("Found child node with improper name: "
							+ childName);
					continue;
				}
				String suffix = childName.substring(prefix.length());
				Long childId = new Long(suffix);
				orderedChildren.put(childId, childName);
			} catch (NumberFormatException e) {
				LOG.warn("Found child node with improper format : " + childName
						+ " " + e, e);
			}
		}

		return orderedChildren;
	}

	/**
	 * Return the head of the queue without modifying the queue. 返回队定的元素
	 * 
	 * @return the data at the head of the queue.
	 * @throws NoSuchElementException
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] element() throws NoSuchElementException, KeeperException,
			InterruptedException {
		TreeMap<Long, String> orderedChildren;

		// element, take, and remove follow the same pattern.
		while (true) {
			try {
				orderedChildren = orderedChildren(null);
			} catch (KeeperException.NoNodeException e) {
				throw new NoSuchElementException();
			}
			if (orderedChildren.size() == 0)
				throw new NoSuchElementException();

			for (String headNode : orderedChildren.values()) {
				if (headNode != null) {
					try {
						return zookeeper.getData(dir + "/" + headNode, false,
								null);
					} catch (KeeperException.NoNodeException e) {
						// Another client removed the node first, try next
					}
				}
			}

		}
	}

	/**
	 * Attempts to remove the head of the queue and return it.
	 * 
	 * @return The former head of the queue
	 * @throws NoSuchElementException
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] remove() throws NoSuchElementException, KeeperException,
			InterruptedException {
		TreeMap<Long, String> orderedChildren;
		// Same as for element. Should refactor this.
		while (true) {
			try {
				orderedChildren = orderedChildren(null);
			} catch (KeeperException.NoNodeException e) {
				throw new NoSuchElementException();
			}
			if (orderedChildren.size() == 0)
				throw new NoSuchElementException();

			for (String headNode : orderedChildren.values()) {
				String path = dir + "/" + headNode;
				try {
					byte[] data = zookeeper.getData(path, false, null);
					zookeeper.delete(path, -1);
					return data;
				} catch (KeeperException.NoNodeException e) {
					// Another client deleted the node first.
				}
			}

		}
	}

	private class LatchChildWatcher implements Watcher {
		CountDownLatch latch;
		public LatchChildWatcher() {
			latch = new CountDownLatch(1);
		}
		public void process(WatchedEvent event) {
			LOG.debug("Watcher fired on path: " + event.getPath() + " state: "
					+ event.getState() + " type " + event.getType());
			latch.countDown();
		}
		public void await() throws InterruptedException {
			latch.await();
		}
	}

	/**
	 * Removes the head of the queue and returns it, blocks until it succeeds.
	 * 
	 * @return The former head of the queue
	 * @throws NoSuchElementException
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] take() throws KeeperException, InterruptedException {
		TreeMap<Long, String> orderedChildren;
		// Same as for element. Should refactor this.
		while (true) {
			LatchChildWatcher childWatcher = new LatchChildWatcher();
			try {
				orderedChildren = orderedChildren(childWatcher);
			} catch (KeeperException.NoNodeException e) {
				zookeeper.create(dir, new byte[0], acl, CreateMode.EPHEMERAL_SEQUENTIAL);
				continue;
			}
			if (orderedChildren.size() == 0) {
				childWatcher.await();
				continue;
			}

			for (String headNode : orderedChildren.values()) {
				String path = dir + "/" + headNode;
				try {
					byte[] data = zookeeper.getData(path, false, null);
					zookeeper.delete(path, -1);
					return data;
				} catch (KeeperException.NoNodeException e) {
					// Another client deleted the node first.
				}
			}
		}
	}

	/**
	 * Inserts data into queue.
	 * 
	 * @param data
	 * @return true if data was successfully added
	 */
	public boolean offer(byte[] data) throws KeeperException,
			InterruptedException {
		for (;;) {
			try {
				zookeeper.create(dir + "/" + prefix, data, acl,
						CreateMode.EPHEMERAL_SEQUENTIAL);
				return true;
			} catch (KeeperException.NoNodeException e) {
				zookeeper.create(dir, new byte[0], acl, CreateMode.EPHEMERAL_SEQUENTIAL);
			}
		}

	}

	/**
	 * Returns the data at the first element of the queue, or null if the queue
	 * is empty.
	 * 
	 * @return data at the first element of the queue, or null.
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] peek() throws KeeperException, InterruptedException {
		try {
			return element();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * Attempts to remove the head of the queue and return it. Returns null if
	 * the queue is empty.
	 * 
	 * @return Head of the queue or null.
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public byte[] poll() throws KeeperException, InterruptedException {
		try {
			return remove();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}