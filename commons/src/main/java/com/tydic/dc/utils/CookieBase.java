/**
 * com.tydic.dc.utils.CookieBase.java
 */
package com.tydic.dc.utils;

 /**
 * @file  CookieBase.java
 * @author chenjiefeng
 * @version 0.1
 * @todo	TODO
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-31
 *      	Author: chenjiefeng
 *      	Modification: this file was created
 *   	2. ...
 */

import java.text.SimpleDateFormat;
import java.util.Date;


import com.tydic.commons.utils.Endecrypt;

public class CookieBase {
	
	private Endecrypt endecrypt = new Endecrypt() ;
	
	public static final String ENCODE_PEY = "gducdc1";

	public static final String SEPARATION = "-->";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final long COOKIE_TIME = 1800000;
	public static final String COOKIE_NAME = "gqdwuecrdtc";
	public static final String COOKIE_PATH = "/";


	private Date sessionTime;
	private String userName;
	private String userPass;
	private String loginState;

	public CookieBase() {
	}

	public CookieBase(String userName, String pwd, Date sessionTime) {
		this.userName = userName;
		this.userPass = pwd;
		this.sessionTime = sessionTime;
	}


	//将帐号和Md5加密后的密码 再用3des加密
	public String toCookieEncryptString() {
		return endecrypt.get3DESEncrypt(userName,ENCODE_PEY) + SEPARATION
				+ endecrypt.get3DESEncrypt(userPass,ENCODE_PEY) + SEPARATION
				+ formateSessionTime() + SEPARATION 
				+ loginState;
	}

	private String formateSessionTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		String timeStr = df.format(sessionTime);
		return timeStr;
	}

	public Date getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Date sessionTime) {
		this.sessionTime = sessionTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

}