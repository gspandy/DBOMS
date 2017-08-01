/*
 * com.tydic.dbs.billing.vo  2016-8-8
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.billing.vo;

import java.math.BigDecimal;
import java.util.Date;

/** 
 * @ClassName: ChaBillVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-8 下午4:19:56 
 *  
 */
public class ChaBillVo extends ChaBill {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	/***工单号***/
	private String ordId;
	/***产品ID***/
	private String prdId;
	/***产品名称***/
	private String prdName;
	/***产品计量单位***/
	private String prdUnit;
	/***产品单位价格***/
	private BigDecimal prdPrice;
	/***工单执行时间***/
	private Date ordDateBegin;
	/***数据行数***/
	private String rowNum;
	/***数据大小(单位：M)***/
	private String dataSize;
	/***数据结果计量金额***/
	private String consumAmout;
	/***数据结果计量金额***/
	private String payAmout;
	/** 
	 * @return ordId 
	 */
	public String getOrdId() {
		return ordId;
	}
	/** 
	 * @param ordId 要设置的 ordId 
	 */
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	/** 
	 * @return prdId 
	 */
	public String getPrdId() {
		return prdId;
	}
	/** 
	 * @param prdId 要设置的 prdId 
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	/** 
	 * @return prdName 
	 */
	public String getPrdName() {
		return prdName;
	}
	/** 
	 * @param prdName 要设置的 prdName 
	 */
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	/** 
	 * @return prdUnit 
	 */
	public String getPrdUnit() {
		return prdUnit;
	}
	/** 
	 * @param prdUnit 要设置的 prdUnit 
	 */
	public void setPrdUnit(String prdUnit) {
		this.prdUnit = prdUnit;
	}
	/** 
	 * @return prdPrice 
	 */
	public BigDecimal getPrdPrice() {
		return prdPrice;
	}
	/** 
	 * @param prdPrice 要设置的 prdPrice 
	 */
	public void setPrdPrice(BigDecimal prdPrice) {
		this.prdPrice = prdPrice;
	}
	/** 
	 * @return ordDateBegin 
	 */
	public Date getOrdDateBegin() {
		return ordDateBegin;
	}
	/** 
	 * @param ordDateBegin 要设置的 ordDateBegin 
	 */
	public void setOrdDateBegin(Date ordDateBegin) {
		this.ordDateBegin = ordDateBegin;
	}
	
	
	/** 
	 * @return consumAmout 
	 */
	public String getConsumAmout() {
		return consumAmout;
	}
	/** 
	 * @param consumAmout 要设置的 consumAmout 
	 */
	public void setConsumAmout(String consumAmout) {
		this.consumAmout = consumAmout;
	}
	/** 
	 * @return payAmout 
	 */
	public String getPayAmout() {
		return payAmout;
	}
	/** 
	 * @param payAmout 要设置的 payAmout 
	 */
	public void setPayAmout(String payAmout) {
		this.payAmout = payAmout;
	}
	/** 
	 * @return rowNum 
	 */
	public String getRowNum() {
		return rowNum;
	}
	/** 
	 * @param rowNum 要设置的 rowNum 
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	/** 
	 * @return dataSize 
	 */
	public String getDataSize() {
		return dataSize;
	}
	/** 
	 * @param dataSize 要设置的 dataSize 
	 */
	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}
	/* (非 Javadoc) 
	 * <p>Title: toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "ChaBillVo [ordId=" + ordId + ", prdId=" + prdId + ", prdName="
				+ prdName + ", prdUnit=" + prdUnit + ", prdPrice=" + prdPrice
				+ ", ordDateBegin=" + ordDateBegin + ", rowNum=" + rowNum
				+ ", dataSize=" + dataSize + ", consumAmout=" + consumAmout
				+ ", payAmout=" + payAmout + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
