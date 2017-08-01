/**
 * com.sunrise.accout.mapper.BillingList.java
 */
package com.tydic.dbs.billing.vo;

import com.tydic.commons.utils.BaseVO;

import java.math.BigDecimal;

/**
 * @file  BillingList.java
 * @author Carson
 * @version 0.1
 * @BillingList实体类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-07-08 02:04:29
 *      	Author: Carson
 *      	Modification: this file was created
 *   	2. ...
 */
public class BillVo extends BaseVO implements java.io.Serializable{

	private long billId;
	private String monthId;
	private String bussId;
	private BigDecimal consumTotal;
	private BigDecimal payTotal;

	public String getBussId() {
		return bussId;
	}

	public void setBussId(String bussId) {
		this.bussId = bussId;
	}

	public BigDecimal getConsumTotal() {
		return consumTotal;
	}

	public void setConsumTotal(BigDecimal consumTotal) {
		this.consumTotal = consumTotal;
	}

	public BigDecimal getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}
}
