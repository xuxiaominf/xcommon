package utils.xcommon.util.thread.interrupt;

public class InterruptTest {
	public static void main(String[] args) throws InterruptedException {
		//sleep 的中断,sleep 10秒，在第三秒钟中断
//		BasicInterrupt inter = new BasicInterrupt();
//		inter.start();
//		Thread.sleep(3000);
//		inter.interrupt();
		
		//wait的中断,sleep wait秒，在第三秒钟中断
//		Object obj = new Object();
//		WaitInterrupt inter = new WaitInterrupt(obj);
//		inter.start();
//		Thread.sleep(2000);
//		inter.interrupt();
		
		//业务处理的中断，业务根据需要处理中断
		BizInterrupt inter = new BizInterrupt();
		inter.start();
		Thread.sleep(300);
		System.out.println("Interrupt now！！！！！！！！！！！！！！！！！！！");
		inter.interrupt();
		
//		Thread.sleep(3000);
//		System.out.println("Interrupt go on running&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		inter.add(111);
	}
}
