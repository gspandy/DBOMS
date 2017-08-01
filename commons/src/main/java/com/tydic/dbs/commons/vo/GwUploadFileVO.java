package com.tydic.dbs.commons.vo;

import java.io.Serializable;
import java.util.Date;

import com.tydic.commons.utils.BaseVO;

/**
 * 
 * @ClassName: GwUploadFileVO 
 * @Description: TODO(上传文件Vo类）
 * @author Zhongjialiang zhongjialiang@tydic.com 
 * @date 2016-7-14 上午10:30:29 
 *
 */
public class GwUploadFileVO extends BaseVO implements Serializable{
	
		private static final long serialVersionUID = 4955910311418547631L;

	 	private String fileId;

	    private String fileType;

	    private String filePath;

	    private String fileName;

	    private String fileStatus;

	    private Date createTime;

	    private String creater;

	    private Date modifyTime;

	    private String modifier;

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileStatus() {
			return fileStatus;
		}

		public void setFileStatus(String fileStatus) {
			this.fileStatus = fileStatus;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getCreater() {
			return creater;
		}

		public void setCreater(String creater) {
			this.creater = creater;
		}

		public Date getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}

		public String getModifier() {
			return modifier;
		}

		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
 
	    
}
