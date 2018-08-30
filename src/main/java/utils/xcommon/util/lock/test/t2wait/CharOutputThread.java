package utils.xcommon.util.lock.test.t2wait;

public class CharOutputThread extends Thread {
	private Object lock = null;

	public CharOutputThread(Object lock) {
		super();
		this.lock = lock;
	}

	public void run() {
		for (int i = 0; i < 26; i++) {
			synchronized (lock) {
				System.out.print((char) (i + 'A'));
				notifyAll();
			}
		}
	}
}
