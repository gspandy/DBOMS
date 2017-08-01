package com.tydic.dbs.commons.utils;

import java.io.*;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.fins.gt.export.CsvReader;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.tydic.dbs.commons.constant.ConfigConstants;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/**
 * @author zjl
 * @date 2016/7/18 11:35.
 */
public class FtpUpFile {
    private final static Log logger= LogFactory.getLog(FtpUpFile.class);
    private String host;
    private int port;
    private String username;
    private char [] password;
	private String ftpRootPath;
	private MultipartFile file;
    private static FtpClient ftpClient = null;

    public FtpUpFile(String host,int port, String username, char[] password, String ftpRootPath2, MultipartFile file2) {
        this.host = host;
        this.port=port;
        this.username = username;
        this.password = password;
        this.ftpRootPath = ftpRootPath;
   	 	this.file = file;
    }

    public FtpUpFile(String host,int port, String username, char[] password) {
        this.host = host;
        this.port=port;
        this.username = username;
        this.password = password;
    }

	/**
     * 打开ftp连接
     * @return
     */
    public FtpClient openFtpConn() {
        try {
            //开始登录ftp服务器
            InetSocketAddress address=new InetSocketAddress(host,port);
            ftpClient = FtpClient.create(address);
            ftpClient.setConnectTimeout(1000*30);//10秒连接超时时间
            ftpClient.login(username, password);
            ftpClient.enablePassiveMode(true);// 用2进制上传、下载
            logger.info("登录ftp["+host+"]服务器成功!");
        } catch (FtpProtocolException e) {
            logger.error("登录ftp服务器异常:"+e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        return ftpClient;
    }

    /**
     * 关闭ftp连接
     */
    public void closeFtpConn() {
        if (null!=ftpClient&&ftpClient.isConnected()) {
            try {
                ftpClient.close();
                ftpClient = null;
            } catch (IOException e) {
                logger.error("关闭ftp连接异常:"+e.getMessage(),e);
            }
        }
    }
    
    /**
     * 
     * @Title: DelFile 
     * @Description: TODO(删除FTP上指定的文件) 
     * @param @param filePath 文件在FTP上的路径
     * @param @param fileName 文件名称
     * @param @return    设定文件 
     * @return Boolean    返回类型 
     * @throws
     */
    @SuppressWarnings("restriction")
	public Boolean DelFile(String filePath,String fileName){
    	try {
        	openFtpConn();
			ftpClient.changeDirectory(filePath);
			logger.info("cd ["+filePath+"] success");
			ftpClient.deleteFile(fileName);
			logger.info("remove ["+fileName+"] success");
	    	return true;
		} catch (Exception e) {
			e.printStackTrace();
	    	return false;
		} finally {
            closeFtpConn();
		}
    }

    
    public List<File> ftpGetFile(String remotePath,String [] fileNames,String localPath,boolean removeFile){
        List<File> fileList = new ArrayList<File>();
        try {
            openFtpConn();
            ftpClient.changeDirectory(remotePath);
            logger.info("cd ["+remotePath+"] success");
            ftpClient.setBinaryType();

            for (String fileName : fileNames) {
                 File localFile = new File(localPath, fileName);
                 OutputStream is = new FileOutputStream(localFile);
                 try {
                	 ftpClient.getFile(fileName, is);//接收文件
                     logger.info("Get file["+remotePath+fileName+"] to ["+localFile.getPath()+"] success!");
                     fileList.add(localFile);
                 } catch(java.io.FileNotFoundException e)
                 {
                	 logger.error("FTP file not exists!");
                 }catch (IOException e) {}finally{
                	 is.flush();
                	 is.close();
                     is=null;
                 }
                 
                 //move file
                 if(removeFile) {
                     ftpClient.deleteFile(fileName);//删除了远程文件才算成功
                     logger.info("Delete the remote file ["+remotePath+fileName+"] success!");
                 }
                
            }
        }
        catch (FtpProtocolException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            closeFtpConn();
        }
        return fileList;
    }

    /**
     * FTP上传文件
     * @param fileDir       本地文件目录
     * @param ftpRootPath   上传到ftp路径
     * @param chilDir       上传到ftp的子路径
     * @param fileNames     上传文件名
     * @param deleteFile    删除文件标识
     * @return
     **/
    public boolean ftpPutFile(File fileDir, String ftpRootPath,String chilDir, String[] fileNames, boolean deleteFile) {
        boolean result = false;
        try {
            openFtpConn();
            if (fileDir.isDirectory()) {
                if (null != ftpClient && ftpClient.isConnected()) {
                    ftpClient.changeDirectory(ftpRootPath);
                    logger.info("cd [" + ftpRootPath + "] success!");

                    if (StringUtils.isNotBlank(chilDir)) {
                        try {
                            mkdir(ftpRootPath,chilDir,false);

                            ftpClient.changeDirectory(ftpRootPath+chilDir);
                            logger.info("cd [" + chilDir + "] success!");
                        }catch(FtpProtocolException e){
                            logger.error("创建异常:"+e.getMessage(),e);
                        }
                    }
                    //上传一个标志文件
                    ftpClient.setBinaryType();
                    File[] fileList = fileDir.listFiles();
                    for (File file1 : fileList) {
                        for (String fileName : fileNames) {
                            if (file1.isFile() && file1.getName().equals(fileName)) {
                                String fileTmpName = file1.getName() + ".TMP";
                                FileInputStream input = new FileInputStream(file1);
                                logger.info("Upload file [" + file1.getPath() + "] ...");
                                ftpClient.putFile(fileTmpName, input);
                                try {
                                    input.close();
                                } catch (IOException ioe) {

                                }
                                ftpClient.rename(fileTmpName, file1.getName());
                                logger.info("Upload file [" + file1.getPath() + "] success!");
                                if (deleteFile) {
                                    file1.delete();
                                    logger.info("Delete file [" + file1.getPath() + "] success!");
                                }
                            }
                        }
                    }

                    result = true;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeFtpConn();
        }
        return result;
    }

    public boolean ftpPutFile1(String ftpRootPath, MultipartFile file) {
        boolean result = false;
        try {
            openFtpConn();
            if (null != ftpClient && ftpClient.isConnected()) {
                ftpClient.changeDirectory(ftpRootPath);
                logger.info("cd [" + ftpRootPath + "] success!");
            }
            //上传一个标志文件
            ftpClient.setBinaryType();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssss");
            String fileData =formatter.format(new Date()) ;
            String fileTmpName = fileData+"_"+file.getOriginalFilename();
          //  FileInputStream input = (FileInputStream);
            
            logger.info("Upload file [" + file.getName() + "] ...");
            ftpClient.putFile(fileTmpName, file.getInputStream());
            
            /*ftpClient.rename(fileTmpName, file.getName());
            logger.info("Upload file  success!");*/
            result = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeFtpConn();
        }
        return result;
    }
    
    public boolean ftpPutPrdFile(String ftpRootPath, String fileName, MultipartFile file) {
        boolean result = false;
        try {
            openFtpConn();
            if (null != ftpClient && ftpClient.isConnected()) {
                ftpClient.changeDirectory(ftpRootPath);
                logger.info("cd [" + ftpRootPath + "] success!");
            }
            //上传一个标志文件
            ftpClient.setBinaryType();
          //  FileInputStream input = (FileInputStream);
            
            logger.info("Upload file [" + file.getName() + "] ...");
            ftpClient.putFile(fileName, file.getInputStream());
            
            /*ftpClient.rename(fileTmpName, file.getName());
            logger.info("Upload file  success!");*/
            result = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeFtpConn();
        }
        return result;
    }
    public boolean ftpPutPrdFile(String ftpRootPath, String fileName, InputStream file) {
        boolean result = false;
        try {
            openFtpConn();
            if (null != ftpClient && ftpClient.isConnected()) {
                ftpClient.changeDirectory(ftpRootPath);
                logger.info("cd [" + ftpRootPath + "] success!");
            }
            //上传一个标志文件
            ftpClient.setBinaryType();
            //  FileInputStream input = (FileInputStream);

            logger.info("Upload file [" + " -- " + "] ...");
            ftpClient.putFile(fileName, file);

            /*ftpClient.rename(fileTmpName, file.getName());
            logger.info("Upload file  success!");*/
            result = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeFtpConn();
        }
        return result;
    }

    public boolean putFile(String ftpRootPath, String childPath, String fileName, InputStream file) {
        boolean result = false;

        try {
            openFtpConn();
            if (null != ftpClient && ftpClient.isConnected()) {

                ftpClient.changeDirectory(ftpRootPath);
                logger.info("cd [" + ftpRootPath + "] success!");
            }

            if (StringUtils.isNotBlank(childPath)) {
                try {
                    mkdir(ftpRootPath,childPath,false);

                    ftpClient.changeDirectory(ftpRootPath+"/"+childPath);
                    logger.info("cd [" + childPath + "] success!");
                }catch(FtpProtocolException e){
                    logger.error("创建异常:"+e.getMessage(),e);
                }
            }
            //上传一个标志文件
            ftpClient.setBinaryType();

            logger.info("Upload file [" + " -- " + "] ...");
            ftpClient.putFile(fileName, file);

            logger.info("Upload file  success!");
            result = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeFtpConn();
        }
        return result;
    }



    /**
     * 检查文件夹在当前目录下是否存在
     * @param dir
     * @return
     */
    private boolean isDirExists(String dir){
        try {
            logger.debug("dir : "+dir);
            ftpClient.changeDirectory(dir);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * 创建FTP目录
     * @param rootDir FTP根目录
     * @param chilDir 要创建的FTP子目录
     * @param isNewConn  是否启用新的FTP连接
     * @return
     */
    public boolean mkdir(String rootDir,String chilDir ,boolean isNewConn) {
        try {
            if (isNewConn) {
                openFtpConn();
            }
            ftpClient.setAsciiType();
            StringTokenizer s = new StringTokenizer(chilDir, "/");
            s.countTokens();

            String pathName = rootDir;
            while (s.hasMoreElements()) {
                pathName = pathName + "/" + (String) s.nextElement();
                try {
                    if (!isDirExists(pathName)) {
                        ftpClient.makeDirectory(pathName);
                        logger.info("mkdir [" + pathName + "] success!");
                    }else{
                        logger.info("[" + pathName + "] is exists!");
                    }
                } catch (Exception e) {
                    logger.error("mkdir [" + pathName + "] error: " + e.getMessage(), e);
                    return false;
                }
            }
            ftpClient.setBinaryType();
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (FtpProtocolException e) {
            e.printStackTrace();
        } finally {
            if (isNewConn) {

                closeFtpConn();
            }
        }
        return false;
    }

    public long getFtpFileRows(String remotePath,String fileName){
        long rowCount= 0;
        InputStream ins = null;
        try {
            openFtpConn();
            ftpClient.changeDirectory(remotePath);
            logger.info("cd ["+remotePath+"] success");
            ftpClient.setBinaryType();

            ins = ftpClient.getFileStream(fileName);

            BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "utf-8"));
            String line="";

            while ((line = reader.readLine()) != null) {
                ++rowCount;
            }
            reader.close();
            if (ins != null) {
                ins.close();
            }

        }
        catch (FtpProtocolException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            closeFtpConn();
        }
        return rowCount;
    }

    /**
     * 读取远程FTP文件
     * @param remotePath
     * @param fileName
     * @return
     */
    public String readCSV(String remotePath,String fileName){
        StringBuffer buffer = new StringBuffer("");
        InputStream ins = null;
        try {
            openFtpConn();
            ftpClient.changeDirectory(remotePath);
            logger.info("cd ["+remotePath+"] success");
            ftpClient.setBinaryType();

            ins = ftpClient.getFileStream(fileName);

            BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "gb2312"));

            CsvReader creader = new CsvReader(reader, '\n');

            while (creader.readRecord()) {
                buffer.append(creader.getRawRecord()).append("\n");
            }
            creader.close();
//            String line="";
//            while ((line = reader.readLine()) != null) {
//               buffer.append(line);
//            }
            creader.close();
            if (ins != null) {
                ins.close();
            }

        }
        catch (FtpProtocolException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            closeFtpConn();
        }
        return buffer.toString();
    }

    public long getFtpFileSize(String remotePath,String fileName){
        long fileSize= 0;
        try {
            openFtpConn();
            ftpClient.changeDirectory(remotePath);
            logger.info("cd ["+remotePath+"] success");
            ftpClient.setBinaryType();

            fileSize = ftpClient.getSize(fileName);
        }
        catch (FtpProtocolException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            closeFtpConn();
        }
        return fileSize;
    }




    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    
    public String getFtpRootPath() {
		return ftpRootPath;
	}

	public void setFtpRootPath(String ftpRootPath) {
		this.ftpRootPath = ftpRootPath;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public static void main(String[] args) {
    	char[]  cha = {'1','2','3','4','5','6'};
   /* 	FtpUpFile ftp = new FtpUpFile("192.168.0.214",21,"dboms",cha,);*/
    	File fileDir = new File("C:\\Users\\Administrator\\Desktop\\");
		String ftpRootPath ="/home/dboms/ftpfile";
		String chilDir = "";
		String[] fileNames = new String[]{"sms.rar"};
		boolean deleteFile = false;
	/*	Boolean ftpisFile =  ftp.ftpPutFile(fileDir, ftpRootPath, chilDir, fileNames, deleteFile);
		System.out.println("---------------------"+ftpisFile);*/
	}
}
