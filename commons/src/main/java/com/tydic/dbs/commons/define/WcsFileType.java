package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsProcessTask 
 * @Description: TODO(文件类型字典) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-13 上午10:01:17 
 *
 */
public class WcsFileType {

	
	private static final String DICT_CODE="FILE_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	/** 商户文件 */
    public static final String BUSSFILE = "1";
    /**产品需求文件 */
    public static final String PRODUCT_REQUIREMENTS_DOCUMENT = "2";
    /** 键值对象 */
    public static Map<String, String> FILE_TYPE_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(FILE_TYPE_MAP, DICT_CODE);
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
