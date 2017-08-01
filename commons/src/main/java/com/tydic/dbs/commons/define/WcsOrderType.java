package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsOrderType {
	private static final String DICT_CODE="ORDER_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**异常单*/
	public static final String ABNORMAL_SINGLE = "0";
	/**正常单*/
	public static final String NORMAL_SINGLE = "1";
	/**换货单*/
	public static final String SWAP_ORDER = "2";
	/**未支付订单*/
	public static final String UNPAID_SINGLE="3";
	
	public static Map<String, String> WCS_ORDER_TYPE_MAP = new HashMap<String, String>();
	
	static {
		/*WCS_ORDER_TYPE_MAP.put(ABNORMAL_SINGLE, "异常单");
		WCS_ORDER_TYPE_MAP.put(NORMAL_SINGLE, "正常单");
		WCS_ORDER_TYPE_MAP.put(SWAP_ORDER, "换货单");*/
		SysDictConstant.initSysDictByCode(WCS_ORDER_TYPE_MAP, DICT_CODE);
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
