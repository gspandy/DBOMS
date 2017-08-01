package com.tydic.dbs.commons.enums;

/** 
 * 
 * @ClassName: OrderStatus 
 * @Description: TODO(工单状态) 
 * @author huangChuQin
 * @date 2016-8-5 下午4:00:07 
 *
 */
public enum OrderStatus {

	UNCOMMITTED("1","待提交"),COMMITTED("2","已提交"),EXECUTED("3","已执行"),EXECUTING("4","正在执行"),EXECUTED_FAIL("5","执行失败");

	private OrderStatus(String code, String name){
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
