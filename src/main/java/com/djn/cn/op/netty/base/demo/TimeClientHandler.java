package com.djn.cn.op.netty.base.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * <b>类   名：</b>com.djn.cn.op.netty.base.demo.TimeClientHandler<br/>
 * <b>类描述：</b>NIO 客户端处理 <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/3 21:23<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/3 21:23<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br />
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private  final ByteBuf firstMessage;
    public TimeClientHandler() {
        byte[] req = "Query Time Order".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("NOW is :" + body);
        ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        System.out.println("unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
