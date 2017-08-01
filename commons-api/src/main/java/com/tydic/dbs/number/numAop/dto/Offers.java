package com.tydic.dbs.number.numAop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tydic.dbs.commons.constant.WcsBssPolicy;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 政策包返回结果
 * @author liulu
 *
 */
public class Offers implements Serializable{
	private static final long serialVersionUID = -4079740724971070336L;
	/**
	 * 地市
	 */
	private String areaCode;
	/**
	 * 政策包id，可能会有多个
	 */
	private String[] policyId;
	public Offers(){}
	
	public Offers(String areaCode,String[] policyId){
		this.areaCode=areaCode;
		this.policyId=policyId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	
	public String[] getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String[] policyId) {
		this.policyId = policyId;
	}

	/**
	 * 将字符窜转换成list
	 * @param str 接口返回的字符窜
	 * @return 政策包list
	 */
	public static List<Offers> createOffer(String str){
		String[] policyids;
		List<Offers> listOffers=new ArrayList<Offers>();
		JSONObject jsonObject = new JSONObject(str);
		JSONArray jsonArray = jsonObject.getJSONArray("offers");
		if(jsonArray!=null && jsonArray.length()>0){
			String array="";
			for(int i=0;i<jsonArray.length();i++){
				Offers offers=new Offers();
				array=jsonArray.get(i).toString();
				JSONObject json = new JSONObject(array);
				offers.setAreaCode(WcsBssPolicy.getCity(json.get("area_code").toString()));
				if(json.get("policyid")!=null && !"".equals(json.get("policyid"))){
					policyids=json.get("policyid").toString().split(",");
					offers.setPolicyId(policyids);
				}
				listOffers.add(offers);
			}
		}
		return listOffers;
	}
}
