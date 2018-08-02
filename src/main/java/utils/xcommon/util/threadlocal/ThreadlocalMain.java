package utils.xcommon.util.threadlocal;

public class ThreadlocalMain {
	public static void main(String[] args) {
		ThreadLocal<String> mainStr = new ThreadLocal<>();
		mainStr.set("mainThreadName");
		System.out.println(mainStr.get());;
		
		ThreadlocalTest test1 = new ThreadlocalTest();
		Thread thread1 = new Thread(test1,"test1");
		thread1.start();
		
		
		ThreadlocalTest test2 = new ThreadlocalTest();
		Thread thread2 = new Thread(test2,"test2");
		thread2.start();
	}
}
