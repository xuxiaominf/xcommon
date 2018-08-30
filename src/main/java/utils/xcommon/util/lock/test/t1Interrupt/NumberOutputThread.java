package utils.xcommon.util.lock.test.t1Interrupt;

public class NumberOutputThread extends Thread {
	private Thread charThread = null;

	public void run() {
		for(int i = 0;i < 26;i++ ){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.print(i*2+1);
				System.out.print(i*2+2);
				super.interrupted();
				charThread.interrupt();
			}
		}
	}
	
	public void setCharThread(Thread charThread) {
		this.charThread = charThread;
	}
}
