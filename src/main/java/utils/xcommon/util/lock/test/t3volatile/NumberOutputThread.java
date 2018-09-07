package utils.xcommon.util.lock.test.t3volatile;

public class NumberOutputThread extends Thread {
	private TestObject lock = null;
	
	public NumberOutputThread(TestObject lock) {
		this.lock = lock;
	}

	public void run() {
		int i=0;
		while(i<26){
			if(lock.getState()==0){
				System.out.print(i * 2 + 1);
				System.out.print(i * 2 + 2);
				i++;
				lock.setState(1);
			}
		}
		
//		for(int i = 0;i < 26;i++ ){
//			lock.tryLock(0);
//			System.out.print(i * 2 + 1);
//			System.out.print(i * 2 + 2);
//			lock.unlock(0);
//		}
	}
}
