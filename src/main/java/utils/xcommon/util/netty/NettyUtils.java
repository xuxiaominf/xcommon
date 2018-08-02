/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * 
 * @author xuxiaoming
 * @version $Id: NettyUtils.java, v 0.1 2016年8月11日 下午5:43:16 xuxiaoming Exp $
 */
public class NettyUtils {
    public static ChannelBuffer getBuffer(String msg){
        byte[] bytes = msg.getBytes();
        ChannelBuffer buffer = ChannelBuffers.buffer(bytes.length);  
        buffer.writeBytes(bytes);
        return buffer;
    }
}
