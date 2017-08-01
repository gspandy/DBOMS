package com.tydic.dbs.commons.define;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by long on 2016/7/25.
 */
public class WcsFtpSize {
    private static final String DICT_CODE = "ROLE_FTP_SIZE";// 对应数据库表SYS_DICT的DICT_CODE值
    public  static final String MEMORY_SIZE_300 ="300";//16G
    public  static final String MEMORY_SIZE_500 ="500";//32G
    public  static final String MEMORY_SIZE_800 ="800";//64G
    public  static final String MEMORY_SIZE_1024 ="1024";//128G
    public  static final String MEMORY_SIZE_2048 ="2048";//256G
    public  static final String MEMORY_SIZE_3072 ="3072";//512G
    /**
     * 键值对像
     */
    public static Map<String, String> WCS_ROLE_FTP_SIZE_MAP = new LinkedHashMap<String, String>();

    static {
        SysDictConstant.initSysDictByCode(WCS_ROLE_FTP_SIZE_MAP, DICT_CODE);
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
