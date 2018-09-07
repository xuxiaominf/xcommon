package utils.xcommon.util.lock.test.t0lock;

public class NumberOutputThread extends Thread {
	private TestLock lock = null;
	
	public NumberOutputThread(TestLock lock) {
		this.lock = lock;
	}

	public void run() {
		for(int i = 0;i < 26;i++ ){
			lock.tryLock(0);
			System.out.print(i * 2 + 1);
			System.out.print(i * 2 + 2);
			lock.unlock(0);
		}
	}
}
