package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.tydic.dbs.commons.sysDict.SysDictConstant;

public class WcsOfficeType {
	private static final String DICT_CODE="OFFICE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	
	/** 自营 */
    public static final String SELF_RUN = "self-run";
    /** 合作*/
    public static final String COOPERATIVE = "cooperative";
    /** 键值对象 */
    public static Map<String, String> WCS_OFFICE_TYPE_MAP = new LinkedHashMap<String, String>();
    
    static {
    	SysDictConstant.initSysDictByCode(WCS_OFFICE_TYPE_MAP, DICT_CODE);
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
