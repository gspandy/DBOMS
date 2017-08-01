/*
 * com.tydic.dbs.commons.utils  2016-8-4
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tydic.dbs.commons.constant.ConfigConstants;

/** 
 * @ClassName: FileDownload 
 * @Description: TODO(文件下载) 
 * @author huangChuQin
 * @date 2016-8-4 下午7:51:39 
 *  
 */
public class FileUtil {
	
	/**
	 * @Title: fileDown 
	 * @Description: TODO(从服务器下载文件) 
	 * @param @param request
	 * @param @param ftpPath  文件路径
	 * @param @param fileName 文件名
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String fileDown(HttpServletRequest request, String ftpPath, String fileName){
		String url = request.getSession().getServletContext().getRealPath("/temp");
		String [] fileNames = {fileName};
		String	ftpPort =ConfigConstants.FTP_SERVER_PORT;//ftp端口号
		char[]  ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
		FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ftpPort),ConfigConstants.FTP_SERVER_USER,ftpPassword);
		List<File> listFile = ftp.ftpGetFile(ftpPath, fileNames, url, false);
		if (listFile.isEmpty()) {
			return null;
		}
		return fileName;
	}
	
	/**
	 * 
	 * @Title: fileDown 
	 * @Description: TODO(下载接口文件，返回zip到页面，支持多个文件下载) 
	 * @param @param request
	 * @param @param response
	 * @param @param mapList 数据参数 List<Map<用户ID，接口文件名称>>
	 * @param @return    设定文件 
	 * @return String    正常流程返回为空
	 * @throws
	 */
	public String fileDown(HttpServletRequest request,HttpServletResponse response,List<Map<String,String>> mapList){
		if (mapList.isEmpty()) {
			return "参数为空下载失败";
		}
		String url = request.getSession().getServletContext().getRealPath("/temp");
		String	ftpPort =ConfigConstants.FTP_SERVER_PORT;//ftp端口号
		char[]  ftpPassword = (ConfigConstants.FTP_SERVER_PASSWORD).toCharArray();//服务器密码
		FtpUpFile ftp = new FtpUpFile(ConfigConstants.FTP_SERVER_IP,Integer.parseInt(ftpPort),ConfigConstants.FTP_SERVER_USER,ftpPassword);
		List<File> listFile = new ArrayList<File>();
		for (Map<String,String> param: mapList) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				String ftpPath = ConfigConstants.UPLOAD_FILE_PATH_INTERFACE_INFO+"/"+entry.getKey();
				if (ftpPath==null||ftpPath.equals("")) {
					continue;
				}
				String fileName = entry.getValue();
				if (fileName==null||fileName.equals("")) {
					continue;
				}
				String[] fileNames = {fileName};
				List<File> list = ftp.ftpGetFile(ftpPath, fileNames, url, false);
				listFile.addAll(list);
			}
		}
		if (listFile.isEmpty()) {
			return "获取文件信息失败";
		}
		String zipName = "Interface_"+DateUtil.DateToString4(new Date());
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+zipName+".zip");  
        ServletOutputStream out = null;  
        File file = writeZip(url,listFile,zipName);
		if (!file.exists()) {
			return "压缩文件失败";
		}
        FileInputStream inputStream = null;
        try {  
            inputStream = new FileInputStream(file);  
            out = response.getOutputStream();  
            int b = 0;  
            byte[] buffer = new byte[1024];  
            while ((b = inputStream.read(buffer)) != -1){  
                out.write(buffer,0,b);  
	            out.flush();
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            return "系统出错下载失败";
        } finally{
            try {
            	if (inputStream!=null) {
    				inputStream.close();
				}
            	if (out!=null) {
    	            out.close();
    	            out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
        	file.delete();
            for(int i=0;i<listFile.size();i++){
                System.out.println("------------"+listFile.get(i).getName() );
                File filetemp= listFile.get(i);
                filetemp.delete();
            }
        }
		return null;
	}
	
	//将多个文件压缩成一个zip文件
	private File writeZip(String url,List<File> listFile,String zipname){
        OutputStream os;
        ZipOutputStream zos = null;
		try {
			os = new BufferedOutputStream( new FileOutputStream( url+"/"+zipname+".zip" ) );
	        zos = new ZipOutputStream( os );
	        byte[] buf = new byte[8192];
	        int len;
	        for (int i=0;i<listFile.size();i++) {  
	            File file = listFile.get(i);
	            if ( !file.isFile() ) continue;
	            ZipEntry ze = new ZipEntry( file.getName() );
	            zos.putNextEntry( ze );
	            InputStream fileIn = new FileInputStream( file );
	            BufferedInputStream bis = new BufferedInputStream( fileIn );
	            while ( ( len = bis.read( buf ) ) > 0 ) {
	                zos.write( buf, 0, len );
	            }
	            bis.close();
	            fileIn.close();
	            zos.closeEntry();
	        }
	        zos.closeEntry();
	        zos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (zos!=null) {
					zos.close();
					zos.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        File zipFile = new File(url+"/"+zipname+".zip");
		return zipFile;
    }
	
	/**
	 * 
	 * @Title: fileDown 
	 * @Description: TODO(从FTP服务器直接下载文件) 
	 * @param @param request
	 * @param @param response
	 * @param @param ftpPath
	 * @param @param fileName
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String fileDown(HttpServletRequest request,HttpServletResponse response, String ftpPath, String fileName){
//		ftpPath = ftpPath.replace(ConfigConstants.BASE_UPLOAD_FILE_PATH.replace("/ftpfile", ""), "");
//		ftpPath = ftpPath.replace("/home/dboms", "");
		String ftpUser = ConfigConstants.FTP_SERVER_USER;
		String ftpPassword = ConfigConstants.FTP_SERVER_PASSWORD;//服务器密码
		String ftpHost = ConfigConstants.FTP_SERVER_IP;
		String ftpPort = ConfigConstants.FTP_SERVER_PORT;//ftp端口号
		
		response.setContentType("multipart/form-data");
        try {
			response.setHeader("Content-Disposition", "attachment;fileName="+new String( fileName.getBytes("utf-8"), "ISO8859-1" ));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        ServletOutputStream out = null;
        InputStream in = null;
        try {  
        	String fileUrl = "ftp://"+ftpUser+":"+ftpPassword+"@"+ftpHost+":"+ftpPort+ftpPath+"/"+fileName;
    		URL urlfile = new URL(fileUrl);
    		in = urlfile.openStream();
            out = response.getOutputStream();  
            int b = 0;  
            byte[] buffer = new byte[10*1024];  
            while ((b = in.read(buffer))!= -1){  
                out.write(buffer,0,b); 
                out.flush();
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            return "系统出错下载失败";
        } finally{
            try {
            	if (out!=null) {
    	            out.close();  
    	            out.flush();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}  
        }
		return null;
	}

	 public static void main(String[] args) throws Exception{
	  URL urlfile = new URL("ftp://dboms:123456@192.168.0.214/ftpfile/20160810110342_aaa.csv");
	  BufferedReader in = new BufferedReader(new InputStreamReader(urlfile.openStream(),"GB2312"));
	  String content="";
	  String inputLine =in.readLine();
	  while(inputLine!=null){
	   content +=inputLine;
	   inputLine =in.readLine();
	  }
	  System.out.println(content);
	  in.close();
	 }
}
