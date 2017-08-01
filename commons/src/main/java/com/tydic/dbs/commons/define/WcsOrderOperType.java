package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsOrderOperType {
	private static final String DICT_CODE="ORDER_OP_TPYE";//对应数据库表SYS_DICT的DICT_CODE值
	/**订单处理*/
	public static final String HANDLE_ORDER = "01";
	/**订单发货*/
	public static final String ORDER_SEND_GOODS = "02";
	/**订单归档*/
	public static final String PLACE_FILE_ORDER = "03";
	/**退单申请*/
	public static final String FROM_SINGLE_APPLICATION = "04";
	/**退款处理*/
	public static final String REFUND = "05";
	/**异常处理*/
	public static final String EXCEPTION_HANDLE = "06";
	/**修改配送地址*/
	public static final String UPDATE_DELIVERY_ADDRESS = "07";
	/**退单处理*/
	public static final String REFUND_PROC = "08";
	
	public static Map<String, String> ORDER_OPER_TYPE = new HashMap<String, String>();
	
	static {
		SysDictConstant.initSysDictByCode(ORDER_OPER_TYPE, DICT_CODE);
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
