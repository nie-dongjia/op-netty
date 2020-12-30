package com.djn.cn.op.netty.base.demo.v3.zt;

import com.djn.cn.op.netty.util.DataSwitchUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


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
public class ZTClientHandler extends ChannelHandlerAdapter {

    private int counter ;
    public ZTClientHandler() {
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        String toMsg = "AAAA5555C800B0C0133004190000000000"+"5AA5";
//        for(int i = 0 ; i < 10000 ;i++){
//            try {
//                // 停半秒为了减少历史协议粘包粘问题，不能根本解决问题
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//            }
//            System.out.println("发送数据："+toMsg);
//
//            ctx.writeAndFlush(DataSwitchUtil.hexStringToBytes(toMsg));
//        }
        ByteBuf message = null;
        for(int i = 0 ; i < 10000 ;i++){
            System.out.println("发送数据："+toMsg);
            byte[]req = DataSwitchUtil.hexStringToBytes(toMsg);
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        String message = getMessage((ByteBuf) msg);
        System.out.println("Client NOW is :" + message+ ";the counter is : " + ++counter);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        System.out.println("unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        return DataSwitchUtil.bytesToHexString(con);
    }
}
