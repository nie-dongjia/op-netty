package com.djn.cn.op.netty.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.study.nio.MultiplexerTimeServer<br/>
 * <b>类描述：</b>NIO 多路复用 <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/2 14:19<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/2 14:19<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br />
 */
public class MultiplexerTimeServer implements  Runnable{
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile  boolean stop;
    /**
     * MultiplexerTimeServer  初始化多路复用 ，绑定监听端口  .
     *        
     * @param 端口号
     * @return  
     * @since 1.0
     * @author op.nie-dongjia  
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port ：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public  void stop(){
        this.stop= true;
    }
    @Override
    public void run() {
        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it =  selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (it.hasNext()){
                    selectionKey = it.next();
                    it.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (IOException e) {
                        if(selectionKey != null ){
                            selectionKey.cancel();
                            if(selectionKey.channel() != null){
                                selectionKey.channel().close();
                            }
                        }
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(selector != null ){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if(selectionKey.isValid()){
            // 处理新接入的请求信息
            if(selectionKey.isAcceptable()){
                // Accept the new connection
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                // Add the new connection to the selector
                socketChannel.register(selector,SelectionKey.OP_READ);
            }
            if(selectionKey.isReadable()){
                // Read the new connection
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(readBuffer);

                if(readBytes> 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);

                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server receiver order :" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString() : "BAD ORDER ";
                    doWrite(socketChannel,currentTime);
                }else if (readBytes < 0 ) {
                    // 链路关闭
                    selectionKey.cancel();
                    socketChannel.close();
                } else {
                    ; // 读到 0 忽略
                }
            }
        }
    }
    /**
     * doWrite   nio 写数据
     *        
     * @param [socketChannel, response]       
     * @return void 
     * @since 1.0
     * @author op.nie-dongjia  
     */
    private void doWrite(SocketChannel socketChannel ,String response) throws IOException {
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer bufferWrite = ByteBuffer.allocate(bytes.length);
            bufferWrite.put(bytes);
            bufferWrite.flip();
            socketChannel.write(bufferWrite);
        }
    }
}
