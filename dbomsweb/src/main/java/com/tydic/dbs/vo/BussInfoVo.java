/**
 * com.sunrise.qqq.mapper.BussInfo.java
 */
package com.tydic.dbs.vo;



/**
 * 
 * @ClassName: BussInfoVo 
 * @Description: TODO(商户信息VO) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-7-27 下午3:15:03 
 *
 */
public class BussInfoVo implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private java.lang.String bussId;
	private java.lang.String bussAccount;
	private java.lang.String bussPasswd;
	private java.lang.String bussType;
	private java.lang.String bussName;
	private java.lang.String bussSex;
	private java.lang.String bussCredNo;
	private java.lang.String bussMobileNo;
	private java.lang.String bussTeleNo;
	private java.lang.String orgName;
	private java.lang.String orgAddress;
	private java.lang.String bussinessNum;
	private java.lang.String bussEmail;
	private java.lang.String bussAddress;
	private java.lang.String fileId;
	private java.lang.String bussStatus;
	private java.lang.String configureStatus;
	private java.lang.String suggestion;
	private java.lang.String remark;
	/***it资源配置状态***/
	private java.lang.String itresourceStatus;
	/***操作员权限配置状态***/
	private java.lang.String leeauthStatus;
	/***数据权限配置状态***/
	private java.lang.String datapemStatus;
		
	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setBussAccount(java.lang.String value) {
		this.bussAccount = value;
	}	
	public java.lang.String getBussAccount() {
		return this.bussAccount;
	}

	public void setBussPasswd(java.lang.String value) {
		this.bussPasswd = value;
	}	
	public java.lang.String getBussPasswd() {
		return this.bussPasswd;
	}

	public void setBussType(java.lang.String value) {
		this.bussType = value;
	}	
	public java.lang.String getBussType() {
		return this.bussType;
	}

	public void setBussName(java.lang.String value) {
		this.bussName = value;
	}	
	public java.lang.String getBussName() {
		return this.bussName;
	}

	public void setBussSex(java.lang.String value) {
		this.bussSex = value;
	}	
	public java.lang.String getBussSex() {
		return this.bussSex;
	}

	public void setBussCredNo(java.lang.String value) {
		this.bussCredNo = value;
	}	
	public java.lang.String getBussCredNo() {
		return this.bussCredNo;
	}

	public void setBussMobileNo(java.lang.String value) {
		this.bussMobileNo = value;
	}	
	public java.lang.String getBussMobileNo() {
		return this.bussMobileNo;
	}

	public void setBussTeleNo(java.lang.String value) {
		this.bussTeleNo = value;
	}	
	public java.lang.String getBussTeleNo() {
		return this.bussTeleNo;
	}

	public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}	
	public java.lang.String getOrgName() {
		return this.orgName;
	}

	public void setOrgAddress(java.lang.String value) {
		this.orgAddress = value;
	}	
	public java.lang.String getOrgAddress() {
		return this.orgAddress;
	}

	public void setBussinessNum(java.lang.String value) {
		this.bussinessNum = value;
	}	
	public java.lang.String getBussinessNum() {
		return this.bussinessNum;
	}

	public void setBussEmail(java.lang.String value) {
		this.bussEmail = value;
	}	
	public java.lang.String getBussEmail() {
		return this.bussEmail;
	}

	public void setBussAddress(java.lang.String value) {
		this.bussAddress = value;
	}	
	public java.lang.String getBussAddress() {
		return this.bussAddress;
	}

	public void setFileId(java.lang.String value) {
		this.fileId = value;
	}	
	public java.lang.String getFileId() {
		return this.fileId;
	}

	public void setBussStatus(java.lang.String value) {
		this.bussStatus = value;
	}	
	public java.lang.String getBussStatus() {
		return this.bussStatus;
	}

	public void setConfigureStatus(java.lang.String value) {
		this.configureStatus = value;
	}	
	public java.lang.String getConfigureStatus() {
		return this.configureStatus;
	}

	public void setSuggestion(java.lang.String value) {
		this.suggestion = value;
	}	
	public java.lang.String getSuggestion() {
		return this.suggestion;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}

	/** 
	 * @return itresourceStatus 
	 */
	public java.lang.String getItresourceStatus() {
		return itresourceStatus;
	}

	/** 
	 * @param itresourceStatus 要设置的 itresourceStatus 
	 */
	public void setItresourceStatus(java.lang.String itresourceStatus) {
		this.itresourceStatus = itresourceStatus;
	}

	/** 
	 * @return leeauthStatus 
	 */
	public java.lang.String getLeeauthStatus() {
		return leeauthStatus;
	}

	/** 
	 * @param leeauthStatus 要设置的 leeauthStatus 
	 */
	public void setLeeauthStatus(java.lang.String leeauthStatus) {
		this.leeauthStatus = leeauthStatus;
	}

	/** 
	 * @return datapemStatus 
	 */
	public java.lang.String getDatapemStatus() {
		return datapemStatus;
	}

	/** 
	 * @param datapemStatus 要设置的 datapemStatus 
	 */
	public void setDatapemStatus(java.lang.String datapemStatus) {
		this.datapemStatus = datapemStatus;
	}

	/* (非 Javadoc) 
	 * <p>Title: toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "BussInfoVo [bussId=" + bussId + ", bussAccount=" + bussAccount
				+ ", bussPasswd=" + bussPasswd + ", bussType=" + bussType
				+ ", bussName=" + bussName + ", bussSex=" + bussSex
				+ ", bussCredNo=" + bussCredNo + ", bussMobileNo="
				+ bussMobileNo + ", bussTeleNo=" + bussTeleNo + ", orgName="
				+ orgName + ", orgAddress=" + orgAddress + ", bussinessNum="
				+ bussinessNum + ", bussEmail=" + bussEmail + ", bussAddress="
				+ bussAddress + ", fileId=" + fileId + ", bussStatus="
				+ bussStatus + ", configureStatus=" + configureStatus
				+ ", suggestion=" + suggestion + ", remark=" + remark
				+ ", itresourceStatus=" + itresourceStatus + ", leeauthStatus="
				+ leeauthStatus + ", datapemStatus=" + datapemStatus
				+ ", toString()=" + super.toString() + "]";
	}
}
