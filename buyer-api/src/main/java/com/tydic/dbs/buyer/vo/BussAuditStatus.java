/**
 * com.sunrise.887.mapper.BussAuditStatus.java
 */
package com.tydic.dbs.buyer.vo;


import com.tydic.commons.utils.BaseVO;

/**
 * @file  BussAuditStatus.java
 * @author Carson
 * @version 0.1
 * @BussAuditStatus实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-26 05:13:08
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BussAuditStatus extends BaseVO implements java.io.Serializable{

	private Long id;
	private String bussId;
	private String type;
	private String auditStatus;
	//private String status;
	private String remark;
	public BussAuditStatus(){
	}

	public BussAuditStatus(
		Long id
	){
		this.id = id;
	}


	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public void setId(Long value) {
		this.id = value;
	}	
	public Long getId() {
		return this.id;
	}

	public void setBussId(String value) {
		this.bussId = value;
	}	
	public String getBussId() {
		return this.bussId;
	}

	public void setType(String value) {
		this.type = value;
	}	
	public String getType() {
		return this.type;
	}

	public void setRemark(String value) {
		this.remark = value;
	}	
	public String getRemark() {
		return this.remark;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("Id").append("='").append(getId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("Type").append("='").append(getType()).append("' ");	
			   buffer.append("Status").append("='").append(getStatus()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BussAuditStatus) ) return false;
		 BussAuditStatus  castOther = ( BussAuditStatus) other;  
		 return 
					( (this.getId()==castOther.getId()) ||( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getType()==castOther.getType()) ||( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
					&&( (this.getStatus()==castOther.getStatus()) ||( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getId() == null ? 0 : this.getId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getType() == null ? 0 : this.getType().hashCode() );
				  result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
