/**
 * 
 */
package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsBussDataType 
 * @Description: TODO(商户数据类型) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-9-23 下午3:21:39 
 *
 */
public class WcsBussDataType {
	private static final String DICT_CODE="BUSS_DATA_TYPE";//对应数据库表SYS_DICT的DICT_CODE值
	

	/**联通内部用户*/
	public static final String LT_Inuser = "1";
	/**联通外部用户*/
	public static final String LT_Outuser = "2";
	
    public static Map<String, String> WCS_BUSS_DATA_TYPE = new LinkedHashMap<String, String>();
    static {
    	SysDictConstant.initSysDictByCode(WCS_BUSS_DATA_TYPE, DICT_CODE);
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
