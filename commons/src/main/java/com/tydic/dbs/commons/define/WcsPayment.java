package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsIdentification 
 * @Description: TODO(商城项目) 
 * @author  chenshaolei@tydic.com 
 * @date 2015-9-16 下午12:49:26 
 *
 */
public class WcsPayment {
	private static final String DICT_CODE="PAYMENT";//对应数据库表SYS_DICT的DICT_CODE值
	
	/**购买*/
	public static final String PAYMENT_BUY = "1";
	/**充值*/
	public static final String PAYMENT_CHARGE = "2";
	/**代购付款/转账*/
	public static final String PAYMENT_TRANSFER="3";
 	/**海币购买*/
	public static final String PAYMENT_BUY_SEA_COIN="4";
	/**海币转赠*/
	public static final String PAYMENT_SEA_COIN_DONATION="5";
	/**前端商城项目集合*/
	public static Map<String, String> WCS_PAYMENT_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_PAYMENT_MAP, DICT_CODE);
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
