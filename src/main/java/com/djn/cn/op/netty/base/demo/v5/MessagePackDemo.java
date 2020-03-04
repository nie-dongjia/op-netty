/**
 * Copyright (c) 2020-2025. op-platform.cn All Rights Reserved.
 * 项目名称：开放开发平台
 * 类名称：MessagePackDemo.java
 * 创建人：op.nie-dongjia
 * 联系方式：niedongjiamail@qq.com
 * 开源地址: https://github.com/nie-dongjia
 * 项目官网:
 */
package com.djn.cn.op.netty.base.demo.v5;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>类  名：</b>com.djn.cn.op.netty.base.demo.v5.MessagePackDemo<br/>
 * <b>类描述：</b>TODO<br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/3/3 20:42<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/3/3 20:42<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 *
 */
public class MessagePackDemo {
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();
        src.add("message");
        src.add("nie");
        src.add("dongjia");
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(src);

        List<String> dst = messagePack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst);
    }
}
