package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsIdentification 
 * @Description: TODO(商城充值渠道) 
 * @author  chenshaolei@tydic.com 
 * @date 2015-9-16 下午12:49:26 
 *
 */
public class WcsIdentification {
	private static final String DICT_CODE="IDENTIFICATION_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**网厅*/
	public static final String IDENTIFICATION_PC = "pc";
	/**手厅*/
	public static final String IDENTIFICATION_APP = "app";
	/**现金充值*/
	public static final String IDENTIFICATION_CRM = "crm";
	/**前端商城充值渠道集合*/
	public static Map<String, String> WCS_IDENTIFICATION_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_IDENTIFICATION_MAP, DICT_CODE);
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
