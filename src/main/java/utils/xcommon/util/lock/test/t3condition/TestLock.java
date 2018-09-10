package utils.xcommon.util.lock.test.t3condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TestLock implements Lock {
	/** Synchronizer providing all implementation mechanics */
	private Sync sync = null;
	/**
	 * state == 0 output 1-52 
	 * state == 1 output A-Z
	 * 
	 * @author xiaoming3.xu
	 * @version $Id: TestLock.java, v 0.1 2018年7月27日 下午4:03:41 xiaoming3.xu Exp
	 *          $
	 */
	static final class Sync extends AbstractQueuedSynchronizer {
		private static final long serialVersionUID = 1L;
		@Override
		protected boolean tryAcquire(int arg) {
			if (!(arg == 0 || arg == 1)) {
				throw new Error("Arg must 1 or 0");
			}
			final Thread current = Thread.currentThread();
			int state = getState();
			if (getExclusiveOwnerThread() == null && state == arg) {
				//如果获取锁成功，设置当前线程为执行线程
				if (compareAndSetState(state, arg)) {
					setExclusiveOwnerThread(current);
					return true;
				}
			}
			return false;
		}
		@Override
		protected boolean tryRelease(int arg) {
			if (!(arg == 0 || arg == 1)) {
				throw new Error("Arg must 1 or 0");
			}
			int modState = ~arg << 31 >>> 31;
			boolean setted = compareAndSetState(arg, modState);
			//设置当前执行线程为空
			if (setted) {
				setExclusiveOwnerThread(null);
			}
			return setted;
		}
        final ConditionObject newCondition() {
            return new ConditionObject();
        }
        protected final boolean isHeldExclusively() {
            // While we must in general read state before owner,
            // we don't need to do so to check if current thread is owner
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
	}
	public TestLock() {
		sync = new Sync();
	}

	public void unlock(int state) {
		sync.release(state);
	}
	public void lock(int state) {
		sync.acquire(state);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return false;
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public void lock() {
	}
	@Override
	public void unlock() {
	}

}
