/*
 *审核状态
 *
*/
package com.tydic.dbs.commons.enums;

/** 
 * @ClassName: Status
 * @Description: TODO(状态)
 * @author 赵季良
 * @date 2016-7-26
 *  
 */
public enum Status {

	VALID("1","有效"),UNVALID("0","无效");

	private Status(String code, String name){
		this.code=code;
		this.name=name;
	}
	
    private String code;
	
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
