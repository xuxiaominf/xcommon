/**
 * <b>项目名：</b>系统项目名称<br/>
 * <b>包名：</b>pool<br/>
 * <b>文件名：</b>ConnectionPoolClearThread.java<br/>
 * <b>Copyright (c)</b> 2016DHCC公司-版权所有<br/>
 * 
 */
package utils.xcommon.util.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.xcommon.util.string.LOG;

/**
 * @description 这里是该类的描述
 * @author xuxiaoming
 * @createTime 2016年7月23日 上午9:12:03
 * @modifyTime 2016年7月23日 上午9:12:03
 * @version V1.0  
 * 
 */
public class ConnectionPoolClearThread implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPoolClearThread.class);
	public void run() {
	    LOGGER.info("开始一次清理连接池的任务");
		try{
			ConnectionPool.getPool().clearConnection();
		}catch(Exception e){
		    LOGGER.error(String.format("捕获到清理连接池异常%s", LOG.str("Exception", e)));
		}
	}
}
