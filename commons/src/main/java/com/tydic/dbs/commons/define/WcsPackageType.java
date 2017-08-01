package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsPackageType 
 * @Description: TODO(套餐类型) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-9-21 下午5:01:35 
 *
 */
public class WcsPackageType {
	private static final String DICT_CODE="PACKAGE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**单包*/
	public static final String SINGLE_PACKAGE = "1";

	/**多包*/
	public static final String MULTI_PACKAGE = "2";
	
	
	public static Map<String, String> WCS_PACKAGE_TYPE_MAP = new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(WCS_PACKAGE_TYPE_MAP, DICT_CODE);
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
