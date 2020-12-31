package com.djn.cn.op.netty.base.demo.v6;


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
public class TimeServerHandler extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        System.out.println("The time server receive body:" + msg );
//        ByteBuf buf =  (ByteBuf)msg;
//        byte[] con = new byte[buf.readableBytes()];
//        buf.readBytes(con);
        byte[] con = (byte[]) msg;
        System.out.println("The time server receive body:" + DataSwitchUtil.bytesToHexString(con));

            String currentTime = "AAAA55554000B0A01411111900000000010000000000000000000000000000000000000000000000000000000000000000000000000000005511C1B9ODOA";
            ByteBuf resp = Unpooled.copiedBuffer(DataSwitchUtil.hexStringToBytes(currentTime));
//            ctx.writeAndFlush(DataSwitchUtil.hexStringToBytes(currentTime));
            ctx.writeAndFlush(resp);
        // 发送数据
//        while(true){
//            // A55AA55A
//            String currentTime = "AAAA55554000B0A01411111900000000010000000000000000000000000000000000000000000000000000000000000000000000000000005511C1B9ODOA";
//            ByteBuf resp = Unpooled.copiedBuffer(DataSwitchUtil.hexStringToBytes(currentTime));
//            ctx.writeAndFlush(resp);
//        }

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
