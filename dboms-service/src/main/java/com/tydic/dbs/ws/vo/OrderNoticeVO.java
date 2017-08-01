package com.tydic.dbs.ws.vo;

public class OrderNoticeVO extends BaseVO{

	private String work_no;
	
	private String execute_status;

	private String ftp_info;
	
	private String file_name;
	
	private String remark;

	public String getWork_no() {
		return work_no;
	}

	public void setWork_no(String work_no) {
		this.work_no = work_no;
	}

	public String getExecute_status() {
		return execute_status;
	}

	public void setExecute_status(String execute_status) {
		this.execute_status = execute_status;
	}

	public String getFtp_info() {
		return ftp_info;
	}

	public void setFtp_info(String ftp_info) {
		this.ftp_info = ftp_info;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
