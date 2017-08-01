/**
 * com.sunrise.wcs.mapper.AppInfo.java
 */
package com.tydic.dbs.buyer.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  AppInfo.java
 * @author ZJL
 * @version 0.1
 * @AppInfo实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-09-29 02:59:54
 *      	Author: ZJL
 *      	Modification: this file was created
 *   	2. ...
 */
public class AppInfo extends BaseVO implements java.io.Serializable{

	private java.lang.String appId;
	private java.lang.String bussId;
	private java.lang.String tenantName;
	private java.lang.String bussName;
	private java.lang.String appName;
	private java.lang.String appurl;
	private java.lang.String appDes;
	private java.lang.String appImg;
	public AppInfo(){
	}

	public AppInfo(
		java.lang.String appId
	){
		this.appId = appId;
	}



	public void setAppId(java.lang.String value) {
		this.appId = value;
	}	
	public java.lang.String getAppId() {
		return this.appId;
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

	public void setBussName(java.lang.String value) {
		this.bussName = value;
	}	
	public java.lang.String getBussName() {
		return this.bussName;
	}

	public void setAppName(java.lang.String value) {
		this.appName = value;
	}	
	public java.lang.String getAppName() {
		return this.appName;
	}

	public void setAppurl(java.lang.String value) {
		this.appurl = value;
	}	
	public java.lang.String getAppurl() {
		return this.appurl;
	}

	public void setAppDes(java.lang.String value) {
		this.appDes = value;
	}	
	public java.lang.String getAppDes() {
		return this.appDes;
	}








    public java.lang.String getAppImg() {
		return appImg;
	}

	public void setAppImg(java.lang.String appImg) {
		this.appImg = appImg;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("AppId").append("='").append(getAppId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("TenantName").append("='").append(getTenantName()).append("' ");	
			   buffer.append("BussName").append("='").append(getBussName()).append("' ");	
			   buffer.append("AppName").append("='").append(getAppName()).append("' ");	
			   buffer.append("Appurl").append("='").append(getAppurl()).append("' ");	
			   buffer.append("AppDes").append("='").append(getAppDes()).append("' ");	
			   buffer.append("AppImg").append("='").append(getAppImg()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Modifier").append("='").append(getModifier()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AppInfo) ) return false;
		 AppInfo  castOther = ( AppInfo) other;  
		 return 
					( (this.getAppId()==castOther.getAppId()) ||( this.getAppId()!=null && castOther.getAppId()!=null && this.getAppId().equals(castOther.getAppId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getTenantName()==castOther.getTenantName()) ||( this.getTenantName()!=null && castOther.getTenantName()!=null && this.getTenantName().equals(castOther.getTenantName()) ) )
					&&( (this.getBussName()==castOther.getBussName()) ||( this.getBussName()!=null && castOther.getBussName()!=null && this.getBussName().equals(castOther.getBussName()) ) )
					&&( (this.getAppName()==castOther.getAppName()) ||( this.getAppName()!=null && castOther.getAppName()!=null && this.getAppName().equals(castOther.getAppName()) ) )
					&&( (this.getAppurl()==castOther.getAppurl()) ||( this.getAppurl()!=null && castOther.getAppurl()!=null && this.getAppurl().equals(castOther.getAppurl()) ) )
					&&( (this.getAppDes()==castOther.getAppDes()) ||( this.getAppDes()!=null && castOther.getAppDes()!=null && this.getAppDes().equals(castOther.getAppDes()) ) )
					&&( (this.getAppImg()==castOther.getAppImg()) ||( this.getAppImg()!=null && castOther.getAppImg()!=null && this.getAppImg().equals(castOther.getAppImg()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getModifier()==castOther.getModifier()) ||( this.getModifier()!=null && castOther.getModifier()!=null && this.getModifier().equals(castOther.getModifier()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getAppId() == null ? 0 : this.getAppId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getTenantName() == null ? 0 : this.getTenantName().hashCode() );
				  result = 37 * result + (getBussName() == null ? 0 : this.getBussName().hashCode() );
				  result = 37 * result + (getAppName() == null ? 0 : this.getAppName().hashCode() );
				  result = 37 * result + (getAppurl() == null ? 0 : this.getAppurl().hashCode() );
				  result = 37 * result + (getAppDes() == null ? 0 : this.getAppDes().hashCode() );
				  result = 37 * result + (getAppImg() == null ? 0 : this.getAppImg().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getModifier() == null ? 0 : this.getModifier().hashCode() );
         return result;
	} 
	
}
