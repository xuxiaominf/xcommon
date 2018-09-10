package utils.xcommon.util.lock.test.t3condition;

import java.util.concurrent.locks.Condition;

public class NumberOutputThread extends Thread {
	private TestLock lock = null;
	Condition numberConditon=null;
	Condition charCondition=null;
	
	
	public NumberOutputThread(TestLock lock,Condition numberConditon,Condition charCondition) {
		this.lock = lock;
		this.numberConditon = numberConditon;
		this.charCondition = charCondition;
	}

	public void run() {
		lock.lock(0);
		for(int i = 0;i < 26;i++ ){
			System.out.print(i * 2 + 1);
			System.out.print(i * 2 + 2);
			charCondition.signal();
			try {
				numberConditon.await();
			} catch (InterruptedException e) {
				System.out.println("NumberOutputThread Interrupted");
			}
		}
		lock.unlock(0);
	}
}
