package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;
import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsTariffPlan 
 * @Description: TODO(资费方案) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-9-21 下午4:59:02 
 *
 */
public class WcsTariffPlan {
	private static final String DICT_CODE="TARIFF_PLAN";//对应数据库表SYS_DICT的DICT_CODE值
	/**自动续订*/
	public static final String NEXT_MONTH_EFFECT = "01";

	/**自动扣费*/
	public static final String IMMEDIATE_EFFECT = "02";
	
	
	public static Map<String, String> WCS_TARIFF_PLAN_MAP = new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_TARIFF_PLAN_MAP, DICT_CODE);
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
