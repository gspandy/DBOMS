package com.tydic.dbs.commons.define;

import com.tydic.dbs.commons.sysDict.SysDictConstant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * CPU类型
 * Created by long on 2016/7/25.
 */
public class WcsCpuType {
    private static final String DICT_CODE = "ROLE_CPU_TYPE";// 对应数据库表SYS_DICT的DICT_CODE值
        public  static final String CPU_TYPE_2 ="2";//2核
        public  static final String CPU_TYPE_4 ="4";//4核
        public  static final String CPU_TYPE_8 ="8";//8核
        public  static final String CPU_TYPE_16 ="16";//16核
        public  static final String CPU_TYPE_32 ="32";//32核
        public  static final String CPU_TYPE_64 ="64";//64核

        /**
         * 键值对像
         */
        public static Map<String, String> WCS_ROLE_CPU_TYPE_MAP = new LinkedHashMap<String, String>();

        static {
            SysDictConstant.initSysDictByCode(WCS_ROLE_CPU_TYPE_MAP, DICT_CODE);
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
