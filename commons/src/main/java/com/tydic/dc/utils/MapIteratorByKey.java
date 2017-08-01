/**
 * com.tydic.dc.utils.MapIteratorByKey.java
 */
package com.tydic.dc.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @file  MapIteratorByKey.java
* @author zhangtian(Fattor)
* @version 0.1
* @todo	TODO
* Copyright(C), 2013-2014
*			Guangzhou Sunrise Electronics Development Co., Ltd.
* History
*   	1. Date: 2013-6-7
*      	Author: zhangtian(Fattor)
*      	Modification: this file was created
*   	2. ...
*   
*   用来在jsp中遍历map类型方便使用
*   解决
*   List<String,Map<String,Object>> 的遍历
*   or
*   List<String>
*   	Map<String,Object>
*   的组合类型遍历
*   
*/
public class MapIteratorByKey extends SimpleTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private Map map;
	private String var;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		Object obj = map.get(key);
		this.getJspContext().setAttribute(var, obj);
		this.getJspBody().invoke(null);
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
