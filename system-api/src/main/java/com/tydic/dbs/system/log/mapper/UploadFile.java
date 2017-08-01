/**
 * com.sunrise.wcs.mapper.UploadFile.java
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
public class UploadFile extends BaseVO implements java.io.Serializable{

	private Long uploadId;
	private String serialNum;
	private String operId;
	private String fileUrl;
	private String fileName;
	private String status;
	private String creator;
	private String remark;
	public UploadFile(){
	}

	public UploadFile(
		Long uploadId
	){
		this.uploadId = uploadId;
	}



	public void setUploadId(Long value) {
		this.uploadId = value;
	}	
	public Long getUploadId() {
		return this.uploadId;
	}

	public void setSerialNum(String value) {
		this.serialNum = value;
	}	
	public String getSerialNum() {
		return this.serialNum;
	}

	public void setOperId(String value) {
		this.operId = value;
	}	
	public String getOperId() {
		return this.operId;
	}

	public void setFileUrl(String value) {
		this.fileUrl = value;
	}	
	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileName(String value) {
		this.fileName = value;
	}	
	public String getFileName() {
		return this.fileName;
	}

	public void setStatus(String value) {
		this.status = value;
	}	
	public String getStatus() {
		return this.status;
	}

	public void setCreator(String value) {
		this.creator = value;
	}	
	public String getCreator() {
		return this.creator;
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
			   buffer.append("UploadId").append("='").append(getUploadId()).append("' ");	
			   buffer.append("SerialNum").append("='").append(getSerialNum()).append("' ");	
			   buffer.append("OperId").append("='").append(getOperId()).append("' ");	
			   buffer.append("FileUrl").append("='").append(getFileUrl()).append("' ");	
			   buffer.append("FileName").append("='").append(getFileName()).append("' ");	
			   buffer.append("Status").append("='").append(getStatus()).append("' ");	
			   buffer.append("Creator").append("='").append(getCreator()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UploadFile) ) return false;
		 UploadFile  castOther = ( UploadFile) other;  
		 return 
					( (this.getUploadId()==castOther.getUploadId()) ||( this.getUploadId()!=null && castOther.getUploadId()!=null && this.getUploadId().equals(castOther.getUploadId()) ) )
					&&( (this.getSerialNum()==castOther.getSerialNum()) ||( this.getSerialNum()!=null && castOther.getSerialNum()!=null && this.getSerialNum().equals(castOther.getSerialNum()) ) )
					&&( (this.getOperId()==castOther.getOperId()) ||( this.getOperId()!=null && castOther.getOperId()!=null && this.getOperId().equals(castOther.getOperId()) ) )
					&&( (this.getFileUrl()==castOther.getFileUrl()) ||( this.getFileUrl()!=null && castOther.getFileUrl()!=null && this.getFileUrl().equals(castOther.getFileUrl()) ) )
					&&( (this.getFileName()==castOther.getFileName()) ||( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
					&&( (this.getStatus()==castOther.getStatus()) ||( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
					&&( (this.getCreator()==castOther.getCreator()) ||( this.getCreator()!=null && castOther.getCreator()!=null && this.getCreator().equals(castOther.getCreator()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getUploadId() == null ? 0 : this.getUploadId().hashCode() );
				  result = 37 * result + (getSerialNum() == null ? 0 : this.getSerialNum().hashCode() );
				  result = 37 * result + (getOperId() == null ? 0 : this.getOperId().hashCode() );
				  result = 37 * result + (getFileUrl() == null ? 0 : this.getFileUrl().hashCode() );
				  result = 37 * result + (getFileName() == null ? 0 : this.getFileName().hashCode() );
				  result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode() );
				  result = 37 * result + (getCreator() == null ? 0 : this.getCreator().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
