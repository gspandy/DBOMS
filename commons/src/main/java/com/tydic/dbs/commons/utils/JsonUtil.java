package com.tydic.dbs.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tydic.dbs.commons.exception.BizException;
public class JsonUtil {
	
	public static JSONObject parseJson(String jsonStr , boolean canNull) throws BizException{
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			return json;
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				//throw new BizException(0,"错误的json格式："+jsonStr);
				throw new BizException(0,"错误的json格式");
			}
		}
	}
	
	public static JSONObject getParseJson(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
			String jsonStr = "";
			jsonStr=getStreamAsString(request.getInputStream(),"UTF-8");
			
			if(jsonStr.length()==0){return null;}
			JSONObject json = JSONObject.fromObject(jsonStr);
			if(json.size()==0){return null;}
			return json;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}
	public static Long getLong(JSONObject json,String key , boolean canNull) throws BizException{
		String v = null;
		try {
			v = json.getString(key);
			/*此处操作主要是因为同步的时候 对方传来的数值可能是 3.00 ~~~~(>_<)~~~~ */
			v=v.split("[.]")[0];
			return Long.parseLong(v);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"不存在必要变量名/变量值错误"+key+":"+v);
			}
			
		}
	}

	public static Date getDate(JSONObject json,String key , boolean canNull) throws BizException{
		try {
			String time = json.getString(key);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			return sdf.parse(time);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
			
		}
	}
	public static Date getDate(JSONObject json,String key , boolean canNull,String formatType) throws BizException{
		try {
			String time = json.getString(key);
			SimpleDateFormat sdf = new SimpleDateFormat(formatType);
			return sdf.parse(time);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
			
		}
	}
	public static Integer getInt(JSONObject json,String key, boolean canNull) throws BizException{
		try {
			return json.getInt(key);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
		}
	}
	
	public static String getString(JSONObject json,String key, boolean canNull) throws BizException{
		try {
			return json.getString(key);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
		}
	}
	public static Boolean getBoolean(JSONObject json,String key, boolean canNull) throws BizException{
		try {
			return Boolean.parseBoolean(json.getString(key));
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
		}
	}
	public static BigDecimal getBigDecimal(JSONObject json,String key, boolean canNull) throws BizException{
		try {
			String decimal = json.getString(key);
			return new BigDecimal(decimal);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
		}
	}
	
	public static JSONArray getJSONArray(JSONObject json,String key, boolean canNull) throws BizException{
		try {
			return json.getJSONArray(key);
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
		}
	}
	
	public static JSONObject getJSONObject(JSONObject json,String key, boolean canNull) throws BizException{
		try {
			JSONObject res = json.getJSONObject(key);
			if(res==null){
				if(canNull){
					return null;
				}else{
					throw new BizException(0,"必要参数"+key+"不存在");
				}
			}
			return res;
		} catch (Exception e) {
			if(canNull){
				return null;
			}else{
				throw new BizException(0,"必要参数"+key+"不存在");
			}
		}
	}
	//获取request请求参数
	private static String getStreamAsString(InputStream stream, String charset) throws Exception {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}
			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
}
