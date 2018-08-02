package utils.xcommon.util.lock.test;

import java.util.concurrent.locks.Condition;

public class NumberOutputThread extends Thread {
	private TestLock lock = null;
	private Condition numCondition = null;
	private Condition chaCondition = null;
	
	public NumberOutputThread(TestLock lock,Condition numCondition,Condition chaCondition) {
		this.lock = lock;
		this.numCondition = numCondition;
		this.chaCondition = chaCondition;
	}

//	public void run() {
//		for(int i = 1;i < 53;i++ ){
//			if(i % 2 == 1){
//				lock.tryLock(0);
//			}
//			System.out.print(i);
//			if(i % 2 == 0){
//				lock.unlock(0);
//			}
//		}
//	}
	
	public void run() {
		lock.tryLock(0);
		for(int i = 1;i < 53;i++ ){
//			if(i % 2 == 1){
//				try {
//					condition.await();
//				} catch (InterruptedException e) {
//					System.out.println("NumberOutputThread exception");
//				}
//			}
			System.out.print(i);
			if(i % 2 == 0){
				try {
				numCondition.await();
				chaCondition.signal();
			} catch (InterruptedException e) {
				System.out.println("NumberOutputThread exception");
			}
			}
		}
		lock.unlock(0);
	}
}
