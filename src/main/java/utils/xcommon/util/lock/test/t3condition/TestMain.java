package utils.xcommon.util.lock.test.t3condition;

import java.util.concurrent.locks.Condition;

public class TestMain {
	public static void main(String[] args) {
		TestLock lock = new TestLock();
		Condition numCondition = lock.newCondition();
		Condition charCondition = lock.newCondition();
		NumberOutputThread numberThread = new NumberOutputThread(lock, numCondition, charCondition);
		numberThread.setName("NumberThread");
		CharOutputThread charThread = new CharOutputThread(lock, numCondition, charCondition);
		charThread.setName("CharThread");
		numberThread.start();
		charThread.start();
	}
}
