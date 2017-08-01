/**
 * com.sunrise.accout.mapper.ChaBill.java
 */
package com.tydic.dbs.billing.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: ChaBill 
 * @Description: TODO(月账单实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:23:30 
 *
 */
public class ChaBill extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -1519083058547039594L;
	private java.lang.String billId;
	private java.lang.Integer monthId;
	private java.lang.String bussId;
	private java.math.BigDecimal billAmount;
	private java.math.BigDecimal payAmount;
	private java.util.Date billTime;
	private java.lang.String remark;
	private java.lang.String bussAccount;
	public ChaBill(){
	}

	public ChaBill(
		java.lang.String billId
	){
		this.billId = billId;
	}



	public void setBillId(java.lang.String value) {
		this.billId = value;
	}	
	public java.lang.String getBillId() {
		return this.billId;
	}

	public void setMonthId(java.lang.Integer value) {
		this.monthId = value;
	}	
	public java.lang.Integer getMonthId() {
		return this.monthId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setBillAmount(java.math.BigDecimal value) {
		this.billAmount = value;
	}	
	public java.math.BigDecimal getBillAmount() {
		return this.billAmount;
	}

	public void setPayAmount(java.math.BigDecimal value) {
		this.payAmount = value;
	}	
	public java.math.BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setBillTime(java.util.Date value) {
		this.billTime = value;
	}	
	public java.util.Date getBillTime() {
		return this.billTime;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}	
	public java.lang.String getRemark() {
		return this.remark;
	}

	public java.lang.String getBussAccount() {
		return bussAccount;
	}
	public void setBussAccount(java.lang.String bussAccount) {
		this.bussAccount = bussAccount;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("BillId").append("='").append(getBillId()).append("' ");	
			   buffer.append("MonthId").append("='").append(getMonthId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("BillAmount").append("='").append(getBillAmount()).append("' ");	
			   buffer.append("PayAmount").append("='").append(getPayAmount()).append("' ");	
			   buffer.append("BillTime").append("='").append(getBillTime()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ChaBill) ) return false;
		 ChaBill  castOther = ( ChaBill) other;  
		 return 
					( (this.getBillId()==castOther.getBillId()) ||( this.getBillId()!=null && castOther.getBillId()!=null && this.getBillId().equals(castOther.getBillId()) ) )
					&&( (this.getMonthId()==castOther.getMonthId()) ||( this.getMonthId()!=null && castOther.getMonthId()!=null && this.getMonthId().equals(castOther.getMonthId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getBillAmount()==castOther.getBillAmount()) ||( this.getBillAmount()!=null && castOther.getBillAmount()!=null && this.getBillAmount().equals(castOther.getBillAmount()) ) )
					&&( (this.getPayAmount()==castOther.getPayAmount()) ||( this.getPayAmount()!=null && castOther.getPayAmount()!=null && this.getPayAmount().equals(castOther.getPayAmount()) ) )
					&&( (this.getBillTime()==castOther.getBillTime()) ||( this.getBillTime()!=null && castOther.getBillTime()!=null && this.getBillTime().equals(castOther.getBillTime()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getBillId() == null ? 0 : this.getBillId().hashCode() );
				  result = 37 * result + (getMonthId() == null ? 0 : this.getMonthId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getBillAmount() == null ? 0 : this.getBillAmount().hashCode() );
				  result = 37 * result + (getPayAmount() == null ? 0 : this.getPayAmount().hashCode() );
				  result = 37 * result + (getBillTime() == null ? 0 : this.getBillTime().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
