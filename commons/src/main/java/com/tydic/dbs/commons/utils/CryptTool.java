package com.tydic.dbs.commons.utils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import org.apache.log4j.Logger;

public class CryptTool {
	private static Logger logger = Logger.getLogger(CryptTool.class);
	
/*	private static String publicKey="";
	private static String privateKey="";
	private static CryptTool cryptTool = null;*/
	private static Map<String, Object> keyMap = null;
	
	static {
		try {
			keyMap = RSACoder.getRSAKey();
		} catch (Exception e) {
			logger.error("oops, got an exception: ", e);
		}
	}

	private CryptTool() throws Exception {
/*		Map<String, Object> keyMap = RSACoder.initKey(RSACoder.DEFAULT_PUBLIC_KEY_STR,RSACoder.DEFAULT_PRIVATE_KEY_STR);
		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);*/
		keyMap = RSACoder.getRSAKey();
	}

/*	public static CryptTool getInstance() throws Exception {
		if (cryptTool == null) {
			cryptTool = new CryptTool();
		}
		return cryptTool;
	}*/
	
	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data) throws Exception{
//		return RSACoder.encryptByPublicKey(data, publicKey);
		return RSACoder.encryptByPublicKey(data, (RSAPublicKey) keyMap.get(RSACoder.PUBLIC_KEY));
	}
	
	/**
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data) throws Exception{
		return RSACoder.encryptByPrivateKey(data, (RSAPrivateKey) keyMap.get(RSACoder.PRIVATE_KEY));
	}
	
	/**
	 * 解密<br>
	 * 用公钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data) throws Exception{
		return RSACoder.decryptByPublicKey(data, (RSAPublicKey) keyMap.get(RSACoder.PUBLIC_KEY));
	}
	
	/**
	 * 解密<br>
	 * 用私钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data) throws Exception{
		return RSACoder.decryptByPrivateKey(data, (RSAPrivateKey) keyMap.get(RSACoder.PRIVATE_KEY));
	}
}
