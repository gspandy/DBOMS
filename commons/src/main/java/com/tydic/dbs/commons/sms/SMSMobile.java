package com.tydic.dbs.commons.sms;

import java.util.Date;

/**
 * @file  SMSMobile.java
 * @author caipeimin
 * @version 0.1
 * @短信实体
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SMSMobile  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6001679112322995668L;
	
    public static final String SMS_MOBILE_CONTENT ="本月流量不够----";//发送短信内容

	private java.lang.Long sendTaskId;
	private java.lang.String smsTemplateCode;
	private java.lang.String smsContent;
	private java.util.Date sendTime;
	private java.lang.String mobileNumber;
	private java.lang.Integer priorityLevel;
	private java.lang.String sourceSystem;
	private java.lang.Integer repeatTimes;
	private java.lang.Integer haveRepeatTimes;
	private java.lang.Integer repeatSpace;
	private java.util.Date riseTime;
	private java.util.Date finishTime;
	private java.lang.String sendResult;
	private java.lang.String creater;
	private java.lang.String modifier;
	private java.util.Date createTime;
	private java.util.Date modifyTime;
	
	private java.lang.String templateContent;
	private java.lang.String templateStatus;
	
	public void setSendTaskId(java.lang.Long value) {
		this.sendTaskId = value;
	}	
	public java.lang.Long getSendTaskId() {
		return this.sendTaskId;
	}

	public void setSmsTemplateCode(java.lang.String value) {
		this.smsTemplateCode = value;
	}	
	public java.lang.String getSmsTemplateCode() {
		return this.smsTemplateCode;
	}

	public void setSmsContent(java.lang.String value) {
		this.smsContent = value;
	}	
	public java.lang.String getSmsContent() {
		return this.smsContent;
	}

	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}	
	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	public void setMobileNumber(java.lang.String value) {
		this.mobileNumber = value;
	}	
	public java.lang.String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setPriorityLevel(java.lang.Integer value) {
		this.priorityLevel = value;
	}	
	public java.lang.Integer getPriorityLevel() {
		return this.priorityLevel;
	}

	public void setSourceSystem(java.lang.String value) {
		this.sourceSystem = value;
	}	
	public java.lang.String getSourceSystem() {
		return this.sourceSystem;
	}

	public void setRepeatTimes(java.lang.Integer value) {
		this.repeatTimes = value;
	}	
	public java.lang.Integer getRepeatTimes() {
		return this.repeatTimes;
	}

	public void setHaveRepeatTimes(java.lang.Integer value) {
		this.haveRepeatTimes = value;
	}	
	public java.lang.Integer getHaveRepeatTimes() {
		return this.haveRepeatTimes;
	}

	public void setRepeatSpace(java.lang.Integer value) {
		this.repeatSpace = value;
	}	
	public java.lang.Integer getRepeatSpace() {
		return this.repeatSpace;
	}

	public void setRiseTime(java.util.Date value) {
		this.riseTime = value;
	}	
	public java.util.Date getRiseTime() {
		return this.riseTime;
	}

	public void setFinishTime(java.util.Date value) {
		this.finishTime = value;
	}	
	public java.util.Date getFinishTime() {
		return this.finishTime;
	}
	public java.lang.String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(java.lang.String templateContent) {
		this.templateContent = templateContent;
	}
	public java.lang.String getTemplateStatus() {
		return templateStatus;
	}
	public void setTemplateStatus(java.lang.String templateStatus) {
		this.templateStatus = templateStatus;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public java.lang.String getSendResult() {
		return sendResult;
	}
	public void setSendResult(java.lang.String sendResult) {
		this.sendResult = sendResult;
	}
}