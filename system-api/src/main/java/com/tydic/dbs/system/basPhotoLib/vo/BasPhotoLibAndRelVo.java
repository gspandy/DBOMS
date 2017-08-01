package com.tydic.dbs.system.basPhotoLib.vo;


import com.tydic.dbs.system.basPhotoLib.mapper.BasPhotoLib;

public class BasPhotoLibAndRelVo extends BasPhotoLib {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 905309479173892827L;

	private Long relationId;
	
	private Integer markType;
	
	private Long markId;
	
	private String isDefault;
	
	private String showFlag;

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public Integer getMarkType() {
		return markType;
	}

	public void setMarkType(Integer markType) {
		this.markType = markType;
	}

	public Long getMarkId() {
		return markId;
	}

	public void setMarkId(Long markId) {
		this.markId = markId;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the showFlag
	 */
	public String getShowFlag() {
		return showFlag;
	}

	/**
	 * @param showFlag the showFlag to set
	 */
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

}
