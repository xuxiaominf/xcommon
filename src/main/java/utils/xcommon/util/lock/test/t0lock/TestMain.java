package utils.xcommon.util.lock.test.t0lock;

public class TestMain {
	public static void main(String[] args) {
		TestLock lock = new TestLock();
		NumberOutputThread numberThread = new NumberOutputThread(lock);
		numberThread.setName("NumberThread");
		CharOutputThread charThread = new CharOutputThread(lock);
		charThread.setName("CharThread");
		numberThread.start();
		charThread.start();
	}
}
