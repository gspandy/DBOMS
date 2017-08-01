package com.tydic.dbs.dbsweb.httpclient;

import com.google.gson.Gson;

import com.tydic.dbs.dbsweb.sign.EpaySubmit;
import com.tydic.dbs.vo.ResultVO;

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
		ResultVO vo = null;
		try {
			Map <String,String>param = new HashMap<String,String>();
			param.put("service_name", "ORDER_RESULT_NOTICE_SERVICE");
			param.put("work_no", "1");
			param.put("file_name", "hehei.xls");
			param.put("session_id", "18627909900");
			vo = service(
					"http://172.16.0.153:8855/demo/service",
					param,ResultVO.class);
			System.out.println(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
