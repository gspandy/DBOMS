/**
 * com.tydic.dbs.mapper.OrdResultFileHis.java
 */
package com.tydic.dbs.order.vo;


import com.tydic.commons.utils.BaseVO;

/**
 * @file  OrdResultFileHis.java
 * @author zhongjialian
 * @version 0.1
 * @OrdResultFileHis实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-08-09 05:40:01
 *      	Author: zhongjialian
 *      	Modification: this file was created
 *   	2. ...
 */
public class OrdResultFileHis extends BaseVO implements java.io.Serializable{

	private Long fileId;
	private String ordId;
	private String serialNum;
	private String fileName;
	private String fileUrl;

	private java.lang.String ftpIp;
	private java.lang.Integer ftpPort;
	private java.lang.String ftpUser;
	private java.lang.String ftpPass;
	private java.lang.String ftpPath;
	private String dealFlag;
	private String dealRemark;

	public OrdResultFileHis(){
	}

	public OrdResultFileHis(
		Long fileId
	){
		this.fileId = fileId;
	}



	public void setFileId(Long value) {
		this.fileId = value;
	}	
	public Long getFileId() {
		return this.fileId;
	}

	public void setSerialNum(String value) {
		this.serialNum = value;
	}	
	public String getSerialNum() {
		return this.serialNum;
	}

	public void setFileName(String value) {
		this.fileName = value;
	}	
	public String getFileName() {
		return this.fileName;
	}

	public void setFileUrl(String value) {
		this.fileUrl = value;
	}	
	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getOrdId() {
		return ordId;
	}

	public String getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}

	public String getDealRemark() {
		return dealRemark;
	}

	public void setDealRemark(String dealRemark) {
		this.dealRemark = dealRemark;
	}


	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPass() {
		return ftpPass;
	}

	public void setFtpPass(String ftpPass) {
		this.ftpPass = ftpPass;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("FileId").append("='").append(getFileId()).append("' ");	
			   buffer.append("SerialNum").append("='").append(getSerialNum()).append("' ");	
			   buffer.append("FileName").append("='").append(getFileName()).append("' ");	
			   buffer.append("FileUrl").append("='").append(getFileUrl()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrdResultFileHis) ) return false;
		 OrdResultFileHis  castOther = ( OrdResultFileHis) other;  
		 return 
					( (this.getFileId()==castOther.getFileId()) ||( this.getFileId()!=null && castOther.getFileId()!=null && this.getFileId().equals(castOther.getFileId()) ) )
					&&( (this.getSerialNum()==castOther.getSerialNum()) ||( this.getSerialNum()!=null && castOther.getSerialNum()!=null && this.getSerialNum().equals(castOther.getSerialNum()) ) )
					&&( (this.getFileName()==castOther.getFileName()) ||( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
					&&( (this.getFileUrl()==castOther.getFileUrl()) ||( this.getFileUrl()!=null && castOther.getFileUrl()!=null && this.getFileUrl().equals(castOther.getFileUrl()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getFileId() == null ? 0 : this.getFileId().hashCode() );
				  result = 37 * result + (getSerialNum() == null ? 0 : this.getSerialNum().hashCode() );
				  result = 37 * result + (getFileName() == null ? 0 : this.getFileName().hashCode() );
				  result = 37 * result + (getFileUrl() == null ? 0 : this.getFileUrl().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
	} 
	
}
