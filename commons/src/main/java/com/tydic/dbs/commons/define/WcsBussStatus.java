package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsProcessTask 
 * @Description: TODO(商户状态字典) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-13 上午10:01:17 
 *
 */
public class WcsBussStatus {

	
	private static final String DICT_CODE="BUSS_STATUS";//对应数据库表SYS_DICT的DICT_CODE值
	/** 待审核 */
    public static final String PENDING = "01";
    /** 审核通过 */
    public static final String EXAMINATION_PASSED = "02";
    /** 审核不通过*/
    public static final String EXAMINATION_NO_PASSED = "03";
    /** 失效 */
    public static final String  Fail = "04";
    /** 键值对象 */
    public static Map<String, String> BUSS_STATUS_MAP = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(BUSS_STATUS_MAP, DICT_CODE);
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
