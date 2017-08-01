/**
 * com.sunrise.qqq.mapper.SysUploadFile.java
 */
package com.tydic.dbs.system.sysUploadFile.mapper;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  SysUploadFile.java
 * @author Carson
 * @version 0.1
 * @SysUploadFile实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-14 07:04:48
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class SysUploadFile extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 3893034810171765773L;
	private java.lang.Integer fileId;
	private java.lang.String fileType;
	private java.lang.String filePath;
	private java.lang.String fileName;
	private java.lang.String fileStatus;
	public SysUploadFile(){
	}

	public SysUploadFile(
		java.lang.Integer fileId
	){
		this.fileId = fileId;
	}



	public void setFileId(java.lang.Integer value) {
		this.fileId = value;
	}	
	public java.lang.Integer getFileId() {
		return this.fileId;
	}

	public void setFileType(java.lang.String value) {
		this.fileType = value;
	}	
	public java.lang.String getFileType() {
		return this.fileType;
	}

	public void setFilePath(java.lang.String value) {
		this.filePath = value;
	}	
	public java.lang.String getFilePath() {
		return this.filePath;
	}

	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}	
	public java.lang.String getFileName() {
		return this.fileName;
	}

	public void setFileStatus(java.lang.String value) {
		this.fileStatus = value;
	}	
	public java.lang.String getFileStatus() {
		return this.fileStatus;
	}






    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("FileId").append("='").append(getFileId()).append("' ");	
			   buffer.append("FileType").append("='").append(getFileType()).append("' ");	
			   buffer.append("FilePath").append("='").append(getFilePath()).append("' ");	
			   buffer.append("FileName").append("='").append(getFileName()).append("' ");	
			   buffer.append("FileStatus").append("='").append(getFileStatus()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Modifier").append("='").append(getModifier()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysUploadFile) ) return false;
		 SysUploadFile  castOther = ( SysUploadFile) other;  
		 return 
					( (this.getFileId()==castOther.getFileId()) ||( this.getFileId()!=null && castOther.getFileId()!=null && this.getFileId().equals(castOther.getFileId()) ) )
					&&( (this.getFileType()==castOther.getFileType()) ||( this.getFileType()!=null && castOther.getFileType()!=null && this.getFileType().equals(castOther.getFileType()) ) )
					&&( (this.getFilePath()==castOther.getFilePath()) ||( this.getFilePath()!=null && castOther.getFilePath()!=null && this.getFilePath().equals(castOther.getFilePath()) ) )
					&&( (this.getFileName()==castOther.getFileName()) ||( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
					&&( (this.getFileStatus()==castOther.getFileStatus()) ||( this.getFileStatus()!=null && castOther.getFileStatus()!=null && this.getFileStatus().equals(castOther.getFileStatus()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getModifier()==castOther.getModifier()) ||( this.getModifier()!=null && castOther.getModifier()!=null && this.getModifier().equals(castOther.getModifier()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getFileId() == null ? 0 : this.getFileId().hashCode() );
				  result = 37 * result + (getFileType() == null ? 0 : this.getFileType().hashCode() );
				  result = 37 * result + (getFilePath() == null ? 0 : this.getFilePath().hashCode() );
				  result = 37 * result + (getFileName() == null ? 0 : this.getFileName().hashCode() );
				  result = 37 * result + (getFileStatus() == null ? 0 : this.getFileStatus().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getModifier() == null ? 0 : this.getModifier().hashCode() );
         return result;
	} 
	
}
