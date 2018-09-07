package utils.xcommon.util.lock.test.t2wait;

public class NumberOutputThread extends Thread {
	private Object lock = null;

	public NumberOutputThread(Object lock) {
		super();
		this.lock = lock;
	}

	public void run() {
		synchronized (lock) {
			for (int i = 0; i < 26; i++) {
				System.out.print(i * 2 + 1);
				System.out.print(i * 2 + 2);
				try {
					lock.notifyAll();
					lock.wait(1000);
				} catch (InterruptedException e) {
					// 测试代码，不处理中断
					e.printStackTrace();
				}
			}
		}
	}
}
