package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/** 
 * @ClassName: WcsTradeType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhanjm 
 * @date 2015-6-4 上午11:09:31 
 *  
 */

public class WcsTradeType {
	
	private static final String DICT_CODE="TRADE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 第三方支付  */
	public static final String WCS_TRADE_THIRDPARTY = "THIRDPARTY";
	/** 余额支付  */
	public static final String WCS_TRADE_BALANCE = "BALANCE";
	/** 海币支付  */
	public static final String WCS_TRADE_SEACOIN = "SEACOIN";
    /** 键值对象 */
	public static Map<String, String> WCS_CLASS_TYPE_MAP = new LinkedHashMap<String, String>();
	
	static {
    	SysDictConstant.initSysDictByCode(WCS_CLASS_TYPE_MAP, DICT_CODE);
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
