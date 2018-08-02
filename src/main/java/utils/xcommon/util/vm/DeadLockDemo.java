package utils.xcommon.util.vm;
 
public class DeadLockDemo {
 
	public static void main(String[] args) {
		final Object resource1 = "资源1";
		final Object resource2 = "资源2";
		Thread t1 = new Thread("线程1"){
			public void run() {
				synchronized(resource1){
					System.out.println("线程1：获取资源1使用权");
					try {
						Thread.sleep(500);
					} catch (Exception e) {	}
					synchronized(resource2){
						System.out.println("线程1：等待资源2");
					}
				}
			}
		};
		Thread t2 = new Thread("线程2"){
			public void run() {
				synchronized(resource2){
					System.out.println("线程2：获取资源2使用权");
					try {
						Thread.sleep(500);
					} catch (Exception e) {	}
					synchronized(resource1){
						System.out.println("线程2：等待资源1");
					}
				}
			}
		};
		
		t1.start();
		t2.start();
	}
}