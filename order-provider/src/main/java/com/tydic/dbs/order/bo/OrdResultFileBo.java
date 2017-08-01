/**
 * com.tydic.dbs.bo.OrdResultFileBo.java
 */
package com.tydic.dbs.order.bo;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;


import com.tydic.commons.utils.Page;
import com.tydic.dbs.billing.dao.BillingListDao;
import com.tydic.dbs.billing.dao.ConsumListDao;
import com.tydic.dbs.billing.vo.BillingList;
import com.tydic.dbs.billing.vo.ConsumList;
import com.tydic.dbs.buyer.dao.BussItResourceDao;
import com.tydic.dbs.buyer.vo.BussItResource;
import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.define.WcsCommonStatus;
import com.tydic.dbs.commons.define.WcsDefinition;
import com.tydic.dbs.commons.enums.DealFlag;
import com.tydic.dbs.commons.enums.OrderStatus;
import com.tydic.dbs.commons.enums.ResultState;
import com.tydic.dbs.commons.utils.DataUtil;
import com.tydic.dbs.commons.utils.DateUtil;
import com.tydic.dbs.commons.utils.FtpUpFile;
import com.tydic.dbs.order.dao.*;
import com.tydic.dbs.order.service.OrdResultFileService;
import com.tydic.dbs.order.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

/**
 * @file  OrdResultFileBo.java
 * @author zhongjialian
 * @version 0.1
 * @OrdResultFile业务操作接口实现类
 * Copyright(C), 2013-2014
 *		 Guangzhou tydic Technology Co., Ltd.
 * History
 *   	1. Date: 2016-08-09 05:40:01
 *      	Author: zhongjialian
 *      	Modification: this file was created
 *   	2. ...
 */
public class OrdResultFileBo implements OrdResultFileService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private OrdResultFileDao ordResultFileDao;

	private OrdResultFileHisDao ordResultFileHisDao;

	private OrdInfoDao ordInfoDao;

	private BussItResourceDao bussItResourceDao;

	private BillingListDao billingListDao;

	private ConsumListDao consumListDao;

	private OrdPrdDao ordPrdDao;

	private OrdRusultDao ordRusultDao;

    /**
	 * @param ordResultFileDao the ordResultFileDao to set
	 */
	public void setOrdResultFileDao(OrdResultFileDao ordResultFileDao) {
		this.ordResultFileDao = ordResultFileDao;
	}

	public void setOrdResultFileHisDao(OrdResultFileHisDao ordResultFileHisDao) {
		this.ordResultFileHisDao = ordResultFileHisDao;
	}

	public void setOrdInfoDao(OrdInfoDao ordInfoDao) {
		this.ordInfoDao = ordInfoDao;
	}

	public void setBussItResourceDao(BussItResourceDao bussItResourceDao) {
		this.bussItResourceDao = bussItResourceDao;
	}

	public void setBillingListDao(BillingListDao billingListDao) {
		this.billingListDao = billingListDao;
	}

	public void setConsumListDao(ConsumListDao consumListDao) {
		this.consumListDao = consumListDao;
	}

	public void setOrdPrdDao(OrdPrdDao ordPrdDao) {
		this.ordPrdDao = ordPrdDao;
	}

	public void setOrdRusultDao(OrdRusultDao ordRusultDao) {
		this.ordRusultDao = ordRusultDao;
	}

	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
	@SuppressWarnings({ "rawtypes" })
    public List getAll() throws Exception {
		return ordResultFileDao.selectAll();
	}
	
	/**
     * 查找符合条件的所有数据库记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public List get(OrdResultFile vo) throws Exception{
		if(vo == null) return null;
        return ordResultFileDao.selectByVo(vo);
	}
	
    /**
     * 分页查找
     * @param params 与数据库中记录对应的值对象
     * @return type : Page 返回满足条件的记录集，操作失败返回null
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes" })
    public Page getPageByParamMap(Map params) throws Exception{
		if (params == null) return null;
		return ordResultFileDao.queryForPage(params);
	}
	
    /**
     * 查找符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public OrdResultFile get(String pkId) throws Exception{
		if(StringUtils.isEmpty(pkId)) return null;
     	return ordResultFileDao.selectByPk(pkId);
	}
	
	/**
     * 向数据库中插入一条记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public OrdResultFile save(OrdResultFile vo) throws Exception{
    	if(vo == null) return null;		
		if(vo.getFileId()!=null) {
			return ordResultFileDao.updateByVo(vo);
		} else {
			return ordResultFileDao.insertByVo(vo);
		}
    }
    
    /**
     * 删除符合条件的数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public boolean delete(String pkId) throws Exception{
    	if(StringUtils.isEmpty(pkId)) return false;
    	return ordResultFileDao.deleteByPk(pkId);
    }
    
    /**
	 * 逻辑删除数据库中的对象
	 * @param ids: Id列表, modifier: 修改者
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean deleteLogical(List ids, String modifier) throws Exception{
    	Map params = new HashMap();
    	params.put("ids", ids);
    	params.put("status", WcsCommonStatus.WCS_INVALID);
    	params.put("modifier", modifier);
    	params.put("modifyTime", Calendar.getInstance().getTime());
    	return ordResultFileDao.updateStatus(params);
	}
	
    /**
	 * 恢复已逻辑删除的数据库中的对象
	 * @param ids: Id列表, modifier: 修改者
	 * @return type : boolean 返回操作是否成功
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean recover(List ids, String modifier) throws Exception{
    	Map params = new HashMap();
    	params.put("ids", ids);
    	params.put("status", WcsCommonStatus.WCS_VALID);
    	params.put("modifier", modifier);
    	params.put("modifyTime", Calendar.getInstance().getTime());
    	return ordResultFileDao.updateStatus(params);
	}

	/**
	 * @param params 与数据库中记录对应的值对象
	 * @return type : Page 返回满足条件的记录集，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getListByParamMap(Map params) throws Exception{
		if (params == null) return null;
		return ordResultFileDao.queryForList("ORD_RESULT_FILE.selectByParams", params);
	}

	/**
	 * 批量更新状态
	 * @return type : Page 返回满足条件的记录集，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public boolean modifyStatus(List<Long> list) throws Exception{
		if(list == null||list.size()==0){
			return false;
		}
		OrdResultFile f = new OrdResultFile();
		for(Long l :list){
			f.setState(DealFlag.DEALING.getCode());
			f.setFileId(l);
			ordResultFileDao.updateByVoNotNull(f);
		}
		return true;
	}

	/**
	 * 计量、计费
	 * @return type
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public boolean meteAndCharge(OrdResultFile ordResultFile) throws Exception{
		if(ordResultFile==null){
			String msg  ="工单返回文件为空";
			logger.error(msg);
			moveToHis(ordResultFile,ResultState.FAIL.getCode(),msg);
			return false;
		}
		//获取工单信息
		OrdInfo ordInfo = null;
		try {
			ordInfo = ordInfoDao.selectByPk(ordResultFile.getOrdId());
		}catch (Exception ex) {
			String msg  ="查询工单信息异常";
			moveToHis(ordResultFile,ResultState.FAIL.getCode(),msg);
			logger.error(msg,ex);
			return false;
		}
		if(ordInfo==null){
			String msg  ="查询工单【"+ordResultFile.getOrdId()+"】信息为空";
			moveToHis(ordResultFile,ResultState.FAIL.getCode(), msg);
			logger.error(msg);
			return false;
		}

		//将文件保存到临时文件夹
		String tmpPath = ConfigConstants.ORD_FILE_PATH+ConfigConstants.PATH_DECOLLATOR+ordInfo.getBusId();
		String file = fileDown(ordResultFile.getFtpIp(),ordResultFile.getFtpPort(),
				ordResultFile.getFtpUser(),ordResultFile.getFtpPass(),
				ordResultFile.getFtpPath(),ordResultFile.getFileName(),tmpPath);
		if (file==null||"".equals(file)) {
			String msg  ="数据文件从远程FTP下载失败！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg);
			return false;
		}
		//将临时文件夹内的文件上传到文件服务器
		//文件上传路径
		String destPath = ConfigConstants.UPLOAD_FILE_PATH_ORD_INFO+"/"+ordInfo.getBusId();
		if(!fileUpFtp(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ConfigConstants.FTP_SERVER_PORT),
				ConfigConstants.FTP_SERVER_USER,ConfigConstants.FTP_SERVER_PASSWORD,
				ordInfo.getBusId(),ordResultFile.getFileName(),tmpPath)){
			String msg  ="数据文件上传文件服务器出错！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg);
			return false;
		}

		//计算文件大小、行数
		String fileFullUrl= tmpPath +ConfigConstants.PATH_DECOLLATOR+ordResultFile.getFileName();
		logger.info("fileFullUrl:"+fileFullUrl);
		double fileSize = DataUtil.byteToMB(getFileSize(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ConfigConstants.FTP_SERVER_PORT),
				ConfigConstants.FTP_SERVER_USER,ConfigConstants.FTP_SERVER_PASSWORD,destPath,ordResultFile.getFileName()));

		long rowCount = getRowCount(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ConfigConstants.FTP_SERVER_PORT),
				ConfigConstants.FTP_SERVER_USER,ConfigConstants.FTP_SERVER_PASSWORD,
				destPath,ordResultFile.getFileName());

		//double fileSize = DataUtil.byteToMB(getFileSize(fileFullUrl));
		//long rowCount = getRowCount(fileFullUrl);

		//获取产品计量单位、计量单价
		OrdAndPrd  ordAndPrd = ordPrdDao.selectPrdByOrdId(ordResultFile.getOrdId());
		String prdUnit = ordAndPrd.getPrdUnit();
		BigDecimal prdPrice = ordAndPrd.getPrdPrice();
		double tatalFee = 0;
		if(WcsDefinition.wcsPrdUnit.PRD_UNIT.equals(prdUnit)){
			tatalFee = prdPrice.doubleValue()*rowCount;
		}else if(WcsDefinition.wcsPrdUnit.PRD_UNIT_MB.equals(prdUnit)){
			tatalFee = prdPrice.doubleValue()*fileSize;
		}

		//保存计费清单
		BillingList billingList = new BillingList();
		billingList.setBussId(ordInfo.getBusId());
		billingList.setConsumAmount(new BigDecimal(tatalFee));
		billingList.setPayAmount(new BigDecimal(tatalFee));
		billingList.setBillingTime(new Date());
		billingList.setMonthId(NumberUtils.toInt(DateUtil.DateToString(new Date(), "YYYYMM")));
		try{
			billingList = billingListDao.insertByVo(billingList);
		} catch (Exception ex) {
			String msg  ="保存计费清单出错！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg,ex);
		}
		//保存消费清单
		ConsumList consumList = new ConsumList();
		consumList.setBussId(ordInfo.getBusId());
		consumList.setWorkNo(ordResultFile.getOrdId());
		consumList.setRowNum(rowCount);
		consumList.setDataSize(fileSize);
		consumList.setBillingId(billingList.getBillingId());
		consumList.setConsumTime(new Date());
		try {
			consumListDao.insertByVo(consumList);
		}catch (Exception ex) {
			String msg  ="保存消费清单出错！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg,ex);
		}


		//更新工单状态
		OrdInfo orInfo = new OrdInfo();
		orInfo.setOrdId(ordResultFile.getOrdId());
		orInfo.setBusId(ordInfo.getBusId());
		orInfo.setOrdStatus(OrderStatus.EXECUTED.getCode());
		try{
			orInfo = ordInfoDao.updateByVo(orInfo);
		}catch (Exception ex) {
			String msg  ="更新工单状态出错！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg,ex);
		}
		//查询工单产品ID
		OrdPrd ordPrd = new OrdPrd();
		ordPrd.setOrdId(ordResultFile.getOrdId());
		List<OrdPrd> ordPrdList = null;
		try {
			ordPrdList = ordPrdDao.selectByVo(ordPrd);
		}catch (Exception ex) {
			String msg  ="查询工单产品ID出错！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg,ex);
		}

		if(ordPrdList == null || ordPrdList.size()==0){
			String msg  ="无法关联工单【"+ordResultFile.getOrdId()+"】对应的产品ID！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg);
			return false;
		}
		ordPrd = ordPrdList.get(0);

		//保存数据结果表
		OrdRusult ordRusult = new OrdRusult();
		ordRusult.setOrdPrdId(ordPrd.getOrdPrdId());
		ordRusult.setWorkNo(ordResultFile.getOrdId());
		ordRusult.setFileName(ordResultFile.getFileName());
		ordRusult.setFilePath(destPath);
		if(WcsDefinition.wcsPrdUnit.PRD_UNIT.equals(prdUnit)){
			ordRusult.setResultCount(String.valueOf(rowCount));
		}else if(WcsDefinition.wcsPrdUnit.PRD_UNIT_MB.equals(prdUnit)){
			ordRusult.setResultCount(String.valueOf(fileSize));
		}
		ordRusult.setResultUnit(prdUnit);
		ordRusult.setRunTime(new Date());
		try{
			ordRusultDao.insertByVo(ordRusult);
		}catch (Exception ex) {
			String msg  ="保存数据结果表出错！";
			moveToHis(ordResultFile, ResultState.FAIL.getCode(), msg);
			logger.error(msg,ex);
		}

		//处理完成将工单返回记录转移到历史表中
		moveToHis(ordResultFile, ResultState.SUCCESS.getCode(), "");
		return true;

	}

	/**
	 * 将远程ftp文件下载到本地文件服务器
	 * @param ftpPath
	 * @param fileName
	 * @param localPath
	 * @return
	 */
	public  String fileDown(String ip,int port,String userName,String pwd,String ftpPath, String fileName,String localPath){

		String [] fileNames = {fileName};
		char[]  ftpPassword = (pwd).toCharArray();//服务器密码
		FtpUpFile ftp = new FtpUpFile(ip, port, userName,ftpPassword);
		logger.info("远程ftp路径："+ftpPath);
		logger.info("下载路径："+localPath);
		File direct = new File (localPath);
		if(!direct.exists()){
			try {
				direct.mkdir();
			} catch (Exception ex){
				logger.error("目录【"+localPath+"】创建失败");
				return null;
			}
		}
		List<File> listFile = ftp.ftpGetFile(ftpPath, fileNames, localPath, false);
		if (listFile.isEmpty()) {
			return null;
		}
		return fileName;
	}

	/**
	 * 将本地文件上传到远程FTP
	 * @param fileName
	 * @param localPath
	 * @return
	 */
	public  boolean fileUpFtp(String ip,int port,String userName,String pwd,String ftpChildPath, String fileName,String localPath){
		char[]  ftpPassword = (pwd).toCharArray();//服务器密码
		FtpUpFile ftp = new FtpUpFile(ip, port, userName,ftpPassword);
//		String [] fileNames = {fileName};
		File localDirect = new File(localPath);
		if(!localDirect.exists()){
			logger.error("目录【"+localPath+"】不存在！");
			return false;
		}
		String fullPath = localPath+ConfigConstants.PATH_DECOLLATOR+fileName;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			logger.error("读取"+fullPath+"错误！");
			return false;
		}
//		return ftp.putFile("/home/zhaojl2", "B2016071513022628664", fileName, inputStream);
		boolean result =  ftp.putFile(ConfigConstants.UPLOAD_FILE_PATH_ORD_INFO, ftpChildPath, fileName, inputStream);
		//文件上传成功后删除临时文件
//		if(result){
//			File file = new File(fullPath);
//			if(file.exists()){
//				file.delete();
//			}
//		}
		return result;
	}

	/**
	 * 获取文件大小
	 * @param fileName
	 * @return
	 */
	public long getFileSize(String fileName){
		long fileSize  = 0;
		if(fileName==null||"".equals(fileName.trim())) {
			logger.error("fileName为空");
			return fileSize;
		}
		File f= new File(fileName);
		if (f.exists() && f.isFile()){
			fileSize =f.length();
		}else{
			logger.info("file doesn't exist or is not a file");
		}
		return fileSize;
	}

	/**
	 * 获取远程FTP文件大小
	 * @param fileName
	 * @return
	 */
	public long getFileSize(String ip,int port,String userName,String pwd,String ftpPath, String fileName){
		long fileSize  = 0;
		if(fileName==null||"".equals(fileName.trim())) {
			logger.error("fileName为空");
			return fileSize;
		}
		char[]  ftpPassword = (pwd).toCharArray();//服务器密码
		FtpUpFile ftp = new FtpUpFile(ip, port, userName,ftpPassword);
		fileSize =ftp.getFtpFileSize(ftpPath,fileName);
		return fileSize;
	}

	/**
	 * 获取本地文件行数
	 * @param
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public long getRowCount(String fileName) throws IOException {
		long rowCount =0;
		if(fileName==null||"".equals(fileName.trim())) {
			logger.error("fileName为空");
			return rowCount;
		}

		long s1 = System.currentTimeMillis();

		InputStream is = new BufferedInputStream(new FileInputStream(fileName));

		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		String line="";

		while ((line = reader.readLine()) != null) {
			++rowCount;
		}
		is.close();
		long s2 = System.currentTimeMillis();
		logger.info("读取行数耗时："+(s2-s1));
		return rowCount;
	}

	/**
	 * 读取远程ftp指定的文件
	 * @param ip
	 * @param port
	 * @param userName
	 * @param pwd
	 * @param ftpPath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public long getRowCount(String ip,int port,String userName,String pwd,String ftpPath, String fileName) throws IOException {
		long rowCount =0;
		if(fileName==null||"".equals(fileName.trim())) {
			logger.error("fileName为空");
			return rowCount;
		}
		long s1 = System.currentTimeMillis();
		char[]  ftpPassword = (pwd).toCharArray();//服务器密码
		FtpUpFile ftp = new FtpUpFile(ip, port, userName,ftpPassword);
		rowCount = ftp.getFtpFileRows(ftpPath,fileName);
		long s2 = System.currentTimeMillis();
		logger.info("读取行数耗时："+(s2-s1));
		return rowCount;
	}

	/**
	 * 将工单返回文件表中的记录转移到历史表中
	 * @param ordResultFile
	 * @param dealFlag
	 * @param dealRemark
	 */
	public void moveToHis(OrdResultFile ordResultFile,String dealFlag,String dealRemark){
		if(ordResultFile==null){
			return;
		}
		OrdInfo tvo = new OrdInfo();
		tvo.setOrdId(ordResultFile.getOrdId());
		if(ResultState.FAIL.getCode().equals(dealFlag)){
			tvo.setOrdStatus(OrderStatus.EXECUTED_FAIL.getCode());
			tvo.setRemark(dealRemark);
		}else{
			tvo.setOrdStatus(OrderStatus.EXECUTED.getCode());
		}


		OrdResultFileHis ordResultFileHis = copyToFileHis(ordResultFile);
		ordResultFileHis.setDealFlag(dealFlag);
		ordResultFileHis.setDealRemark(dealRemark);
		try {
			ordInfoDao.updateByVoNotNull(tvo);
			ordResultFileHisDao.insertByVo(ordResultFileHis);
			ordResultFileDao.deleteByPk(ordResultFile.getFileId().toString());
		} catch (Exception ex) {
			logger.error("更新历史记录错误！",ex);
		}
	}


	/**
	 * 将OrdResultFile共有属性cp到OrdResultFileHis
	 * @param file
	 * @return
	 */
	private OrdResultFileHis copyToFileHis(OrdResultFile file){
		OrdResultFileHis ordResultFileHis = new OrdResultFileHis();
		ordResultFileHis.setSerialNum(file.getSerialNum());
		ordResultFileHis.setFileName(file.getFileName());
		ordResultFileHis.setFileUrl(file.getFileUrl());
		ordResultFileHis.setFtpIp(file.getFtpIp());
		ordResultFileHis.setFtpPort(file.getFtpPort());
		ordResultFileHis.setFtpUser(file.getFtpUser());
		ordResultFileHis.setFtpPass(file.getFtpPass());
		ordResultFileHis.setFtpPath(file.getFtpPath());
		ordResultFileHis.setOrdId(file.getOrdId());
		ordResultFileHis.setCreateTime(new Date());
		return ordResultFileHis;
	}


	public static void main(String [] args){
		OrdResultFileBo bo = new OrdResultFileBo();
		bo.fileUpFtp("192.168.0.101",21,"zhaojl2","123456","ord_info/B201608082001","a.java","d:\\tmp\\B2016071513022628664");
		System.out.println("upload success");


	}



}
