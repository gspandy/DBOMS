package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsProductPackageType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-9-22 下午2:10:28 
 *
 */
public class WcsProductPackageType {
	private static final String DICT_CODE="PACKAGE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/**语音*/
	public static final String VOICE = "1";
	/**短信*/
	public static final String MESSAGE = "2";
	/**功能*/
	public static final String FUNCTION = "3";
	
	public static Map<String, String> Wcs_Product_Package_Type_Map=new HashMap<String, String>();
	static {
		SysDictConstant.initSysDictByCode(Wcs_Product_Package_Type_Map, DICT_CODE);
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
