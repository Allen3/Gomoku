/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.business;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import static io.netty.channel.ChannelOption.TCP_NODELAY;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * @author allen
 */
public class Client {
    
    private final EventLoopGroup group = new NioEventLoopGroup();
    
    public void connect() {
        
        try {            
            Bootstrap bootstrap = new Bootstrap();
            
            bootstrap.
                    group(group).
                    channel(NioSocketChannel.class).
                    option(TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    
                    //socketChannel.pipeline().addLast("MessageDecoder", new MessageDecoder());
                    //socketChannel.pipeline().addLast("MessageEncoder", new MessageEncoder());
                    //socketChannel.pipeline().addLast("DetectTimeoutHandler", new DetectTimeoutHandler());
                    //socketChannel.pipeline().addLast("LoginAuthenticationHandler", new LoginAuthenticationHandler());
                    //socketChannel.pipeline().addLast("HeartBeatHandler", new HeartBeatHandler());
                    
                }   //initChannel();
                        
                    });
            
            
        } finally {
            group.shutdownGracefully();
        }
        
    }   //connect()
    
}   //Client
