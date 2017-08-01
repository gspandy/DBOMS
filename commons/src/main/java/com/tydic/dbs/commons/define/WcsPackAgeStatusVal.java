package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsPackAgeStatusVal {
	private static final String DICT_CODE="PACKAGE_STATUS_VAL";//对应数据库表SYS_DICT的DICT_CODE值
	
	public static final String PACKAGE_NAME="package_name";
	
	public static final String PRICE="price";
	
	public static final String VALUE_NAME="value_name";
	
	public static final String SALE_PRICE="sale_price";
	
	public static final String CHANNELGOODSID="2717789l";
	
	public static final String STATUS="1";
	
	public static final String LANGUAGE="en_US";
	
	public static Map<String, String> WCS_PACKAGE_STATUS_VAL_MAP = new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_PACKAGE_STATUS_VAL_MAP, DICT_CODE);
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
