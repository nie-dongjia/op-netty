/**
 * Copyright (c) 2020-2025. op-platform.cn All Rights Reserved.
 * 项目名称：开放开发平台
 * 类名称：DataSwitchUtil.java
 * 创建人：op.nie-dongjia
 * 联系方式：niedongjiamail@qq.com
 * 开源地址: https://github.com/nie-dongjia
 * 项目官网:
 */
package com.djn.cn.op.netty.util;

/**
 * <b>类  名：</b>DataSwitchUtil<br/>
 * <b>类描述：</b>数据转换工具类<br/>
 * <b>创建人：</b>op.nie-dongjia<br/>
 * <b>创建时间：</b>2020/3/7 17:19<br/>
 * <b>修改人：</b>op.nie-dongjia<br/>
 * <b>修改时间：</b>2020/3/7 17:19<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
public class DataSwitchUtil {

    /**
     * Convert byte[] to hex string(将16进制的字节数组按照16进制打印出来) byte[] = {0x7e ,0x7e} -> "7e7e"
     *
     * @return String
     * @Title bytesToHexString
     */
    public static String bytesToHexString(byte[] src) {
        if (src == null || src.length <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < src.length; i++) {
            String hv = Integer.toHexString(src[i] & 0xFF);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[] 例如 "7e7e"-> byte[] src ={126,126}
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    /**
     * 将字符串转换成16进制字符串  例如 DYD,000000#  -> 4459442c30303030303023
     *
     * @return String
     * @Title string2HexString
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }


    //ASCII码转换为16进制
    public static String convertStringToHex(String str) {

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    //16进制转换为ASCII
    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }


    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 一个十六进制字符串 转换成 二进制字符串  例如  "00" -> "00000000" ;
     *
     * @param hexString 十六进制字符串  "00"
     * @return
     */
    public static String hexStringToBinString(String hexString) {
        int num = Integer.parseInt(hexString, 16);
        String binString = Integer.toBinaryString(num).toString();
        // 高位补零
        int length = binString.length();
        if (length < 8) {
            int addLength = 8 - length;
            for (int i = 0; i < addLength; i++) {
                binString = "0" + binString;
            }
        }
        return binString;
    }


}
