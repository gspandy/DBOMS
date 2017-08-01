/**
 * com.tydic.dc.utils.JSONParser.java
 */
package com.tydic.dc.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * @file  JSONTimeStampProcessor.java
 * @author zhangtian(viscar)
 * @version 0.1
 * @todo	TODO
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-22
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class JSONTimeStampProcessor implements JsonValueProcessor {

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
		// TODO Auto-generated method stub

		return obj;
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig) {
		// TODO Auto-generated method stub
		if (obj instanceof Timestamp) {
			try {
				Timestamp timeStamp = (Timestamp) obj;
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String rtnType = sdf.format(timeStamp);
				return rtnType;
			} catch (Exception e) {
			}
		}

		return obj;
	}
}
