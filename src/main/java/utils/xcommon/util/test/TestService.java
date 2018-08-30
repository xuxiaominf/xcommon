/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.xcommon.util.lock.zk.CallBack;

/**
 * 
 * @author xuxiaoming
 * @version $Id: TestService.java, v 0.1 2016年8月2日 下午1:57:10 xuxiaoming Exp $
 */
public class TestService implements CallBack {
    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);
    public Object call(Object obj) {
        LOGGER.info("执行了对象的call方法");
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LOGGER.info("call方法执行完成！");
        return obj.toString()+" success";
    }
}
