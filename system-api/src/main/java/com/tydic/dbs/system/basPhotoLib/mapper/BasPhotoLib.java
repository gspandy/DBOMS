/**
 * com.tydic.dbs.basicConfig.basPhotoLib.mapper.BasPhotoLib.java
 */
package com.tydic.dbs.system.basPhotoLib.mapper;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  BasPhotoLib.java
 * @author caipeimin
 * @version 0.1
 * @todo	BasPhotoLib数据值
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2014-02-22 05:56:27
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class BasPhotoLib extends BaseVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long photoId;
	private String photoName;
	private String photoDesc;
	private String photoPath;
	private String mdValue;
	public BasPhotoLib(){
	}

	public BasPhotoLib(
		Long photoId
	){
		this.photoId = photoId;
	}



	public void setPhotoId(Long value) {
		this.photoId = value;
	}
	public Long getPhotoId() {
		return this.photoId;
	}

	public void setPhotoName(String value) {
		this.photoName = value;
	}
	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoDesc(String value) {
		this.photoDesc = value;
	}
	public String getPhotoDesc() {
		return this.photoDesc;
	}

	public void setPhotoPath(String value) {
		this.photoPath = value;
	}
	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setMdValue(String value) {
		this.mdValue = value;
	}
	public String getMdValue() {
		return this.mdValue;
	}






    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("PhotoId").append("='").append(getPhotoId()).append("' ");	
			   buffer.append("PhotoName").append("='").append(getPhotoName()).append("' ");	
			   buffer.append("PhotoDesc").append("='").append(getPhotoDesc()).append("' ");	
			   buffer.append("PhotoPath").append("='").append(getPhotoPath()).append("' ");	
			   buffer.append("MdValue").append("='").append(getMdValue()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Modifier").append("='").append(getModifier()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BasPhotoLib) ) return false;
		 BasPhotoLib  castOther = ( BasPhotoLib) other;  
		 return 
					( (this.getPhotoId()==castOther.getPhotoId()) ||( this.getPhotoId()!=null && castOther.getPhotoId()!=null && this.getPhotoId().equals(castOther.getPhotoId()) ) )
					&&( (this.getPhotoName()==castOther.getPhotoName()) ||( this.getPhotoName()!=null && castOther.getPhotoName()!=null && this.getPhotoName().equals(castOther.getPhotoName()) ) )
					&&( (this.getPhotoDesc()==castOther.getPhotoDesc()) ||( this.getPhotoDesc()!=null && castOther.getPhotoDesc()!=null && this.getPhotoDesc().equals(castOther.getPhotoDesc()) ) )
					&&( (this.getPhotoPath()==castOther.getPhotoPath()) ||( this.getPhotoPath()!=null && castOther.getPhotoPath()!=null && this.getPhotoPath().equals(castOther.getPhotoPath()) ) )
					&&( (this.getMdValue()==castOther.getMdValue()) ||( this.getMdValue()!=null && castOther.getMdValue()!=null && this.getMdValue().equals(castOther.getMdValue()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getModifier()==castOther.getModifier()) ||( this.getModifier()!=null && castOther.getModifier()!=null && this.getModifier().equals(castOther.getModifier()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getPhotoId() == null ? 0 : this.getPhotoId().hashCode() );
				  result = 37 * result + (getPhotoName() == null ? 0 : this.getPhotoName().hashCode() );
				  result = 37 * result + (getPhotoDesc() == null ? 0 : this.getPhotoDesc().hashCode() );
				  result = 37 * result + (getPhotoPath() == null ? 0 : this.getPhotoPath().hashCode() );
				  result = 37 * result + (getMdValue() == null ? 0 : this.getMdValue().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getModifier() == null ? 0 : this.getModifier().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
         return result;
	} 
	
}
