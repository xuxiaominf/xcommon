package utils.xcommon.util.lock.test.t0lock;

public class CharOutputThread extends Thread {
	private TestLock lock = null;
	
	public CharOutputThread(TestLock lock) {
		this.lock = lock;
	}
	
	public void run() {
		for(int i = 0;i < 26;i++ ){
			lock.tryLock(1);
			System.out.print((char)(i+'A'));
			lock.unlock(1);
		}
		
	}
}
