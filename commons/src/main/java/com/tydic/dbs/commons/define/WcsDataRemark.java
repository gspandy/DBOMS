package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * @file  WcsDataRemark.java
 * @ClassName: WcsDataRemark 
 * @Description: TODO(WcsDataRemark公共状态) 
 * @author zjl zhongjialiang@tydic.com 
 * @date 2015-12-3 上午10:16:53 
 *
 */
public class WcsDataRemark {
	private static final String DICT_CODE="DATAREMARK_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**购买*/
	public static final String EVENT_BENEFIT = "purchase";
	/**流量转入*/
	public static final String TANSFER_IN = "Tansfer_In";
	/**流量转出*/
	public static final String TANSFER_OUT="Tansfer_Out";

	public static Map<String, String> WCS_DATAREMARK_MAP = new HashMap<String, String>();
	
	static {
		
		SysDictConstant.initSysDictByCode(WCS_DATAREMARK_MAP, DICT_CODE);
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
