/**
 * com.sunrise.ord.mapper.OrdPrd.java
 */
package com.tydic.dbs.order.vo;

import com.tydic.commons.utils.BaseVO;



/**
 * 
 * @ClassName: OrdPrd 
 * @Description: TODO(实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:29:10 
 *
 */
public class OrdPrd extends BaseVO implements java.io.Serializable{

	private java.lang.String ordPrdId;
	private java.lang.String ordId;
	private java.lang.String prdId;
	private java.lang.Integer startRow;
	private java.lang.Integer endRow;
	private java.util.Date ordDatebegin;
	private java.util.Date ordDateend;
	private java.lang.String resoureId;
	private String bussId;
	private String operType;

	public void setBussId(String bussId) {
		this.bussId = bussId;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getBussId() {
		return bussId;
	}

	public String getOperType() {
		return operType;
	}

	public OrdPrd(){
	}

	public OrdPrd(
		java.lang.String ordPrdId
	){
		this.ordPrdId = ordPrdId;
	}



	public void setOrdPrdId(java.lang.String value) {
		this.ordPrdId = value;
	}	
	public java.lang.String getOrdPrdId() {
		return this.ordPrdId;
	}

	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}	
	public java.lang.String getOrdId() {
		return this.ordId;
	}

	public void setPrdId(java.lang.String value) {
		this.prdId = value;
	}	
	public java.lang.String getPrdId() {
		return this.prdId;
	}

	public void setStartRow(java.lang.Integer value) {
		this.startRow = value;
	}	
	public java.lang.Integer getStartRow() {
		return this.startRow;
	}

	public void setEndRow(java.lang.Integer value) {
		this.endRow = value;
	}	
	public java.lang.Integer getEndRow() {
		return this.endRow;
	}

	public void setOrdDatebegin(java.util.Date value) {
		this.ordDatebegin = value;
	}	
	public java.util.Date getOrdDatebegin() {
		return this.ordDatebegin;
	}

	public void setOrdDateend(java.util.Date value) {
		this.ordDateend = value;
	}	
	public java.util.Date getOrdDateend() {
		return this.ordDateend;
	}

	public void setResoureId(java.lang.String value) {
		this.resoureId = value;
	}	
	public java.lang.String getResoureId() {
		return this.resoureId;
	}


    public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("OrdPrdId").append("='").append(getOrdPrdId()).append("' ");	
			   buffer.append("OrdId").append("='").append(getOrdId()).append("' ");	
			   buffer.append("PrdId").append("='").append(getPrdId()).append("' ");	
			   buffer.append("StartRow").append("='").append(getStartRow()).append("' ");	
			   buffer.append("EndRow").append("='").append(getEndRow()).append("' ");	
			   buffer.append("OrdDatebegin").append("='").append(getOrdDatebegin()).append("' ");	
			   buffer.append("OrdDateend").append("='").append(getOrdDateend()).append("' ");	
			   buffer.append("ResoureId").append("='").append(getResoureId()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrdPrd) ) return false;
		 OrdPrd  castOther = ( OrdPrd) other;  
		 return 
					( (this.getOrdPrdId()==castOther.getOrdPrdId()) ||( this.getOrdPrdId()!=null && castOther.getOrdPrdId()!=null && this.getOrdPrdId().equals(castOther.getOrdPrdId()) ) )
					&&( (this.getOrdId()==castOther.getOrdId()) ||( this.getOrdId()!=null && castOther.getOrdId()!=null && this.getOrdId().equals(castOther.getOrdId()) ) )
					&&( (this.getPrdId()==castOther.getPrdId()) ||( this.getPrdId()!=null && castOther.getPrdId()!=null && this.getPrdId().equals(castOther.getPrdId()) ) )
					&&( (this.getStartRow()==castOther.getStartRow()) ||( this.getStartRow()!=null && castOther.getStartRow()!=null && this.getStartRow().equals(castOther.getStartRow()) ) )
					&&( (this.getEndRow()==castOther.getEndRow()) ||( this.getEndRow()!=null && castOther.getEndRow()!=null && this.getEndRow().equals(castOther.getEndRow()) ) )
					&&( (this.getOrdDatebegin()==castOther.getOrdDatebegin()) ||( this.getOrdDatebegin()!=null && castOther.getOrdDatebegin()!=null && this.getOrdDatebegin().equals(castOther.getOrdDatebegin()) ) )
					&&( (this.getOrdDateend()==castOther.getOrdDateend()) ||( this.getOrdDateend()!=null && castOther.getOrdDateend()!=null && this.getOrdDateend().equals(castOther.getOrdDateend()) ) )
					&&( (this.getResoureId()==castOther.getResoureId()) ||( this.getResoureId()!=null && castOther.getResoureId()!=null && this.getResoureId().equals(castOther.getResoureId()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getOrdPrdId() == null ? 0 : this.getOrdPrdId().hashCode() );
				  result = 37 * result + (getOrdId() == null ? 0 : this.getOrdId().hashCode() );
				  result = 37 * result + (getPrdId() == null ? 0 : this.getPrdId().hashCode() );
				  result = 37 * result + (getStartRow() == null ? 0 : this.getStartRow().hashCode() );
				  result = 37 * result + (getEndRow() == null ? 0 : this.getEndRow().hashCode() );
				  result = 37 * result + (getOrdDatebegin() == null ? 0 : this.getOrdDatebegin().hashCode() );
				  result = 37 * result + (getOrdDateend() == null ? 0 : this.getOrdDateend().hashCode() );
				  result = 37 * result + (getResoureId() == null ? 0 : this.getResoureId().hashCode() );
         return result;
	} 
	
}
