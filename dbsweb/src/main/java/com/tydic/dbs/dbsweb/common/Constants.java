/*
 * com.tydic.dbs.obh.common  2014-12-26
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.dbsweb.common;

/**
 * 
 * @ClassName: Constants 
 * @Description: TODO(公有常量) 
 * @author Michael dengwenjie@tydic.com 
 * @date 2016-8-18 下午3:00:35 
 *
 */
public class Constants {

	/**用户登录session key**/
	public static final String LOGIN_SESSION_MEMBER_KEY="loginMember";
	/**用户未登录session key**/
	public static final String VISIT_SESSION_MEMBER_KEY="visitMember";
	
	/**前端验证码session key**/
	public static final String VALCODE_SESSION_KEY="obhValCode";
	
	/**账户密码登录方式**/
	public static final int LOGIN_TYPE_SERVICE=1;
	/**手机,密码，短信验证码登录方式**/
	public static final int LOGIN_TYPE_SMS=2;
	/**邮箱，密码登录方式**/
	public static final int LOGIN_TYPE_MAIL=3;
	
	/**登录购买**/
	public static final Integer LOGIN_BUY=1;
	/**免登录购买**/
	public static final Integer NO_LOGIN_BUY=0;
	
	public static  final String IT_STATUS_DRAFT="0";//it资源状态

	public static final String SUCCESS="1";
	public static final String ERROR="0";

	public  static  final  String ROLE_STATUS_VALID ="1";//有效
	public  static  final  String ROLE_STATUS_INVALID ="0";//失效

	public static final String OPER_SAVE="0";
	public static final String OPER_SUBMIT="1";
	/***工单状态***/
	public static final String ORDER_SUBMITED="2";//已提交工单
	public static final String ORDER_PRE_SUBMIT="1";//待提交工单
	public static final String ORDER_COMMITED="3";//已执行工单
	
	/***产品状态***/
	public static final String PRODUCT_AUDITED="2";//已执行工单

	/**来源应用平台ID**/
	public static  final String SYSTEM_ID="0001";

	/**产品状态**/
	public static final String PRD_STATUS="2";

	/*工单申请提交时状态*/
	public static final String ORDER_APPLY_SAVE="11";//新增保存
	public static final String ORDER_APPLY_SUBMIT="10";//新增提交
	public static final String ORDER_APPLY_UPDATE_SAVE="01";//修改保存
	public static final String ORDER_APPLY_UPDATE_SUBMIT="00";//修改提交
	
	public static final String ORDER_SUBMIT="3";//提交

	public static final String NEW_STATUS="1";
	public static final String UPDATE_STATUS="0";

	public static final String PAGE_SIZE="10";
	/**APP缩图长宽*/
	public static int PHOTO_SMALL_PHOTO_124 = 124;
	/**APP缩图长宽*/
	public static int PHOTO_SMALL_PHOTO_400 = 400;

}
