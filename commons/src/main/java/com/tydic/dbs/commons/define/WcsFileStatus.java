package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsProcessTask 
 * @Description: TODO(文件状态字典) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-13 上午10:01:17 
 *
 */
public class WcsFileStatus {

	
	private static final String DICT_CODE="FILE_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 有效*/
    public static final String EFFECTIVE = "1";
    /** 无效 */
    public static final String INVALID = "2";
    /** 键值对象 */
    public static Map<String, String> FILE_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(FILE_STATUS_MAP, DICT_CODE);
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
