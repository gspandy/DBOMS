/*
 * com.tydic.dbs.obh.controller  2014-12-31
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.dbsweb.controller;

import com.tydic.dbs.commons.vo.LoginMemberVo;
import com.tydic.dbs.dbsweb.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/** 
 * @ClassName: ErrorController 
 * @Description: 错误控制器 
 * @author zjl
 * @date 2016-7-29
 *  
 */
@Controller
public class ErrorController extends BaseController{

	/****
	 * 
	 * @Title: notFind 
	 * @Description: 404错误 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
    @RequestMapping("/404")
    public String notFind( HttpServletRequest request){
        //判断用户是否登录
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        if(loginMemberVo==null){
            return "error/error2";
        }else{
            return "error/exception";
        }


    }
    
    @RequestMapping("/500")
    public String sysError(HttpServletRequest request,ModelMap model){
        //判断用户是否登录
        LoginMemberVo loginMemberVo = (LoginMemberVo) request.getSession().getAttribute(Constants.LOGIN_SESSION_MEMBER_KEY);
        if(loginMemberVo==null){
            return "error/error2";
        }else{
            return "error/exception";
        }
    }
}
