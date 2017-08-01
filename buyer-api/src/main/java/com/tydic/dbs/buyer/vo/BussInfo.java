/**
 * com.sunrise.qqq.mapper.BussInfo.java
 */
package com.tydic.dbs.buyer.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  BussInfo.java
 * @author zjl
 * @version 0.1
 * @BussInfo实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-14 07:04:48
 *      	Author: zjl
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussInfo extends BaseVO implements java.io.Serializable{

	private java.lang.String bussId;
	private java.lang.String bussAccount;
	private java.lang.String bussPasswd;
	private java.lang.String bussType;
	private java.lang.String bussName;
	private java.lang.String bussSex;
	private java.lang.String bussCredNo;
	private java.lang.String bussMobileNo;
	private java.lang.String bussTeleNo;
	private java.lang.String orgName;
	private java.lang.String orgAddress;
	private java.lang.String bussinessNum;
	private java.lang.String bussEmail;
	private java.lang.String bussAddress;
	private java.lang.String fileId;
	private java.lang.String bussStatus;
	private java.lang.String configureStatus;
	private java.lang.String suggestion;
	private java.lang.String remark;
	private java.lang.String bussDataType;
	public BussInfo(){
	}

	public BussInfo(
		java.lang.String bussId
	){
		this.bussId = bussId;
	}



	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setBussAccount(java.lang.String value) {
		this.bussAccount = value;
	}	
	public java.lang.String getBussAccount() {
		return this.bussAccount;
	}

	public void setBussPasswd(java.lang.String value) {
		this.bussPasswd = value;
	}	
	public java.lang.String getBussPasswd() {
		return this.bussPasswd;
	}

	public void setBussType(java.lang.String value) {
		this.bussType = value;
	}	
	public java.lang.String getBussType() {
		return this.bussType;
	}

	public void setBussName(java.lang.String value) {
		this.bussName = value;
	}	
	public java.lang.String getBussName() {
		return this.bussName;
	}

	public void setBussSex(java.lang.String value) {
		this.bussSex = value;
	}	
	public java.lang.String getBussSex() {
		return this.bussSex;
	}

	public void setBussCredNo(java.lang.String value) {
		this.bussCredNo = value;
	}	
	public java.lang.String getBussCredNo() {
		return this.bussCredNo;
	}

	public void setBussMobileNo(java.lang.String value) {
		this.bussMobileNo = value;
	}	
	public java.lang.String getBussMobileNo() {
		return this.bussMobileNo;
	}

	public void setBussTeleNo(java.lang.String value) {
		this.bussTeleNo = value;
	}	
	public java.lang.String getBussTeleNo() {
		return this.bussTeleNo;
	}

	public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}	
	public java.lang.String getOrgName() {
		return this.orgName;
	}

	public void setOrgAddress(java.lang.String value) {
		this.orgAddress = value;
	}	
	public java.lang.String getOrgAddress() {
		return this.orgAddress;
	}

	public void setBussinessNum(java.lang.String value) {
		this.bussinessNum = value;
	}	
	public java.lang.String getBussinessNum() {
		return this.bussinessNum;
	}

	public void setBussEmail(java.lang.String value) {
		this.bussEmail = value;
	}	
	public java.lang.String getBussEmail() {
		return this.bussEmail;
	}

	public void setBussAddress(java.lang.String value) {
		this.bussAddress = value;
	}	
	public java.lang.String getBussAddress() {
		return this.bussAddress;
	}

	public void setFileId(java.lang.String value) {
		this.fileId = value;
	}	
	public java.lang.String getFileId() {
		return this.fileId;
	}

	public void setBussStatus(java.lang.String value) {
		this.bussStatus = value;
	}	
	public java.lang.String getBussStatus() {
		return this.bussStatus;
	}

	public void setConfigureStatus(java.lang.String value) {
		this.configureStatus = value;
	}	
	public java.lang.String getConfigureStatus() {
		return this.configureStatus;
	}

	public void setSuggestion(java.lang.String value) {
		this.suggestion = value;
	}	
	public java.lang.String getSuggestion() {
		return this.suggestion;
	}



	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}


    public java.lang.String getBussDataType() {
		return bussDataType;
	}

	public void setBussDataType(java.lang.String bussDataType) {
		this.bussDataType = bussDataType;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("BussAccount").append("='").append(getBussAccount()).append("' ");	
			   buffer.append("BussPasswd").append("='").append(getBussPasswd()).append("' ");	
			   buffer.append("BussType").append("='").append(getBussType()).append("' ");	
			   buffer.append("BussName").append("='").append(getBussName()).append("' ");	
			   buffer.append("BussSex").append("='").append(getBussSex()).append("' ");	
			   buffer.append("BussCredNo").append("='").append(getBussCredNo()).append("' ");	
			   buffer.append("BussMobileNo").append("='").append(getBussMobileNo()).append("' ");	
			   buffer.append("BussTeleNo").append("='").append(getBussTeleNo()).append("' ");	
			   buffer.append("OrgName").append("='").append(getOrgName()).append("' ");	
			   buffer.append("OrgAddress").append("='").append(getOrgAddress()).append("' ");	
			   buffer.append("BussinessNum").append("='").append(getBussinessNum()).append("' ");	
			   buffer.append("BussEmail").append("='").append(getBussEmail()).append("' ");	
			   buffer.append("BussAddress").append("='").append(getBussAddress()).append("' ");	
			   buffer.append("FileId").append("='").append(getFileId()).append("' ");	
			   buffer.append("BussStatus").append("='").append(getBussStatus()).append("' ");	
			   buffer.append("ConfigureStatus").append("='").append(getConfigureStatus()).append("' ");	
			   buffer.append("Suggestion").append("='").append(getSuggestion()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");
			   buffer.append("BussDataType").append("='").append(getBussDataType()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BussInfo) ) return false;
		 BussInfo  castOther = ( BussInfo) other;  
		 return 
					( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getBussAccount()==castOther.getBussAccount()) ||( this.getBussAccount()!=null && castOther.getBussAccount()!=null && this.getBussAccount().equals(castOther.getBussAccount()) ) )
					&&( (this.getBussPasswd()==castOther.getBussPasswd()) ||( this.getBussPasswd()!=null && castOther.getBussPasswd()!=null && this.getBussPasswd().equals(castOther.getBussPasswd()) ) )
					&&( (this.getBussType()==castOther.getBussType()) ||( this.getBussType()!=null && castOther.getBussType()!=null && this.getBussType().equals(castOther.getBussType()) ) )
					&&( (this.getBussName()==castOther.getBussName()) ||( this.getBussName()!=null && castOther.getBussName()!=null && this.getBussName().equals(castOther.getBussName()) ) )
					&&( (this.getBussSex()==castOther.getBussSex()) ||( this.getBussSex()!=null && castOther.getBussSex()!=null && this.getBussSex().equals(castOther.getBussSex()) ) )
					&&( (this.getBussCredNo()==castOther.getBussCredNo()) ||( this.getBussCredNo()!=null && castOther.getBussCredNo()!=null && this.getBussCredNo().equals(castOther.getBussCredNo()) ) )
					&&( (this.getBussMobileNo()==castOther.getBussMobileNo()) ||( this.getBussMobileNo()!=null && castOther.getBussMobileNo()!=null && this.getBussMobileNo().equals(castOther.getBussMobileNo()) ) )
					&&( (this.getBussTeleNo()==castOther.getBussTeleNo()) ||( this.getBussTeleNo()!=null && castOther.getBussTeleNo()!=null && this.getBussTeleNo().equals(castOther.getBussTeleNo()) ) )
					&&( (this.getOrgName()==castOther.getOrgName()) ||( this.getOrgName()!=null && castOther.getOrgName()!=null && this.getOrgName().equals(castOther.getOrgName()) ) )
					&&( (this.getOrgAddress()==castOther.getOrgAddress()) ||( this.getOrgAddress()!=null && castOther.getOrgAddress()!=null && this.getOrgAddress().equals(castOther.getOrgAddress()) ) )
					&&( (this.getBussinessNum()==castOther.getBussinessNum()) ||( this.getBussinessNum()!=null && castOther.getBussinessNum()!=null && this.getBussinessNum().equals(castOther.getBussinessNum()) ) )
					&&( (this.getBussEmail()==castOther.getBussEmail()) ||( this.getBussEmail()!=null && castOther.getBussEmail()!=null && this.getBussEmail().equals(castOther.getBussEmail()) ) )
					&&( (this.getBussAddress()==castOther.getBussAddress()) ||( this.getBussAddress()!=null && castOther.getBussAddress()!=null && this.getBussAddress().equals(castOther.getBussAddress()) ) )
					&&( (this.getFileId()==castOther.getFileId()) ||( this.getFileId()!=null && castOther.getFileId()!=null && this.getFileId().equals(castOther.getFileId()) ) )
					&&( (this.getBussStatus()==castOther.getBussStatus()) ||( this.getBussStatus()!=null && castOther.getBussStatus()!=null && this.getBussStatus().equals(castOther.getBussStatus()) ) )
					&&( (this.getConfigureStatus()==castOther.getConfigureStatus()) ||( this.getConfigureStatus()!=null && castOther.getConfigureStatus()!=null && this.getConfigureStatus().equals(castOther.getConfigureStatus()) ) )
					&&( (this.getSuggestion()==castOther.getSuggestion()) ||( this.getSuggestion()!=null && castOther.getSuggestion()!=null && this.getSuggestion().equals(castOther.getSuggestion()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
					&&( (this.getBussDataType()==castOther.getBussDataType()) ||( this.getBussDataType()!=null && castOther.getBussDataType()!=null && this.getBussDataType().equals(castOther.getBussDataType()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getBussAccount() == null ? 0 : this.getBussAccount().hashCode() );
				  result = 37 * result + (getBussPasswd() == null ? 0 : this.getBussPasswd().hashCode() );
				  result = 37 * result + (getBussType() == null ? 0 : this.getBussType().hashCode() );
				  result = 37 * result + (getBussName() == null ? 0 : this.getBussName().hashCode() );
				  result = 37 * result + (getBussSex() == null ? 0 : this.getBussSex().hashCode() );
				  result = 37 * result + (getBussCredNo() == null ? 0 : this.getBussCredNo().hashCode() );
				  result = 37 * result + (getBussMobileNo() == null ? 0 : this.getBussMobileNo().hashCode() );
				  result = 37 * result + (getBussTeleNo() == null ? 0 : this.getBussTeleNo().hashCode() );
				  result = 37 * result + (getOrgName() == null ? 0 : this.getOrgName().hashCode() );
				  result = 37 * result + (getOrgAddress() == null ? 0 : this.getOrgAddress().hashCode() );
				  result = 37 * result + (getBussinessNum() == null ? 0 : this.getBussinessNum().hashCode() );
				  result = 37 * result + (getBussEmail() == null ? 0 : this.getBussEmail().hashCode() );
				  result = 37 * result + (getBussAddress() == null ? 0 : this.getBussAddress().hashCode() );
				  result = 37 * result + (getFileId() == null ? 0 : this.getFileId().hashCode() );
				  result = 37 * result + (getBussStatus() == null ? 0 : this.getBussStatus().hashCode() );
				  result = 37 * result + (getConfigureStatus() == null ? 0 : this.getConfigureStatus().hashCode() );
				  result = 37 * result + (getSuggestion() == null ? 0 : this.getSuggestion().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
				  result = 37 * result + (getBussDataType() == null ? 0 : this.getBussDataType().hashCode() );
         return result;
	} 
	
}
