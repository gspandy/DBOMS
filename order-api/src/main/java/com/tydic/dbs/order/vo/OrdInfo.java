/**
 * com.sunrise.ord.mapper.OrdInfo.java
 */
package com.tydic.dbs.order.vo;


import com.tydic.commons.utils.BaseVO;



/**
 * 
 * @ClassName: OrdInfo 
 * @Description: TODO(实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:26:24 
 *
 */
 
 
public class OrdInfo extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -201143545371405174L;

	private String ordId;

    private String busId;

    private String ordStatus;

	private String remark;

	public OrdInfo(){
	}

	public OrdInfo(
		java.lang.String ordId
	){
		this.ordId = ordId;
	}

	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}	
	public java.lang.String getOrdId() {
		return this.ordId;
	}

	public void setBusId(java.lang.String value) {
		this.busId = value;
	}	
	public java.lang.String getBusId() {
		return this.busId;
	}

	public void setOrdStatus(java.lang.String value) {
		this.ordStatus = value;
	}	
	public java.lang.String getOrdStatus() {
		return this.ordStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("OrdId").append("='").append(getOrdId()).append("' ");	
			   buffer.append("BusId").append("='").append(getBusId()).append("' ");	
			   buffer.append("OrdStatus").append("='").append(getOrdStatus()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Modifier").append("='").append(getModifier()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrdInfo) ) return false;
		 OrdInfo  castOther = ( OrdInfo) other;  
		 return 
					( (this.getOrdId()==castOther.getOrdId()) ||( this.getOrdId()!=null && castOther.getOrdId()!=null && this.getOrdId().equals(castOther.getOrdId()) ) )
					&&( (this.getBusId()==castOther.getBusId()) ||( this.getBusId()!=null && castOther.getBusId()!=null && this.getBusId().equals(castOther.getBusId()) ) )
					&&( (this.getOrdStatus()==castOther.getOrdStatus()) ||( this.getOrdStatus()!=null && castOther.getOrdStatus()!=null && this.getOrdStatus().equals(castOther.getOrdStatus()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getModifier()==castOther.getModifier()) ||( this.getModifier()!=null && castOther.getModifier()!=null && this.getModifier().equals(castOther.getModifier()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getOrdId() == null ? 0 : this.getOrdId().hashCode() );
				  result = 37 * result + (getBusId() == null ? 0 : this.getBusId().hashCode() );
				  result = 37 * result + (getOrdStatus() == null ? 0 : this.getOrdStatus().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getModifier() == null ? 0 : this.getModifier().hashCode() );
         return result;
	} 
	
}
