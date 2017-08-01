/**
 * com.sunrise.ord.mapper.OrdLog.java
 */
package com.tydic.dbs.order.vo;

import com.tydic.commons.utils.BaseVO;



/**
 * 
 * @ClassName: OrdLog 
 * @Description: TODO(实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:27:43 
 *
 */
public class OrdLog extends BaseVO implements java.io.Serializable{

	private java.lang.String ordLogId;
	private java.lang.String ordId;
	private java.util.Date ordLogTime;
	private java.lang.String ordLogType;
	private java.lang.String ordLogMemo;
	private java.lang.String ordLogUser;
	private java.lang.String ordLogUserTel;
	private java.lang.String ordLogUserAccount;
	public OrdLog(){
	}

	public OrdLog(
		java.lang.String ordLogId
	){
		this.ordLogId = ordLogId;
	}



	public void setOrdLogId(java.lang.String value) {
		this.ordLogId = value;
	}	
	public java.lang.String getOrdLogId() {
		return this.ordLogId;
	}

	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}	
	public java.lang.String getOrdId() {
		return this.ordId;
	}

	public void setOrdLogTime(java.util.Date value) {
		this.ordLogTime = value;
	}	
	public java.util.Date getOrdLogTime() {
		return this.ordLogTime;
	}

	public void setOrdLogType(java.lang.String value) {
		this.ordLogType = value;
	}	
	public java.lang.String getOrdLogType() {
		return this.ordLogType;
	}

	public void setOrdLogMemo(java.lang.String value) {
		this.ordLogMemo = value;
	}	
	public java.lang.String getOrdLogMemo() {
		return this.ordLogMemo;
	}

	public void setOrdLogUser(java.lang.String value) {
		this.ordLogUser = value;
	}	
	public java.lang.String getOrdLogUser() {
		return this.ordLogUser;
	}


    /** 
	 * @return ordLogUserTel 
	 */
	public java.lang.String getOrdLogUserTel() {
		return ordLogUserTel;
	}

	/** 
	 * @param ordLogUserTel 要设置的 ordLogUserTel 
	 */
	public void setOrdLogUserTel(java.lang.String ordLogUserTel) {
		this.ordLogUserTel = ordLogUserTel;
	}

	/** 
	 * @return ordLogUserAccount 
	 */
	public java.lang.String getOrdLogUserAccount() {
		return ordLogUserAccount;
	}

	/** 
	 * @param ordLogUserAccount 要设置的 ordLogUserAccount 
	 */
	public void setOrdLogUserAccount(java.lang.String ordLogUserAccount) {
		this.ordLogUserAccount = ordLogUserAccount;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("OrdLogId").append("='").append(getOrdLogId()).append("' ");	
			   buffer.append("OrdId").append("='").append(getOrdId()).append("' ");	
			   buffer.append("OrdLogTime").append("='").append(getOrdLogTime()).append("' ");	
			   buffer.append("OrdLogType").append("='").append(getOrdLogType()).append("' ");	
			   buffer.append("OrdLogMemo").append("='").append(getOrdLogMemo()).append("' ");	
			   buffer.append("OrdLogUser").append("='").append(getOrdLogUser()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrdLog) ) return false;
		 OrdLog  castOther = ( OrdLog) other;  
		 return 
					( (this.getOrdLogId()==castOther.getOrdLogId()) ||( this.getOrdLogId()!=null && castOther.getOrdLogId()!=null && this.getOrdLogId().equals(castOther.getOrdLogId()) ) )
					&&( (this.getOrdId()==castOther.getOrdId()) ||( this.getOrdId()!=null && castOther.getOrdId()!=null && this.getOrdId().equals(castOther.getOrdId()) ) )
					&&( (this.getOrdLogTime()==castOther.getOrdLogTime()) ||( this.getOrdLogTime()!=null && castOther.getOrdLogTime()!=null && this.getOrdLogTime().equals(castOther.getOrdLogTime()) ) )
					&&( (this.getOrdLogType()==castOther.getOrdLogType()) ||( this.getOrdLogType()!=null && castOther.getOrdLogType()!=null && this.getOrdLogType().equals(castOther.getOrdLogType()) ) )
					&&( (this.getOrdLogMemo()==castOther.getOrdLogMemo()) ||( this.getOrdLogMemo()!=null && castOther.getOrdLogMemo()!=null && this.getOrdLogMemo().equals(castOther.getOrdLogMemo()) ) )
					&&( (this.getOrdLogUser()==castOther.getOrdLogUser()) ||( this.getOrdLogUser()!=null && castOther.getOrdLogUser()!=null && this.getOrdLogUser().equals(castOther.getOrdLogUser()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getOrdLogId() == null ? 0 : this.getOrdLogId().hashCode() );
				  result = 37 * result + (getOrdId() == null ? 0 : this.getOrdId().hashCode() );
				  result = 37 * result + (getOrdLogTime() == null ? 0 : this.getOrdLogTime().hashCode() );
				  result = 37 * result + (getOrdLogType() == null ? 0 : this.getOrdLogType().hashCode() );
				  result = 37 * result + (getOrdLogMemo() == null ? 0 : this.getOrdLogMemo().hashCode() );
				  result = 37 * result + (getOrdLogUser() == null ? 0 : this.getOrdLogUser().hashCode() );
         return result;
	} 
	
}
