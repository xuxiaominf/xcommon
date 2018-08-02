package utils.xcommon.util.lock.test;

import java.util.concurrent.locks.Condition;

public class CharOutputThread extends Thread {
	private TestLock lock = null;
	private Condition numCondition = null;
	private Condition chaCondition = null;
	
	public CharOutputThread(TestLock lock,Condition numCondition,Condition chaCondition) {
		this.lock = lock;
		this.numCondition = numCondition;
		this.chaCondition = chaCondition;
	}
	
	public void run() {
		lock.tryLock(1);
		for(int i = 0;i < 26;i++ ){
//			try {
//				condition.await();
//			} catch (InterruptedException e) {
//				System.out.println("CharOutputThread exception");
//			}
			System.out.print((char)(i+'A'));
			try {
			chaCondition.await();
			numCondition.signal();
		} catch (InterruptedException e) {
			System.out.println("CharOutputThread exception");
		}
		}
		lock.unlock(1);
	}
}
