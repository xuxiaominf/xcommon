package utils.xcommon.util.lock.test.t1Interrupt;

public class CharOutputThread extends Thread {
	private Thread numberThread = null;
	
	public void run() {
		for(int i = 0;i < 26;i++ ){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.print((char)(i+'A'));
				super.interrupted();
				numberThread.interrupt();
			}
		}
	}
	public void setNumberThread(Thread numberThread) {
		this.numberThread = numberThread;
	}
}
