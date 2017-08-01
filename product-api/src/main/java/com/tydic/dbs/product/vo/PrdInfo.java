/**
 * com.sunrise.pro.mapper.PrdInfo.java
 */
package com.tydic.dbs.product.vo;


import com.tydic.commons.utils.BaseVO;


/**
 * 
 * @ClassName: PrdInfo 
 * @Description: TODO(实体) 
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-11 下午5:31:01 
 *
 */
public class PrdInfo extends BaseVO implements java.io.Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 8528842840918137219L;
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
	private java.lang.String prdType;
	private java.lang.String dataBaseIp;
	private java.lang.String dataBasePort;
	private java.lang.String dataBaseUserName;
	private java.lang.String dataBaseUserPwd;
    private String prdDataName;
	public PrdInfo(){
	}

	public PrdInfo(
		java.lang.String prdId
	){
		this.prdId = prdId;
	}



	public void setPrdId(java.lang.String value) {
		this.prdId = value;
	}	
	public java.lang.String getPrdId() {
		return this.prdId;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}	
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setPrdName(java.lang.String value) {
		this.prdName = value;
	}	
	public java.lang.String getPrdName() {
		return this.prdName;
	}

	public void setPrdDes(java.lang.String value) {
		this.prdDes = value;
	}	
	public java.lang.String getPrdDes() {
		return this.prdDes;
	}

	public void setPrdDatafile(java.lang.String value) {
		this.prdDatafile = value;
	}	
	public java.lang.String getPrdDatafile() {
		return this.prdDatafile;
	}

	public void setPrdOthers(java.lang.String value) {
		this.prdOthers = value;
	}	
	public java.lang.String getPrdOthers() {
		return this.prdOthers;
	}

	public void setPrdStatus(java.lang.String value) {
		this.prdStatus = value;
	}	
	public java.lang.String getPrdStatus() {
		return this.prdStatus;
	}

	public void setPrdUnit(java.lang.String value) {
		this.prdUnit = value;
	}	
	public java.lang.String getPrdUnit() {
		return this.prdUnit;
	}

	public void setPrdPrice(java.math.BigDecimal value) {
		this.prdPrice = value;
	}	
	public java.math.BigDecimal getPrdPrice() {
		return this.prdPrice;
	}





	public void setChecker(java.lang.String value) {
		this.checker = value;
	}	
	public java.lang.String getChecker() {
		return this.checker;
	}

	public void setCheckTime(java.util.Date value) {
		this.checkTime = value;
	}	
	public java.util.Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckOpin(java.lang.String value) {
		this.checkOpin = value;
	}	
	public java.lang.String getCheckOpin() {
		return this.checkOpin;
	}


    public java.lang.String getPrdType() {
		return prdType;
	}

	public void setPrdType(java.lang.String prdType) {
		this.prdType = prdType;
	}

	public java.lang.String getDataBaseIp() {
		return dataBaseIp;
	}

	public java.lang.String getDataBasePort() {
		return dataBasePort;
	}

	public java.lang.String getDataBaseUserName() {
		return dataBaseUserName;
	}

	public java.lang.String getDataBaseUserPwd() {
		return dataBaseUserPwd;
	}

	public void setDataBaseIp(java.lang.String dataBaseIp) {
		this.dataBaseIp = dataBaseIp;
	}

	public void setDataBasePort(java.lang.String dataBasePort) {
		this.dataBasePort = dataBasePort;
	}

	public void setDataBaseUserName(java.lang.String dataBaseUserName) {
		this.dataBaseUserName = dataBaseUserName;
	}

	public void setDataBaseUserPwd(java.lang.String dataBaseUserPwd) {
		this.dataBaseUserPwd = dataBaseUserPwd;
	}

	/** 
	 * @return prdDataName 
	 */
	public String getPrdDataName() {
		return prdDataName;
	}

	/** 
	 * @param prdDataName 要设置的 prdDataName 
	 */
	public void setPrdDataName(String prdDataName) {
		this.prdDataName = prdDataName;
	}

	public String toString() {
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
			   buffer.append("PrdId").append("='").append(getPrdId()).append("' ");	
			   buffer.append("BussId").append("='").append(getBussId()).append("' ");	
			   buffer.append("PrdName").append("='").append(getPrdName()).append("' ");	
			   buffer.append("PrdDes").append("='").append(getPrdDes()).append("' ");	
			   buffer.append("PrdDatafile").append("='").append(getPrdDatafile()).append("' ");	
			   buffer.append("PrdOthers").append("='").append(getPrdOthers()).append("' ");	
			   buffer.append("PrdStatus").append("='").append(getPrdStatus()).append("' ");	
			   buffer.append("PrdUnit").append("='").append(getPrdUnit()).append("' ");	
			   buffer.append("PrdPrice").append("='").append(getPrdPrice()).append("' ");	
			   buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");	
			   buffer.append("Creater").append("='").append(getCreater()).append("' ");	
			   buffer.append("ModifyTime").append("='").append(getModifyTime()).append("' ");	
			   buffer.append("Modifier").append("='").append(getModifier()).append("' ");	
			   buffer.append("Checker").append("='").append(getChecker()).append("' ");	
			   buffer.append("CheckTime").append("='").append(getCheckTime()).append("' ");	
			   buffer.append("CheckOpin").append("='").append(getCheckOpin()).append("' ");	
			   buffer.append("PrdType").append("='").append(getPrdType()).append("' ");	
			   buffer.append("DataBaseIp").append("='").append(getDataBaseIp()).append("' ");	
			   buffer.append("DataBasePort").append("='").append(getDataBasePort()).append("' ");	
			   buffer.append("DataBaseUserName").append("='").append(getDataBaseUserName()).append("' ");	
			   buffer.append("DataBaseUserPwd").append("='").append(getDataBaseUserPwd()).append("' ");	
			   buffer.append("PrdDataName").append("='").append(getPrdDataName()).append("' ");	
		  buffer.append("]");
		  return buffer.toString();
	}		

	public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrdInfo) ) return false;
		 PrdInfo  castOther = ( PrdInfo) other;  
		 return 
					( (this.getPrdId()==castOther.getPrdId()) ||( this.getPrdId()!=null && castOther.getPrdId()!=null && this.getPrdId().equals(castOther.getPrdId()) ) )
					&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
					&&( (this.getPrdName()==castOther.getPrdName()) ||( this.getPrdName()!=null && castOther.getPrdName()!=null && this.getPrdName().equals(castOther.getPrdName()) ) )
					&&( (this.getPrdDes()==castOther.getPrdDes()) ||( this.getPrdDes()!=null && castOther.getPrdDes()!=null && this.getPrdDes().equals(castOther.getPrdDes()) ) )
					&&( (this.getPrdDatafile()==castOther.getPrdDatafile()) ||( this.getPrdDatafile()!=null && castOther.getPrdDatafile()!=null && this.getPrdDatafile().equals(castOther.getPrdDatafile()) ) )
					&&( (this.getPrdOthers()==castOther.getPrdOthers()) ||( this.getPrdOthers()!=null && castOther.getPrdOthers()!=null && this.getPrdOthers().equals(castOther.getPrdOthers()) ) )
					&&( (this.getPrdStatus()==castOther.getPrdStatus()) ||( this.getPrdStatus()!=null && castOther.getPrdStatus()!=null && this.getPrdStatus().equals(castOther.getPrdStatus()) ) )
					&&( (this.getPrdUnit()==castOther.getPrdUnit()) ||( this.getPrdUnit()!=null && castOther.getPrdUnit()!=null && this.getPrdUnit().equals(castOther.getPrdUnit()) ) )
					&&( (this.getPrdPrice()==castOther.getPrdPrice()) ||( this.getPrdPrice()!=null && castOther.getPrdPrice()!=null && this.getPrdPrice().equals(castOther.getPrdPrice()) ) )
					&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
					&&( (this.getCreater()==castOther.getCreater()) ||( this.getCreater()!=null && castOther.getCreater()!=null && this.getCreater().equals(castOther.getCreater()) ) )
					&&( (this.getModifyTime()==castOther.getModifyTime()) ||( this.getModifyTime()!=null && castOther.getModifyTime()!=null && this.getModifyTime().equals(castOther.getModifyTime()) ) )
					&&( (this.getModifier()==castOther.getModifier()) ||( this.getModifier()!=null && castOther.getModifier()!=null && this.getModifier().equals(castOther.getModifier()) ) )
					&&( (this.getChecker()==castOther.getChecker()) ||( this.getChecker()!=null && castOther.getChecker()!=null && this.getChecker().equals(castOther.getChecker()) ) )
					&&( (this.getCheckTime()==castOther.getCheckTime()) ||( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
					&&( (this.getCheckOpin()==castOther.getCheckOpin()) ||( this.getCheckOpin()!=null && castOther.getCheckOpin()!=null && this.getCheckOpin().equals(castOther.getCheckOpin()) ) )
					&&( (this.getPrdType()==castOther.getPrdType()) ||( this.getPrdType()!=null && castOther.getPrdType()!=null && this.getPrdType().equals(castOther.getPrdType()) ) )
					&&( (this.getDataBaseIp()==castOther.getDataBaseIp()) ||( this.getDataBaseIp()!=null && castOther.getDataBaseIp()!=null && this.getDataBaseIp().equals(castOther.getDataBaseIp()) ) )
					&&( (this.getDataBasePort()==castOther.getDataBasePort()) ||( this.getDataBasePort()!=null && castOther.getDataBasePort()!=null && this.getDataBasePort().equals(castOther.getDataBasePort()) ) )
					&&( (this.getDataBaseUserName()==castOther.getDataBaseUserName()) ||( this.getDataBaseUserName()!=null && castOther.getDataBaseUserName()!=null && this.getDataBaseUserName().equals(castOther.getDataBaseUserName()) ) )
					&&( (this.getDataBaseUserPwd()==castOther.getDataBaseUserPwd()) ||( this.getDataBaseUserPwd()!=null && castOther.getDataBaseUserPwd()!=null && this.getDataBaseUserPwd().equals(castOther.getDataBaseUserPwd()) ) )
					&&( (this.getPrdDataName()==castOther.getPrdDataName()) ||( this.getPrdDataName()!=null && castOther.getPrdDataName()!=null && this.getPrdDataName().equals(castOther.getPrdDataName()) ) )
			;
    }

	public int hashCode() {
         int result = 17;
				  result = 37 * result + (getPrdId() == null ? 0 : this.getPrdId().hashCode() );
				  result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
				  result = 37 * result + (getPrdName() == null ? 0 : this.getPrdName().hashCode() );
				  result = 37 * result + (getPrdDes() == null ? 0 : this.getPrdDes().hashCode() );
				  result = 37 * result + (getPrdDatafile() == null ? 0 : this.getPrdDatafile().hashCode() );
				  result = 37 * result + (getPrdOthers() == null ? 0 : this.getPrdOthers().hashCode() );
				  result = 37 * result + (getPrdStatus() == null ? 0 : this.getPrdStatus().hashCode() );
				  result = 37 * result + (getPrdUnit() == null ? 0 : this.getPrdUnit().hashCode() );
				  result = 37 * result + (getPrdPrice() == null ? 0 : this.getPrdPrice().hashCode() );
				  result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
				  result = 37 * result + (getCreater() == null ? 0 : this.getCreater().hashCode() );
				  result = 37 * result + (getModifyTime() == null ? 0 : this.getModifyTime().hashCode() );
				  result = 37 * result + (getModifier() == null ? 0 : this.getModifier().hashCode() );
				  result = 37 * result + (getChecker() == null ? 0 : this.getChecker().hashCode() );
				  result = 37 * result + (getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
				  result = 37 * result + (getCheckOpin() == null ? 0 : this.getCheckOpin().hashCode() );
				  result = 37 * result + (getPrdType() == null ? 0 : this.getPrdType().hashCode() );
				  result = 37 * result + (getPrdDataName() == null ? 0 : this.getPrdDataName().hashCode() );
				  result = 37 * result + (getDataBaseIp() == null ? 0 : this.getDataBaseIp().hashCode() );
				  result = 37 * result + (getDataBasePort() == null ? 0 : this.getDataBasePort().hashCode() );
				  result = 37 * result + (getDataBaseUserName() == null ? 0 : this.getDataBaseUserName().hashCode() );
				  result = 37 * result + (getDataBaseUserPwd() == null ? 0 : this.getDataBaseUserPwd().hashCode() );
        
         return result;
	} 
	
}
