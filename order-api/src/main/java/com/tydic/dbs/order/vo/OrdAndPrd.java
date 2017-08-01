package com.tydic.dbs.order.vo;

import com.tydic.commons.utils.BaseVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by long on 2016/8/4.
 */
public class OrdAndPrd extends BaseVO implements Serializable {


	private java.lang.String ordPrdId;
	private java.lang.String ordId;
	private java.lang.String prdId;
	private java.lang.Integer startRow;
	private java.lang.Integer endRow;
	private java.util.Date ordDatebegin;
	private java.util.Date ordDateend;
	private java.lang.String resoureId;
	private String operType;
	private String bussId;
    private String prdName;
    private String prdDes;
    private String prdDatafile;
    private String prdOthers;
    private String prdStatus;
    private String prdUnit;
    private BigDecimal prdPrice;
    private String checker;
    private Date checkTime;
	private String checkOpin;



    public OrdAndPrd(){

    }

	public String getOrdPrdId() {
		return ordPrdId;
	}

	public void setOrdPrdId(String ordPrdId) {
		this.ordPrdId = ordPrdId;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getPrdId() {
		return prdId;
	}

	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Date getOrdDatebegin() {
		return ordDatebegin;
	}

	public void setOrdDatebegin(Date ordDatebegin) {
		this.ordDatebegin = ordDatebegin;
	}

	public Date getOrdDateend() {
		return ordDateend;
	}

	public void setOrdDateend(Date ordDateend) {
		this.ordDateend = ordDateend;
	}

	public String getResoureId() {
		return resoureId;
	}

	public void setResoureId(String resoureId) {
		this.resoureId = resoureId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getBussId() {
		return bussId;
	}

	public void setBussId(String bussId) {
		this.bussId = bussId;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getPrdDes() {
		return prdDes;
	}

	public void setPrdDes(String prdDes) {
		this.prdDes = prdDes;
	}

	public String getPrdDatafile() {
		return prdDatafile;
	}

	public void setPrdDatafile(String prdDatafile) {
		this.prdDatafile = prdDatafile;
	}

	public String getPrdOthers() {
		return prdOthers;
	}

	public void setPrdOthers(String prdOthers) {
		this.prdOthers = prdOthers;
	}

	public String getPrdStatus() {
		return prdStatus;
	}

	public void setPrdStatus(String prdStatus) {
		this.prdStatus = prdStatus;
	}

	public String getPrdUnit() {
		return prdUnit;
	}

	public void setPrdUnit(String prdUnit) {
		this.prdUnit = prdUnit;
	}

	public BigDecimal getPrdPrice() {
		return prdPrice;
	}

	public void setPrdPrice(BigDecimal prdPrice) {
		this.prdPrice = prdPrice;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckOpin() {
		return checkOpin;
	}

	public void setCheckOpin(String checkOpin) {
		this.checkOpin = checkOpin;
	}
}
