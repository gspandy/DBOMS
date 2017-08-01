/**
 * com.sunrise.88.mapper.InterfaceLog.java
 */
package com.tydic.dbs.buyer.vo;


import com.tydic.commons.utils.BaseVO;

/**
 * @file  InterfaceLog.java
 * @author Carson
 * @version 0.1
 * @InterfaceLog实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-08-03 03:48:28
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class InterfaceLog extends BaseVO implements java.io.Serializable{

	private Long logId;
	private String serialNum;
	private String busiType;
	private String paramIn;
	private String resultContent;
	private String remark;
	private String tabName;

	public InterfaceLog(){
	}

	public InterfaceLog(
		Long logId
	){
		this.logId = logId;
	}



	public void setLogId(Long value) {
		this.logId = value;
	}	
	public Long getLogId() {
		return this.logId;
	}

	public void setSerialNum(String value) {
		this.serialNum = value;
	}	
	public String getSerialNum() {
		return this.serialNum;
	}

	public void setBusiType(String value) {
		this.busiType = value;
	}	
	public String getBusiType() {
		return this.busiType;
	}

	public void setParamIn(String value) {
		this.paramIn = value;
	}	
	public String getParamIn() {
		return this.paramIn;
	}

	public void setResultContent(String value) {
		this.resultContent = value;
	}	
	public String getResultContent() {
		return this.resultContent;
	}


	public void setRemark(String value) {
		this.remark = value;
	}	
	public String getRemark() {
		return this.remark;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("LogId").append("='").append(getLogId()).append("' ");	
			   buffer.append("SerialNum").append("='").append(getSerialNum()).append("' ");	
			   buffer.append("BusiType").append("='").append(getBusiType()).append("' ");	
			   buffer.append("ParamIn").append("='").append(getParamIn()).append("' ");	
			   buffer.append("ResultContent").append("='").append(getResultContent()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InterfaceLog) ) return false;
		 InterfaceLog  castOther = ( InterfaceLog) other;  
		 return 
					( (this.getLogId()==castOther.getLogId()) ||( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
					&&( (this.getSerialNum()==castOther.getSerialNum()) ||( this.getSerialNum()!=null && castOther.getSerialNum()!=null && this.getSerialNum().equals(castOther.getSerialNum()) ) )
					&&( (this.getBusiType()==castOther.getBusiType()) ||( this.getBusiType()!=null && castOther.getBusiType()!=null && this.getBusiType().equals(castOther.getBusiType()) ) )
					&&( (this.getParamIn()==castOther.getParamIn()) ||( this.getParamIn()!=null && castOther.getParamIn()!=null && this.getParamIn().equals(castOther.getParamIn()) ) )
					&&( (this.getResultContent()==castOther.getResultContent()) ||( this.getResultContent()!=null && castOther.getResultContent()!=null && this.getResultContent().equals(castOther.getResultContent()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getLogId() == null ? 0 : this.getLogId().hashCode() );
				  result = 37 * result + (getSerialNum() == null ? 0 : this.getSerialNum().hashCode() );
				  result = 37 * result + (getBusiType() == null ? 0 : this.getBusiType().hashCode() );
				  result = 37 * result + (getParamIn() == null ? 0 : this.getParamIn().hashCode() );
				  result = 37 * result + (getResultContent() == null ? 0 : this.getResultContent().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
