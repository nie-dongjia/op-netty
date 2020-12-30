package com.djn.cn.op.netty.base.demo.v3.zt;


import com.djn.cn.op.netty.util.DataSwitchUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.base.demo.TimeServerHandler<br/>
 * <b>类描述：</b> 考虑TCP 粘包 Demo  <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/3 17:07<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/3 17:07<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br/>
 */
public class ZTServerHandler extends ChannelHandlerAdapter {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String message = getMessage((ByteBuf) msg);
        System.out.println("Server The time server receive order:" + message
                + "; the counter is ：" + ++counter);
        String body = "AAAA555500020002000200020002" + "5AA5";
        ByteBuf resp = Unpooled.copiedBuffer(DataSwitchUtil.hexStringToBytes(body));
        ctx.writeAndFlush(resp);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        return DataSwitchUtil.bytesToHexString(con);
    }
}
