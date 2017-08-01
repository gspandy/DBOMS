/**
 * com.sunrise.111.mapper.SysLoginSms.java
 */
package com.tydic.dbs.system.SysLoginSms.mapper;

import com.tydic.commons.utils.BaseVO;



/**
 * 
 * @ClassName: SysLoginSms 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-20 下午4:22:55 
 *
 */
public class SysLoginSms extends BaseVO implements java.io.Serializable{

	private java.lang.Integer loginSmsId;
	private java.lang.Integer smsId;
	private java.lang.String mobile;
	private java.lang.String veriCode;
	private java.lang.Integer smsType;
	private java.util.Date expireTime;
	public SysLoginSms(){
	}

	public SysLoginSms(
		java.lang.Integer loginSmsId
	){
		this.loginSmsId = loginSmsId;
	}



	public void setLoginSmsId(java.lang.Integer value) {
		this.loginSmsId = value;
	}	
	public java.lang.Integer getLoginSmsId() {
		return this.loginSmsId;
	}

	public void setSmsId(java.lang.Integer value) {
		this.smsId = value;
	}	
	public java.lang.Integer getSmsId() {
		return this.smsId;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}	
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setVeriCode(java.lang.String value) {
		this.veriCode = value;
	}	
	public java.lang.String getVeriCode() {
		return this.veriCode;
	}

	public void setSmsType(java.lang.Integer value) {
		this.smsType = value;
	}	
	public java.lang.Integer getSmsType() {
		return this.smsType;
	}


	public void setExpireTime(java.util.Date value) {
		this.expireTime = value;
	}	
	public java.util.Date getExpireTime() {
		return this.expireTime;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("LoginSmsId").append("='").append(getLoginSmsId()).append("' ");	
			   buffer.append("SmsId").append("='").append(getSmsId()).append("' ");	
			   buffer.append("Mobile").append("='").append(getMobile()).append("' ");	
			   buffer.append("VeriCode").append("='").append(getVeriCode()).append("' ");	
			   buffer.append("SmsType").append("='").append(getSmsType()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("ExpireTime").append("='").append(getExpireTime()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysLoginSms) ) return false;
		 SysLoginSms  castOther = ( SysLoginSms) other;  
		 return 
					( (this.getLoginSmsId()==castOther.getLoginSmsId()) ||( this.getLoginSmsId()!=null && castOther.getLoginSmsId()!=null && this.getLoginSmsId().equals(castOther.getLoginSmsId()) ) )
					&&( (this.getSmsId()==castOther.getSmsId()) ||( this.getSmsId()!=null && castOther.getSmsId()!=null && this.getSmsId().equals(castOther.getSmsId()) ) )
					&&( (this.getMobile()==castOther.getMobile()) ||( this.getMobile()!=null && castOther.getMobile()!=null && this.getMobile().equals(castOther.getMobile()) ) )
					&&( (this.getVeriCode()==castOther.getVeriCode()) ||( this.getVeriCode()!=null && castOther.getVeriCode()!=null && this.getVeriCode().equals(castOther.getVeriCode()) ) )
					&&( (this.getSmsType()==castOther.getSmsType()) ||( this.getSmsType()!=null && castOther.getSmsType()!=null && this.getSmsType().equals(castOther.getSmsType()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getExpireTime()==castOther.getExpireTime()) ||( this.getExpireTime()!=null && castOther.getExpireTime()!=null && this.getExpireTime().equals(castOther.getExpireTime()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getLoginSmsId() == null ? 0 : this.getLoginSmsId().hashCode() );
				  result = 37 * result + (getSmsId() == null ? 0 : this.getSmsId().hashCode() );
				  result = 37 * result + (getMobile() == null ? 0 : this.getMobile().hashCode() );
				  result = 37 * result + (getVeriCode() == null ? 0 : this.getVeriCode().hashCode() );
				  result = 37 * result + (getSmsType() == null ? 0 : this.getSmsType().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getExpireTime() == null ? 0 : this.getExpireTime().hashCode() );
         return result;
	} 
	
}
