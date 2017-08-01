/**
 * com.tydic.dc.utils.CookieUtil.java
 */
package com.tydic.dc.utils;

/**
 * @file  CookieUtil.java
 * @author chenjiefeng
 * @version 0.1
 * @todo	TODO
 * Copyright(C), 2013-2014
 *			Guangzhou Sunrise Electronics Development Co., Ltd.
 * History
 *   	1. Date: 2013-5-31
 *      	Author: chenjiefeng
 *      	Modification: this file was created
 *   	2. ...
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CookieUtil {

	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge, String path) {
		try {
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
			cookie.setMaxAge(maxAge);
			if(path!=null){
				cookie.setPath(path);
			}
			response.addCookie(cookie);
			System.out.println("写cookie成功");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getValue(HttpServletRequest request, String name) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return value;
	}

	public static void delCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
