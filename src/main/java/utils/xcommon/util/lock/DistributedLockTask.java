package utils.xcommon.util.lock;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.xcommon.util.string.LOG;

public class DistributedLockTask extends RecursiveTask<Object>  implements Watcher{
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockTask.class);

    /** zk */
    private ZooKeeper zk = null;

    /** path */
    private String selfPath;
    private String waitPath;

    /** create node */
    private String groupPath;
    private String subPath;

    /** zookeeper 连接超时时间 */
    private final int SESSION_TIMEOUT = 10 * 1000;
    
    /** 执行等待事件 等待5s*/
    private final int WAIT_TIMEOUT = 20;
    
    /** 调用参数*/
    private CallBack callBack;
    private Object inPara;
    private Object outPara;

    /** 等待compute 执行完成 */
    private CountDownLatch computeSemaphore = new CountDownLatch(1);

    public DistributedLockTask(String zookeeperAddr,String rootPath, Object inPara, CallBack callBack) {
        this.inPara = inPara;
        this.callBack = callBack;
        createConnection(getAddress(zookeeperAddr), SESSION_TIMEOUT);
        if(zk != null){
            groupPath = rootPath;
            if(createRootPath()){
                subPath = groupPath + "/sub";
                createSubPath();
            }
        }
    }

    /**
     * 获取锁
     * 
     * @return
     * @throws InterruptedException 
     * @throws KeeperException 
     */
    public boolean getLock() throws KeeperException, InterruptedException{
        boolean gotLock = checkMinPath();
        if (gotLock && zk.exists(this.selfPath, false)!=null) {
            return getLockSuccess();
        }
        return false;
    }

    /**
     * 创建节点
     * 
     * @param path
     *            节点path
     * @param data
     *            初始数据内容
     * @return
     */
    public boolean createRootPath(){
        Stat status = null;
        String rootPath = null;
        try{
            status = zk.exists(groupPath, false);
            if (status == null) {
                rootPath = this.zk.create(groupPath, "CreateGroupPath".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }catch(Exception e){
            LOGGER.error(String.format("创建根路径的时候出现了Exception%s", LOG.str("Exception", e.getMessage())));
        }

        LOGGER.info(String.format("创建根路径%s,状态%s", LOG.str("rootPath", rootPath),LOG.str("status", status)));
        return status!=null || rootPath!=null;
    }
    
    /**
     * 创建节点
     * 
     * @param path
     *            节点path
     * @param data
     *            初始数据内容
     * @return
     */
    public void createSubPath(){
        try {
            selfPath = zk.create(subPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (Exception e) {
            LOGGER.error(String.format("创建根路径的时候出现了Exception%s", LOG.str("Exception", e.getMessage())));
        }
        LOGGER.info(String.format("创建子路径%s", LOG.str("selfPath", selfPath)));
    }

    /**
     * 创建ZK连接
     * 
     * @param connectString
     *            ZK服务器地址列表
     * @param sessionTimeout
     *            Session超时时间
     */
    public void createConnection(String connectString, int sessionTimeout){
        try {
            zk = new ZooKeeper(connectString, sessionTimeout, this);
        } catch (IOException e) {
            LOGGER.info(String.format("捕获到创建zk连接异常%s", LOG.str("IOException", e.getMessage())));
        }
        LOGGER.info(String.format("创建zk连接%s,连接超时时间%s,是否成功%s", LOG.str("connectString", connectString),
                LOG.str("sessionTimeout", sessionTimeout),LOG.str("zk", (zk!=null))));
    }

    /**
     * 获取锁成功
     */
    public boolean getLockSuccess(){
        try{
            LOGGER.info("获取锁成功！开始执行！");
            //执行完成，返回参数
            outPara = callBack.call(inPara);
            computeSemaphore.countDown();
            return true;
        }catch(Exception e){
            LOGGER.info(String.format("执行任务的时候异常%s", LOG.str("IOException", e.getMessage())));
            return false;
        }finally {
            LOGGER.info("执行完毕！解锁");
            try {
                this.zk.delete(this.selfPath, -1);
                this.zk.close();
            } catch (Exception e) {
                LOGGER.info(String.format("捕获到释放zk连接异常%s", LOG.str("IOException", e.getMessage())));
            }
        }
    }

    /**
     * 检查自己是不是最小的节点
     * 
     * @return
     */
    public boolean checkMinPath() throws KeeperException, InterruptedException {
        List<String> subNodes = zk.getChildren(groupPath, false);
        Collections.sort(subNodes);
        int index = subNodes.indexOf(selfPath.substring(groupPath.length() + 1));
        LOGGER.info(String.format("当前节点的索引为%s",LOG.str("index", index)));
        switch (index) {
        case -1: {
            LOGGER.info("当前节点不存在！");
            return false;
        }
        case 0: {
            LOGGER.info("获取锁成功！");
            return true;
        }
        default: {
            this.waitPath = groupPath + "/" + subNodes.get(index - 1);
            LOGGER.info(String.format("得到等待节点%s",LOG.str("waitPath", this.waitPath)));
            try {
                zk.getData(waitPath, true, new Stat());
                LOGGER.info("给等待节点加监视");
                return false;
            } catch (KeeperException e) {
                if (zk.exists(waitPath, false) == null) {
                    LOGGER.info(String.format("等待节点不在了的异常%s！",LOG.str("KeeperException", e)));
                    return checkMinPath();
                } else {
                    LOGGER.info("给等待节点加监视出现其他异常！");
                    throw e;
                }
            }
        }

        }
    }

    public void process(WatchedEvent event) {
        if (event == null) {
            return;
        }
        Event.KeeperState keeperState = event.getState();
        Event.EventType eventType = event.getType();
        LOGGER.info(String.format("监视事件%s %s！",LOG.str("keeperState", keeperState),LOG.str("event", event)));
        if (eventType == Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
            LOGGER.info("等待节点收到监视事件！");
            try {
                getLock();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从地址字符串中提取 192.168.0.23:2181类似的地址字符串
     * zookeeper\://192.168.0.27\:2181?backup\=192.168.0.12\:2181 返回  192.168.0.27:2181,192.168.0.12:2181
     * @param addr
     * @return
     */
    private static String getAddress(String addr) {
        String ret = "";
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{2,5}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(addr);
        while (m.find()) {
            ret += m.group() + ",";
        }
        return ret;
    }

    /** 
     * @see java.util.concurrent.RecursiveTask#compute()
     */
    @Override
    protected Object compute() {
        try {
            boolean bool = getLock();
            if(!bool){
                computeSemaphore.await(WAIT_TIMEOUT, TimeUnit.SECONDS);
            }
        } catch (KeeperException e) {
            LOGGER.info("捕获到等待执行完成的时候的异常%s！",LOG.str("KeeperException", e.getMessage()));
        } catch (InterruptedException e) {
            LOGGER.info("捕获到等待执行完成的时候的异常%s！",LOG.str("InterruptedException", e.getMessage()));
        }

        return outPara;
    }
}
