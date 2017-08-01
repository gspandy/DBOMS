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
public enum DealFlag {

	WAIT_DEAL("0","待处理"),DEALING("1","正在处理"),DEAL_FAIL("2","处理失败");

	private DealFlag(String code, String name){
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
