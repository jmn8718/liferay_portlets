package com.bbva.apimarket.get_api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class SalesforceJourneySendEmail {

	private String contactKey;
	private String eventDefinitionKey;
	private JSONObject data;
	
	private String clientId;
	private String clientSecret;
	
	private String tokenEndpoint = "https://auth.exacttargetapis.com/v1/requestToken";
	private String journeyEndpoint = "https://www.exacttargetapis.com/interaction-experimental/v1/events";
	
	private String TOKEN_TYPE = "Bearer ";
	
	public SalesforceJourneySendEmail(String tokenEndpoint, String journeEndpoint, String clientId, String clientSecret, String contactKey, String eventDefinitionKey, JSONObject data) {
		this.tokenEndpoint = tokenEndpoint;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		
		this.journeyEndpoint = journeEndpoint;
		this.contactKey = contactKey;
		this.eventDefinitionKey = eventDefinitionKey;
		this.data = data;
	}
	
	private String generateAuthenticationBody(){
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("clientId", this.clientId);
		jsonObject.put("clientSecret", this.clientSecret);
		return jsonObject.toString();
	}
	private String parseToken(String tokenResponse){
		String token = "";
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(tokenResponse);
			token = this.TOKEN_TYPE+jsonObject.getString("accessToken");
		} catch (JSONException e) {
			return "ERROR parsing token json | "+e.getMessage();
		}
		return token;
	}
	private String authenticate(){
		String token = "";
		try {
			URL obj = new URL(this.tokenEndpoint);
			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setDoOutput(true);
			connection.connect();
			
			OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
		    out.write(this.generateAuthenticationBody());
		    out.flush();
		    out.close();
		    
		    int httpResult = connection.getResponseCode();
		    if(httpResult == HttpsURLConnection.HTTP_OK){
		    	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	                token += line;  
	            }  
	            br.close();  
			    token = this.parseToken(token);
		    } else {
		    	token = "ERROR http code in salesforce token "+String.valueOf(httpResult);
		    }
		    connection.disconnect();
		} catch (Exception e) {
			return "ERROR authenticating in salesforce | "+e.getMessage();
		}
		return token;
	}
	
	private String generateJourneyBody(){		
		JSONObject journeyBody = JSONFactoryUtil.createJSONObject();
		journeyBody.put("ContactKey", this.contactKey);
		journeyBody.put("EventDefinitionKey", this.eventDefinitionKey);
		journeyBody.put("Data", this.data);
		
		return journeyBody.toString();
	}
	
	private String callToJourney(String token){
		String response = "";
		try {
			URL obj = new URL(this.journeyEndpoint);
			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", token);
			connection.setDoOutput(true);
			connection.connect();
			
			OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
		    out.write(this.generateJourneyBody());
		    out.flush();
		    out.close();
		    
		    int httpResult = connection.getResponseCode();
		    if(httpResult == HttpsURLConnection.HTTP_CREATED){
		    	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	            	response += line;  
	            }  
	            br.close();  
		    } else {
		    	response = "ERROR http code in salesforce journey "+String.valueOf(httpResult);
		    }
		    connection.disconnect();
		} catch (Exception e) {
			return "ERROR in salesforce journey | "+e.getMessage();
		}
		return response;
	}
	
	public JSONObject launchJourney(){
		String token = this.authenticate();
		String response = "";
		if(!token.contains("ERROR ")){
			response = this.callToJourney(token);
		} else {
			response = token;
		}
		return this.generateResponse(response);
	}
	
	private JSONObject generateResponse(String response){
		JSONObject data = JSONFactoryUtil.createJSONObject();
		if(response.contains("ERROR ")){
			data.put("error","true");
			data.put("message",response);
			data.put("values",this.generateJourneyBody());
		} else {
			String resp = new String(response.toString());
			data.put("code", resp.trim());
			data.put("values",this.generateJourneyBody());
		}
		
		return data;
	}

}
