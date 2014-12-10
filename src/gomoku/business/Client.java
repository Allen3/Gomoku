/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gomoku.business;

import gomoku.util.MarshallingCodecFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
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

    private final DeskInformationHandler deskInformationHandler;

    public Client() {
        deskInformationHandler = new DeskInformationHandler();
    }   //Client()

    public void connect(int port, String host) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        
        try {            
            Bootstrap bootstrap = new Bootstrap();
            
            bootstrap.
                    group(group).
                    channel(NioSocketChannel.class).
                    option(TCP_NODELAY, true).
                    handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(MarshallingCodecFactory.buildMarshallingDecoder());
                    socketChannel.pipeline().addLast(MarshallingCodecFactory.buildMarshallingEncoder());
                    socketChannel.pipeline().addLast(deskInformationHandler);
                }   //initChannel();
                    });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            
        } finally {
            group.shutdownGracefully();
        }
    }   //connect()

    public DeskInformationHandler getDeskInformationHandler() {
        return deskInformationHandler;
    }   //getDeskInformationHandler()
}   //Client
