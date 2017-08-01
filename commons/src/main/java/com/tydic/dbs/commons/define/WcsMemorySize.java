package com.tydic.dbs.commons.define;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 内存需求
 * Created by long on 2016/7/25.
 */
public class WcsMemorySize {
    private static final String DICT_CODE = "ROLE_MEMORY_SIZE";// 对应数据库表SYS_DICT的DICT_CODE值
    public  static final String MEMORY_SIZE_16 ="16";//16G
    public  static final String MEMORY_SIZE_32 ="32";//32G
    public  static final String MEMORY_SIZE_64 ="64";//64G
    public  static final String MEMORY_SIZE_128 ="128";//128G
    public  static final String MEMORY_SIZE_256 ="256";//256G
    public  static final String MEMORY_SIZE_512 ="512";//512G
    /**
     * 键值对像
     */
    public static Map<String, String> WCS_ROLE_MEMORY_SIZE_MAP = new LinkedHashMap<String, String>();

    static {
        SysDictConstant.initSysDictByCode(WCS_ROLE_MEMORY_SIZE_MAP, DICT_CODE);
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
