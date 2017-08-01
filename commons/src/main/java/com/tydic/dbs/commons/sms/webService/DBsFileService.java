package com.tydic.dbs.commons.sms.webService;

/**
 * DBsFileService:(文件处理接口)
 * @author Michael-Deng
 *
 */


public interface DBsFileService {
	
	public void save() throws Exception;//需要文件保存实体VO
	
	public void findById(Long fileId) throws Exception;
}
