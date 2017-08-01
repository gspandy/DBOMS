/**
 * com.sunrise.accout.mapper.ConsumList.java
 */
package com.tydic.dbs.billing.vo;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: ConsumList 
 * @Description: TODO(消费清单实体) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-29 上午11:27:41 
 *
 */
public class ConsumList extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -5263087709531622786L;
	private java.lang.String consumId;
	private java.lang.String bussId;
	private java.lang.String workNo;
	private Long rowNum;
	private Double dataSize;
	private java.util.Date consumTime;
	private java.lang.String billingId;
	private java.lang.String remark;
	public ConsumList(){
	}

	public ConsumList(
		java.lang.String consumId
	){
		this.consumId = consumId;
	}



	public void setConsumId(java.lang.String value) {
		this.consumId = value;
	}	
	public java.lang.String getConsumId() {
		return this.consumId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setWorkNo(java.lang.String value) {
		this.workNo = value;
	}	
	public java.lang.String getWorkNo() {
		return this.workNo;
	}

	public void setRowNum(Long value) {
		this.rowNum = value;
	}	
	public Long getRowNum() {
		return this.rowNum;
	}

	public void setDataSize(Double value) {
		this.dataSize = value;
	}	
	public Double getDataSize() {
		return this.dataSize;
	}

	public void setConsumTime(java.util.Date value) {
		this.consumTime = value;
	}	
	public java.util.Date getConsumTime() {
		return this.consumTime;
	}

	public void setBillingId(java.lang.String value) {
		this.billingId = value;
	}	
	public java.lang.String getBillingId() {
		return this.billingId;
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
			   buffer.append("ConsumId").append("='").append(getConsumId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("WorkNo").append("='").append(getWorkNo()).append("' ");	
			   buffer.append("RowNum").append("='").append(getRowNum()).append("' ");	
			   buffer.append("DataSize").append("='").append(getDataSize()).append("' ");	
			   buffer.append("ConsumTime").append("='").append(getConsumTime()).append("' ");	
			   buffer.append("BillingId").append("='").append(getBillingId()).append("' ");	
			   buffer.append("Remark").append("='").append(getRemark()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ConsumList) ) return false;
		 ConsumList  castOther = ( ConsumList) other;  
		 return 
					( (this.getConsumId()==castOther.getConsumId()) ||( this.getConsumId()!=null && castOther.getConsumId()!=null && this.getConsumId().equals(castOther.getConsumId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getWorkNo()==castOther.getWorkNo()) ||( this.getWorkNo()!=null && castOther.getWorkNo()!=null && this.getWorkNo().equals(castOther.getWorkNo()) ) )
					&&( (this.getRowNum()==castOther.getRowNum()) ||( this.getRowNum()!=null && castOther.getRowNum()!=null && this.getRowNum().equals(castOther.getRowNum()) ) )
					&&( (this.getDataSize()==castOther.getDataSize()) ||( this.getDataSize()!=null && castOther.getDataSize()!=null && this.getDataSize().equals(castOther.getDataSize()) ) )
					&&( (this.getConsumTime()==castOther.getConsumTime()) ||( this.getConsumTime()!=null && castOther.getConsumTime()!=null && this.getConsumTime().equals(castOther.getConsumTime()) ) )
					&&( (this.getBillingId()==castOther.getBillingId()) ||( this.getBillingId()!=null && castOther.getBillingId()!=null && this.getBillingId().equals(castOther.getBillingId()) ) )
					&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getConsumId() == null ? 0 : this.getConsumId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getWorkNo() == null ? 0 : this.getWorkNo().hashCode() );
				  result = 37 * result + (getRowNum() == null ? 0 : this.getRowNum().hashCode() );
				  result = 37 * result + (getDataSize() == null ? 0 : this.getDataSize().hashCode() );
				  result = 37 * result + (getConsumTime() == null ? 0 : this.getConsumTime().hashCode() );
				  result = 37 * result + (getBillingId() == null ? 0 : this.getBillingId().hashCode() );
				  result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
	} 
	
}
