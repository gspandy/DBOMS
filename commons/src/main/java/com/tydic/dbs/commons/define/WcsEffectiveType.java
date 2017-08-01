package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsEffectiveType 
 * @Description: TODO(生效方式) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-9-21 下午4:55:29 
 *
 */
public class WcsEffectiveType {
	private static final String DICT_CODE="EFFECTIVE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**下月生效*/
	public static final String NEXT_MONTH_EFFECT = "1";

	/**立即生效*/
	public static final String IMMEDIATE_EFFECT = "0";
	
	
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
