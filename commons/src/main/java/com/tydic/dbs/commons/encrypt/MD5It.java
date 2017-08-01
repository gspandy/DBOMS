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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 无密钥Md5实现
 * 
 * @author dawn
 */
public class MD5It {

    private String algorithm = System.getProperty("MD5.algorithm", "MD5");

    /**
     * 加密
     */
    public byte[] encrypt(byte[] bInputArr) throws Exception {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);

        }
        catch (SecurityException se) {
            throw new Exception(se);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex);
        }

        md.update(bInputArr);
        byte bDigest[] = md.digest();

        return bDigest;
    }

    /**
     * MD5 Arithmetic decrypt Unsupported operation
     */
    public byte[] decrypt(byte[] bInputArr) throws Exception {
        throw new java.lang.UnsupportedOperationException("MD5   decryption Unsupported operation  !");
    }

}
