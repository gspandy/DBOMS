package com.tydic.dbs.commons.utils;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DataUtil {

	public static double byteToMB(long da){
		double d = da;
		double data = (d / 1024/1024);
		BigDecimal b   =   new   BigDecimal(data);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}