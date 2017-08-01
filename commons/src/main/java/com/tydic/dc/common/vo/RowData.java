/**
 * com.tydic.dc.common.vo.RowData.java
 */
package com.tydic.dc.common.vo;

import java.util.Map;

 /**
 * @file  RowData.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 报表数据行值对象
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-19
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class RowData {

	private Map<String,Object> row;
	
	public String toString(){
		return row.toString();
	}

	/**
	 * @return the row
	 */
	public Map<String, Object> getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(Map<String, Object> row) {
		this.row = row;
	}
	
	
}
