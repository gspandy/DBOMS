package com.tydic.dbs.commons.define;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @ClassName: WcsPrdUnit 
 * @Description: TODO(计量单位) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-8-3 下午4:31:08 
 *
 */
public class WcsPrdUnit {
    private static final String DICT_CODE = "PRD_UNIT";// 对应数据库表SYS_DICT的DICT_CODE值
    public  static final String PRD_UNIT ="1";//按数据结果条数计算
    public  static final String PRD_UNIT_MB ="2";//按数据结果容量计算(MB)

    /**
     * 键值对像
     */
    public static Map<String, String> WCS_PRD_UNIT_MAP = new LinkedHashMap<String, String>();

    static {
        SysDictConstant.initSysDictByCode(WCS_PRD_UNIT_MAP, DICT_CODE);
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
