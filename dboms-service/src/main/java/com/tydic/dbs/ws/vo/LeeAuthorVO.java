package com.tydic.dbs.ws.vo;

public class LeeAuthorVO extends BaseVO{

	private String user_id;
	
	private String result_flag;

	private String result;
	
	private String message;
	

	public String getResult_flag() {
		return result_flag;
	}

	public void setResult_flag(String result_flag) {
		this.result_flag = result_flag;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
	
}
