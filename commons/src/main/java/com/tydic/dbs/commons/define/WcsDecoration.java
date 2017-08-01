package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsDecoration {

	private static final String DICT_CODE="DECORATION";//对应数据库表SYS_DICT的DICT_CODE值
	/**毛坯*/
    public static final String WCS_BLANK = "1";
    /**简装*/
    public static final String WCS_PAPERBACK= "2";
    /**精装*/
    public static final String WCS_HARDCOVER = "3";
    /**键值对象 */
    public static Map<String, String> WCS_DECORATION_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_DECORATION_MAP, DICT_CODE);
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
