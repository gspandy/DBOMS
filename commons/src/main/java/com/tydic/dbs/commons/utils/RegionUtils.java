/**
 * RegionUtils
 */
package com.tydic.dbs.commons.utils;


/**
 * <p>Title: </p>
 * <p>Description:   </p>
 * <p>Company: 从兴技术有限公司 </p>
 * @author: caipeimin
 * @version 1.0
 * @date: 2014-7-30 09:31:09
 */

public class RegionUtils {
    /**
	 * 转换成BSS的地市编码
	 * @param regionCode 沃云购平台使用的地市编码
	 * @return
	 */
	public static String convertToBssEparchyCode(String regionCode) {
		String code = "";
	    if (regionCode.equals("440100"))//广州
	    	  code = "510";
	    else if (regionCode.equals("440300"))//深圳
		      code = "540";
	    else if (regionCode.equals("441900"))//东莞
		      code = "580";
	    else if (regionCode.equals("440400"))//珠海
		      code = "620";
	    else if (regionCode.equals("442000"))//中山
		      code = "556";
	    else if (regionCode.equals("440600"))//佛山
		      code = "530";
	    else if (regionCode.equals("441300"))//惠州
		      code = "570";
	    else if (regionCode.equals("440700"))//江门
		      code = "550";
	    else if (regionCode.equals("440200"))//韶关
		      code = "558";
	    else if (regionCode.equals("441800"))//清远
		      code = "535";
	    else if (regionCode.equals("440500"))//汕头
		      code = "560";
	    else if (regionCode.equals("445100"))//潮州
	    	  code = "531";
	    else if (regionCode.equals("441500"))//汕尾
		      code = "525";
	    else if (regionCode.equals("445200"))//揭阳
		      code = "526";
	    else if (regionCode.equals("441400"))//梅州
		      code = "528";
	    else if (regionCode.equals("441600"))//河源
	    	  code = "670";
	    else if (regionCode.equals("445300"))//云浮
		      code = "538";
	    else if (regionCode.equals("441200"))//肇庆
		      code = "536";
	    else if (regionCode.equals("441700"))//阳江
		      code = "565";
	    else if (regionCode.equals("440900"))//茂名
		      code = "568";
	    else if (regionCode.equals("440800"))//湛江
		      code = "520";
		return code;
	}
	
    /**
	 * 转换成AOP的地市编码
	 * @param regionCode 沃云购平台使用的地市编码
	 * @return
	 */
	public static String convertToAopRegionCode(String regionCode) {
		String code = "";
	    if (regionCode.equals("440100"))//广州
	    	  code = "0020";
	    else if (regionCode.equals("440300"))//深圳
		      code = "0755";
	    else if (regionCode.equals("441900"))//东莞
		      code = "0769";
	    else if (regionCode.equals("440400"))//珠海
		      code = "0756";
	    else if (regionCode.equals("442000"))//中山
		      code = "0760";
	    else if (regionCode.equals("440600"))//佛山
		      code = "0757";
	    else if (regionCode.equals("441300"))//惠州
		      code = "0752";
	    else if (regionCode.equals("440700"))//江门
		      code = "0750";
	    else if (regionCode.equals("440200"))//韶关
		      code = "0751";
	    else if (regionCode.equals("441800"))//清远
		      code = "0763";
	    else if (regionCode.equals("440500"))//汕头
		      code = "0754";
	    else if (regionCode.equals("445100"))//潮州
	    	  code = "0768";
	    else if (regionCode.equals("441500"))//汕尾
		      code = "0660";
	    else if (regionCode.equals("445200"))//揭阳
		      code = "0663";
	    else if (regionCode.equals("441400"))//梅州
		      code = "0753";
	    else if (regionCode.equals("441600"))//河源
	    	  code = "0762";
	    else if (regionCode.equals("445300"))//云浮
		      code = "0766";
	    else if (regionCode.equals("441200"))//肇庆
		      code = "0758";
	    else if (regionCode.equals("441700"))//阳江
		      code = "0662";
	    else if (regionCode.equals("440900"))//茂名
		      code = "0668";
	    else if (regionCode.equals("440800"))//湛江
		      code = "0759";
		return code;
	}
	
	
	 /**
		 * 转换成新商城的地市编码
		 * @param regionCode 沃云购平台使用的地市编码
		 * @return
		 */
		public static String convertToRegionCode(String regionCode) {
			String code = "";
		    if (regionCode.equals("0020"))//广州
		    	  code = "440100";
		    else if (regionCode.equals("0755"))//深圳
			      code = "440300";
		    else if (regionCode.equals("0769"))//东莞
			      code = "441900";
		    else if (regionCode.equals("0756"))//珠海
			      code = "440400";
		    else if (regionCode.equals("0760"))//中山
			      code = "442000";
		    else if (regionCode.equals("0757"))//佛山
			      code = "440600";
		    else if (regionCode.equals("0752"))//惠州
			      code = "441300";
		    else if (regionCode.equals("0750"))//江门
			      code = "440700";
		    else if (regionCode.equals("0751"))//韶关
			      code = "440200";
		    else if (regionCode.equals("0763"))//清远
			      code = "441800";
		    else if (regionCode.equals("0754"))//汕头
			      code = "440500";
		    else if (regionCode.equals("0768"))//潮州
		    	  code = "445100";
		    else if (regionCode.equals("0660"))//汕尾
			      code = "441500";
		    else if (regionCode.equals("0663"))//揭阳
			      code = "445200";
		    else if (regionCode.equals("0753"))//梅州
			      code = "441400";
		    else if (regionCode.equals("0762"))//河源
		    	  code = "441600";
		    else if (regionCode.equals("0766"))//云浮
			      code = "445300";
		    else if (regionCode.equals("0758"))//肇庆
			      code = "441200";
		    else if (regionCode.equals("0662"))//阳江
			      code = "441700";
		    else if (regionCode.equals("0668"))//茂名
			      code = "440900";
		    else if (regionCode.equals("0759"))//湛江
			      code = "440800";
			return code;
		}
}
