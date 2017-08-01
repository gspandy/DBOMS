/*
 * com.tydic.dbs.commons.utils  2015-1-14
 *
 * Copyright 2010 Shenzhen TYDIC Information Technology Co.,Ltd.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.tydic.dbs.commons.utils;

import org.apache.commons.lang3.StringUtils;

/** 
 * @ClassName: SqlUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Carson yuchanghong@tydic.com 
 * @date 2015-1-14 下午6:40:35 
 *  
 */
public class SqlUtil {

	public static final String H = "#";
	public static final String S = "$";
	
	/**
	 * mapper.xml中的取值方式为#{}时
	 * @param str like的查询条件
	 * @return
	 */
	public static String likeEscapeH(String str) {
		return likeEscapeZ(str, H, true, true);
	}
	
	/**
	 * mapper.xml中的取值方式为${}时
	 * @param str like的查询条件
	 * @return
	 */
	public static String likeEscapeS(String str) {
		return likeEscapeZ(str, S, true, true);
	}
	
	/**
	 * @param str   like的查询条件
	 * @param type  mapper.xml中的取值方式，只能“#”或“$”
	 * @param start 字符串前部是否拼接“%”
	 * @param end   字符串尾部是否拼接“%”
	 * @return
	 */
	public static String likeEscapeZ(String str, String type, boolean start, boolean end) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		// 拼接顺序不能改变
		if (S.equals(type)) {
			buffer.append(" '");
		}
		if (start) {
			buffer.append("%");
		}
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			switch (c) {
			case '\'':
				if (S.equals(type)) {
					buffer.append("''");// 单引号替换成两个单引号
				} else {
					buffer.append(c);
				}
				break;
			case '*':
				buffer.append("\\*");
				break;
			case '_':
				buffer.append("\\_");
				break;
			case '%':
				buffer.append("\\%");
				break;
			default:
				buffer.append(c);
			}
		}
		
		if (end) {
			buffer.append("%");
		}
		if (S.equals(type)) {
			buffer.append("' ");
		}
		return buffer.toString();
	}
		
}
