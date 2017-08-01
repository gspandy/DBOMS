/**
 * com.sunrise.wcs.mapper.BussTenant.java
 */
package com.tydic.dbs.buyer.vo;


import com.tydic.commons.utils.BaseVO;

import java.util.List;

/**
 * 
 * @ClassName: BussTenant 
 * @Description: TODO(商户操作员信息表) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-29 上午11:05:21 
 *
 */
public class BussTenant extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 458296651875231842L;
	private java.lang.String tenantId;
	private java.lang.String bussId;
	private java.lang.String tenantName;
	private java.lang.String mobile;
	private java.lang.String remark;
	private String email;
	//private String status;
	private String auditStatus;
	private String auditReason;
	private  String  idCard;

	private List <BussTenantRole> roleList ;



	public BussTenant(){
	}

	public BussTenant(
		java.lang.String tenantId
	){
		this.tenantId = tenantId;
	}



	public void setTenantId(java.lang.String value) {
		this.tenantId = value;
	}	
	public java.lang.String getTenantId() {
		return this.tenantId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setTenantName(java.lang.String value) {
		this.tenantName = value;
	}	
	public java.lang.String getTenantName() {
		return this.tenantName;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}	
	public java.lang.String getMobile() {
		return this.mobile;
	}


	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public List<BussTenantRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<BussTenantRole> roleList) {
		this.roleList = roleList;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("TenantId").append("='").append(getTenantId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("TenantName").append("='").append(getTenantName()).append("' ");	
			   buffer.append("Mobile").append("='").append(getMobile()).append("' ");	

			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");
		       buffer.append("idCard").append("='").append(getIdCard()).append("' ");
		buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BussTenant) ) return false;
		 BussTenant  castOther = ( BussTenant) other;  
		 return 
					( (this.getTenantId()==castOther.getTenantId()) ||( this.getTenantId()!=null && castOther.getTenantId()!=null && this.getTenantId().equals(castOther.getTenantId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getTenantName()==castOther.getTenantName()) ||( this.getTenantName()!=null && castOther.getTenantName()!=null && this.getTenantName().equals(castOther.getTenantName()) ) )
					&&( (this.getMobile()==castOther.getMobile()) ||( this.getMobile()!=null && castOther.getMobile()!=null && this.getMobile().equals(castOther.getMobile()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
					&&( (this.getIdCard()==castOther.getIdCard()) ||( this.getIdCard()!=null && castOther.getIdCard()!=null && this.getIdCard().equals(castOther.getIdCard()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getTenantId() == null ? 0 : this.getTenantId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getTenantName() == null ? 0 : this.getTenantName().hashCode() );
				  result = 37 * result + (getMobile() == null ? 0 : this.getMobile().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
				  result = 37 * result + (getIdCard() == null ? 0 : this.getIdCard().hashCode() );
         return result;
	} 
	
}
