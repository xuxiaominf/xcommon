package utils.xcommon.util.lock.test.t0lock;

public class NumberOutputThread extends Thread {
	private TestLock lock = null;
	
	public NumberOutputThread(TestLock lock) {
		this.lock = lock;
	}

	public void run() {
		for(int i = 1;i < 53;i++ ){
			if(i % 2 == 1){
				lock.tryLock(0);
			}
			System.out.print(i);
			if(i % 2 == 0){
				lock.unlock(0);
			}
		}
	}
}
