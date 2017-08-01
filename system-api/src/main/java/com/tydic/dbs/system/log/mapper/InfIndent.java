/**
 * com.sunrise.wcs.mapper.InfIndent.java
 */
package com.tydic.dbs.system.log.mapper;


import com.tydic.commons.utils.BaseVO;

/**
 * @file  UploadFile.java
 * @author Carson
 * @version 0.1
 * @UploadFile实体类
 * Copyright(C), 2013-2014
 *		 zql
 * History
 *   	1. Date: 2016-08-09 05:28:44
 *      	Author: zql
 *   	2. ...
 */
public class InfIndent extends BaseVO implements java.io.Serializable{

	private Long infId;
	private String infName;
	private String infFlag;
	public InfIndent(){
	}

	public InfIndent(
		Long infId
	){
		this.infId = infId;
	}



	public void setInfId(Long value) {
		this.infId = value;
	}	
	public Long getInfId() {
		return this.infId;
	}

	public void setInfName(String value) {
		this.infName = value;
	}	
	public String getInfName() {
		return this.infName;
	}

	public void setInfFlag(String value) {
		this.infFlag = value;
	}	
	public String getInfFlag() {
		return this.infFlag;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("InfId").append("='").append(getInfId()).append("' ");	
			   buffer.append("InfName").append("='").append(getInfName()).append("' ");	
			   buffer.append("InfFlag").append("='").append(getInfFlag()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InfIndent) ) return false;
		 InfIndent  castOther = ( InfIndent) other;  
		 return 
					( (this.getInfId()==castOther.getInfId()) ||( this.getInfId()!=null && castOther.getInfId()!=null && this.getInfId().equals(castOther.getInfId()) ) )
					&&( (this.getInfName()==castOther.getInfName()) ||( this.getInfName()!=null && castOther.getInfName()!=null && this.getInfName().equals(castOther.getInfName()) ) )
					&&( (this.getInfFlag()==castOther.getInfFlag()) ||( this.getInfFlag()!=null && castOther.getInfFlag()!=null && this.getInfFlag().equals(castOther.getInfFlag()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getInfId() == null ? 0 : this.getInfId().hashCode() );
				  result = 37 * result + (getInfName() == null ? 0 : this.getInfName().hashCode() );
				  result = 37 * result + (getInfFlag() == null ? 0 : this.getInfFlag().hashCode() );
         return result;
	} 
	
}
