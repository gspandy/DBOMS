/**
 * com.tydic.dc.utils.TimeStampFormat.java
 */
package com.tydic.dc.utils;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


import org.apache.commons.lang.time.DateFormatUtils;

 /**
 * @file  TimeStampFormat.java
 * @author zhangtian(Fattor)
 * @version 0.1
 * @todo	TODO
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-8-26
 *      	Author: zhangtian(Fattor)
 *      	Modification: this file was created
 *   	2. Date: 2013-09-17
 *      	Author: weishaojia(viscar)
 *          Modification: 类型不能正常转换问题处理
 *          
 */
public class TimeStampFormat extends SimpleTagSupport {
	private static final long serialVersionUID = 1L;
	
	private Object value;
	private String pattern;
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		String rtnVal="";
		//System.out.println("pattern->"+pattern+" -x->"+value);
		try{

			//改类类名比较方式(viscar)
			if(value.getClass().getName().equals("oracle.sql.Timestamp")){
				rtnVal=DateFormatUtils.format((Timestamp)value, pattern);
			}
			//修改，用反射取得值转换
			if(value.getClass().getName().equals("oracle.sql.TIMESTAMP")){
				Class clz = value.getClass();
				Timestamp t=(Timestamp)clz.getMethod("timestampValue").invoke(value);
				rtnVal=DateFormatUtils.format(t, pattern);
			}			
		}catch (Exception e) {
			//e.printStackTrace();
		}
		getJspContext().getOut().write(rtnVal);
	}
	

	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	
}
