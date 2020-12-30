package com.djn.cn.op.netty.base.demo.v3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Executors;


/**
 * <b>类   名：</b>com.djn.cn.op.netty.base.demo.TimeClientHandler<br/>
 * <b>类描述：</b>NIO 客户端处理 考虑TCP 粘包   <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/3 21:23<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/3 21:23<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br />
 */
public class TimeClientHandlerV2 extends ChannelHandlerAdapter {

    private int counter ;
    private byte[] req ;


    public TimeClientHandlerV2() {
         req = ("Query Time Order" + System.getProperty("line.separator")).getBytes();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
//        ByteBuf message = null;
//        for(int i = 0 ; i < 1 ;i++){
//            message = Unpooled.buffer(req.length);
//            message.writeBytes(req);
//            ctx.writeAndFlush(message);
//        }

        sendMsg(ctx);
    }
    public void sendMsg(ChannelHandlerContext ctx) {
        Executors.newSingleThreadExecutor().submit(new MsgRunnable(ctx));
    }

    class MsgRunnable implements Runnable {
        ChannelHandlerContext ctx;

        MsgRunnable(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            while (true) {
                ByteBuf message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        String body = (String) msg;
        System.out.println("NOW is :" + body+ ";the counter is : " + ++counter);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        System.out.println("unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
