package com.tydic.dbs.commons.constant;

/**
 *<p>File: WcsSession.java</p>
 *<p>Description: 系统共用session变量定义</p>
 *<p>Company: 从兴技术有限公司</p>
 *<p>Author: 刘高林</p>
 *<p>Version: 1.0,2014-3-24 下午8:44:33,刘高林,Ins</p>
 *
 */
public class WcsSessionConstant {

	/**session中当前用户对象的名称*/
	public static final String SESSION_OPERATOR="operator";
	/**session中验证码的名称*/
	public static final String SESSION_VALIDATE_CODE="validateCode";
	/**session中当前用户登录日志id的名称*/
	public static final String SESSION_LOGIN_LOG_ID="loginLogId";
	/**对密码进行3DES加密的key*/
	public static final String SPKEY_PASSWORD="WCS";
	/**session中当前登录用户所拥有操作菜单数据*/
	public static final String SESSION_MUNE="mune";
	/**session中当前登录用户所拥有的功能操作数据*/
	public static final String SESSION_FUN_OPERATE="funOperate";
	/**session中当前登录用户所属的渠道ID*/
	public static final String SESSION_OPERATOR_CHANNEL = "channelId";
	/**session中当前登录用户所属的组织机构地市*/
	public static final String OPERATOR_REGION_CODE = "regionCode";
	/**session中的cps*/
	public static final String SESSION_CPS = "cps";
	
}
