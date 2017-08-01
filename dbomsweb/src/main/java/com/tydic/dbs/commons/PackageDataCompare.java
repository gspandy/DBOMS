package com.tydic.dbs.commons;

public class PackageDataCompare implements Comparable{

	private Long monthFee;//月费(46,66,96等)
	private String planType;//类型(A,B,C等)
	
	public Long getMonthFee() {
		return monthFee;
	}

	public void setMonthFee(Long monthFee) {
		this.monthFee = monthFee;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Override
	public int compareTo(Object o) {
		PackageDataCompare compareObj = (PackageDataCompare)o;
		int result = 0;
		if(this.getMonthFee() > compareObj.getMonthFee()){
			result = 1;
		}else if(this.getMonthFee() == compareObj.getMonthFee()){
			result = this.getPlanType().compareTo(compareObj.getPlanType());
		}else{
			result = -1;
		}
		return result;
	}

}
