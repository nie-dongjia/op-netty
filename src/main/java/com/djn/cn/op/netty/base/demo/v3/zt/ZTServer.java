package com.djn.cn.op.netty.base.demo.v3.zt;

import com.djn.cn.op.netty.util.DataSwitchUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.base.demo.TimeServer<br/>
 * <b>类描述：</b>TODO<br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/3 16:40<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/3 16:40<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br />
 */
public class ZTServer {

    public void bind(int port ) throws InterruptedException {
        // 配置服务端的nio线程组
        EventLoopGroup bossEventLoopGroup   = new NioEventLoopGroup();
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup,workerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());
            // 绑定端口，同步等待成功

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 等待服务器端端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
//            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//            ch.pipeline().addLast(new StringDecoder());
            String end = "5AA5" ;
            ByteBuf delimiter = Unpooled.copiedBuffer(DataSwitchUtil.hexStringToBytes(end));
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
//            ch.pipeline().addLast(new ByteArrayEncoder());

            ch.pipeline().addLast(new ZTServerHandler());
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // 默认 接口
            }
        }
        new ZTServer().bind(port);
    }
}
