package utils.xcommon.util.lock.test;

import java.util.concurrent.locks.Condition;

public class TestMain {
	public static void main(String[] args) {
		TestLock lock = new TestLock();
		Condition numCondition = lock.newCondition();
		Condition chaCondition = lock.newCondition();
		NumberOutputThread numberThread = new NumberOutputThread(lock, numCondition, chaCondition);
		numberThread.setName("NumberThread");
		CharOutputThread charThread = new CharOutputThread(lock, numCondition, chaCondition);
		charThread.setName("CharThread");
		numberThread.start();
		charThread.start();
	}
}
