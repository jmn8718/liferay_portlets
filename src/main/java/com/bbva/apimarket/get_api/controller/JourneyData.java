package com.bbva.apimarket.get_api.controller;

import java.sql.Timestamp;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class JourneyData {

	long userId;
	String userEmail;
	String userName;
	String subscriberKey;
	String subscriberKeyUserTemplate;
	JSONObject data;
	
	public JourneyData(String subscriberKey, String subscriberKeyUserTemplate, String userEmail, String username, long userId, JSONObject data) {
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = username;
		this.subscriberKey = subscriberKey;
		this.subscriberKeyUserTemplate = subscriberKeyUserTemplate;
		this.data = data;
	}

	public JSONObject generateJourneyDataBody(){	
		JSONObject data = JSONFactoryUtil.createJSONObject();
		data.put("subscriberKey", this.subscriberKey);
		data.put("subscriberKeyUser", this.subscriberKeyUserTemplate + this.userId);
		data.put("desCompany", this.data.getString("company"," "));
		data.put("desCountry", this.data.getString("country"," "));
		data.put("desWebsite", this.data.getString("website"," "));
		data.put("desUsername", this.userName);
		data.put("desRequiredAPIs", this.data.getString("requiredApis"," "));
		data.put("desAPIBizzGoal", this.data.getString("apiBusinessObjective"," "));
		data.put("timeStamp", new Timestamp(System.currentTimeMillis()));		
		data.put("emailUser", this.userEmail+"@email.com");
		
		return data;
	}
}
