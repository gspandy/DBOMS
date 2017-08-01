package com.tydic.dbs.commons.vo;

import java.io.Serializable;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4325850245014818640L;
	//成功
	public static final String RESP_CODE_SUCCESS = "0";
	//失败
	public static final String RESP_CODE_FAIL = "1";
	//不作处理
	public static final String RESP_CODE_SKIP = "2";
	
	private String resp_code;
	
	private String resp_msg;

	public String getResp_code() {
		return resp_code;
	}

	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}

	public String getResp_msg() {
		return resp_msg;
	}

	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}
}
