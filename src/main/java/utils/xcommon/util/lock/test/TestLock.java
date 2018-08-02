package utils.xcommon.util.lock.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;

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
			System.out.println(String.format("%s tryAcquire(%s)",Thread.currentThread().getName(), arg));
			if (getExclusiveOwnerThread() == null && state == arg) {
				System.out.println(String.format("%s tryAcquire(%s) in",Thread.currentThread().getName(), arg));
				if (compareAndSetState(state, arg)) {
					System.out.println(String.format("%s tryAcquire(%s) in success",Thread.currentThread().getName(), arg));
					setExclusiveOwnerThread(current);
					return true;
				}
			}
			System.out.println(String.format("%s tryAcquire(%s) failed",Thread.currentThread().getName(), arg));
			return false;
		}
		@Override
		protected boolean tryRelease(int arg) {
			if (!(arg == 0 || arg == 1)) {
				throw new Error("Arg must 1 or 0");
			}
			int modState = ~arg << 31 >>> 31;
			System.out.println(String.format("%s tryRelease(%s)",Thread.currentThread().getName(),arg));
			boolean setted = compareAndSetState(arg, modState);
			System.out.println(String.format("%s tryRelease(%s) result %s",Thread.currentThread().getName(),arg, setted));
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

	public void tryLock(int state) {
		sync.acquire(state);
	}

	public void unlock(int state) {
		sync.release(state);
	}

	@Override
	public void lock() {
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
	public void unlock() {
	}

}
