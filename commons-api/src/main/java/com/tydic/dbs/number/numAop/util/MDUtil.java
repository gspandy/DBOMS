package com.tydic.dbs.number.numAop.util;

import java.security.MessageDigest;

public class MDUtil {
	public  static String MD5(String pwd){
		char md5String[]={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try{
			byte[] btInput=pwd.getBytes();
			MessageDigest mdInst=MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md=mdInst.digest();
			int j=md.length;
			char str[]=new char[j*2];
			int k=0;
			for(int i=0;i<j;i++){
				byte byte0=md[i];
				str[k++]=md5String[byte0 >>> 4 & 0xf];
				str[k++]=md5String[byte0 & 0xf];
			}
			return new String(str);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args[]){
		System.out.println(MDUtil.MD5("201307011535128618620210864123456789"));
	}

}
