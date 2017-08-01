package com.tydic.dbs.httpclient;

import com.google.gson.Gson;

import com.tydic.dbs.sign.EpaySubmit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

public class HttpRemoteService {
	private static Log log = LogFactory.getLog(HttpRemoteService.class);


	public static <T> T  service(String url, Map<String, String> params,Class<T> classOfT)
			throws Exception {


		try {

			String result = HttpUtil.getInstance().doPostForString(url, EpaySubmit.buildRequestPara(params));
			log.info(" return【"+result+"】");
			Gson gson = new Gson();
			return gson.fromJson(result, classOfT);
			
		//	return result;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	public static CloseableHttpClient createSSLClientDefault(){
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				//信任所有
				public boolean isTrusted(X509Certificate[] chain,
										 String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			log.error(e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
		} catch (KeyStoreException e) {
			log.error(e);
		}
		return  HttpClients.createDefault();
	}

	public static void main(String[] args) {

		System.out.println("----------------------分割线-----------------------");
		String postData="";
		try {
			Map <String,String>param = new HashMap<String,String>();
			param.put("service", "search_account_balance");
			param.put("partner", "2088123");
			param.put("seller_id", "2088123");
			param.put("cust_id", "18627909900");
//			postData = service(
//					"https://172.16.0.103:8443/epay/service/gateway?_input_charset=utf-8",
//					param);
			
			System.out.println(postData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
