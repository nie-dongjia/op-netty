package com.djn.cn.op.netty.study.bio;

import sun.applet.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.study.bio.TimeClient<br/>
 * <b>类描述：</b>TODO<br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/1 18:16<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/1 18:16<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br       />
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8099;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            socket = new Socket("127.0.0.1", port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("QUERY TIME ORDER");
            System.out.println("Send success");
            String resp = bufferedReader.readLine();
            System.out.println("Now is :" + resp);

        } catch (IOException e) {

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (printWriter != null) {
                printWriter.close();
                printWriter = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                socket = null ;
            }
        }


    }
}
