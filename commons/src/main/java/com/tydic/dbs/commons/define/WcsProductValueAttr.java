package com.tydic.dbs.commons.define;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

/**
 * 
 * @ClassName: WcsProductValueAttr 
 * @Description: TODO增值业务属性
 * @author Carson liulu@tydic.com 
 * @date 2015-9-18 下午3:49:33 
 *
 */
public class WcsProductValueAttr {
	private static final String DICT_CODE="PRODUCT_VALUE_ATTR";//对应数据库表SYS_DICT的DICT_CODE值
	/**呼叫转移标识 */
    public static final String WCS_ATTR_BRANSFER_CALL = "transfer_call";
	/**业务名称 */
    public static final String WCS_ATTR_BAS_NAME = "bas_name";
	/**业务编码 */
    public static final String WCS_ATTR_BAS_CODE = "bas_code";
    /**生效方式 */
    public static final String WCS_ATTR_EFFECT_WAY = "effect_way";
    /**办理费用 */
    public static final String WCS_ATTR_HANDLE_COST = "handle_cost";
    /**销售价格*/
    public static final String WCS_ATTR_SALE_PRICE = "sale_price";
    /**增值业务名称*/
    public static final String WCS_ATTR_VALUE_NAME = "value_name";
    /**资费说明*/
    public static final String WCS_ATTR_VALUE_TARIFF="value_tariff";
	/** 键值对象 */
    public static Map<String, String> WCS_PRODUCT_VALUE_ATTR_MAP = new LinkedHashMap<String, String>();
   
    static {
    	SysDictConstant.initSysDictByCode(WCS_PRODUCT_VALUE_ATTR_MAP, DICT_CODE);
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
