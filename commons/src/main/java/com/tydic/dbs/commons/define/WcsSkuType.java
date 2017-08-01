package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsSkuType 
 * @Description: TODO(单点sku值) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-9-27 下午11:01:33 
 *
 */
public class WcsSkuType {
	private static final String DICT_CODE="SKU_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**短信sms_packet*/
	public static final String SKU_SMS_PACKET = "sms_packet";

	/**语音voice_packet*/
	public static final String SKU_VOICE_PACKET = "voice_packet";
	
	/***语言**/
	public static final String SKU_LANGUAGE="en_US";
	
	
	public static Map<String, String> WCS_SKU_TYPE_MAP = new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_SKU_TYPE_MAP, DICT_CODE);
	}
	
	/**
	 * 
	 * @Title: getMap 
	 * @Description: 获取指定语种
	 * @param @param lang
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public static Map<String, String> getMap(String lang) {
		Map<String, String> result = new HashMap<String, String>();
		SysDictConstant.initSysDictByCode(result, DICT_CODE, lang);
		return result;
	}
}
