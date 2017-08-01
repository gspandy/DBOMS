package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsEffType {
	private static final String DICT_CODE="EFF_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**次日生效*/
	public static final String NEXT_DAY_EFFECT = "0";
	
	/**下月生效*/
	public static final String NEXT_MONTH_EFFECT = "1";

	/**立即生效*/
	public static final String IMMEDIATE_EFFECT = "2";
	
	/**指定生效日期*/
	public static final String ASSIGN_DAY = "3";
	
	/**默认方式生效*/
	public static final String DEFAULT_EFFECT = "4";
	public static Map<String, String> WCS_EFFECTIVE_TYPE_MAP = new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_EFFECTIVE_TYPE_MAP, DICT_CODE);
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
