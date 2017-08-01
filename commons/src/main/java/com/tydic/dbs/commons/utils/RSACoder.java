package com.tydic.dbs.commons.utils;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

 

/**
 * RSA安全编码组件
 * 
 * @version 1.0
 * @since 1.0
 */
public abstract class RSACoder extends Coder {
	private static Logger logger = Logger.getLogger(RSACoder.class);
	
//	public static final String DEFAULT_PUBLIC_KEY_STR ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7IZehCKVyMYSOEPmBK7KXeewyVV0mcif97b4oP7Ntbj2vXW6jZH9WDGEnyacroA1DvToHQUGcNRy23uw46QopJ/DHDbinKq08ISDxRZjUbiu3o23O0KWuYdgMntmiKNdVOkRdjjyrVH9sa7GGKV4adgzToRQq3kpLcmag0rfOTzrI2YMzAgctXDBeKbntCV/2gXhSqieb/rNV5qAgBWF5SLS/OqK16DtWUEYwSzuUp2/Nnr/CgOJyuUjuQNeYgj+T3bZVi6A2PUWHBGGIpMT2+j5MzvHMIOQV3OxAhAUh+sqbobiWzDWkbX+jz+qpfxBoD1SZmOEoqylYnVDdFjKcwIDAQAB";
	
/*	public static final String DEFAULT_PRIVATE_KEY_STR ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDshl6EIpXIxhI4"
			+ "Q+YErspd57DJVXSZyJ/3tvig/s21uPa9dbqNkf1YMYSfJpyugDUO9OgdBQZw1HLb"
			+ "e7DjpCikn8McNuKcqrTwhIPFFmNRuK7ejbc7Qpa5h2Aye2aIo11U6RF2OPKtUf2x"
			+ "rsYYpXhp2DNOhFCreSktyZqDSt85POsjZgzMCBy1cMF4pue0JX/aBeFKqJ5v+s1X"
			+ "moCAFYXlItL86orXoO1ZQRjBLO5Snb82ev8KA4nK5SO5A15iCP5PdtlWLoDY9RYc"
			+ "EYYikxPb6PkzO8cwg5BXc7ECEBSH6ypuhuJbMNaRtf6PP6ql/EGgPVJmY4SirKVi"
			+ "dUN0WMpzAgMBAAECggEABg8/NqVoLvecGeLrbq1Hs/YumkndLd9s8by3S/CbNo+h"
			+ "iqMMf3oBApRqMGWd1/6i3Vo65Btv2c4njyXxFzPqcqVBK2Tgk2DwN8U9VvxG2R4h"
			+ "JMR+sD75e5FTTpfIvbFpv7Yk42F8HiKenEG9A36hwhxu3BEZu/rjqi+jDMALTf0L"
			+ "8IDk/WjMpkl7wFtmSzK43y3VZTVH5WT8NCh0UYJtTJje7FlDqijoqMD4cmfGnw6s"
			+ "+9K7rKATj6phsaZIRYkcztqL2MWQdlR/YDB821W6t8A49wNc3tf+MpY0mX27DYG1"
			+ "lo8xxma6pSUaTFqV5eXTInKk0pBF43KDet6rjXTaoQKBgQD749NgtdUkQ29G8fbs"
			+ "jL8onqlZc4R8z9dEB8HNCvyuHUGtwiX+NhNTVMmpKm7W77cKFubFpcigMR9l6q0c"
			+ "V229w6/ubYVuFbwnE7u3LywQgwCHrtyEFbcsLcmxCFk6q9P8Rok3LqJCGSOViOJF"
			+ "bu+cDnc7uK2djSZobvL7ALAUsQKBgQDwYlyfO5Y4eGOqHyLYc6IZV+soFPEJlaLp"
			+ "WOT6g8XtbZceB6lBPtEFj7rMO/zn1zEbgh5wryG56pdV0Me1fYL07Nj0d0+wbzd0"
			+ "MCawBq1LZKhL0hUtuib0AfW/+8xrSBOos9cx3kMZcg3YQnTWbYYpanhJa4X1J3Ro"
			+ "9QLAlIfqYwKBgHT65Vvwlj/1IWStUqOg6dYPeU6Vm13rmGl7wMFc7ORfqfTsSYC5"
			+ "1FfK9KyfjEI9qaAB6sK3r2jV52MJjcOvxsNucfIk4uaxKWRsga0dNzWj4UciKj8d"
			+ "ZqgZy6Vv3yCkVBrBF1CNU8WmryPvIkZkstqizTTrG5Yjy0aM4EM5mozBAoGARBKm"
			+ "xhT6um0lVCTTGlbMnb6vAj/D7oHoi4o39dVhNJZnSALFZIukZ53HOVT3jrP63R5g"
			+ "/mop89tCFfQ0yemML03vNnOfJoioPIfyPMD3TDVqD2ajxrpDk7AGZuKohez4XDrp"
			+ "gt69Q7RvxUE0kxCbAJl9DNWdLsqIhbx0lhK51p8CgYAB0hfR4IiH7+SG8OWyjLmk"
			+ "FuBMhfvuQh73BoQEKdpp6/opOYrbuJ6j2RKawrgVEzlZW89gaYi0hB4I5/F2JqiM"
			+ "x0sbEWWvhw8bA90HVApLDwIDw4WcpP+sj27t3KP7roP3MWCnZpX65sjZPZ4L6uSQ"
			+ "zAOxv/EhN302r7hn87Ny9Q==";*/
	
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	
/*	private static final String PUBLIC_KEY_FILE = "/wcsapp/conf/public_key_file";
	private static final String PRIVATE_KEY_FILE = "/wcsapp/conf/private_key_file";*/
	private static final String PUBLIC_KEY_FILE = "com/sunrise/wcs/commons/utils/public_key_file";
	private static final String PRIVATE_KEY_FILE = "com/sunrise/wcs/commons/utils/private_key_file";

	  /**
     * RSA最大加密明文大小
     */
// private static final int MAX_ENCRYPT_BLOCK = 245;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /**
     * RSA最大解密密文大小
     */
// private static final int MAX_DECRYPT_BLOCK = 256;
    private static final int MAX_DECRYPT_BLOCK = 128;
	
	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data  加密数据
	 * @param privateKey
	 *            私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);

		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);

		return encryptBASE64(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * 
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {

		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);

		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
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
/*	public static byte[] decryptByPrivateKey(byte[] request, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		
		byte encryptedData [] = decryptBASE64(new String(request));

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		
		 int inputLen = encryptedData.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段解密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();
	        return decryptedData;
		
//		return cipher.doFinal(data);
	}*/

	/**
	 * 解密<br>
	 * 用公钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
/*	public static byte[] decryptByPublicKey(byte[] request, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		byte encryptedData [] = decryptBASE64(new String(request));
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
        int inputLen = encryptedData.length;
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段解密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();
	        return decryptedData;
		
//		return cipher.doFinal(data);
	}*/

	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
/*	public static byte[] encryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        
       byte [] rtn = encryptBASE64(encryptedData).getBytes();
        
        return rtn;
		
	}*/

	/**
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
/*	public static byte[] encryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		  int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段加密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_ENCRYPT_BLOCK;
	        }
	        byte[] encryptedData = out.toByteArray();
	        out.close();
	        
	        byte [] rtn = encryptBASE64(encryptedData).getBytes();
	        
	        return rtn;
		
//		return cipher.doFinal(data);
	}*/

	/**
	 * 取得私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 取得公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);

		KeyPair keyPair = keyPairGen.generateKeyPair();

		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	public static void createRSAKeyFile() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		
		ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        } finally{
            oos1.close();
            oos2.close();
        }
	}
	
	public static Map<String, Object> getRSAKey() throws Exception {
		ObjectInputStream ois1 = null;
		ObjectInputStream ois2 = null;
		RSAPublicKey publicKey = null;
		RSAPrivateKey privateKey = null;
		try {
			/** 将文件中的私钥对象读出 */
//			ois1 = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			ois1 = new ObjectInputStream(RSACoder.class.getClassLoader().getResourceAsStream(PUBLIC_KEY_FILE));
			publicKey = (RSAPublicKey) ois1.readObject();
			
//			ois2 = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
			ois2 = new ObjectInputStream(RSACoder.class.getClassLoader().getResourceAsStream(PRIVATE_KEY_FILE));
			privateKey = (RSAPrivateKey) ois2.readObject();
	    } catch (Exception e) {
	    	logger.error("oops, got an exception: ", e);
	        throw e;
	    } finally {
	    	ois1.close();
	    	ois2.close();
	    }
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey(String publicKeyStr,String privateKeyStr) throws Exception {
		
		// 公钥
		RSAPublicKey publicKey = loadPublicKey(publicKeyStr);

		// 私钥
		RSAPrivateKey privateKey = loadPrivateKey(privateKeyStr);

		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PUBLIC_KEY, publicKey);
		
		keyMap.put(PRIVATE_KEY, privateKey);
		
		return keyMap;
	}
	
	/**
	 * 从字符串中加载公钥
	 * @param publicKeyStr 公钥数据字符串
	 * @throws Exception 加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception{
		try {
			
			byte[] buffer= decryptBASE64(publicKeyStr);;
			
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			
			X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
			
			RSAPublicKey publicKey =  (RSAPublicKey) keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (IOException e) {
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}
	
	public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception{
		try {
			
			byte[] buffer= decryptBASE64(privateKeyStr);
			
			PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
			return privateKey;
 		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}
	
	public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
		// 取得公钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        
        byte [] rtn = encryptBASE64(encryptedData).getBytes();
        return rtn;
	}
	
	public static byte[] encryptByPrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
		// 取得公钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		
		int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        
        byte [] rtn = encryptBASE64(encryptedData).getBytes();
        return rtn;
	}
	
	public static byte[] decryptByPublicKey(byte[] request, RSAPublicKey publicKey) throws Exception {
		byte encryptedData [] = decryptBASE64(new String(request));
		// 取得公钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
        int inputLen = encryptedData.length;
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段解密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();
	        return decryptedData;
	}
	
	public static byte[] decryptByPrivateKey(byte[] request, RSAPrivateKey privateKey) throws Exception {
		byte encryptedData [] = decryptBASE64(new String(request));

		// 取得私钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		
		 int inputLen = encryptedData.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段解密
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();
	        return decryptedData;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		Map<String, Object> keyMap = getRSAKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
		
		byte[] encodedData = RSACoder.encryptByPublicKey("<?xml version=\"1.0\" encoding=\"UTF-8\"?><QuickpayRequestBean><orderId>WCS14090320302866815309</orderId><orderAmount>1.00</orderAmount><orderTime>Wed Sep 03 20:30:28 HKT 2014</orderTime><phoneNO>13560408475</phoneNO><cityId>440100</cityId><mallId>WCS</mallId><pageUrl>http://112.96.28.187:7002/component_lib/order/status.do?env=wap</pageUrl><productName>上网卡测试0616</productName><ext1></ext1><ext2></ext2></QuickpayRequestBean>".getBytes("utf-8"), publicKey);
		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData, privateKey);
		String outputStr = new String(decodedData);
		System.out.println("公钥加密 -》私钥解密：" + outputStr);
		
		encodedData = RSACoder.encryptByPrivateKey("测试".getBytes("utf-8"), privateKey);
		decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);
		outputStr = new String(decodedData);
		System.out.println("私钥加密 -》公钥解密：" + outputStr);
	}
}
