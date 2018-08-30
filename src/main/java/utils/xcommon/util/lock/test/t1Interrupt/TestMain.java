package utils.xcommon.util.lock.test.t1Interrupt;

public class TestMain {
	public static void main(String[] args) {
		NumberOutputThread numberThread = new NumberOutputThread();
		numberThread.setName("NumberThread");
		CharOutputThread charThread = new CharOutputThread();
		charThread.setName("CharThread");
		
		numberThread.setCharThread(charThread);
		charThread.setNumberThread(numberThread);
		numberThread.start();
		charThread.start();
		
		numberThread.interrupt();
	}
}
