package com.tydic.dbs.order.vo;

import com.tydic.commons.utils.BaseVO;

import java.io.Serializable;

/**
 * Created by long on 2016/8/4.
 */
public class OrdPrdAndData extends BaseVO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5322915431749959372L;
	private java.lang.String prdId;
    private java.lang.String bussId;
    private java.lang.String prdName;
    private java.lang.String prdDes;
    private java.lang.String prdDatafile;
    private java.lang.String prdOthers;
    private java.lang.String prdStatus;
    private java.lang.String prdUnit;
    private java.math.BigDecimal prdPrice;
    private java.lang.String checker;
    private java.util.Date checkTime;
    private java.lang.String checkOpin;
    private String prdDataName;
    private String DataResourceId;
    private String dataResourceName;
    private String fileName;
    private String columnName;
    private String columnType;
    private String columnDesc;
    
    public OrdPrdAndData(){

    }
    
    public java.lang.String getPrdId() {
		return prdId;
	}

	public void setPrdId(java.lang.String prdId) {
		this.prdId = prdId;
	}

	public java.lang.String getBussId() {
		return bussId;
	}

	public void setBussId(java.lang.String bussId) {
		this.bussId = bussId;
	}

	public java.lang.String getPrdName() {
		return prdName;
	}

	public void setPrdName(java.lang.String prdName) {
		this.prdName = prdName;
	}

	public java.lang.String getPrdDes() {
		return prdDes;
	}

	public void setPrdDes(java.lang.String prdDes) {
		this.prdDes = prdDes;
	}

	public java.lang.String getPrdDatafile() {
		return prdDatafile;
	}

	public void setPrdDatafile(java.lang.String prdDatafile) {
		this.prdDatafile = prdDatafile;
	}

	public java.lang.String getPrdOthers() {
		return prdOthers;
	}

	public void setPrdOthers(java.lang.String prdOthers) {
		this.prdOthers = prdOthers;
	}

	public java.lang.String getPrdStatus() {
		return prdStatus;
	}

	public void setPrdStatus(java.lang.String prdStatus) {
		this.prdStatus = prdStatus;
	}

	public java.lang.String getPrdUnit() {
		return prdUnit;
	}

	public void setPrdUnit(java.lang.String prdUnit) {
		this.prdUnit = prdUnit;
	}

	public java.math.BigDecimal getPrdPrice() {
		return prdPrice;
	}

	public void setPrdPrice(java.math.BigDecimal prdPrice) {
		this.prdPrice = prdPrice;
	}

	public java.lang.String getChecker() {
		return checker;
	}

	public void setChecker(java.lang.String checker) {
		this.checker = checker;
	}

	public java.util.Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(java.util.Date checkTime) {
		this.checkTime = checkTime;
	}

	public java.lang.String getCheckOpin() {
		return checkOpin;
	}

	public void setCheckOpin(java.lang.String checkOpin) {
		this.checkOpin = checkOpin;
	}

	public String getPrdDataName() {
		return prdDataName;
	}

	public void setPrdDataName(String prdDataName) {
		this.prdDataName = prdDataName;
	}

	public String getDataResourceId() {
		return DataResourceId;
	}

	public void setDataResourceId(String dataResourceId) {
		DataResourceId = dataResourceId;
	}

	public String getDataResourceName() {
		return dataResourceName;
	}

	public void setDataResourceName(String dataResourceName) {
		this.dataResourceName = dataResourceName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnDesc() {
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}


}
