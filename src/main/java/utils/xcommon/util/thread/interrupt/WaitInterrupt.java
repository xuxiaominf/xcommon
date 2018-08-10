package utils.xcommon.util.thread.interrupt;

public class WaitInterrupt extends Thread {
	private Object lockObject = null;
	
	public WaitInterrupt(Object lockObject) {
		this.lockObject = lockObject;
	}

	@Override
	public void run() {
		try {
			synchronized (lockObject) {
				System.out.println("WaitInterrupt got lock, ready to wait!");
				lockObject.wait(10000);
			}
		} catch (InterruptedException e) {
			System.out.println("WaitInterrupt sleep was interrputed:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
