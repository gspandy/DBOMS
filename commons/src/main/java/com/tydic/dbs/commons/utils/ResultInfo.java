/**
 * 
 */
package com.tydic.dbs.commons.utils;

/**返回操作信息
 * <p>Title: </p>
 * <p>Description:   </p>
 * <p>Company: 从兴技术有限公司 </p>
 * @author: yuchanghong
 * @version 1.0
 * @date: 2014-7-28 下午4:44:10
 */

@SuppressWarnings("serial")
public class ResultInfo implements java.io.Serializable{
	private boolean result;//操作标识
	private int resultCode;//操作标识code
	private String info;//操作内容
	/**
	 * @return the result
	 */
	public boolean getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
}
