/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * 
 * @author xuxiaoming
 * @version $Id: NIOServer.java, v 0.1 2016年8月8日 上午11:46:27 xuxiaoming Exp $
 */
public class NIOServer {
    public void connect(){
        // Server服务启动器
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // 设置一个处理客户端消息和各种消息事件的类(Handler)

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            public ChannelPipeline getPipeline() throws Exception {

                return Channels.pipeline(new SimpleChannelHandler(){
                    /** 
                     * 当绑定到服务端的时候触发，打印"Hello world, I'm client." 
                     * 
                     * @alia OneCoder 
                     * @author lihzh 
                     */  
                    public void channelConnected(ChannelHandlerContext ctx,  ChannelStateEvent e) {  
                        System.out.println("Hello world, I'm Server.");  
                        
//                        String msg = "连接成功，该我发送消息了";
//                        e.getChannel().write(NettyUtils.getBuffer(msg));
                    }  
                    
                    /** 
                     * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
                     */
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        System.out.println("客户端收到消息："+e.getMessage());
                        String msg = "Server replay:"+e.getMessage();
                        
                        e.getChannel().write(NettyUtils.getBuffer(msg));
                    }
                });

            }

        });

        // 开放8000端口供客户端访问。
        bootstrap.bind(new InetSocketAddress(8000));
    }
}
