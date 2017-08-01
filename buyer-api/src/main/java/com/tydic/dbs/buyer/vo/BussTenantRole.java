/**
 * com.sunrise.w55.mapper.BussTenantRole.java
 */
package com.tydic.dbs.buyer.vo;


import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: BussTenantRole 
 * @Description: TODO(商户租户角色) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-29 上午11:03:59 
 *
 */
public class BussTenantRole extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 7612158008630042517L;
	private java.lang.String tenantId;
	private java.lang.String roleId;
	private java.lang.String url;
	private java.lang.String loginId;
	private java.lang.String remark;

	public BussTenantRole(){
	}

	public BussTenantRole(
		java.lang.String tenantId,
		java.lang.String roleId
	){
		this.tenantId = tenantId;
		this.roleId = roleId;
	}



	public void setTenantId(java.lang.String value) {
		this.tenantId = value;
	}	
	public java.lang.String getTenantId() {
		return this.tenantId;
	}

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}	
	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
	}	
	public java.lang.String getUrl() {
		return this.url;
	}

	public void setLoginId(java.lang.String value) {
		this.loginId = value;
	}	
	public java.lang.String getLoginId() {
		return this.loginId;
	}


	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("TenantId").append("='").append(getTenantId()).append("' ");	
			   buffer.append("RoleId").append("='").append(getRoleId()).append("' ");	
			   buffer.append("Url").append("='").append(getUrl()).append("' ");	
			   buffer.append("LoginId").append("='").append(getLoginId()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BussTenantRole) ) return false;
		 BussTenantRole  castOther = ( BussTenantRole) other;  
		 return 
					( (this.getTenantId()==castOther.getTenantId()) ||( this.getTenantId()!=null && castOther.getTenantId()!=null && this.getTenantId().equals(castOther.getTenantId()) ) )
					&&( (this.getUrl()==castOther.getUrl()) ||( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
					&&( (this.getLoginId()==castOther.getLoginId()) ||( this.getLoginId()!=null && castOther.getLoginId()!=null && this.getLoginId().equals(castOther.getLoginId()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getTenantId() == null ? 0 : this.getTenantId().hashCode() );
				  result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode() );
				  result = 37 * result + (getUrl() == null ? 0 : this.getUrl().hashCode() );
				  result = 37 * result + (getLoginId() == null ? 0 : this.getLoginId().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
