/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.netty;

/**
 * 
 * @author xuxiaoming
 * @version $Id: TestServer.java, v 0.1 2016年8月8日 下午5:20:32 xuxiaoming Exp $
 */
public class TestServer {
    public static void main(String[] args) {
        NIOServer server = new NIOServer();
        server.connect();
    }
}
