package utils.xcommon.util.lock.test.t4volatile;

public class CharOutputThread extends Thread {
	private TestObject lock = null;
	
	public CharOutputThread(TestObject lock) {
		this.lock = lock;
	}
	
	public void run() {
		int i=0;
		while(i<26){
			if(lock.getState()==1){
				System.out.print((char)(i+'A'));
				i++;
				lock.setState(0);
			}
		}
//		for(int i = 0;i < 26;i++ ){
//			if(lock.getState()==1){
//				System.out.print((char)(i+'A'));
//			}
//		}
	}
}
