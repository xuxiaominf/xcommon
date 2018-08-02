/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * 
 * @author xuxiaoming
 * @version $Id: NIOClient.java, v 0.1 2016年8月8日 下午1:14:23 xuxiaoming Exp $
 */
public class NIOClient {
    public void connect(){
        // Client服务启动器  
        ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));  

        ChannelPipeline channelPipeline = Channels.pipeline();

        channelPipeline.addFirst("firstDownPingHander", pingHandler);
        
        // 设置一个处理服务端消息和各种消息事件的类(Handler)  
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
                        System.out.println("Hello world, I'm client.");  
                        
                        String msg = "连接成功，该我发送消息了";
                        e.getChannel().write(NettyUtils.getBuffer(msg));
                    }  
                    
                    /** 
                     * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
                     */
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        System.out.println("客户端收到消息："+e.getMessage());
                        
                        String msg = "Client replay:"+e.getMessage();
                        e.getChannel().write(NettyUtils.getBuffer(msg));
                    }
                });  
            }  
        });  
        
        ChannelPipeline channelPipeLine = null;
        channelPipeLine.addFirst("1", new ChannelDownstreamHandler() {
            
            public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
                // TODO Auto-generated method stub
                
            }
        });
        
        // 连接到本地的8000端口的服务端  
        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
        bootstrap.setOption("child.keepAlive",true);
    }
    
    
    ChannelHandler pingHandler = new ChannelDownstreamHandler() {
        public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
            
        }
    };
    
    ChannelHandler msgHandler = new SimpleChannelHandler() {
        /** 
         * 当绑定到服务端的时候触发，打印"Hello world, I'm client." 
         * 
         * @alia OneCoder 
         * @author lihzh 
         */  
        public void channelConnected(ChannelHandlerContext ctx,  ChannelStateEvent e) {  
            System.out.println("Hello world, I'm client.");  
            
            String msg = "连接成功，该我发送消息了";
            e.getChannel().write(NettyUtils.getBuffer(msg));
        }  
        
        /** 
         * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
         */
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            System.out.println("客户端收到消息："+e.getMessage());
            
            String msg = "Client replay:"+e.getMessage();
            e.getChannel().write(NettyUtils.getBuffer(msg));
        }
    };
    
    ChannelPipelineFactory channelPipelineFactory = new ChannelPipelineFactory() {
        public ChannelPipeline getPipeline() throws Exception {
            // TODO Auto-generated method stub
            return null;
        }
    };
    
}
