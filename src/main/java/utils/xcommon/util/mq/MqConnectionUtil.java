/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.impl.Method;

/** 
* rabbitmq连接的工具类
* 
* date: 2015年12月11日
* time: 下午1:07:39
* author: huangqiang1 
*/
public class MqConnectionUtil {
    /**
     * 初始化日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MqConnectionUtil.class);
    private static String host;
    private static int port;
    /** 连接工厂**/
    private static ConnectionFactory factory;
    
    static {
//        host = (String) SpringContextUtil.getProperty("rabbitmq.host");
//        port = Integer.valueOf(SpringContextUtil.getProperty("rabbitmq.port").toString());
        
        newFactory();
    }
    
    public static void newFactory(){
        factory = new ConnectionFactory();
        factory.setAutomaticRecoveryEnabled(true);
        factory.setHost(host);
        factory.setPort(port);
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        LOGGER.debug("开始获取连接");
        try {
            LOGGER.info("MQPool=====getConnection====host:【{}】,port:【{}】", host, port);
            if(factory == null){
                newFactory();
            }
            Connection conn = factory.newConnection();
            conn.addShutdownListener(new ShutdownListener() {
                public void shutdownCompleted(ShutdownSignalException cause) {
                    Method reason = (Method) cause.getReason();
                    if(cause.isHardError()){
                        LOGGER.info("MQPool=====session closed because:",reason.toString());
                    }else{
                        LOGGER.info("MQPool=====channel closed because:",reason.toString());
                    }
                }
            });
            LOGGER.debug("获取连接成功！");
            return conn;
        } catch (Exception e) {
            LOGGER.error("Connect mq error:{}!",e.getMessage());
            return null;
        }
    }
}
