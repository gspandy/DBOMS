/**
 * com.sunrise.wcs.mapper.BussDataResource.java
 */
package com.tydic.dbs.buyer.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  BussDataResource.java
 * @author Carson
 * @version 0.1
 * @BussDataResource实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-12 06:58:33
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussDataResource extends BaseVO implements java.io.Serializable{

	private java.lang.String resoureId;
	private java.lang.String bussId;
	private java.lang.String serviceName;
	private java.lang.String appStatus;
	private java.lang.String remark;
	public BussDataResource(){
	}

	public BussDataResource(
		java.lang.String resoureId
	){
		this.resoureId = resoureId;
	}



	public void setResoureId(java.lang.String value) {
		this.resoureId = value;
	}	
	public java.lang.String getResoureId() {
		return this.resoureId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setServiceName(java.lang.String value) {
		this.serviceName = value;
	}	
	public java.lang.String getServiceName() {
		return this.serviceName;
	}

	public void setAppStatus(java.lang.String value) {
		this.appStatus = value;
	}	
	public java.lang.String getAppStatus() {
		return this.appStatus;
	}



	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("ResoureId").append("='").append(getResoureId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("ServiceName").append("='").append(getServiceName()).append("' ");	
			   buffer.append("AppStatus").append("='").append(getAppStatus()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BussDataResource) ) return false;
		 BussDataResource  castOther = ( BussDataResource) other;  
		 return 
					( (this.getResoureId()==castOther.getResoureId()) ||( this.getResoureId()!=null && castOther.getResoureId()!=null && this.getResoureId().equals(castOther.getResoureId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getServiceName()==castOther.getServiceName()) ||( this.getServiceName()!=null && castOther.getServiceName()!=null && this.getServiceName().equals(castOther.getServiceName()) ) )
					&&( (this.getAppStatus()==castOther.getAppStatus()) ||( this.getAppStatus()!=null && castOther.getAppStatus()!=null && this.getAppStatus().equals(castOther.getAppStatus()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getResoureId() == null ? 0 : this.getResoureId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getServiceName() == null ? 0 : this.getServiceName().hashCode() );
				  result = 37 * result + (getAppStatus() == null ? 0 : this.getAppStatus().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
