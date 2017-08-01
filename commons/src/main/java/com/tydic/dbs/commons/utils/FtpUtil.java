/*
 * com.tydic.dbs.commons.utils  2015-2-5
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

import com.tydic.dbs.commons.constant.ConfigConstants;
import com.tydic.dbs.commons.vo.DataFileFTPVO;
/** 
 * @ClassName: FtpUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-2-5 下午2:51:16 
 *  
 */
public class FtpUtil extends FTPClient {
	private static Log log = LogFactory.getLog(FtpUtil.class);  
	/**ftp根目录*/
	private String ftpPath = "/";
	
	public boolean ftpLogin(String host, int port, String userName, String password, String ftpPath) { 
		//设置跟目录
		this.ftpPath = ftpPath;
		
		return ftpLogin(host, port, userName, password);
	}
	public boolean ftpLogin(String host, int port, String userName, String password) { 
		
        boolean isLogined = false;  
        try {  
            log.debug("ftp login start ...");  
            super.connect(host, port);  
            isLogined = super.login(userName, password);  
            if(isLogined) {
                log.debug("ftp login successfully ...");  
            }else {
                log.debug("ftp login failed ...");  
            }
            return isLogined;  
        } catch (SocketException e) {  
            log.error("", e);  
            return false;  
        } catch (IOException e) {  
            log.error("", e);  
            return false;  
        } catch (RuntimeException e) {  
            log.error("", e);  
            return false;  
        }  
    }  
	
	/**
	 * 
	 * @Title: setFtpToUtf8 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @throws IOException    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public void setFtpToUtf8() throws IOException {  
		FTPClientConfig conf = new FTPClientConfig();  
        super.configure(conf);  
        //super.setFileType(FTP.IMAGE_FILE_TYPE);  
        int reply = super.sendCommand("OPTS UTF8 ON");  
        if (reply == 200) { // UTF8 Command  
            super.setControlEncoding("UTF-8");  
        }  
    }  
	
	/**
	 * 
	 * @Title: close 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public void close() {
		if (super.isConnected()) {
			try {
				super.logout();
				super.disconnect();
				log.debug("ftp logout ....");
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new RuntimeException(e.toString());
			}
		}
	}
	
	/**
	 * 
	 * @Title: downFtpFile 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param filePath ftp文件
	 * @param @param localFileName 本地文件
	 * @param @return
	 * @param @throws IOException    设定文件 
	 * @return File    返回类型 
	 * @throws
	 */
	public File downFtpFile(String filePath, String localFileName) throws IOException {  
        File outfile = new File(localFileName);  
        OutputStream oStream = null;  
        try {  
            oStream = new FileOutputStream(outfile);  
            super.retrieveFile(filePath, oStream);  
            return outfile;  
        } finally {  
            if (oStream != null)  
                oStream.close();  
        }  
    }  
  
	/**
	 * 
	 * @Title: listFtpFiles 
	 * @Description:获取目录下面子文件
	 * @param @param filePath
	 * @param @return
	 * @param @throws IOException    设定文件 
	 * @return FTPFile[]    返回类型 
	 * @throws
	 */
    public FTPFile[] listFtpFiles(String filePath) throws IOException {  
        return super.listFiles(filePath);  
    }  
  
    /**
     * 
     * @Title: deleteFtpFiles 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param ftpFiles
     * @param @throws IOException    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void deleteFtpFiles(FTPFile[] ftpFiles) throws IOException {  
        String path = ftpPath;  
        for (FTPFile ff : ftpFiles) {  
            if (ff.isFile()) {  
                if (!super.deleteFile(path + ff.getName()))  
                    throw new RuntimeException("delete File" + ff.getName() + " is n't seccess");  
            }  
        }  
    }  
    
    /**
     * 
     * @Title: deleteFtpFile 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param fileName
     * @param @throws IOException    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void deleteFtpFile(String fileName) throws IOException {  
        if (!super.deleteFile(ftpPath + fileName))  
            throw new RuntimeException("delete File" + ftpPath + fileName + " is n't seccess");  
    }  
    
    /**
     * 
     * @Title: downFtpFile 
     * @Description: 下载ftp文件
     * @param @param fileName
     * @param @return
     * @param @throws IOException    设定文件 
     * @return InputStream    返回类型 
     * @throws
     */
    public InputStream downFtpFile(String fileName) throws IOException {  
        return super.retrieveFileStream(ftpPath + fileName);  
    }
    
    /**
	 * 
	 * @Title: uploadFtpFile 
	 * @Description: TODO(上传文件) 
	 * @param @param filePath ftp文件
	 * @param @param localFileName 本地文件
	 * @param @return
	 * @param @throws IOException    设定文件 
	 * @return File    返回类型 
	 * @throws
	 */
	public boolean uploadFtpFile(String filePath, String localFileName) throws IOException {  
        File infile = new File(localFileName);  
        InputStream iStream = null;  
        try {  
            iStream = new FileInputStream(infile);  
            return super.storeFile(filePath, iStream);  
        } finally {  
            if (iStream != null)  
                iStream.close();  
        }  
    }
	
	
	/**
	 * 
	 * @Title: pushFileToFtp 
	 * @Description: TODO(推送文件到FTP服务器上) 
	 * @param @param request
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
  	public static boolean pushFileToFtp(DataFileFTPVO dataFileVO) throws Exception {
		try {

			String command =ConfigConstants.FTP_PUSH_FILE_SHELL+ " "+dataFileVO.getFtpIp()+" \""+dataFileVO.getFtpUser()+"\" \""+dataFileVO.getFtpPassword()
					+"\" "+"C:\\Users\\Administrator\\Desktop\\sms.rar"+" "+dataFileVO.getFileName();
			log.error("command="+command);
			String returnValue = ShellUtil.execCmd(command, ConfigConstants.FTP_SERVER_USER, ConfigConstants.FTP_SERVER_PASSWORD, ConfigConstants.FTP_SERVER_IP);
			log.error("returnValue="+returnValue);
			if(returnValue==null || returnValue.indexOf(dataFileVO.getFileName())==-1){
				log.error("taskId="+dataFileVO.getTaskId()+",fileId="+dataFileVO.getFileId()+" push文件到FTP("+dataFileVO.getFtpIp()+")失败，原因："+returnValue);
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("输出文件push到ftp失败："+e.getMessage());
			return false;
		}
	}
}
