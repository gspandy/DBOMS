/*
 *审核状态
 *
*/
package com.tydic.dbs.commons.enums;

/** 
 * @ClassName: Status
 * @Description: TODO(审核类型)
 * @author 赵季良
 * @date 2016-7-26
 *  
 */
public enum AuditType {
	ITRESOURCE("ITRESOURCE","IT资源审核"),LEEAUTH("LEEAUTH","操作员权限审核"),DATAPEM("DATAPEM","数据权限审核");

	private AuditType(String code, String name){
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
