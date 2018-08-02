/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author xuxiaoming
 * @version $Id: test.java, v 0.1 2016年8月2日 下午1:05:40 xuxiaoming Exp $
 */
public class test {
    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(test.class);
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        for(int i=0;i<5;i++){
//            TestLockThread thread = new TestLockThread();
//            thread.start();
//        }
//    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	BigDecimal big = new BigDecimal("1300.657");
    	System.out.println(big.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString());
//    	System.out.println(String.valueOf(big.intValue()));
    }
}
