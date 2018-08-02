/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.netty;

/**
 * 
 * @author xuxiaoming
 * @version $Id: TestClient.java, v 0.1 2016年8月8日 下午5:24:10 xuxiaoming Exp $
 */
public class TestClient {
    public static void main(String[] args) {
        NIOClient client = new NIOClient();
        client.connect();
    }
}
