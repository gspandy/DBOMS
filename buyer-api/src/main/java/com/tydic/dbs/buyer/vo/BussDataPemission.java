/**
 * com.sunrise.wcs.mapper.BussDataPemission.java
 */
package com.tydic.dbs.buyer.vo;


import com.tydic.commons.utils.BaseVO;

/**
 * @file  BussDataPemission.java
 * @author Carson
 * @version 0.1
 * @BussDataPemission实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-12 06:58:33
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussDataPemission extends BaseVO implements java.io.Serializable{

	private java.lang.String pemissionId;
	private java.lang.String bussId;
	private java.lang.String pemissionName;
	private java.lang.String appStatus;
	private java.lang.String remark;
	public BussDataPemission(){
	}

	public BussDataPemission(
		java.lang.String pemissionId
	){
		this.pemissionId = pemissionId;
	}



	public void setPemissionId(java.lang.String value) {
		this.pemissionId = value;
	}	
	public java.lang.String getPemissionId() {
		return this.pemissionId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setPemissionName(java.lang.String value) {
		this.pemissionName = value;
	}
	public java.lang.String getPemissionName() {
		return this.pemissionName;
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
			   buffer.append("PemissionId").append("='").append(getPemissionId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
//			   buffer.append("PemissionName").append("='").append(getPemissionName()).append("' ");
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
		 if ( !(other instanceof BussDataPemission) ) return false;
		 BussDataPemission  castOther = ( BussDataPemission) other;  
		 return 
					( (this.getPemissionId()==castOther.getPemissionId()) ||( this.getPemissionId()!=null && castOther.getPemissionId()!=null && this.getPemissionId().equals(castOther.getPemissionId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
//					&&( (this.getPemissionName()==castOther.getPemissionName()) ||( this.getPemissionName()!=null && castOther.getPemissionName()!=null && this.getPemissionName().equals(castOther.getPemissionName()) ) )
					&&( (this.getAppStatus()==castOther.getAppStatus()) ||( this.getAppStatus()!=null && castOther.getAppStatus()!=null && this.getAppStatus().equals(castOther.getAppStatus()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getPemissionId() == null ? 0 : this.getPemissionId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
//				  result = 37 * result + (getPemissionName() == null ? 0 : this.getPemissionName().hashCode() );
				  result = 37 * result + (getAppStatus() == null ? 0 : this.getAppStatus().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
