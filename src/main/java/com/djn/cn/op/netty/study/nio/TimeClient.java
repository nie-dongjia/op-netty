package com.djn.cn.op.netty.study.nio;

/**
 * <b>类   名：</b>com.djn.cn.op.netty.study.bio.TimeClient<br/>
 * <b>类描述：</b>TODO<br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/2/1 18:16<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/2/1 18:16<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0<br               />
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


    }
}
