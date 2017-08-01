/**
 * com.tydic.dbs.system.operator.mapper.SysChanOperRel.java
 */
package com.tydic.dbs.system.operator.mapper;

import com.tydic.commons.utils.BaseVO;

/**
 * @file  SysChanOperRel.java
 * @author liugaolin
 * @version 0.1
 * @SysChanOperRel实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-22 02:22:02
 *      	Author: liugaolin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SysChanOperRel extends BaseVO implements java.io.Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 605359299117161208L;
    private Long operChannelId;
	private String operId;
	private Long channelId;
	private String remark;
	private String operName;
	private String channelName;

	public SysChanOperRel(){
	}
	public SysChanOperRel(Long operChannelId){
		this.operChannelId = operChannelId;
	}
	public void setOperChannelId(Long value) {
		this.operChannelId = value;
	}	
	public Long getOperChannelId() {
		return this.operChannelId;
	}
	public void setOperId(String value) {
		this.operId = value;
	}	
	public String getOperId() {
		return this.operId;
	}
	public void setChannelId(Long value) {
		this.channelId = value;
	}	
	public Long getChannelId() {
		return this.channelId;
	}
	public void setRemark(String value) {
		this.remark = value;
	}	
	public String getRemark() {
		return this.remark;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
}
