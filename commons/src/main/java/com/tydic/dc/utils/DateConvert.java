/**
 * com.tydic.dc.utils.DateConvert.java
 */
package com.tydic.dc.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.time.DateUtils;

/**
* @file  DateConvert.java
* @author zhangtian(Fattor)
* @version 0.1
* @todo	TODO
* Copyright(C), 2013-2014
*			Guangzhou Sunrise Electronics Development Co., Ltd.
* History
*   	1. Date: 2013-6-4
*      	Author: zhangtian(Fattor)
*      	Modification: this file was created
*   	2. ...
*/
public class DateConvert implements Converter {

	/* (non-Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	public Object convert(Class arg0, Object value) {
		// TODO Auto-generated method stub

		if (value == null || "".equals(value)) {
			return null;
		}

		DateUtils du = new DateUtils();
		try {
			Date dt = du.parseDate((String) value, new String[] {
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm","yyyy-MM-dd" });
			return dt;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
