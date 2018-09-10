package utils.xcommon.util.lock.test.t3condition;

import java.util.concurrent.locks.Condition;

public class CharOutputThread extends Thread {
	private TestLock lock = null;
	Condition numberConditon=null;
	Condition charCondition=null;
	
	public CharOutputThread(TestLock lock,Condition numberConditon,Condition charCondition) {
		this.lock = lock;
		this.numberConditon = numberConditon;
		this.charCondition = charCondition;
	}
	
	public void run() {
		lock.lock(1);
		for(int i = 0;i < 26;i++ ){
			System.out.print((char)(i+'A'));
			numberConditon.signal();
			try {
				charCondition.await();
			} catch (InterruptedException e) {
				System.out.println("CharOutputThread Interrupted");
			}
		}
		lock.unlock(1);
	}
}
