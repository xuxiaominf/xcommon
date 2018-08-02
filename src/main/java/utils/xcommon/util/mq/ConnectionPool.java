/**
 * <b>项目名：</b>系统项目名称<br/>
 * <b>包名：</b>pool<br/>
 * <b>文件名：</b>ConnectionPool.java<br/>
 * <b>Copyright (c)</b> 2016DHCC公司-版权所有<br/>
 * 
 */
package utils.xcommon.util.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;

/**
 * @description 这里是该类的描述
 * @author xuxiaoming
 * @createTime 2016年7月23日 上午9:04:28
 * @modifyTime 2016年7月23日 上午9:04:28
 * @version V1.0
 * 
 */
public class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    // 连接池里的链接
    private static List<Connection> connList = new ArrayList<Connection>();

    private static ReentrantReadWriteLock readAndWriteLock = new ReentrantReadWriteLock();

    private static ConnectionPool pool;

    private static long scanTime = 60 * 60;

    private ConnectionPool() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        ConnectionPoolClearThread clearThread = new ConnectionPoolClearThread();
        service.scheduleAtFixedRate(clearThread, 5, scanTime, TimeUnit.SECONDS);
    }

    public static ConnectionPool getPool() {
        if (pool == null) {
            synchronized (ConnectionPool.class) {
                if (pool == null) {
                    pool = new ConnectionPool();
                }
            }
        }
        return pool;
    }

    /**
     * 获取连接池中连接数
     * 
     * @return
     */
    public int getSize() {
        readAndWriteLock.readLock().lock();
        try {
            return connList.size();
        } finally {
            readAndWriteLock.readLock().unlock();
        }
    }

    /**
     * 定时清除已经关闭的连接
     */
    public void clearConnection() {
        readAndWriteLock.writeLock().lock();
        try {
            for (int i = 0; i < connList.size(); i++) {
                Connection conn = connList.get(i);
                if (conn != null && !conn.isOpen()) {
                    LOGGER.info("Clear Connection is not open");
                    connList.remove(conn);
                    conn = null;
                    continue;
                }
            }
        } catch (Exception e) {
            LOGGER.info("Clear Connection exception!");
            e.printStackTrace();
        } finally {
            readAndWriteLock.writeLock().unlock();
        }
    }

    /**
     * 获取连接，对外使用
     * 
     * @return
     */
    public Connection getConnection() {
        readAndWriteLock.readLock().lock();
        Connection connection = null;
        try {
            for (int i = 0; i < connList.size(); i++) {
                Connection conn = connList.get(i);
                if (conn != null && conn.isOpen()) {
                    connection = conn;
                    break;
                }
            }
            if (connection == null) {
                Connection conn = MqConnectionUtil.getConnection();
                connection = conn;
                connList.add(conn);
            }
        } finally {
            readAndWriteLock.readLock().unlock();
        }
        return connection;
    }
}
