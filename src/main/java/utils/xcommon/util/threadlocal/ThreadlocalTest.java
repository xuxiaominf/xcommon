package utils.xcommon.util.threadlocal;

public class ThreadlocalTest implements Runnable{
	private static ThreadLocal<String> localStr = new ThreadLocal<>();
	public ThreadlocalTest() {
	}
	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		localStr.set("threadlocal test:"+thread.getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(localStr.get());
	}
}
