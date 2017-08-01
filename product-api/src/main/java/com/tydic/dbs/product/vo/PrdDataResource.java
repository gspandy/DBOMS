/**
 * com.tydic.dbs.mapper.PrdDataResource.java
 */
package com.tydic.dbs.product.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: PrdDataResource 
 * @Description: TODO(产品数据服务信息实体) 
 * @author huangChuQin
 * @date 2016-8-3 下午8:06:24 
 *
 */
public class PrdDataResource extends BaseVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private java.lang.String dataResourceId;
	private java.lang.String prdId;
	private java.lang.String dataResourceName;
	private java.lang.String remark;
	public PrdDataResource(){
	}

	public PrdDataResource(
		java.lang.String dataResourceId
	){
		this.dataResourceId = dataResourceId;
	}



	public void setDataResourceId(java.lang.String value) {
		this.dataResourceId = value;
	}	
	public java.lang.String getDataResourceId() {
		return this.dataResourceId;
	}

	public void setPrdId(java.lang.String value) {
		this.prdId = value;
	}	
	public java.lang.String getPrdId() {
		return this.prdId;
	}

	public void setDataResourceName(java.lang.String value) {
		this.dataResourceName = value;
	}	
	public java.lang.String getDataResourceName() {
		return this.dataResourceName;
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
			   buffer.append("DataResourceId").append("='").append(getDataResourceId()).append("' ");	
			   buffer.append("PrdId").append("='").append(getPrdId()).append("' ");	
			   buffer.append("DataResourceName").append("='").append(getDataResourceName()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrdDataResource) ) return false;
		 PrdDataResource  castOther = ( PrdDataResource) other;  
		 return 
					( (this.getDataResourceId()==castOther.getDataResourceId()) ||( this.getDataResourceId()!=null && castOther.getDataResourceId()!=null && this.getDataResourceId().equals(castOther.getDataResourceId()) ) )
					&&( (this.getPrdId()==castOther.getPrdId()) ||( this.getPrdId()!=null && castOther.getPrdId()!=null && this.getPrdId().equals(castOther.getPrdId()) ) )
					&&( (this.getDataResourceName()==castOther.getDataResourceName()) ||( this.getDataResourceName()!=null && castOther.getDataResourceName()!=null && this.getDataResourceName().equals(castOther.getDataResourceName()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getDataResourceId() == null ? 0 : this.getDataResourceId().hashCode() );
				  result = 37 * result + (getPrdId() == null ? 0 : this.getPrdId().hashCode() );
				  result = 37 * result + (getDataResourceName() == null ? 0 : this.getDataResourceName().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}