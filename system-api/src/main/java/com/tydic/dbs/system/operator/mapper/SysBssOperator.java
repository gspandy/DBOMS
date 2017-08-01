/**
 * com.tydic.dbs.mapper.SysBssOperator.java
 */
package com.tydic.dbs.system.operator.mapper;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  SysBssOperator.java
 * @author yancan
 * @version 0.1
 * @SysBssOperator实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-07-21 02:12:25
 *      	Author: yancan
 *      	Modification: this file was created
 *   	2. ...
 */
public class SysBssOperator extends BaseVO implements java.io.Serializable{

	private java.lang.String openStaffCode;
	private java.lang.String staffName;
	private java.lang.String leaderStaffCode;
	private java.lang.String regionCode;
	private java.lang.String regionName;
	private java.lang.String channelCode;
	private java.lang.String sonChannel;
	private java.lang.String staffTelno;
	private java.lang.String orderOrigin;
	private java.lang.String originCode;
	public SysBssOperator(){
	}

	public SysBssOperator(
		java.lang.String openStaffCode
	){
		this.openStaffCode = openStaffCode;
	}



	public void setOpenStaffCode(java.lang.String value) {
		this.openStaffCode = value;
	}	
	public java.lang.String getOpenStaffCode() {
		return this.openStaffCode;
	}

	public void setStaffName(java.lang.String value) {
		this.staffName = value;
	}	
	public java.lang.String getStaffName() {
		return this.staffName;
	}

	public void setLeaderStaffCode(java.lang.String value) {
		this.leaderStaffCode = value;
	}	
	public java.lang.String getLeaderStaffCode() {
		return this.leaderStaffCode;
	}

	public void setRegionCode(java.lang.String value) {
		this.regionCode = value;
	}	
	public java.lang.String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionName(java.lang.String value) {
		this.regionName = value;
	}	
	public java.lang.String getRegionName() {
		return this.regionName;
	}

	public void setChannelCode(java.lang.String value) {
		this.channelCode = value;
	}	
	public java.lang.String getChannelCode() {
		return this.channelCode;
	}

	public void setSonChannel(java.lang.String value) {
		this.sonChannel = value;
	}	
	public java.lang.String getSonChannel() {
		return this.sonChannel;
	}

	public void setStaffTelno(java.lang.String value) {
		this.staffTelno = value;
	}	
	public java.lang.String getStaffTelno() {
		return this.staffTelno;
	}

	public void setOrderOrigin(java.lang.String value) {
		this.orderOrigin = value;
	}	
	public java.lang.String getOrderOrigin() {
		return this.orderOrigin;
	}

	public void setOriginCode(java.lang.String value) {
		this.originCode = value;
	}	
	public java.lang.String getOriginCode() {
		return this.originCode;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("OpenStaffCode").append("='").append(getOpenStaffCode()).append("' ");	
			   buffer.append("StaffName").append("='").append(getStaffName()).append("' ");	
			   buffer.append("LeaderStaffCode").append("='").append(getLeaderStaffCode()).append("' ");	
			   buffer.append("RegionCode").append("='").append(getRegionCode()).append("' ");	
			   buffer.append("RegionName").append("='").append(getRegionName()).append("' ");	
			   buffer.append("ChannelCode").append("='").append(getChannelCode()).append("' ");	
			   buffer.append("SonChannel").append("='").append(getSonChannel()).append("' ");	
			   buffer.append("StaffTelno").append("='").append(getStaffTelno()).append("' ");	
			   buffer.append("OrderOrigin").append("='").append(getOrderOrigin()).append("' ");	
			   buffer.append("OriginCode").append("='").append(getOriginCode()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysBssOperator) ) return false;
		 SysBssOperator  castOther = ( SysBssOperator) other;  
		 return 
					( (this.getOpenStaffCode()==castOther.getOpenStaffCode()) ||( this.getOpenStaffCode()!=null && castOther.getOpenStaffCode()!=null && this.getOpenStaffCode().equals(castOther.getOpenStaffCode()) ) )
					&&( (this.getStaffName()==castOther.getStaffName()) ||( this.getStaffName()!=null && castOther.getStaffName()!=null && this.getStaffName().equals(castOther.getStaffName()) ) )
					&&( (this.getLeaderStaffCode()==castOther.getLeaderStaffCode()) ||( this.getLeaderStaffCode()!=null && castOther.getLeaderStaffCode()!=null && this.getLeaderStaffCode().equals(castOther.getLeaderStaffCode()) ) )
					&&( (this.getRegionCode()==castOther.getRegionCode()) ||( this.getRegionCode()!=null && castOther.getRegionCode()!=null && this.getRegionCode().equals(castOther.getRegionCode()) ) )
					&&( (this.getRegionName()==castOther.getRegionName()) ||( this.getRegionName()!=null && castOther.getRegionName()!=null && this.getRegionName().equals(castOther.getRegionName()) ) )
					&&( (this.getChannelCode()==castOther.getChannelCode()) ||( this.getChannelCode()!=null && castOther.getChannelCode()!=null && this.getChannelCode().equals(castOther.getChannelCode()) ) )
					&&( (this.getSonChannel()==castOther.getSonChannel()) ||( this.getSonChannel()!=null && castOther.getSonChannel()!=null && this.getSonChannel().equals(castOther.getSonChannel()) ) )
					&&( (this.getStaffTelno()==castOther.getStaffTelno()) ||( this.getStaffTelno()!=null && castOther.getStaffTelno()!=null && this.getStaffTelno().equals(castOther.getStaffTelno()) ) )
					&&( (this.getOrderOrigin()==castOther.getOrderOrigin()) ||( this.getOrderOrigin()!=null && castOther.getOrderOrigin()!=null && this.getOrderOrigin().equals(castOther.getOrderOrigin()) ) )
					&&( (this.getOriginCode()==castOther.getOriginCode()) ||( this.getOriginCode()!=null && castOther.getOriginCode()!=null && this.getOriginCode().equals(castOther.getOriginCode()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getOpenStaffCode() == null ? 0 : this.getOpenStaffCode().hashCode() );
				  result = 37 * result + (getStaffName() == null ? 0 : this.getStaffName().hashCode() );
				  result = 37 * result + (getLeaderStaffCode() == null ? 0 : this.getLeaderStaffCode().hashCode() );
				  result = 37 * result + (getRegionCode() == null ? 0 : this.getRegionCode().hashCode() );
				  result = 37 * result + (getRegionName() == null ? 0 : this.getRegionName().hashCode() );
				  result = 37 * result + (getChannelCode() == null ? 0 : this.getChannelCode().hashCode() );
				  result = 37 * result + (getSonChannel() == null ? 0 : this.getSonChannel().hashCode() );
				  result = 37 * result + (getStaffTelno() == null ? 0 : this.getStaffTelno().hashCode() );
				  result = 37 * result + (getOrderOrigin() == null ? 0 : this.getOrderOrigin().hashCode() );
				  result = 37 * result + (getOriginCode() == null ? 0 : this.getOriginCode().hashCode() );
         return result;
	} 
	
}
