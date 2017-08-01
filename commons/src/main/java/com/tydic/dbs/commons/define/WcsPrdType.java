package com.tydic.dbs.commons.define;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: WcsPrdType 
 * @Description: TODO(计量单位) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-9-22 下午4:31:08 
 *
 */
public class WcsPrdType {
    private static final String DICT_CODE = "PRD_TYPE";// 对应数据库表SYS_DICT的DICT_CODE值
    public  static final String BUSS_DATA ="1";//商户结果数据
    public  static final String LIANTONG_DATA ="2";//联通原始数据
    public  static final String APP_DATA ="3";//应用数据

    /**
     * 键值对像
     */
    public static Map<String, String> WCS_PRD_TYPE_MAP = new LinkedHashMap<String, String>();

    static {
        SysDictConstant.initSysDictByCode(WCS_PRD_TYPE_MAP, DICT_CODE);
    }

    /**
     *获取CPU类型
     * @param lang
     * @return
     */
    public static Map<String, String> getMap(String lang) {
        Map<String, String> result = new HashMap<String, String>();
        SysDictConstant.initSysDictByCode(result, DICT_CODE, lang);
        return result;
    }
}
