package com.tydic.dbs.system.sms.mapper;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: SysSms 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-23 上午10:45:20 
 *
 */
public class SysSms extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 6825686747474991054L;
	private java.lang.Integer smsId;
	private java.lang.String calledNum;
	private java.lang.String content;
	private java.lang.String userType;
	private java.lang.String result;
	private java.lang.String callingNum;
	public SysSms(){
	}

	public SysSms(
		java.lang.Integer smsId
	){
		this.smsId = smsId;
	}



	public void setSmsId(java.lang.Integer value) {
		this.smsId = value;
	}	
	public java.lang.Integer getSmsId() {
		return this.smsId;
	}

	public void setCalledNum(java.lang.String value) {
		this.calledNum = value;
	}	
	public java.lang.String getCalledNum() {
		return this.calledNum;
	}

	public void setContent(java.lang.String value) {
		this.content = value;
	}	
	public java.lang.String getContent() {
		return this.content;
	}

	public void setUserType(java.lang.String value) {
		this.userType = value;
	}	
	public java.lang.String getUserType() {
		return this.userType;
	}

	public void setResult(java.lang.String value) {
		this.result = value;
	}	
	public java.lang.String getResult() {
		return this.result;
	}


	public void setCallingNum(java.lang.String value) {
		this.callingNum = value;
	}	
	public java.lang.String getCallingNum() {
		return this.callingNum;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("SmsId").append("='").append(getSmsId()).append("' ");	
			   buffer.append("CalledNum").append("='").append(getCalledNum()).append("' ");	
			   buffer.append("Content").append("='").append(getContent()).append("' ");	
			   buffer.append("UserType").append("='").append(getUserType()).append("' ");	
			   buffer.append("Result").append("='").append(getResult()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("CallingNum").append("='").append(getCallingNum()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysSms) ) return false;
		 SysSms  castOther = ( SysSms) other;  
		 return 
					( (this.getSmsId()==castOther.getSmsId()) ||( this.getSmsId()!=null && castOther.getSmsId()!=null && this.getSmsId().equals(castOther.getSmsId()) ) )
					&&( (this.getCalledNum()==castOther.getCalledNum()) ||( this.getCalledNum()!=null && castOther.getCalledNum()!=null && this.getCalledNum().equals(castOther.getCalledNum()) ) )
					&&( (this.getContent()==castOther.getContent()) ||( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
					&&( (this.getUserType()==castOther.getUserType()) ||( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) ) )
					&&( (this.getResult()==castOther.getResult()) ||( this.getResult()!=null && castOther.getResult()!=null && this.getResult().equals(castOther.getResult()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getCallingNum()==castOther.getCallingNum()) ||( this.getCallingNum()!=null && castOther.getCallingNum()!=null && this.getCallingNum().equals(castOther.getCallingNum()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getSmsId() == null ? 0 : this.getSmsId().hashCode() );
				  result = 37 * result + (getCalledNum() == null ? 0 : this.getCalledNum().hashCode() );
				  result = 37 * result + (getContent() == null ? 0 : this.getContent().hashCode() );
				  result = 37 * result + (getUserType() == null ? 0 : this.getUserType().hashCode() );
				  result = 37 * result + (getResult() == null ? 0 : this.getResult().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getCallingNum() == null ? 0 : this.getCallingNum().hashCode() );
         return result;
	} 
	
}
