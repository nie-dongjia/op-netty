/**
 * Copyright (c) 2020-2025. www.zntie.com All Rights Reserved.
 * 项目名称：隧道监控平台
 * 类名称：QueryTask.java
 * 创建人：nie-dongjia
 * 联系方式：niedongjiamail@qq.com
 * 项目官网:
 */
package com.djn.cn.op.netty.base.demo.v3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * <b>类  名：</b>com.djn.cn.op.netty.base.demo.v3.QueryTask<br/>
 * <b>类描述：</b>TODO<br/>
 * <b>创建人：</b>nie-dongjia<br/>
 * <b>创建时间：</b>2020/7/24 1:03<br/>
 * <b>修改人：</b>nie-dongjia<br/>
 * <b>修改时间：</b>2020/7/24 1:03<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br       />
 */
public class QueryTask implements Runnable {
    private final ChannelHandlerContext ctx;

    public QueryTask(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        //do something
        byte[] req = ("Query Time Order" + System.getProperty("line.separator")).getBytes();
        ByteBuf message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);
    }
}
