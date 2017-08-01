package com.tydic.dbs.commons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import com.tydic.dbs.system.basPhotoLib.mapper.BasPhotoLib;
import com.tydic.dbs.system.basPhotoLib.service.BasPhotoLibService;
import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;


/**
 * @file  FileUtil.java
 * @author shuyongfu
 * @version 0.1
 * @FileUtil文件处理工具类
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-03-26 14:00:15
 *      	Author: shuyongfu
 *      	Modification: this file was created
 *   	2. ...
 */
public class FileUtil {
	private final static Logger logger = Logger.getLogger(FileUtil.class);
	
	public final static String DEFAULT_CHARSET = "UTF-8";
	public final static String FILE_PATH_KEY = "filePath";
	public final static String FILE_NAME_KEY = "fileName";
	
	private BasPhotoLibService basPhotoLibService;

	/** 
	 * @return basPhotoLibService 
	 */
	public BasPhotoLibService getBasPhotoLibService() {
		return basPhotoLibService;
	}

	/** 
	 * @param basPhotoLibService 要设置的 basPhotoLibService 
	 */
	public void setBasPhotoLibService(BasPhotoLibService basPhotoLibService) {
		this.basPhotoLibService = basPhotoLibService;
	}

	//读取config.properties配置文件
	public static Properties properties = null;
	static{
		properties = new Properties();
		try{
			InputStream ins = FileUtil.class.getResourceAsStream("/config.properties");
			properties.load(ins);
			ins.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: saveHtml 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param str
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> saveHtml(String str) {
		String htmlPath = properties.getProperty("htmlSavePath");//无斜杠结尾
		return saveStr(str, htmlPath);
	}
	
	/**
	 * 
	 * @Title: saveStr 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param str
	 * @param @param path
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	private Map<String, String> saveStr(String str, String path) {
		if(logger.isDebugEnabled()){
			logger.debug("save content : " + str);
		}
		
		Map<String , String> map = new HashMap<String, String>();
		FileOutputStream  fos = null;
		try {
			String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());//日作为文件夹
			String dateTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//年月日时分秒作为文件名
			int randomNumber = new Random().nextInt(999999);
			
			createFilePath(path + "/" + dateStr);
			
			String fileName = dateTimeStr + randomNumber + ".html";
			String filePath = path + "/" + dateStr + "/" + fileName;//无斜杠结尾
			
			map.put(FILE_PATH_KEY, dateStr + "/" + fileName);
			
			fos = new FileOutputStream(filePath);
			fos.write(str.getBytes(DEFAULT_CHARSET));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 
	 * @Title: saveFile 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param file
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> saveFile(MultipartFile file) {
		String filePath = properties.getProperty("fileSavePath");//无斜杠结尾
		return saveFile(file, filePath);
	}
	
	/**
	 * 
	 * @Title: saveApp 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param file
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> saveApp(MultipartFile file) {
		String filePath = properties.getProperty("appSavePath");//无斜杠结尾
		return saveAppFile(file, filePath);
	}
	
	/**
	 * 
	 * @Title: saveFile 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param file
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> saveAppFile(MultipartFile file, String path) {
		Map<String , String> map = new HashMap<String, String>();
		InputStream ins = null;
		OutputStream ous = null;
		try {
			byte[] b = file.getBytes();
			ins = file.getInputStream();
			
			String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());//日作为文件夹
			String dateTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//年月日时分秒作为文件名
			int randomNumber = new Random().nextInt(999999);
			
			createFilePath(path + "/" + dateStr);

			String fileName = "";
			String originalName = file.getOriginalFilename();
			if(originalName.lastIndexOf(".") > -1){
				fileName = dateTimeStr + randomNumber + originalName.substring(originalName.lastIndexOf("."));
			}else{
				fileName = dateTimeStr + randomNumber;
			}
			
			String filePath = path + "/" + dateStr + "/" + fileName;//无斜杠结尾
			
			ous = new FileOutputStream(filePath);
			while((ins.read(b)) != -1){
				ous.write(b);
			}
			ous.flush();
			
			if(originalName.lastIndexOf("\\") != -1){
				originalName = originalName.substring(originalName.lastIndexOf("\\"));
			}else if(originalName.lastIndexOf("/") != -1){
				originalName = originalName.substring(originalName.lastIndexOf("/"));
			}
			if(logger.isDebugEnabled()){
				logger.debug("fileName:" + originalName);
			}
			map.put("conentType", file.getContentType());
			map.put("name", file.getName());
			map.put(FILE_NAME_KEY, originalName);
			map.put("size", String.valueOf(file.getSize()));
			map.put("fileFolder", dateStr);
			map.put(FILE_PATH_KEY, dateStr + "/" + fileName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ins.close();
				ous.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: saveFile 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param file
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> saveFile(MultipartFile file, String path) {
		Map<String , String> map = new HashMap<String, String>();
		InputStream ins = null;
		OutputStream ous = null;
		byte[] b = new byte[1024];
		try {
			ins = file.getInputStream();
			
			String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());//日作为文件夹
			String dateTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//年月日时分秒作为文件名
			int randomNumber = new Random().nextInt(999999);
			
			createFilePath(path + "/" + dateStr);

			String fileName = "";
			String originalName = file.getOriginalFilename();
			if(originalName.lastIndexOf(".") > -1){
				fileName = dateTimeStr + randomNumber + originalName.substring(originalName.lastIndexOf("."));
			}else{
				fileName = dateTimeStr + randomNumber;
			}
			
			String filePath = path + "/" + dateStr + "/" + fileName;//无斜杠结尾
			
			ous = new FileOutputStream(filePath);
			while((ins.read(b)) != -1){
				ous.write(b);
			}
			ous.flush();
			
			if(originalName.lastIndexOf("\\") != -1){
				originalName = originalName.substring(originalName.lastIndexOf("\\"));
			}else if(originalName.lastIndexOf("/") != -1){
				originalName = originalName.substring(originalName.lastIndexOf("/"));
			}
			if(logger.isDebugEnabled()){
				logger.debug("fileName:" + originalName);
			}
			map.put("conentType", file.getContentType());
			map.put("name", file.getName());
			map.put(FILE_NAME_KEY, originalName);
			map.put("size", String.valueOf(file.getSize()));
			map.put("fileFolder", dateStr);
			map.put(FILE_PATH_KEY, dateStr + "/" + fileName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ins.close();
				ous.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: savePhoto 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param file
	 * @param @param sizes
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> savePhoto(MultipartFile file, int[] sizes) {
		String photoPath = properties.getProperty("photoSavePath");//无斜杠结尾
		Map<String, String> map = saveFile(file, photoPath);
		
		String photoFile = map.get(FILE_PATH_KEY);
		
		if(ArrayUtils.isNotEmpty(sizes) && StringUtils.isNotEmpty(photoFile)){
			for(Integer size : sizes){
				String smallPhotoFile = photoPath + "/" + Integer.toString(size) + "/" + photoFile;
				String orgPhotoFile = photoPath + "/" + photoFile;
				createFilePath(photoPath + "/" + Integer.toString(size) + "/" + map.get("fileFolder"));
	    		try {
					Thumbnails.of(orgPhotoFile).size(size, size).toFile(smallPhotoFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: savePhoto 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param file
	 * @param @param sizes
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String, String> savePhoto(MultipartFile file, int[] size1,int[] size2,int[] size3) {
		String photoPath = properties.getProperty("photoSavePath");//无斜杠结尾
		Map<String, String> map = saveFile(file, photoPath);
		try {
			String photoFile = map.get(FILE_PATH_KEY);
			if(ArrayUtils.isNotEmpty(size1) && StringUtils.isNotEmpty(photoFile)){
				String smallPhotoFile = photoPath + "/" + Integer.toString(size1[0])+ "/" + photoFile;
				String orgPhotoFile = photoPath + "/" + photoFile;
				createFilePath(photoPath + "/" + Integer.toString(size1[0]) + "/" + map.get("fileFolder"));
	    		Thumbnails.of(orgPhotoFile).size(size1[0], size1[1]).keepAspectRatio(false).toFile(smallPhotoFile);
			}
			
			if(ArrayUtils.isNotEmpty(size2) && StringUtils.isNotEmpty(photoFile)){
				String smallPhotoFile = photoPath + "/" + Integer.toString(size2[0])+ "/" + photoFile;
				String orgPhotoFile = photoPath + "/" + photoFile;
				createFilePath(photoPath + "/" + Integer.toString(size2[0]) + "/" + map.get("fileFolder"));
	    		Thumbnails.of(orgPhotoFile).scale(0.65f).toFile(smallPhotoFile);
			}
			
			if(ArrayUtils.isNotEmpty(size3) && StringUtils.isNotEmpty(photoFile)){
				String smallPhotoFile = photoPath + "/" + Integer.toString(size3[0])+ "/" + photoFile;
				String orgPhotoFile = photoPath + "/" + photoFile;
				createFilePath(photoPath + "/" + Integer.toString(size3[0]) + "/" + map.get("fileFolder"));
	    		Thumbnails.of(orgPhotoFile).size(size3[0], size3[1]).keepAspectRatio(false).toFile(smallPhotoFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*if(ArrayUtils.isNotEmpty(widthSizes) && StringUtils.isNotEmpty(photoFile)){
			if(ArrayUtils.isNotEmpty(heightSizes) && StringUtils.isNotEmpty(photoFile)){
				for(Integer size : widthSizes){
					for(Integer size2 : heightSizes){
						String smallPhotoFile = photoPath + "/" + Integer.toString(size)+""+Integer.toString(size2)+ "/" + photoFile;
						String orgPhotoFile = photoPath + "/" + photoFile;
						createFilePath(photoPath + "/" + Integer.toString(size)+""+Integer.toString(size2) + "/" + map.get("fileFolder"));
			    		try {
							Thumbnails.of(orgPhotoFile).size(size,size2).toFile(smallPhotoFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}*/
		return map;
	}
	
	
	/**
	 * 文件保存
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public Map<String , String> savePhoto(MultipartFile file, String userId) throws Exception{
		Map<String , String> map = saveFile(file);
		BasPhotoLib basPhotoLib = new BasPhotoLib();
		basPhotoLib.setPhotoName(map.get(FILE_NAME_KEY));
		basPhotoLib.setPhotoPath(map.get(FILE_PATH_KEY));
		basPhotoLib.setCreater(userId);
		basPhotoLib = basPhotoLibService.savePhoto(basPhotoLib);
		map.put("photoId", basPhotoLib.getPhotoId().toString());
		return map;
	}
	
	/**
	 * 文件保存
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public Map<String , String> savePhoto(MultipartFile file, String userId,  int[] sizes) throws Exception{
		Map<String , String> map = savePhoto(file, sizes);
		BasPhotoLib basPhotoLib = new BasPhotoLib();
		basPhotoLib.setPhotoName(map.get(FILE_NAME_KEY));
		basPhotoLib.setPhotoPath(map.get(FILE_PATH_KEY));
		basPhotoLib.setCreater(userId);
		basPhotoLib = basPhotoLibService.savePhoto(basPhotoLib);
		map.put("photoId", basPhotoLib.getPhotoId().toString());
		return map;
	}
	
	
	/**
	 * 文件保存
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public Map<String , String> savePhoto(MultipartFile file, String userId,  int[] size1,int[] size2, int[] size3) throws Exception{
		Map<String , String> map = savePhoto(file, size1,size2,size3);
		BasPhotoLib basPhotoLib = new BasPhotoLib();
		basPhotoLib.setPhotoName(map.get(FILE_NAME_KEY));
		basPhotoLib.setPhotoPath(map.get(FILE_PATH_KEY));
		basPhotoLib.setCreater(userId);
		basPhotoLib = basPhotoLibService.savePhoto(basPhotoLib);
		map.put("photoId", basPhotoLib.getPhotoId().toString());
		return map;
	}
	
	/**
	 * @param url 需要生成二维码的地址
	 * @return
	 */
	public Map<String,Object> saveQRCodeUrl(String url){
		Map<String,Object> map=new HashMap<String,Object>();
		boolean flag=true;
		try{
			String imagePath = properties.getProperty("fileSavePath");//无斜杠结尾
			createFilePath(imagePath);
			String dateStr = "QRcode";
			String dateTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//年月日时分秒作为文件名
			int randomNumber = new Random().nextInt(999999);
			String fileName = dateTimeStr+randomNumber+"QRcode";//文件名称
			String filePath = imagePath + "/" + dateStr;//无斜杠结尾
			flag=QRCodeEncoder.qrcode(url, filePath, fileName);
			if(flag==true){
				String accessPath=properties.getProperty("photoAccessPath");
				map.put("photoPath",accessPath+"/"+dateStr+"/"+fileName+".jpg");
			}else{
				map.put("photoPath", "");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: createFilePath 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param foldName    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	private void createFilePath(String foldName){
		if(StringUtils.isNotEmpty(foldName)){
			File file = new File(foldName);
			if(file.exists()){
				if(file.isFile()){
					file.deleteOnExit();
					file.mkdir();
				}
			}else{
				file.mkdirs();
			}
		}
	}
	
	/**
	 * 对集合重新排序(冒泡,暂不用)
	 * @param list
	 * @param compareKeyName
	 * @return
	 */
	public List<Map<String,Object>> reSortList1(List<Map<String,Object>> list , String compareKeyName){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(list != null && list.size() > 0){
			Map<String,Object> compareMap = null;
			Map<String,Object> comparedMap = null;
			String lastCompareValue;
			String tempCompareValue;
			for (int i = 0; i < list.size(); i++) {
				compareMap = list.get(i);
				lastCompareValue = (String)compareMap.get(compareKeyName);
				for (int j = 1; j < list.size() -1; j++) {
					comparedMap = list.get(j);
					tempCompareValue = (String)compareMap.get(compareKeyName);
				}
			}
		}
		return resultList;
	}
	
	/**
	 * 对集合重新排序(用于销售商品-合约机-套餐数据排序)
	 * @param list
	 * @param compareKeyName
	 * @param compareKeyName1
	 * @return
	 */
	public List<Map<String,Object>> reSortList(List<Map<String,Object>> list , String compareKeyName , String compareKeyName1){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(list != null && list.size() > 0){
			List<PackageDataCompare> keyList = new ArrayList<PackageDataCompare>();
			for (Map<String , Object> map : list) {
				PackageDataCompare dcmp = new PackageDataCompare();
				dcmp.setMonthFee((Long)map.get(compareKeyName));
				dcmp.setPlanType((String)map.get(compareKeyName1));
				keyList.add(dcmp);
			}
			Collections.sort(keyList);
			
			List<PackageDataCompare> newKeyList = new ArrayList<PackageDataCompare>();//定义新的变量，去除keyList中的重复部分
			for(PackageDataCompare tempObj : keyList){
				boolean exitsKey = false;//key是否已经存在
				for(PackageDataCompare tempObjNew : newKeyList){
					if(tempObjNew.getMonthFee().equals(tempObj.getMonthFee()) && tempObjNew.getPlanType().equals(tempObj.getPlanType())){
						exitsKey = true;
						break;
					}
				}
				
				if(!exitsKey){//如果不存在，则加入到新的数组中
					newKeyList.add(tempObj);
				}
			}
			
			for (PackageDataCompare key : newKeyList) {
				for (Map<String , Object> map : list) {
					if(key.getMonthFee().equals(map.get(compareKeyName)) && key.getPlanType().equals(map.get(compareKeyName1))){
						resultList.add(map);
						//break;
					}
				}
			}
		}
		return resultList;
	}
	
	/**
	 * 保存文件到服务器临时路径
	 * 
	 * @param fileName
	 * @param is
	 * @return 文件全路径
	 */
	public static String writeFile(String fileName, InputStream is) {
		if (fileName == null || fileName.trim().length() == 0) {
			return null;
		}
		try {
			/** 首先保存到临时文件 */
			FileOutputStream fos = new FileOutputStream(fileName);
			byte[] readBytes = new byte[512];// 缓冲大小
			int readed = 0;
			while ((readed = is.read(readBytes)) > 0) {
				fos.write(readBytes, 0, readed);
			}
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	 * 功能：Java读取文件的内容
	 * @param filePath
	 * @return String 文件的内容
	 */
	public static String readFile(String filePath) {
		String str = "";
        try {
        	String imagePath = properties.getProperty("htmlSavePath");//无斜杠结尾
        	filePath = imagePath + "//" + filePath;
        	
        	str = FileUtils.readFileToString(new File(filePath), DEFAULT_CHARSET);
        	
            /*Scanner in = new Scanner(new File(filePath));
            
            while (in.hasNextLine()) {
                str += in.nextLine();
            }
            in.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
	
	public HashMap<String, Object> getPicProperty(MultipartFile file) throws Exception{
		InputStream ins = file.getInputStream();
    	BufferedImage sourceImg =ImageIO.read(ins);
    	System.out.println("图片大小："+file.getSize());
		System.out.println("图片宽度："+sourceImg.getWidth());
		System.out.println("图片高度："+sourceImg.getHeight());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("picSize", file.getSize());
		map.put("picWidth", sourceImg.getWidth());
		map.put("picHeight", sourceImg.getHeight());
		
		return map;
	}
	
}
