package com.tydic.dbs.commons.utils;

import java.util.Map;

import com.tydic.dbs.commons.exception.BizException;

public class MapUtil {

	public static Long getLong(Map<String,String> map,String key,boolean canNull) throws BizException{
		String val = null;
		try {
			val=map.get(key);
			val = val.split("[.]")[0];
			return Long.parseLong(val);
		} catch (Exception e) {
			if(canNull){
				return 0l;
			}else{
				throw new BizException(0,"不存在的变量名/错误的变量值:key="+key);
			}
		}
	}
	
	public static String getString(Map<String,String> map,String key,boolean canNull) throws BizException{
		String val = null;
		val=map.get(key);
		if(val==null&&!canNull){
			throw new BizException(0,"不存在必要值key="+key);
		}
		return val;
		
	}
}
