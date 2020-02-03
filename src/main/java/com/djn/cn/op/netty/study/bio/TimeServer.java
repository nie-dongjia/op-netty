package com.djn.cn.op.netty.study.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * * <b>类   名：</b>com.djn.cn.op.netty.study.bio.TimeServer<br/>
 * * <b>类描述：</b>BIO 同步阻塞  Server<br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/1 12:14<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/1 12:14<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br                               />
 */
public class TimeServer {


    public static void main(String[] args) throws IOException {
        int port = 8099;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port :  " + port);
            Socket socket = null;

            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
        }
        } finally {
            if (serverSocket != null) {
                System.out.println("The time server is closed");
                serverSocket.close();
                serverSocket = null;
            }

        }


    }
}
