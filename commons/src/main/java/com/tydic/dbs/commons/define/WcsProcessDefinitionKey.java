package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsProcessDefinitionKey {
	
	private static final String DICT_CODE="PROCESS_DEFINITION_KEY";//对应数据库表SYS_DICT的DICT_CODE值
	/**订单流程*/
	public static final String ORDER_PROCESS = "orderProcess";
	/**退单流程*/
	public static final String REFUND_PROCESS = "refundProcess";
	/**测试流程*/
	public static final String TEST_PROCESS = "testProcess";
	
	public static Map<String, String> PROCESS_DEFINITION_KEY_MAP = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(PROCESS_DEFINITION_KEY_MAP, DICT_CODE);
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
