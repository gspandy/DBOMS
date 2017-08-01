/**
 * com.tydic.dc.common.vo.DimVO.java
 */
package com.tydic.dc.common.vo;

import java.io.Serializable;

 /**
 * @file  DimVO.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 维度值对象
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-14
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class DimVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 31000L;
	private String key;
	private String code;
	private String value;
	
	public String toString(){
		return "[key:"+key+",code:"+code+",value:"+value+"]";
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}	
	
}
