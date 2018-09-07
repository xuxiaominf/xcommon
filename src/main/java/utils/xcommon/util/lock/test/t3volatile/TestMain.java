package utils.xcommon.util.lock.test.t3volatile;

public class TestMain {
	public static void main(String[] args) {
		TestObject lock = new TestObject();
		NumberOutputThread numberThread = new NumberOutputThread(lock);
		numberThread.setName("NumberThread");
		CharOutputThread charThread = new CharOutputThread(lock);
		charThread.setName("CharThread");
		numberThread.start();
		charThread.start();
	}
}
