package utils.xcommon.util.thread.interrupt;

public class BasicInterrupt extends Thread {
	
	@Override
	public void run() {
		try {
			System.out.println("BasicInterrupt is sleeping!");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("BasicInterrupt sleep was interrputed:" + e.getMessage());
//			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}
}
