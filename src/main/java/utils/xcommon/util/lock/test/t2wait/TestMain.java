package utils.xcommon.util.lock.test.t2wait;

public class TestMain {
	public static void main(String[] args) {
		Object lock = new Object();
		NumberOutputThread numberThread = new NumberOutputThread(lock);
		numberThread.setName("NumberThread");
		CharOutputThread charThread = new CharOutputThread(lock);
		charThread.setName("CharThread");
		
		numberThread.start();
		charThread.start();
	}
}
