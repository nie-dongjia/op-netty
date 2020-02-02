package com.djn.cn.op.netty.study.nio;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.study.nio.TimeServer<br/>
 * <b>类描述：</b>NIO Server <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/2 13:32<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/2 13:32<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br   />
 */
public class TimeServer {
    /**
     * main
     *        
     * @param
     * @return void 
     * @since 1.0
     * @author op.nie-dongjia  
     */
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
               // 默认 接口
            }
        }
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        // 一个线程 搞定
        new Thread(multiplexerTimeServer,"NIO-MultiplexerTimeServer-001").start();

    }

}
