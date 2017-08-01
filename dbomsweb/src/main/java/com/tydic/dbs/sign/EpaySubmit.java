package com.tydic.dbs.sign;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tydic.dbs.common.constant.CommonConfig;

/* *
 *类名：EpaySubmit
 *功能：E支付各接口请求提交类
 *详细：构造E支付各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究E支付接口使用，只是提供一个参考。
 */

public class EpaySubmit {

    
    /**
     * E支付提供给商户的服务接入网关URL(新)
     */
    private static final String EPAY_GATEWAY_NEW = "https://172.16.0.113:8443/epay/service/gateway?_input_charset=utf-8";
    //private static final String EPAY_GATEWAY_NEW = "https://192.168.0.107:8443/epay/service/gateway?";
	
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestSign(Map<String, String> sPara) {
    	String prestr = RequestHandleUtil.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = mysign = MD5.sign(prestr, CommonConfig.httpKey, CommonConfig.httpEncode);
        return mysign;
    }
	
    /**
     * 生成要请求给E支付的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
    	if(sParaTemp == null){
    		sParaTemp = new HashMap<String,String>();
    	}
    	sParaTemp.put("input_charset", "utf-8");
    	
        //除去数组中的空值和签名参数
        Map<String, String> sPara = RequestHandleUtil.filteParam(sParaTemp);

        //生成签名结果
        String sign = buildRequestSign(sPara);



        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign_key", sign);

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {

        //整理一下请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);

        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"epaysubmit\" name=\"epaysubmit\" action=\"" + EPAY_GATEWAY_NEW
                      + "_input_charset=" + CommonConfig.httpEncode + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        //加了这一句，页面会自动跳转
        sbHtml.append("<script>document.forms['epaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    

    public static void main(String[] args) {

		System.out.println("----------------------分割线-----------------------");
		try {
			Map <String,String>param = new HashMap<String,String>();
			param.put("service", "search_account_balance");
			param.put("partner", "2088123");
			param.put("seller_id", "2088123");
			param.put("cust_id", "18627909900");
			
			System.out.println(buildRequest(param, "", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
