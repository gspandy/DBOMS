package com.tydic.dbs.number.numAop.service;

import java.util.List;

import com.tydic.dbs.number.numAop.dto.Offers;

public abstract interface PolicyPackageService {
	
	/**
	 * 根据老用户号码选择政策包
	 * @param mobile
	 * @return
	 */
	public List<Offers> getPolicy(String mobile) throws Exception;

}
