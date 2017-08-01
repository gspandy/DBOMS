/**
 * com.sunrise.wcs.mapper.PrdDataResourceRequire.java
 */
package com.tydic.dbs.product.vo;

import com.tydic.commons.utils.BaseVO;


/**
 * 
 * @ClassName: PrdInfo 
 * @Description: TODO(实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:31:01 
 *
 */
public class PrdDataResourceRequire extends BaseVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8684095695176896449L;
	private java.lang.String dataResourceRequireId;
	private java.lang.String prdId;
	private java.lang.String columnName;
	private java.lang.String columnType;
	private java.lang.String columnDesc;
	public PrdDataResourceRequire(){
	}

	public PrdDataResourceRequire(
		java.lang.String dataResourceRequireId
	){
		this.dataResourceRequireId = dataResourceRequireId;
	}



	public void setDataResourceRequireId(java.lang.String value) {
		this.dataResourceRequireId = value;
	}	
	public java.lang.String getDataResourceRequireId() {
		return this.dataResourceRequireId;
	}

	public void setPrdId(java.lang.String value) {
		this.prdId = value;
	}	
	public java.lang.String getPrdId() {
		return this.prdId;
	}

	public void setColumnName(java.lang.String value) {
		this.columnName = value;
	}	
	public java.lang.String getColumnName() {
		return this.columnName;
	}

	public void setColumnType(java.lang.String value) {
		this.columnType = value;
	}	
	public java.lang.String getColumnType() {
		return this.columnType;
	}

	public void setColumnDesc(java.lang.String value) {
		this.columnDesc = value;
	}	
	public java.lang.String getColumnDesc() {
		return this.columnDesc;
	}




    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("DataResourceId").append("='").append(getDataResourceRequireId()).append("' ");	
			   buffer.append("PrdId").append("='").append(getPrdId()).append("' ");	
			   buffer.append("ColumnName").append("='").append(getColumnName()).append("' ");	
			   buffer.append("ColumnType").append("='").append(getColumnType()).append("' ");	
			   buffer.append("ColumnDesc").append("='").append(getColumnDesc()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrdDataResourceRequire) ) return false;
		 PrdDataResourceRequire  castOther = ( PrdDataResourceRequire) other;  
		 return 
					( (this.getDataResourceRequireId()==castOther.getDataResourceRequireId()) ||( this.getDataResourceRequireId()!=null && castOther.getDataResourceRequireId()!=null && this.getDataResourceRequireId().equals(castOther.getDataResourceRequireId()) ) )
					&&( (this.getPrdId()==castOther.getPrdId()) ||( this.getPrdId()!=null && castOther.getPrdId()!=null && this.getPrdId().equals(castOther.getPrdId()) ) )
					&&( (this.getColumnName()==castOther.getColumnName()) ||( this.getColumnName()!=null && castOther.getColumnName()!=null && this.getColumnName().equals(castOther.getColumnName()) ) )
					&&( (this.getColumnType()==castOther.getColumnType()) ||( this.getColumnType()!=null && castOther.getColumnType()!=null && this.getColumnType().equals(castOther.getColumnType()) ) )
					&&( (this.getColumnDesc()==castOther.getColumnDesc()) ||( this.getColumnDesc()!=null && castOther.getColumnDesc()!=null && this.getColumnDesc().equals(castOther.getColumnDesc()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getDataResourceRequireId() == null ? 0 : this.getDataResourceRequireId().hashCode() );
				  result = 37 * result + (getPrdId() == null ? 0 : this.getPrdId().hashCode() );
				  result = 37 * result + (getColumnName() == null ? 0 : this.getColumnName().hashCode() );
				  result = 37 * result + (getColumnType() == null ? 0 : this.getColumnType().hashCode() );
				  result = 37 * result + (getColumnDesc() == null ? 0 : this.getColumnDesc().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
	} 
	
}
