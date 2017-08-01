/**
 * com.tydic.dc.common.vo.UserDimVO.java
 */
package com.tydic.dc.common.vo;

import java.io.Serializable;

 /**
 * @file  UserDimVO.java
 * @author weishaojia(viscar)
 * @version 0.1
 * @todo 用户维度数据
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-14
 *      	Author: weishaojia(viscar)
 *      	Modification: this file was created
 *   	2. ...
 */
public class UserDimVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 32000L;
	private String userId;
	private String dimId;
	
	public String toString(){
		return "[userId:"+userId+",dimId:"+dimId+"]";
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the dimId
	 */
	public String getDimId() {
		return dimId;
	}
	/**
	 * @param dimId the dimId to set
	 */
	public void setDimId(String dimId) {
		this.dimId = dimId;
	}
	
}
