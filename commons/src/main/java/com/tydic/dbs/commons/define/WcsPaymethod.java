package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsPaymethod {
private static final String DICT_CODE="PAY_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**信用/借记卡*/
	public static final String PAYTYPE_credit = "A";
	/**支票*/
	public static final String PAYTYPE_creditheck = "B";
	/**现金*/
	public static final String PAYTYPE_creditCash = "C";
	/**直接借记*/
	public static final String PAYTYPE_creditDirectDebit = "D";
	/**其他*/
	public static final String PAYTYPE_creditOthers = "E";
	/**充值卡*/
	public static final String PAYTYPE_IVRVC = "V";
	/**前端商城充值渠道集合*/
	public static Map<String, String> WCS_PAYMENTHOD_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_PAYMENTHOD_MAP, DICT_CODE);
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
