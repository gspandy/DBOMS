package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsTransferErrorType {
	private static final String DICT_CODE="TRANSFERERROR_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**转账账户错误*/
	public static final String TRANSFERERROR_TYPE1 = "error payer";
	/**支付密码错误*/
	public static final String TRANSFERERROR_TYPE2 = "error password";
	/**收款方不存在*/
	public static final String TRANSFERERROR_TYPE3 = "no credit side";
	/**转账金额错误*/
	public static final String TRANSFERERROR_TYPE4 = "error payment fee";
	/**支付失败*/
	public static final String TRANSFERERROR_TYPE5 = "payment fail";
	/**未知错误*/
	public static final String TRANSFERERROR_TYPE6 = "unknow error";
	/**错误消息集合*/
	public static Map<String, String> WCS_TRANSFERERROR_TYPE_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_TRANSFERERROR_TYPE_MAP, DICT_CODE);
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
