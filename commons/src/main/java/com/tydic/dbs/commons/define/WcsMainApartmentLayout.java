package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsMainApartmentLayout {


	private static final String DICT_CODE="MAIN_APARTMENT_LAYOUT";//对应数据库表SYS_DICT的DICT_CODE值
	/**三居(95㎡) 、 */
    public static final String WCS_SANJU = "1";
    /**两居(75㎡)*/
    public static final String WCS_LIANGJU= "2";
    /**四居（147平）*/
    public static final String WCS_SHIJU= "3";
    /**键值对象 */
    public static Map<String, String> WCS_MAIN_APARTMENT_LAYOUT_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_MAIN_APARTMENT_LAYOUT_MAP, DICT_CODE);
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
