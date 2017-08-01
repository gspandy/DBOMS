/**
 * com.sunrise.wcs.mapper.InfFileLog.java
 */
package com.tydic.dbs.system.log.mapper;


import com.tydic.commons.utils.BaseVO;

/**
 *
 * @ClassName: InfFileLog
 * @Description: TODO(文件接口日志记录)
 * @author Michael dengwenjie@tydic.com
 * @date 2016-8-10 上午10:46:25
 *
 **/
public class InfFileLog extends BaseVO implements java.io.Serializable{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Long infLogId;
	private java.lang.String serialNum;
	private java.lang.String bussId;
	private java.lang.String operId;
	private java.lang.String infCode;
	private java.lang.String infName;
	private java.lang.String infFileName;
	private java.lang.String creator;
	private java.lang.String remark;
	public InfFileLog(){
	}

	public InfFileLog(
			java.lang.Long infLogId
	){
		this.infLogId = infLogId;
	}



	public void setInfLogId(java.lang.Long value) {
		this.infLogId = value;
	}
	public java.lang.Long getInfLogId() {
		return this.infLogId;
	}

	public void setSerialNum(java.lang.String value) {
		this.serialNum = value;
	}
	public java.lang.String getSerialNum() {
		return this.serialNum;
	}

	public void setBussId(java.lang.String value) {
		this.bussId = value;
	}
	public java.lang.String getBussId() {
		return this.bussId;
	}

	public void setOperId(java.lang.String value) {
		this.operId = value;
	}
	public java.lang.String getOperId() {
		return this.operId;
	}

	public void setInfCode(java.lang.String value) {
		this.infCode = value;
	}
	public java.lang.String getInfCode() {
		return this.infCode;
	}

	public void setInfName(java.lang.String value) {
		this.infName = value;
	}
	public java.lang.String getInfName() {
		return this.infName;
	}

	public void setInfFileName(java.lang.String value) {
		this.infFileName = value;
	}
	public java.lang.String getInfFileName() {
		return this.infFileName;
	}

	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	public java.lang.String getCreator() {
		return this.creator;
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
		buffer.append("InfLogId").append("='").append(getInfLogId()).append("' ");
		buffer.append("SerialNum").append("='").append(getSerialNum()).append("' ");
		buffer.append("BussId").append("='").append(getBussId()).append("' ");
		buffer.append("OperId").append("='").append(getOperId()).append("' ");
		buffer.append("InfCode").append("='").append(getInfCode()).append("' ");
		buffer.append("InfName").append("='").append(getInfName()).append("' ");
		buffer.append("InfFileName").append("='").append(getInfFileName()).append("' ");
		buffer.append("Status").append("='").append(getStatus()).append("' ");
		buffer.append("Creator").append("='").append(getCreator()).append("' ");
		buffer.append("CreateTime").append("='").append(getCreateTime()).append("' ");
		buffer.append("Remark").append("='").append(getRemark()).append("' ");
		buffer.append("]");
		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof InfFileLog) ) return false;
		InfFileLog  castOther = ( InfFileLog) other;
		return
				( (this.getInfLogId()==castOther.getInfLogId()) ||( this.getInfLogId()!=null && castOther.getInfLogId()!=null && this.getInfLogId().equals(castOther.getInfLogId()) ) )
						&&( (this.getSerialNum()==castOther.getSerialNum()) ||( this.getSerialNum()!=null && castOther.getSerialNum()!=null && this.getSerialNum().equals(castOther.getSerialNum()) ) )
						&&( (this.getBussId()==castOther.getBussId()) ||( this.getBussId()!=null && castOther.getBussId()!=null && this.getBussId().equals(castOther.getBussId()) ) )
						&&( (this.getOperId()==castOther.getOperId()) ||( this.getOperId()!=null && castOther.getOperId()!=null && this.getOperId().equals(castOther.getOperId()) ) )
						&&( (this.getInfCode()==castOther.getInfCode()) ||( this.getInfCode()!=null && castOther.getInfCode()!=null && this.getInfCode().equals(castOther.getInfCode()) ) )
						&&( (this.getInfName()==castOther.getInfName()) ||( this.getInfName()!=null && castOther.getInfName()!=null && this.getInfName().equals(castOther.getInfName()) ) )
						&&( (this.getInfFileName()==castOther.getInfFileName()) ||( this.getInfFileName()!=null && castOther.getInfFileName()!=null && this.getInfFileName().equals(castOther.getInfFileName()) ) )
						&&( (this.getStatus()==castOther.getStatus()) ||( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
						&&( (this.getCreator()==castOther.getCreator()) ||( this.getCreator()!=null && castOther.getCreator()!=null && this.getCreator().equals(castOther.getCreator()) ) )
						&&( (this.getCreateTime()==castOther.getCreateTime()) ||( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
						&&( (this.getRemark()==castOther.getRemark()) ||( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
				;
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getInfLogId() == null ? 0 : this.getInfLogId().hashCode() );
		result = 37 * result + (getSerialNum() == null ? 0 : this.getSerialNum().hashCode() );
		result = 37 * result + (getBussId() == null ? 0 : this.getBussId().hashCode() );
		result = 37 * result + (getOperId() == null ? 0 : this.getOperId().hashCode() );
		result = 37 * result + (getInfCode() == null ? 0 : this.getInfCode().hashCode() );
		result = 37 * result + (getInfName() == null ? 0 : this.getInfName().hashCode() );
		result = 37 * result + (getInfFileName() == null ? 0 : this.getInfFileName().hashCode() );
		result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode() );
		result = 37 * result + (getCreator() == null ? 0 : this.getCreator().hashCode() );
		result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
		result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode() );
		return result;
	}

}
