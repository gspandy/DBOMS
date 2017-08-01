/**
 * com.sunrise.ord.mapper.OrdRusult.java
 */
package com.tydic.dbs.order.vo;

import java.util.Date;

import com.tydic.commons.utils.BaseVO;


/**
 * 
 * @ClassName: OrdRusult 
 * @Description: TODO(实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:30:01 
 *
 */
public class OrdRusult extends BaseVO implements java.io.Serializable{

    private String ordRusultId;

    private String ordPrdId;

    private String filePath;

    private String fileName;

    private String fileStatus;
    
    private String resultCount;
    
    private String resultUnit;

    private Date runTime;
    
    private String workNo;
    
	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public OrdRusult(){
	}

	public OrdRusult(
		java.lang.String ordRusultId
	){
		this.ordRusultId = ordRusultId;
	}



	public void setOrdRusultId(java.lang.String value) {
		this.ordRusultId = value;
	}	
	public java.lang.String getOrdRusultId() {
		return this.ordRusultId;
	}

	public void setOrdPrdId(java.lang.String value) {
		this.ordPrdId = value;
	}	
	public java.lang.String getOrdPrdId() {
		return this.ordPrdId;
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
	
	public String getResultCount() {
		return resultCount;
	}
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public String getResultUnit() {
		return resultUnit;
	}
	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}
	
	

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("OrdRusultId").append("='").append(getOrdRusultId()).append("' ");	
			   buffer.append("OrdPrdId").append("='").append(getOrdPrdId()).append("' ");	
			   buffer.append("FilePath").append("='").append(getFilePath()).append("' ");	
			   buffer.append("FileName").append("='").append(getFileName()).append("' ");	
			   buffer.append("FileStatus").append("='").append(getFileStatus()).append("' ");	
			   buffer.append("runTime").append("='").append(getRunTime()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrdRusult) ) return false;
		 OrdRusult  castOther = ( OrdRusult) other;  
		 return 
					( (this.getOrdRusultId()==castOther.getOrdRusultId()) ||( this.getOrdRusultId()!=null && castOther.getOrdRusultId()!=null && this.getOrdRusultId().equals(castOther.getOrdRusultId()) ) )
					&&( (this.getOrdPrdId()==castOther.getOrdPrdId()) ||( this.getOrdPrdId()!=null && castOther.getOrdPrdId()!=null && this.getOrdPrdId().equals(castOther.getOrdPrdId()) ) )
					&&( (this.getFilePath()==castOther.getFilePath()) ||( this.getFilePath()!=null && castOther.getFilePath()!=null && this.getFilePath().equals(castOther.getFilePath()) ) )
					&&( (this.getFileName()==castOther.getFileName()) ||( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
					&&( (this.getFileStatus()==castOther.getFileStatus()) ||( this.getFileStatus()!=null && castOther.getFileStatus()!=null && this.getFileStatus().equals(castOther.getFileStatus()) ) )
					&&( (this.getRunTime()==castOther.getRunTime()) ||( this.getRunTime()!=null && castOther.getRunTime()!=null && this.getRunTime().equals(castOther.getRunTime()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getOrdRusultId() == null ? 0 : this.getOrdRusultId().hashCode() );
				  result = 37 * result + (getOrdPrdId() == null ? 0 : this.getOrdPrdId().hashCode() );
				  result = 37 * result + (getFilePath() == null ? 0 : this.getFilePath().hashCode() );
				  result = 37 * result + (getFileName() == null ? 0 : this.getFileName().hashCode() );
				  result = 37 * result + (getFileStatus() == null ? 0 : this.getFileStatus().hashCode() );
				  result = 37 * result + (getRunTime() == null ? 0 : this.getRunTime().hashCode() );
         return result;
	} 
	
}
