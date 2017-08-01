/*
 *审核状态
 *
*/
package com.tydic.dbs.commons.enums;

/**
 * @ClassName: Status
 * @Description: TODO(审核状态)
 * @author 赵季良
 * @date 2016-7-26
 *
 */
public enum ResultState {

	SUCCESS("0","成功"),FAIL("1","处理失败");

	private ResultState(String code, String name){
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
