/**
 * com.tydic.commons.web.BaseAction.java
 */
package com.tydic.commons.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.tydic.commons.utils.AjaxResult;
import com.tydic.dc.utils.TimeStampPropertyEditor;

/**
* @file  BaseAction.java
* @author weishaojia(viscar)
* @version 0.1
* @todo 控制器基类
* Copyright(C), 2013-2014
*			Guangzhou Sunrise Electronics Development Co., Ltd.
* History
*   	1. Date: 2013-4-18
*      	Author: weishaojia(viscar)
*      	Modification: this file was created
*   	2. ...
*/
public abstract class BaseAction extends MultiActionController {
	protected final Log log = LogFactory.getLog(BaseAction.class);
	
	protected static final String SESSION_CHECK_CODE = "checkcode";
	protected static final String SESSION_LOGINED_USER_ID = "LoginedUserId";
	protected static final String SESSION_LOGINED_USER_NAME = "LoginedUserName";
	protected static final String SESSION_USER_NOT_LOGIN = "userNotLogin";
	/**
	 * 业务视图菜单权限
	 */
	protected static final String SESSION_LOGINED_USER_MENU = "LoginedUserMenu";
	/**
	 * 管理视图菜单权限
	 */
	protected static final String SESSION_LOGINED_USER_MENU_GL = "LoginedUserMenu_GL";
	/**
	 * 系统管理菜单权限
	 * */
	protected static final String SESSION_LOGINED_USER_MENU_SYSTEM = "LoginedUserMenu_SYSTEM";
	final static protected String errorResult = new AjaxResult("1").toString();
	final static protected String successResult = new AjaxResult("0")
			.toString();

	public String getIndexUrl() {
		return null;
	};

	public void asyncReturnResult(HttpServletResponse response, String msg) {
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset=utf-8");//注意返回的内容格式
			PrintWriter pw = response.getWriter();
			pw.print(msg);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.multiaction.MultiActionController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// TODO Auto-generated method stub
		//yyy/mmdd yyy-mm-dd yy-mm-dd HH:mm:ss
		binder.registerCustomEditor(Timestamp.class,
				new TimeStampPropertyEditor());
		super.initBinder(request, binder);
	}

	/**
	 * 主界面
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug(getIndexUrl());

		return new ModelAndView(getIndexUrl());
	}

}
