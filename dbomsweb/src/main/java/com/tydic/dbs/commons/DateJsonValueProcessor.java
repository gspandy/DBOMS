package com.tydic.dbs.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * <p>Title:处理java.util.Date类型的json转换类 </p>
 * <p>Description: 为了解决对象转换成json格式时，其中Date类型的属性值不能转化成日常的格式的问题</p>
 * <p>Company: 从兴技术有限公司</p>
 * @author 汤浩洋
 * @version 1.0
 * @date 2012-9-6上午10:38:07
 */
public class DateJsonValueProcessor implements JsonValueProcessor {   
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";    
    private DateFormat dateFormat;

    /** 默认构造函数 */
    public DateJsonValueProcessor() {
        dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);   
    }
    
    public DateJsonValueProcessor(String datePattern) {    
        try {    
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);    
        }    
    }    
    
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {    
        return process(value);    
    }    
    
    public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {    
        return process(value);    
    }    
    
    private Object process(Object value) {    
        if(value!=null){
            if (value instanceof java.util.Date) {
                return dateFormat.format((Date) value); 
            }
        }
        return null;
    } 
}