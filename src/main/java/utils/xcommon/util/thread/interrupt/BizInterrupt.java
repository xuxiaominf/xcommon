package utils.xcommon.util.thread.interrupt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BizInterrupt extends Thread {
	BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	@Override
	public void run() {
		System.out.println("WaitInterrupt got lock, ready to wait!");
		int i = 0;
		while (true) {
			System.out.println("BizInterrupt is running:" + i++);
			if (i % 99999 ==0 && Thread.currentThread().isInterrupted()) {
//				System.out.println("Thread.interrupted()"+Thread.interrupted());
//				System.out.println("Thread.interrupted()"+Thread.interrupted());
				System.out.println("BizInterrupt was interrputed, exist loop!");
				return;
			}
		}
//		System.out.println(getNextTask());
	}
	
	//不可取消的任务，while
	public Integer getNextTask() {
	    boolean interrupted = false;
	    try {
	        while (true) {
	            try {
	                return queue.take();
	            } catch (InterruptedException e) {
	            	System.out.println("Catch InterruptedException");
	                interrupted = true;
	                // fall through and retry
	            }
	        }
	    } finally {
	        if (interrupted)
	            Thread.currentThread().interrupt();
	    }
	}
	
	//向queue中添加一个数字后，任务取消
	public void add(Integer a){
		queue.add(a);
	}
}
