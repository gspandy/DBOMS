package com.tydic.dbs.commons.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * bss政策包接口联调的参数转换
 * @author X230
 *
 */
public class WcsBssPolicy {
	/**地市编码*/
    public static final String CITY_GZ="440100";//广州  
    public static final String CITY_SG="440200";//韶关
    public static final String CITY_SZ="440300";//深圳
    public static final String CITY_ZH="440400";//珠海
    public static final String CITY_ST="440500";//汕头
    public static final String CITY_FS="440600";//佛山
    public static final String CITY_JM="440700";//江门
    public static final String CITY_ZJ="440800";//湛江
    public static final String CITY_MM="440900";//茂名
    public static final String CITY_ZQ="441200";//肇庆
    public static final String CITY_HZ="441300";//惠州
    public static final String CITY_MZ="441400";//梅州
    public static final String CITY_SW="441500";//汕尾
    public static final String CITY_HY="441600";//河源
    public static final String CITY_YJ="441700";//阳江
    public static final String CITY_QY="441800";//清远
    public static final String CITY_DG="441900";//东莞
    public static final String CITY_ZS="442000";//中山
    public static final String CITY_CZ="445100";//潮州 
    public static final String CITY_JY="445200";//揭阳
    public static final String CITY_YF="445300";//云浮
    
    /**外部地市编码*/
    public static final String GZ="0020";//广州  
    public static final String SG="0751";//韶关
    public static final String SZ="0755";//深圳
    public static final String ZH="0756";//珠海
    public static final String ST="0754";//汕头
    public static final String FS="0757";//佛山
    public static final String JM="0750";//江门
    public static final String ZJ="0759";//湛江
    public static final String MM="0668";//茂名
    public static final String ZQ="0758";//肇庆
    public static final String HZ="0752";//惠州
    public static final String MZ="0753";//梅州
    public static final String SW="0660";//汕尾
    public static final String HY="0762";//河源
    public static final String YJ="0662";//阳江
    public static final String QY="0763";//清远
    public static final String DG="0769";//东莞
    public static final String ZS="0760";//中山
    public static final String CZ="0768";//潮州 
    public static final String JY="0663";//揭阳
    public static final String YF="0766";//云浮
	public static  Map<String,String> AREACODE_OUT_TO_IN=null;//返回值转换
	static{
		AREACODE_OUT_TO_IN = new HashMap<String, String>();
		AREACODE_OUT_TO_IN.put(GZ, CITY_GZ);
		AREACODE_OUT_TO_IN.put(SG, CITY_SG);
		AREACODE_OUT_TO_IN.put(SZ, CITY_SZ);
		AREACODE_OUT_TO_IN.put(ZH, CITY_ZH);
		AREACODE_OUT_TO_IN.put(ST, CITY_ST);
		AREACODE_OUT_TO_IN.put(FS, CITY_FS);
		AREACODE_OUT_TO_IN.put(JM, CITY_JM);
		AREACODE_OUT_TO_IN.put(ZJ, CITY_ZJ);
		AREACODE_OUT_TO_IN.put(MM, CITY_MM);
		AREACODE_OUT_TO_IN.put(ZQ, CITY_ZQ);
		AREACODE_OUT_TO_IN.put(HZ, CITY_HZ);
		AREACODE_OUT_TO_IN.put(MZ, CITY_MZ);
		AREACODE_OUT_TO_IN.put(SW, CITY_SW);
		AREACODE_OUT_TO_IN.put(HY, CITY_HY);
		AREACODE_OUT_TO_IN.put(YJ, CITY_YJ);
		AREACODE_OUT_TO_IN.put(QY, CITY_QY);
		AREACODE_OUT_TO_IN.put(DG, CITY_DG);
		AREACODE_OUT_TO_IN.put(ZS, CITY_ZS);
		AREACODE_OUT_TO_IN.put(CZ, CITY_CZ);
		AREACODE_OUT_TO_IN.put(JY, CITY_JY);
		AREACODE_OUT_TO_IN.put(YF, CITY_YF);
	}
	
	/**
	 * 将外部地市编码转换为内部地市编码
	 * @param areaCity外部地市编码
	 * @return 内部地市编码
	 */
	public static final String getCity(String areaCity){
		return AREACODE_OUT_TO_IN.get(areaCity);
	}
}
