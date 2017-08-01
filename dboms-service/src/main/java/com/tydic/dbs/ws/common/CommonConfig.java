/*
 * com.sunrise.wcs.obh.common  2015-1-28
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.ws.common;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/** 
 * @ClassName: CommonConfig 
 * @Description: common
 *  
 */
@Component
public class CommonConfig implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 8270367415397285438L;

	private String systemId;


	private String httpKey;
	
	private String resultFlag;

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getHttpKey() {
		return httpKey;
	}

	public void setHttpKey(String httpKey) {
		this.httpKey = httpKey;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}
