package gomoku.business;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * Created by allen on 12/9/14.
 */
public class DeskInformationHandler extends ChannelHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("DeskInformation Delivered!");
        ctx.writeAndFlush(msg);
    }   //write()

}   //DeskInformationHandler
