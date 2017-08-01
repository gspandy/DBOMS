package com.tydic.dbs.dbsweb.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.tydic.dbs.commons.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;


public class FileUnil {
		private final static Logger logger = Logger.getLogger(FileUtil.class);
	
		public final static String DEFAULT_CHARSET = "UTF-8";
	    public final static String FILE_PATH_KEY = "filePath";
		public final static String FILE_NAME_KEY = "fileName";
		
	/*
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

	//读取config.properties配置文件
	public static Properties properties = null;
	static{
		properties = new Properties();
		try{
			InputStream ins = FileUnil.class.getResourceAsStream("/conf/dbs-config.properties");
			properties.load(ins);
			ins.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
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
}
