package utils.xcommon.util.zk.lock;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkLock implements Watcher{
    private ZooKeeper zk = null;
    private String selfPath;
    private String waitPath;
    private String LOG_PREFIX_OF_THREAD;
    private  final String GROUP_PATH = "/olympicBonusLocks";
    private  final String SUB_PATH = "/olympicBonusLocks/sub";
    private  final int SESSION_TIMEOUT = 10000;
    private  final String CONNECTION_STRING = "192.168.160.200:2181";
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);
    
    public ZkLock(){
    	try {
			createConnection(CONNECTION_STRING, SESSION_TIMEOUT);
			createPath(GROUP_PATH, "Create group path!", false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
    	LOG_PREFIX_OF_THREAD = "Thread "+Thread.currentThread().getId();
    }
    
    /**
     * 获取锁
     * @return
     * @throws IOException 
     */
    public void getLock() throws KeeperException, InterruptedException, IOException {
        selfPath = zk.create(SUB_PATH,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(LOG_PREFIX_OF_THREAD+"创建锁路径:"+selfPath);
        if(checkMinPath()){
            getLockSuccess();
        }
    }
    
    /**
     * 释放锁
     * @return
     * @throws IOException 
     */
    public void releaseLock() throws KeeperException, InterruptedException, IOException {
        zk.delete(SUB_PATH,-1);
    }
    /**
     * 创建节点
     * @param path 节点path
     * @param data 初始数据内容
     * @return
     */
    public boolean createPath( String path, String data, boolean needWatch) throws KeeperException, InterruptedException {
        if(zk.exists(path, needWatch)==null){
            System.out.println( LOG_PREFIX_OF_THREAD + "节点创建成功, Path: "
                    + this.zk.create( path,
                    data.getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT )
                    + ", content: " + data );
        }
        return true;
    }
    /**
     * 创建ZK连接
     * @param connectString	 ZK服务器地址列表
     * @param sessionTimeout Session超时时间
     */
    public void createConnection( String connectString, int sessionTimeout ) throws IOException, InterruptedException {
        zk = new ZooKeeper( connectString, sessionTimeout, this);
        connectedSemaphore.await();
    }
    /**
     * 获取锁成功
    */
    public void getLockSuccess() throws KeeperException, InterruptedException {
        if(zk.exists(this.selfPath,false) == null){
            System.out.println(LOG_PREFIX_OF_THREAD+"本节点已不在了...");
            return;
        }
        System.out.println(LOG_PREFIX_OF_THREAD + "获取锁成功，赶紧干活！");
        Thread.sleep(5000);
        System.out.println(LOG_PREFIX_OF_THREAD + "删除本节点："+selfPath);
        zk.delete(this.selfPath, -1);
        releaseConnection();
    }
    /**
     * 关闭ZK连接
     */
    public void releaseConnection() {
        if ( this.zk !=null ) {
            try {
                this.zk.close();
            } catch ( InterruptedException e ) {}
        }
        System.out.println(LOG_PREFIX_OF_THREAD + "释放连接");
    }
    /**
     * 检查自己是不是最小的节点
     * @return
     */
    public boolean checkMinPath() throws KeeperException, InterruptedException {
    	//为了避免羊群效应，这里获取子节点的时候，不注册wather，如果注册了watcher，则节点变化时，会通知每一个客户端
         List<String> subNodes = zk.getChildren(GROUP_PATH, false);
         Collections.sort(subNodes);
         int index = subNodes.indexOf( selfPath.substring(GROUP_PATH.length()+1));
         switch (index){
             case -1:{
                 System.out.println(LOG_PREFIX_OF_THREAD+"本节点已不在了..."+selfPath);
                 return false;
             }
             case 0:{
                 System.out.println(LOG_PREFIX_OF_THREAD+"子节点中，我果然是老大"+selfPath);
                 return true;
             }
             default:{
                 this.waitPath = GROUP_PATH +"/"+ subNodes.get(index - 1);
                 System.out.println(LOG_PREFIX_OF_THREAD+"获取子节点中，排在我前面的"+waitPath);
                 try{
                     zk.getData(waitPath, true, new Stat());
                     return false;
                 }catch(KeeperException e){
                     if(zk.exists(waitPath,false) == null){
                         System.out.println(LOG_PREFIX_OF_THREAD+"子节点中，排在我前面的"+waitPath+"已失踪，幸福来得太突然?");
                         return checkMinPath();
                     }else{
                         throw e;
                     }
                 }
             }
                 
         }
    }
    @Override
    public void process(WatchedEvent event) {
        if(event == null){
            return;
        }
        Event.KeeperState keeperState = event.getState();
        Event.EventType eventType = event.getType();
        if ( Event.KeeperState.SyncConnected == keeperState) {
            if ( Event.EventType.None == eventType ) {
                System.out.println( LOG_PREFIX_OF_THREAD + "成功连接上ZK服务器" );
                connectedSemaphore.countDown(); 
            }else if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
                System.out.println(LOG_PREFIX_OF_THREAD + "收到情报，排我前面的家伙已挂，我是不是可以出山了？");
                try {
                    if(checkMinPath()){
                        getLockSuccess();
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else if ( Event.KeeperState.Disconnected == keeperState ) {
            System.out.println( LOG_PREFIX_OF_THREAD + "与ZK服务器断开连接" );
        } else if ( Event.KeeperState.AuthFailed == keeperState ) {
            System.out.println( LOG_PREFIX_OF_THREAD + "权限检查失败" );
        } else if ( Event.KeeperState.Expired == keeperState ) {
            System.out.println( LOG_PREFIX_OF_THREAD + "会话失效" );
        }
    }
}

