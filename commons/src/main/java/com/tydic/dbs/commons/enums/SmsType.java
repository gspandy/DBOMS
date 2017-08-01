/*
 * com.sunrise.wcs.obh.enums  2015-3-19
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.commons.enums;

/**
 * 
 * @ClassName: SmsType 
 * @Description: TODO(发送短信随机验证码方式) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-12 下午8:34:06 
 *
 */
public enum SmsType {

	LOGIN_VALIDATE(1,"登录验证"),MODIFY_PWD(2,"忘记密码");
	
	private Integer code;
	
	private String desc;
	
	private SmsType(Integer code,String desc){
		this.code=code;
		this.desc=desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
