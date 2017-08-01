/**
 * com.sunrise.wcs.mapper.BussItResource.java
 */
package com.tydic.dbs.buyer.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: BussItResource 
 * @Description: TODO(平台IT资源信息表) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-29 上午11:01:57 
 *
 */
public class BussItResource extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -1802432783907316009L;
	private java.lang.String resoureId;
	private java.lang.String bussId;
	private java.lang.String cupType;
	private java.lang.Integer memorySize;
	private java.lang.Integer diskSize;
	private java.lang.Integer ftpSize;
	private java.lang.String ftpIp;
	private java.lang.Integer ftpPort;
	private java.lang.String ftpUser;
	private java.lang.String ftpPass;
	private java.lang.String ftpPath;
	private java.lang.String remark;
	private java.util.Date appTime;
	public BussItResource(){
	}

	public BussItResource(
		java.lang.String resoureId
	){
		this.resoureId = resoureId;
	}



	public void setResoureId(java.lang.String value) {
		this.resoureId = value;
	}	
	public java.lang.String getResoureId() {
		return this.resoureId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setCupType(java.lang.String value) {
		this.cupType = value;
	}	
	public java.lang.String getCupType() {
		return this.cupType;
	}

	public void setMemorySize(java.lang.Integer value) {
		this.memorySize = value;
	}	
	public java.lang.Integer getMemorySize() {
		return this.memorySize;
	}

	public void setDiskSize(java.lang.Integer value) {
		this.diskSize = value;
	}	
	public java.lang.Integer getDiskSize() {
		return this.diskSize;
	}

	public void setFtpSize(java.lang.Integer value) {
		this.ftpSize = value;
	}	
	public java.lang.Integer getFtpSize() {
		return this.ftpSize;
	}

	public void setFtpIp(java.lang.String value) {
		this.ftpIp = value;
	}	
	public java.lang.String getFtpIp() {
		return this.ftpIp;
	}

	public void setFtpPort(java.lang.Integer value) {
		this.ftpPort = value;
	}	
	public java.lang.Integer getFtpPort() {
		return this.ftpPort;
	}

	public void setFtpUser(java.lang.String value) {
		this.ftpUser = value;
	}	
	public java.lang.String getFtpUser() {
		return this.ftpUser;
	}

	public void setFtpPass(java.lang.String value) {
		this.ftpPass = value;
	}	
	public java.lang.String getFtpPass() {
		return this.ftpPass;
	}

	public void setFtpPath(java.lang.String value) {
		this.ftpPath = value;
	}	
	public java.lang.String getFtpPath() {
		return this.ftpPath;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setAppTime(java.util.Date value) {
		this.appTime = value;
	}	
	public java.util.Date getAppTime() {
		return this.appTime;
	}





    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("ResoureId").append("='").append(getResoureId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("CupType").append("='").append(getCupType()).append("' ");	
			   buffer.append("MemorySize").append("='").append(getMemorySize()).append("' ");	
			   buffer.append("DiskSize").append("='").append(getDiskSize()).append("' ");	
			   buffer.append("FtpSize").append("='").append(getFtpSize()).append("' ");	
			   buffer.append("FtpIp").append("='").append(getFtpIp()).append("' ");	
			   buffer.append("FtpPort").append("='").append(getFtpPort()).append("' ");	
			   buffer.append("FtpUser").append("='").append(getFtpUser()).append("' ");	
			   buffer.append("FtpPass").append("='").append(getFtpPass()).append("' ");	
			   buffer.append("FtpPath").append("='").append(getFtpPath()).append("' ");	
			   buffer.append("Status").append("='").append(getStatus()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
			   buffer.append("AppTime").append("='").append(getAppTime()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Modifier").append("='").append(getModifier()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BussItResource) ) return false;
		 BussItResource  castOther = ( BussItResource) other;  
		 return 
					( (this.getResoureId()==castOther.getResoureId()) ||( this.getResoureId()!=null && castOther.getResoureId()!=null && this.getResoureId().equals(castOther.getResoureId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getCupType()==castOther.getCupType()) ||( this.getCupType()!=null && castOther.getCupType()!=null && this.getCupType().equals(castOther.getCupType()) ) )
					&&( (this.getMemorySize()==castOther.getMemorySize()) ||( this.getMemorySize()!=null && castOther.getMemorySize()!=null && this.getMemorySize().equals(castOther.getMemorySize()) ) )
					&&( (this.getDiskSize()==castOther.getDiskSize()) ||( this.getDiskSize()!=null && castOther.getDiskSize()!=null && this.getDiskSize().equals(castOther.getDiskSize()) ) )
					&&( (this.getFtpSize()==castOther.getFtpSize()) ||( this.getFtpSize()!=null && castOther.getFtpSize()!=null && this.getFtpSize().equals(castOther.getFtpSize()) ) )
					&&( (this.getFtpIp()==castOther.getFtpIp()) ||( this.getFtpIp()!=null && castOther.getFtpIp()!=null && this.getFtpIp().equals(castOther.getFtpIp()) ) )
					&&( (this.getFtpPort()==castOther.getFtpPort()) ||( this.getFtpPort()!=null && castOther.getFtpPort()!=null && this.getFtpPort().equals(castOther.getFtpPort()) ) )
					&&( (this.getFtpUser()==castOther.getFtpUser()) ||( this.getFtpUser()!=null && castOther.getFtpUser()!=null && this.getFtpUser().equals(castOther.getFtpUser()) ) )
					&&( (this.getFtpPass()==castOther.getFtpPass()) ||( this.getFtpPass()!=null && castOther.getFtpPass()!=null && this.getFtpPass().equals(castOther.getFtpPass()) ) )
					&&( (this.getFtpPath()==castOther.getFtpPath()) ||( this.getFtpPath()!=null && castOther.getFtpPath()!=null && this.getFtpPath().equals(castOther.getFtpPath()) ) )
					&&( (this.getStatus()==castOther.getStatus()) ||( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
					&&( (this.getAppTime()==castOther.getAppTime()) ||( this.getAppTime()!=null && castOther.getAppTime()!=null && this.getAppTime().equals(castOther.getAppTime()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getModifier()==castOther.getModifier()) ||( this.getModifier()!=null && castOther.getModifier()!=null && this.getModifier().equals(castOther.getModifier()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getResoureId() == null ? 0 : this.getResoureId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getCupType() == null ? 0 : this.getCupType().hashCode() );
				  result = 37 * result + (getMemorySize() == null ? 0 : this.getMemorySize().hashCode() );
				  result = 37 * result + (getDiskSize() == null ? 0 : this.getDiskSize().hashCode() );
				  result = 37 * result + (getFtpSize() == null ? 0 : this.getFtpSize().hashCode() );
				  result = 37 * result + (getFtpIp() == null ? 0 : this.getFtpIp().hashCode() );
				  result = 37 * result + (getFtpPort() == null ? 0 : this.getFtpPort().hashCode() );
				  result = 37 * result + (getFtpUser() == null ? 0 : this.getFtpUser().hashCode() );
				  result = 37 * result + (getFtpPass() == null ? 0 : this.getFtpPass().hashCode() );
				  result = 37 * result + (getFtpPath() == null ? 0 : this.getFtpPath().hashCode() );
				  result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
				  result = 37 * result + (getAppTime() == null ? 0 : this.getAppTime().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getModifier() == null ? 0 : this.getModifier().hashCode() );
         return result;
	} 
	
}
