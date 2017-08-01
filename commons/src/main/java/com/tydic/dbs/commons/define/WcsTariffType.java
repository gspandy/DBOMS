package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsTariffType 
 * @Description: TODO(资费类型) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-9-21 下午4:50:47 
 *
 */
public class WcsTariffType {
	private static final String DICT_CODE="TARIFF_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**语音*/
	public static final String VOICE = "1";
	/**短信*/
	public static final String MESSAGE = "2";
	/**功能*/
	public static final String FUNCTION = "3";
	/**流量*/
	public static final String FLOW = "4";
	/**其他*/
	public static final String OTHER = "0";
	
	
	public static Map<String, String> WCS_TARIFF_TYPE_MAP = new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_TARIFF_TYPE_MAP, DICT_CODE);
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
