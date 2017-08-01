package com.tydic.dbs.ws.vo;

public class BaseVO {
	
	private String service_name;
	
	private String session_id;
	
	private String input_charset;
	
	private String sign_key;
	
	private String system_id;

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getSign_key() {
		return sign_key;
	}

	public void setSign_key(String sign_key) {
		this.sign_key = sign_key;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}
	
	

}
