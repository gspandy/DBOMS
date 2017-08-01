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
public enum AuditStatus {

	DRAFT("0","草稿"),WAIT("1","待审核"),PASS("2","审核通过"),
	NO_PASS("3","审核不通过"),REAUDIT("4","重新审核");
	
	private AuditStatus(String code,String name){
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
