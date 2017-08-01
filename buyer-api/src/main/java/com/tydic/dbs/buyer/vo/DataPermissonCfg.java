/**
 * com.tydic.dbs.mapper.DataPermissonCfg.java
 */
package com.tydic.dbs.buyer.vo;

import java.util.List;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  DataPermissonCfg.java
 * @author Carson
 * @version 0.1
 * @DataPermissonCfg实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-10-19 11:05:34
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class DataPermissonCfg extends BaseVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String parentId;
	private String type;
	private String columns;
	
	private String childFlag;
	
	private List<DataPermissonCfg> childList;
	
	public DataPermissonCfg(){
	}

	public DataPermissonCfg(
		Long id
	){
		this.id = id;
	}



	public void setId(Long value) {
		this.id = value;
	}	
	public Long getId() {
		return this.id;
	}

	public void setName(String value) {
		this.name = value;
	}	
	public String getName() {
		return this.name;
	}

	public void setParentId(String value) {
		this.parentId = value;
	}	
	public String getParentId() {
		return this.parentId;
	}

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}
	
	

	public String getChildFlag() {
		return childFlag;
	}

	public void setChildFlag(String childFlag) {
		this.childFlag = childFlag;
	}

	public List<DataPermissonCfg> getChildList() {
		return childList;
	}

	public void setChildList(List<DataPermissonCfg> childList) {
		this.childList = childList;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("Id").append("='").append(getId()).append("' ");	
			   buffer.append("Name").append("='").append(getName()).append("' ");	
			   buffer.append("ParentId").append("='").append(getParentId()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DataPermissonCfg) ) return false;
		 DataPermissonCfg  castOther = ( DataPermissonCfg) other;  
		 return 
					( (this.getId()==castOther.getId()) ||( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
					&&( (this.getName()==castOther.getName()) ||( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
					&&( (this.getParentId()==castOther.getParentId()) ||( this.getParentId()!=null && castOther.getParentId()!=null && this.getParentId().equals(castOther.getParentId()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getId() == null ? 0 : this.getId().hashCode() );
				  result = 37 * result + (getName() == null ? 0 : this.getName().hashCode() );
				  result = 37 * result + (getParentId() == null ? 0 : this.getParentId().hashCode() );
         return result;
	} 
	
}
