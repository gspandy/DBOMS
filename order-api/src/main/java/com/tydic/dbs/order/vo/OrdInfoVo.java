/*
 * com.tydic.dbs.system.web.action.order.vo  2016-8-1
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.order.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.tydic.dbs.order.vo.OrdInfo;

/** 
 * @ClassName: OrdInfoVo 
 * @Description: TODO(工单) 
 * @author huangChuQin
 * @date 2016-8-1 下午5:00:38 
 *  
 */
public class OrdInfoVo extends OrdInfo {

	private static final long serialVersionUID = 1L;
	/***产品ID***/
	private String prdId;
	private String bussName;
	private Integer startRow;
	private Integer endRow;
	private String rows;
	/***工单产品ID***/
	private String ordPrdId;
	/***产品名称***/
	private String prdName;
	/***产品计量单位***/
	private String prdUnit;
	/***产品单位价格***/
	private BigDecimal prdPrice;
	/***数据服务ID***/
	private String dataResourceId;
	/***数据服务名称***/
	private String dataResourceName;
	/***数据结果ID***/
	private String ordRusultId;
	/***数据结果计量结果***/
	private String resultCount;
	/***数据结果计量类型***/
	private String resultUnit;
	/***数据结果返回时间***/
	private Date resultTime;
	/***数据结果文件id***/
	private String fileId;
	/***数据结果返回名称***/
	private String fileName;
	/***数据结果返回路径***/
	private String filePath;
	/***数据时段开始时间***/
	private Date ordDateBegin;
	/***数据时段结束时间***/
	private Date ordDateEnd;
	/***工单执行时间***/
	private Date consumTime;
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
	
	public String getBussName() {
		return bussName;
	}
	public void setBussName(String bussName) {
		this.bussName = bussName;
	}
	/** 
	 * @return startRow 
	 */
	public Integer getStartRow() {
		return startRow;
	}
	/** 
	 * @param startRow 要设置的 startRow 
	 */
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
		setRows(null);
	}
	/** 
	 * @return endRow 
	 */
	public Integer getEndRow() {
		return endRow;
	}
	/** 
	 * @param endRow 要设置的 endRow 
	 */
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
		setRows(null);
	}
	/** 
	 * @return rows 
	 */
	public String getRows() {
		return rows;
	}
	/** 
	 * @param rows 要设置的 rows 
	 */
	public void setRows(String rows) {
		this.rows = startRow+"至"+endRow+"条";
	}
	/** 
	 * @return ordPrdId 
	 */
	public String getOrdPrdId() {
		return ordPrdId;
	}
	/** 
	 * @param ordPrdId 要设置的 ordPrdId 
	 */
	public void setOrdPrdId(String ordPrdId) {
		this.ordPrdId = ordPrdId;
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
	 * @return resultCount 
	 */
	public String getResultCount() {
		return resultCount;
	}
	/** 
	 * @param resultCount 要设置的 resultCount 
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}	
	/** 
	 * @return resultTime 
	 */
	public Date getResultTime() {
		return resultTime;
	}
	/** 
	 * @param resultTime 要设置的 resultTime 
	 */
	public void setResultTime(Date resultTime) {
		this.resultTime = resultTime;
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
	 * @return ordDateEnd 
	 */
	public Date getOrdDateEnd() {
		return ordDateEnd;
	}
	public Date getConsumTime() {
		return consumTime;
	}
	public void setConsumTime(Date consumTime) {
		this.consumTime = consumTime;
	}
	/** 
	 * @param ordDateEnd 要设置的 ordDateEnd 
	 */
	public void setOrdDateEnd(Date ordDateEnd) {
		this.ordDateEnd = ordDateEnd;
	}
	/** 
	 * @return dataResourceId 
	 */
	public String getDataResourceId() {
		return dataResourceId;
	}
	/** 
	 * @param dataResourceId 要设置的 dataResourceId 
	 */
	public void setDataResourceId(String dataResourceId) {
		this.dataResourceId = dataResourceId;
	}
	/** 
	 * @return dataResourceName 
	 */
	public String getDataResourceName() {
		return dataResourceName;
	}
	/** 
	 * @param dataResourceName 要设置的 dataResourceName 
	 */
	public void setDataResourceName(String dataResourceName) {
		this.dataResourceName = dataResourceName;
	}
	/** 
	 * @return ordRusultId 
	 */
	public String getOrdRusultId() {
		return ordRusultId;
	}
	/** 
	 * @param ordRusultId 要设置的 ordRusultId 
	 */
	public void setOrdRusultId(String ordRusultId) {
		this.ordRusultId = ordRusultId;
	}
	/** 
	 * @return resultUnit 
	 */
	public String getResultUnit() {
		return resultUnit;
	}
	/** 
	 * @param resultUnit 要设置的 resultUnit 
	 */
	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}
	/** 
	 * @return fileName 
	 */
	public String getFileName() {
		return fileName;
	}
	/** 
	 * @param fileName 要设置的 fileName 
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/** 
	 * @return filePath 
	 */
	public String getFilePath() {
		return filePath;
	}
	/** 
	 * @param filePath 要设置的 filePath 
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/** 
	 * @return fileId 
	 */
	public String getFileId() {
		return fileId;
	}
	/** 
	 * @param fileId 要设置的 fileId 
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/* (非 Javadoc) 
	 * <p>Title: toString</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "OrdInfoVo [prdId=" + prdId + ", bussName=" + bussName
				+ ", startRow=" + startRow + ", endRow=" + endRow + ", rows="
				+ rows + ", ordPrdId=" + ordPrdId + ", prdName=" + prdName
				+ ", prdUnit=" + prdUnit + ", prdPrice=" + prdPrice
				+ ", dataResourceId=" + dataResourceId + ", dataResourceName="
				+ dataResourceName + ", ordRusultId=" + ordRusultId
				+ ", resultCount=" + resultCount + ", resultUnit=" + resultUnit
				+ ", resultTime=" + resultTime + ", fileId=" + fileId
				+ ", fileName=" + fileName + ", filePath=" + filePath
				+ ", ordDateBegin=" + ordDateBegin + ", ordDateEnd="
				+ ordDateEnd + ", consumTime=" + consumTime + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
