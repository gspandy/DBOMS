/**
 * com.tydic.dc.common.vo.IndexVO.java
 */
package com.tydic.dc.common.vo;

 /**
 * @file  IndexVO.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 指标定义
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-14
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class IndexVO {
	private static final long serialVersionUID = 34000L;
	private String key;
	private String code;
	private String value;
	
	private String unitType;
	private String unit;
	
	//用于报表说明
	private String user;
	private String desc;
	//获取指标类型日 or月 1 day 2 month
	private String indexType ;
	
	public String toString(){
		return "[key:"+key+",code:"+code+",value:"+value+",unitType:"+unitType+",unit:"+unit+",user:"+user+",desc:"+desc+",indexType:"+indexType+"]";
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

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	
	
	
	
}
