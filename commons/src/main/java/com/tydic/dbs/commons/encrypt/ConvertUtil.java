/**
 * Copyright 2010 ZTEsoft Inc. All Rights Reserved.
 *
 * This software is the proprietary information of ZTEsoft Inc.
 * Use is subject to license terms.
 * 
 * $Tracker List
 * 
 * $TaskId: $ $Date: 9:24:36 AM (May 9, 2008) $comments: create 
 * $TaskId: $ $Date: 3:56:36 PM (SEP 13, 2010) $comments: upgrade jvm to jvm1.5 
 *  
 *  
 */
package com.tydic.dbs.commons.encrypt;

import java.io.UnsupportedEncodingException;

/**
 * Convert工具类
 * 
 * @author dawn
 */
public final class ConvertUtil {
    private ConvertUtil() {

    }

    /**
     * 把字节数组转换成16进制字符串
     * 
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        if (bArray == null || bArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp);
        }
        return sb.toString();
    }

    public static byte[] str2Byte(String str, String encoding) {
        boolean useDefault = true;
        byte[] retOut = null;
        if (encoding != null) {
            try {
                retOut = str.getBytes(encoding);
                useDefault = false;
            }
            catch (UnsupportedEncodingException e1) {
                System.out.println("Unsupport Encoding " + encoding + " for str2Byte");

            }
        }

        if (useDefault) {
            retOut = str.getBytes();
        }
        return retOut;
    }

    public static String byte2Str(byte[] inArr, String encoding) {
        boolean useDefault = true;
        String retOut = null;
        if (encoding != null) {
            try {
                retOut = new String(inArr, encoding);
                useDefault = false;
            }
            catch (UnsupportedEncodingException e1) {
                System.out.println("Unsupport Encoding " + encoding + " for byte2Str");

            }
        }

        if (useDefault) {
            retOut = new String(inArr);
        }
        return retOut;
    }

}
