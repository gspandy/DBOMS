package com.tydic.dbs.commons.utils;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 基础加密组件
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public abstract class Coder {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5 
	 * HmacSHA1 
	 * HmacSHA256 
	 * HmacSHA384 
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}
	
	/***
	 * 
	 * @Title: generateSmsCode 
	 * @Description: TODO(生成6位随机短信码) 
	 * @param @param length
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String generateSmsCode(int length){
		StringBuilder sb = null;
        sb = new StringBuilder();
        Random random = new Random();
        String charStr = "0123456789";
        for (int i = 0; i < length; i++) {
            sb.append(charStr.charAt(random.nextInt(charStr.length())));
        }
        return sb.toString();
	}
	
	/**
     * @return
     * @description: id生成器
     */
    public static String generateId(Integer length) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        String dateString = format.format(date);
        String random="";
        if(length!=null){
        	random = generateString(length);
        }else{
        	random = generateString(8);
        }      

        return dateString + random;
    }
       
    /**
     * @return
     * @description: id生成器
     */
    public static String generateId(String prefix,Integer length) {
    	if(StringUtils.isNotBlank(prefix)){
    		return prefix+generateId(length);
    	}else{
    		return generateId(length);
    	}
    }
    
    /***
     * 
     * @description: id生成器
     * @param prefix
     * @return
     */
    public static String generateId(String prefix){
    	if(StringUtils.isNotBlank(prefix)){
    		return prefix+generateId(6);
    	}else{
    		return generateId(6);
    	}
    }

    /**
     * 生成字母和数字混合的n位随机数
     *
     * @param length
     * @return
     */
    public static String generateString(Integer length) {
        StringBuilder sb = null;
        sb = new StringBuilder();
        Random random = new Random();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
        for (int i = 0; i < length; i++) {
            sb.append(charStr.charAt(random.nextInt(charStr.length())));
        }
        return sb.toString();
    }
}
