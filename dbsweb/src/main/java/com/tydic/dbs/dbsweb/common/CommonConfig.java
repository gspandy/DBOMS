/*
 * com.sunrise.wcs.obh.common  2015-1-28
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.dbsweb.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/** 
 * @ClassName: CommonConfig 
 * @Description: TODO 注入累
 * @author Carson haoyuyang@tydic.com 
 * @date 2015-1-28 上午11:12:51 
 *  
 */
@Component
public class CommonConfig implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 8270367415397285438L;

	private String httpPath;
	@Value("${http.encode}")
	public static String httpEncode;
	@Value("${http.key}")
	public static String httpKey;

	private String imageRoot;


	private String httpServicePath;
	
	private String bussResourceUrl;
	
	private String bussTenantUrl;
	
	private String orderApplyUrl;


	public String getHttpEncode() {
		return httpEncode;
	}

	public String getHttpPath() {
		return httpPath;
	}

	public String getHttpKey() {
		return httpKey;
	}


	
	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}

	public  String getImageRoot() {
		return imageRoot;
	}

	public  void setImageRoot(String imageRoot) {
		this.imageRoot = imageRoot;
	}

	public void setHttpEncode(String httpEncode) {
		this.httpEncode = httpEncode;
	}

	public void setHttpKey(String httpKey) {
		this.httpKey = httpKey;
	}

	public String getHttpServicePath() {
		return httpServicePath;
	}

	public void setHttpServicePath(String httpServicePath) {
		this.httpServicePath = httpServicePath;
	}

	public String getBussResourceUrl() {
		return bussResourceUrl;
	}

	public void setBussResourceUrl(String bussResourceUrl) {
		this.bussResourceUrl = bussResourceUrl;
	}

	public String getBussTenantUrl() {
		return bussTenantUrl;
	}

	public void setBussTenantUrl(String bussTenantUrl) {
		this.bussTenantUrl = bussTenantUrl;
	}

	public String getOrderApplyUrl() {
		return orderApplyUrl;
	}

	public void setOrderApplyUrl(String orderApplyUrl) {
		this.orderApplyUrl = orderApplyUrl;
	}
	
	
}
