/**
 * com.sunrise.accout.mapper.BillingList.java
 */
package com.tydic.dbs.billing.vo;

import com.tydic.commons.utils.BaseVO;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: BillingList 
 * @Description: TODO(计费清单实体) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-29 上午11:29:41 
 *
 */
public class BillingList extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -2240800715948079261L;
	private java.lang.String billingId;
	private java.lang.String bussId;
	private BigDecimal consumAmount;
	private BigDecimal payAmount;
	private java.util.Date billingTime;
	private java.lang.Integer monthId;
	private java.lang.String remark;
	public BillingList(){
	}

	public BillingList(
		java.lang.String billingId
	){
		this.billingId = billingId;
	}



	public void setBillingId(java.lang.String value) {
		this.billingId = value;
	}	
	public java.lang.String getBillingId() {
		return this.billingId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setConsumAmount(BigDecimal value) {
		this.consumAmount = value;
	}

	public BigDecimal getConsumAmount() {
		return consumAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public void setBillingTime(java.util.Date value) {
		this.billingTime = value;
	}	
	public java.util.Date getBillingTime() {
		return this.billingTime;
	}

	public void setMonthId(java.lang.Integer value) {
		this.monthId = value;
	}	
	public java.lang.Integer getMonthId() {
		return this.monthId;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("BillingId").append("='").append(getBillingId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("ConsumAmount").append("='").append(getConsumAmount()).append("' ");	
			   buffer.append("PayAmount").append("='").append(getPayAmount()).append("' ");	
			   buffer.append("BillingTime").append("='").append(getBillingTime()).append("' ");	
			   buffer.append("MonthId").append("='").append(getMonthId()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BillingList) ) return false;
		 BillingList  castOther = ( BillingList) other;  
		 return 
					( (this.getBillingId()==castOther.getBillingId()) ||( this.getBillingId()!=null && castOther.getBillingId()!=null && this.getBillingId().equals(castOther.getBillingId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getConsumAmount()==castOther.getConsumAmount()) ||( this.getConsumAmount()!=null && castOther.getConsumAmount()!=null && this.getConsumAmount().equals(castOther.getConsumAmount()) ) )
					&&( (this.getPayAmount()==castOther.getPayAmount()) ||( this.getPayAmount()!=null && castOther.getPayAmount()!=null && this.getPayAmount().equals(castOther.getPayAmount()) ) )
					&&( (this.getBillingTime()==castOther.getBillingTime()) ||( this.getBillingTime()!=null && castOther.getBillingTime()!=null && this.getBillingTime().equals(castOther.getBillingTime()) ) )
					&&( (this.getMonthId()==castOther.getMonthId()) ||( this.getMonthId()!=null && castOther.getMonthId()!=null && this.getMonthId().equals(castOther.getMonthId()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getBillingId() == null ? 0 : this.getBillingId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getConsumAmount() == null ? 0 : this.getConsumAmount().hashCode() );
				  result = 37 * result + (getPayAmount() == null ? 0 : this.getPayAmount().hashCode() );
				  result = 37 * result + (getBillingTime() == null ? 0 : this.getBillingTime().hashCode() );
				  result = 37 * result + (getMonthId() == null ? 0 : this.getMonthId().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
