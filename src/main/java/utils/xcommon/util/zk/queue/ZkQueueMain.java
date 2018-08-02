/**
 * <b>项目名：</b>系统项目名称<br/>
 * <b>包名：</b>utils.xcommon.util.zk.lock<br/>
 * <b>文件名：</b>ZkLockMain.java<br/>
 * <b>Copyright (c)</b> 2017DHCC公司-版权所有<br/>
 * 
 */
package utils.xcommon.util.zk.queue;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;



/**
 * @description 这里是该类的描述
 * @author xuxiaoming
 * @createTime 2017年6月24日 上午10:36:48
 * @modifyTime 2017年6月24日 上午10:36:48
 * @version V1.0  
 * 
 */
public class ZkQueueMain {
	
	public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
		DistributedQueue queue = new DistributedQueue();
		queue.offer("This is a test".getBytes());
		queue.offer("This is a test2".getBytes());
		queue.offer("This is a test3".getBytes());
		queue.offer("This is a test4".getBytes());
		queue.offer("This is a test5".getBytes());
		queue.offer("This is a test6".getBytes());
		
		Thread.sleep(10*10000);
		byte[] test = queue.peek();
		System.out.println(new String(test));
	}
}
