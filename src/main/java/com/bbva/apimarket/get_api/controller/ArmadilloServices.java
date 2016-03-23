package com.bbva.apimarket.get_api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class ArmadilloServices {

	private String tokenEndpoint;
	private String cipherUrl;
	private String authorization;
	
	public ArmadilloServices(String tokenEndpoint, String authorization, String cipherUrl) {
		this.tokenEndpoint = tokenEndpoint;
		this.cipherUrl = cipherUrl;		
		this.authorization = authorization;
	}
	
	private String parseToken(String tokenResponse){
//		TODO parse token
		String token = "";
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(tokenResponse);
			String access_token = jsonObject.getString("access_token");
			String token_type = jsonObject.getString("token_type");
			token = token_type + " " + access_token;
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
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", this.authorization);
			connection.setDoOutput(true);
			connection.connect();
		    
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
		    	token = "ERROR http code in armadillo token "+String.valueOf(httpResult);
		    }
		    connection.disconnect();
		} catch (Exception e) {
			return "ERROR authencating in armadillo | "+e.getMessage();
		}
		return token;
	}
	
	private String callToCipher(String token, String email){
		String response = "";
		try {
			URL obj = new URL(this.cipherUrl);
			HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", token);
			connection.setDoOutput(true);
			connection.connect();
			
			OutputStreamWriter out = new   OutputStreamWriter(connection.getOutputStream());
//			TODO
		    out.write("[{\"id\":\"1\",\"content\":\""+email+"\"}]");
		    out.flush();
		    out.close();
		    
		    int httpResult = connection.getResponseCode();
		    if(httpResult == HttpsURLConnection.HTTP_OK){
		    	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	            	response += line;  
	            }  
	            br.close();  
		    } else {
		    	response = "ERROR http code in cipher phase "+String.valueOf(httpResult);
		    }
		    connection.disconnect();
		} catch (Exception e) {
			return "ERROR ciphering email | "+e.getMessage();
		}
		return response;
	}
	
	private String parseCipher(String response){
		String cipheredEmail = "";
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(response);
			JSONObject result = jsonObject.getJSONObject("result");
			if(result.has("code") && !result.isNull("code") && result.getInt("code")==200){
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				JSONObject dataObject0 = jsonArray.getJSONObject(0);
				if(dataObject0.has("content") && !dataObject0.isNull("content")){
					cipheredEmail = dataObject0.getString("content");
				} else {
					cipheredEmail = "ERROR ciphered email is not in the content. " + result.toString(); 
				}
			} else {
				cipheredEmail = "ERROR result has no code or isnt 200. " + result.toString();
			}
		} catch (JSONException e) {
			return "ERROR parsing cipher phase response | "+e.getMessage();
		}
		
		return cipheredEmail;
	}
	
	public JSONObject cipherEmail(String email){
		JSONObject data = JSONFactoryUtil.createJSONObject();
		
		String token = this.authenticate();
		if(token.contains("ERROR")){
			data.put("error", true);
			data.put("message", token);
		} else {
			String callToCipher = this.callToCipher(token, email);
			if(callToCipher.contains("ERROR ")){
				data.put("error", true);
				data.put("message", callToCipher);				
			} else {
				String cipheredEmail = this.parseCipher(callToCipher);
				if(cipheredEmail.contains("ERROR ")){
					data.put("error", true);
					data.put("message", cipheredEmail);	
				} else {
					data.put("cipheredEmail", cipheredEmail);
				}
			}
		}		
		return data;
	}
}
