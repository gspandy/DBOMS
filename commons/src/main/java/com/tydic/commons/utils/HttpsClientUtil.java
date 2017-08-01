/*
 * <p>Title:HttpsClientUtil.java </p>
 * <p>Copyright: Copyright (C) 2014 - 2018 china unicom</p>
 * <p>Company: unicom</p>
 * @author lijinzhong
 * @version 1.0
 * @time: 2014-9-4
 */
package com.tydic.commons.utils;

import java.io.UnsupportedEncodingException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijz220
 *
 *https工具类
 */
@SuppressWarnings("deprecation")
public class HttpsClientUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpsClientUtil.class);
	
	private static HttpClient client;
	
	static{
		try{
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	           
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
				}
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
				}
	        };
	        ctx.init(null, new TrustManager[] { tm }, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("https", 443, ssf));
	        ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);

        
	        client = new DefaultHttpClient(mgr);
	        // 连接超时  
	        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);  
             // 读取超时  
	        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000); 
	        
        }catch(Exception e) {
        	log.error("初始化httpsClientUtil失败：",e);
        }   
	}
	
	/**
	 * https 协议发送工具类
	 * @param url  发送的url
	 * @param requestStr   发送的数据包（空则认为是get操作）
	 * @param sendChartSet  发送数据包编码（不填默认为utf-8）
	 * @param recvChartSet  接收数据包编码（不填默认为utf-8）
	 * @return 发送失败或报错则返回空
	 */
	public static String send(String url, String requestStr, String sendChartSet, String recvChartSet) {	
		if(StringUtils.isBlank(sendChartSet)) {
			sendChartSet = "UTF-8";
		}
		if(StringUtils.isBlank(recvChartSet)) {
			recvChartSet = "UTF-8";
		}
		try{
			HttpUriRequest request = null;
			if(StringUtils.isNotBlank(requestStr)){
				HttpPost httpPost = new HttpPost(url);
//				httpPost.addHeader("content-Type", contentType);
				HttpEntity he = new StringEntity(requestStr, sendChartSet);
				httpPost.setEntity(he);
				request = httpPost;
			}else{
				request = new HttpGet(url);
			}

			HttpResponse response = client.execute(request);
			
			//读取流的数据不能多次读取，只能读取一次，这里读取，后面的全部使用读取的结果，否则后面继续读取会出错
			HttpEntity he = response.getEntity();
			String responseStr = EntityUtils.toString(he, recvChartSet);
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return responseStr;
	        }else{
	        	log.warn("响应状态：" + response.getStatusLine().getStatusCode());
	        	return null;
	        }
		}catch(Exception e){
			log.error("https协议发送数据包出现异常：", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "https://125.35.4.160/BOCCASH/merCheck!checkStatus";
		String requestStr = "<orderCheck><orig><serialno>140981933851500001</serialno><masterid>104310058121110</masterid><orderid>WCS14050909454132422548</orderid><terminalID>31001110</terminalID><remark></remark></orig><sign>MIIEBgYJKoZIhvcNAQcCoIID9zCCA/MCAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCAwYwggMCMIIB6qADAgECAgUQAHWAWDANBgkqhkiG9w0BAQUFADAbMQswCQYDVQQGEwJDTjEMMAoGA1UEChMDQk9DMB4XDTE0MDIyNjAxNDQzNloXDTE2MDIyNjAxNDQzNlowYjELMAkGA1UEBhMCY24xDDAKBgNVBAoTA0JPQzEQMA4GA1UECxMHQk9DLVRQQzEYMBYGA1UECxMPT3JnYW5pemF0aW9uYWwyMRkwFwYDVQQDExA5NTU2NlNaODAwMDAwMDA3MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOYBUKfEO58ClQOibx+iAbTnrmpmhq7NSg90Jc8xeXxWByGcAc4UM7RK6K7CS6iIKOx3kuA5A6gqinpBtl0am/lEwAptJ07HqtfY57bfb8Tp4seFdMshfKBmVdpNbj/oU3EljYyjezUEH6xOtRihWYqXkJmaABRVPoHnv9pkE+7wIDAQABo4GJMIGGMB8GA1UdIwQYMBaAFM9wnWHrnXwuuPfLAkD3CZ3+M3SAMDcGA1UdHwQwMC4wLKAqoCiGJmh0dHA6Ly91Y3JsLmNmY2EuY29tLmNuL1JTQS9jcmw1NjguY3JsMAsGA1UdDwQEAwIGwDAdBgNVHQ4EFgQUEDj0JXiQWoIBGwQlXB1O9aDlkSIwDQYJKoZIhvcNAQEFBQADggEBAKdhyiUgqoqNMzWmnlftpZ45lQMjxB1GSWinE4z24vWfwwIjNrhJp9U2iZOVQoqxEO72YaOzHlr1Tz8JPlyWZFMUiNo6WTx8Ysa9GDqaXHxInK1gWhj/yE9yNQmrSaSod4helmcNyEQHNz2gkyya0zymbub0MwYMxRF++MYzPbcJZ2jCScVrbuVg9HJrxyUQPWaiEd97UHhbuynXA6VNlXxVAX/7RObFFtYL0lPz4fJ3S8V1S+gqodME5a3xJu6zRelkHzHDmHQyh0+nzmZ7JKVzm1ZgboJTE3//IrtY66iogoQiJcZfzmEm9Jw7oe9RrH+wOP21bl0Ojp8cBS6nCJsxgckwgcYCAQEwJDAbMQswCQYDVQQGEwJDTjEMMAoGA1UEChMDQk9DAgUQAHWAWDAJBgUrDgMCGgUAMA0GCSqGSIb3DQEBAQUABIGAvOdOVKhzKEN17o3YuOryrrg1Hs/x5xfzXthlqCjodly2m8D7H7L3bLST2++fLS93tk/eor+1cppm2BVg2uMW6hASVHRf21GY3+/kn39Dx3QgM11ItF0PDhuWF7UC5z1nt3AyyOk8jBDmrfepJv93Xd2SXOiT3RkPYQHIB9DKZe8=</sign></orderCheck>";
		String reStr = HttpsClientUtil.send(url, requestStr, "UTF-8", "UTF-8");
		System.out.println("the return string is:"+reStr);
		System.out.println("the return string is:"+new String(reStr.getBytes("ISO_8859_1"), "gbk"));
		System.out.println("the return string is:"+new String(reStr.getBytes(), "gbk"));
	}

}
