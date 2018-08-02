/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.xcommon.util.lock.DistributedLockTask;

/**
 * 
 * @author xuxiaoming
 * @version $Id: TestLockThread.java, v 0.1 2016年8月2日 下午1:58:41 xuxiaoming Exp $
 */
public class TestLockThread extends Thread{
    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestLockThread.class);
    public void run(){
        LOGGER.info("创建一个抢锁的线程！");
        TestService service = new TestService();
        String inpara = Thread.currentThread().getName() + " in";
        ForkJoinTask<Object> bog = new DistributedLockTask("115.28.103.239:4180", "/testDistr", inpara, service);
        ForkJoinPool fjpool = new ForkJoinPool(1);
        ForkJoinTask<Object> resut = fjpool.submit(bog);
        try {
            LOGGER.info("线程执行完毕",resut.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
