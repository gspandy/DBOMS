package com.tydic.dbs.commons.define;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 存储需求大小
 * Created by long on 2016/7/25.
 */
public class WcsDiskSize {
    private static final String DICT_CODE = "ROLE_DISK_SIZE";// 对应数据库表SYS_DICT的DICT_CODE值
    public  static final String DISK_SIZE_1 ="1";//1T
    public  static final String DISK_SIZE_2 ="2";//2T
    public  static final String DISK_SIZE_3 ="3";//3T
    public  static final String DISK_SIZE_4 ="4";//4T
    public  static final String DISK_SIZE_5 ="5";//5T
    public  static final String DISK_SIZE_6 ="6";//6T

    /**
     * 键值对像
     */
    public static Map<String, String> WCS_ROLE_DISK_SIZE_MAP = new LinkedHashMap<String, String>();

    static {
        SysDictConstant.initSysDictByCode(WCS_ROLE_DISK_SIZE_MAP, DICT_CODE);
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
