package com.djn.cn.op.netty.study.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.study.bio.TimeServerHandle<br/>
 * <b>类描述：</b>同步阻塞 TimeServerHandler <br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/1 12:40<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/1 12:40<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br   />
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader  =  new BufferedReader( new InputStreamReader(this.socket.getInputStream()));
            printWriter = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTiem = null ;
            String body = null ;

            while(true){
                body = bufferedReader.readLine();
                if(body == null ){
                    break;
                }
                System.out.println("The time server receive  ordedr : "+body );
                currentTiem = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString() : "BAD ORDER ";
                printWriter.println(currentTiem);
            }
        } catch (Exception e) {
            if(bufferedReader != null ){
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(printWriter != null ){
                printWriter.close();
                printWriter = null ;
            }
            if(this.socket != null ) {
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }finally{

        }
    }
}
