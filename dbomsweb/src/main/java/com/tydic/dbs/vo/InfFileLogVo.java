/**
 * com.sunrise.wcs.mapper.InfFileLog.java
 */
package com.tydic.dbs.vo;

import java.util.Date;


/**
 * 
 * @ClassName: InfFileLog 
 * @Description: TODO(文件接口日志记录Vo) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-10 上午10:46:25 
 *
 */
public class InfFileLogVo implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	private Long infLogId;
	private String serialNum;
	private String operId;
	private String infCode;
	private String infName;
	private String infFileName;
	private String status;
	private String creator;
	private String remark;
	private String bussAccount;
	private Date createTime;
	private java.lang.String bussId;
	public InfFileLogVo(){
	}

	public InfFileLogVo(
		Long infLogId
	){
		this.infLogId = infLogId;
	}



	public void setInfLogId(Long value) {
		this.infLogId = value;
	}	
	public Long getInfLogId() {
		return this.infLogId;
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

	public void setInfCode(String value) {
		this.infCode = value;
	}	
	public String getInfCode() {
		return this.infCode;
	}

	public void setInfName(String value) {
		this.infName = value;
	}	
	public String getInfName() {
		return this.infName;
	}

	public void setInfFileName(String value) {
		this.infFileName = value;
	}	
	public String getInfFileName() {
		return this.infFileName;
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

	/** 
	 * @return bussAccount 
	 */
	public String getBussAccount() {
		return bussAccount;
	}

	/** 
	 * @param bussAccount 要设置的 bussAccount 
	 */
	public void setBussAccount(String bussAccount) {
		this.bussAccount = bussAccount;
	}
	
	/** 
	 * @return generateTime 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 
	 * @param generateTime 要设置的 generateTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 
	 * @return bussId 
	 */
	public java.lang.String getBussId() {
		return bussId;
	}

	/** 
	 * @param bussId 要设置的 bussId 
	 */
	public void setBussId(java.lang.String bussId) {
		this.bussId = bussId;
	}

	public String toString() {
		return "InfFileLogVo [infLogId=" + infLogId + ", serialNum="
				+ serialNum + ", operId=" + operId + ", infCode=" + infCode
				+ ", infName=" + infName + ", infFileName=" + infFileName
				+ ", status=" + status + ", creator=" + creator + ", remark="
				+ remark + ", bussAccount=" + bussAccount + ", createTime="
				+ createTime + ", bussId=" + bussId + "]";
	}
	
	
}
