package com.tydic.dbs.system.menu.mapper;

import java.util.Date;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: SysRoleMenu 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:51:50 
 *
 */
public class SysRoleMenu extends BaseVO implements java.io.Serializable{

	    private Long roleMenuRelId;

	    private String roleCode;

	    private String menuCode;

	    private String creater;

	    private Date createTime;

	    private String remark;
	    
	    public String getCreater() {
			return creater;
		}

		public void setCreater(String creater) {
			this.creater = creater;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

	public SysRoleMenu(){
	}

	public SysRoleMenu(
		java.lang.Long roleMenuRelId
	){
		this.roleMenuRelId = roleMenuRelId;
	}



	public void setRoleMenuRelId(java.lang.Long value) {
		this.roleMenuRelId = value;
	}	
	public java.lang.Long getRoleMenuRelId() {
		return this.roleMenuRelId;
	}

	public void setRoleCode(java.lang.String value) {
		this.roleCode = value;
	}	
	public java.lang.String getRoleCode() {
		return this.roleCode;
	}

	public void setMenuCode(java.lang.String value) {
		this.menuCode = value;
	}	
	public java.lang.String getMenuCode() {
		return this.menuCode;
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
			   buffer.append("RoleMenuRelId").append("='").append(getRoleMenuRelId()).append("' ");	
			   buffer.append("RoleCode").append("='").append(getRoleCode()).append("' ");	
			   buffer.append("MenuCode").append("='").append(getMenuCode()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysRoleMenu) ) return false;
		 SysRoleMenu  castOther = ( SysRoleMenu) other;  
		 return 
					( (this.getRoleMenuRelId()==castOther.getRoleMenuRelId()) ||( this.getRoleMenuRelId()!=null && castOther.getRoleMenuRelId()!=null && this.getRoleMenuRelId().equals(castOther.getRoleMenuRelId()) ) )
					&&( (this.getRoleCode()==castOther.getRoleCode()) ||( this.getRoleCode()!=null && castOther.getRoleCode()!=null && this.getRoleCode().equals(castOther.getRoleCode()) ) )
					&&( (this.getMenuCode()==castOther.getMenuCode()) ||( this.getMenuCode()!=null && castOther.getMenuCode()!=null && this.getMenuCode().equals(castOther.getMenuCode()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getRoleMenuRelId() == null ? 0 : this.getRoleMenuRelId().hashCode() );
				  result = 37 * result + (getRoleCode() == null ? 0 : this.getRoleCode().hashCode() );
				  result = 37 * result + (getMenuCode() == null ? 0 : this.getMenuCode().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
