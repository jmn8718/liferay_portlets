package com.bbva.apimarket.user_entity.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.GetterUtil;

@Controller
@RequestMapping(value={"EDIT"})
public class UserEntityEditControllerView extends DefaultConfigurationAction{
	
	@RenderMapping
	public String defaultView(final RenderRequest renderRequest,
			final RenderResponse response, final Model model,
			Locale locale) throws PortletException {
		
		return "edit";
	}


	@ResourceMapping(value = "getPortletPreferences")
	@ResponseBody
	public void getPortletPreferences(ResourceRequest request, ResourceResponse response) {
		PortletPreferences preferences = request.getPreferences();		
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("operationSuccess", true);
			jsonObject.put("preferences", this.getPreferences(preferences));
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String setPreferences(PortletPreferences preferences,JSONObject myPreferences){
		try {
			if(myPreferences.has("appId"))
				preferences.setValue("appId", myPreferences.getString("appId"));
			if(myPreferences.has("appKey"))
				preferences.setValue("appKey", myPreferences.getString("appKey"));
			if(myPreferences.has("endpoint"))
				preferences.setValue("endpoint", myPreferences.getString("endpoint"));
			if(myPreferences.has("delay"))
				preferences.setValue("delay", myPreferences.getString("delay"));
			preferences.store();
		} catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("error", true);
			jsonObject.put("errorStatus", "setting preferences");
			return jsonObject.toString();
		}
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("operationSuccess", true);
		jsonObject.put("preferences", this.getPreferences(preferences));
		return jsonObject.toString();
	}
	
	private JSONObject getPreferences(PortletPreferences preferences){
		JSONObject jsonPreferences = JSONFactoryUtil.createJSONObject();		
		jsonPreferences.put("appId", GetterUtil.getString(preferences.getValue("appId", "AppId")));
		jsonPreferences.put("appKey", GetterUtil.getString(preferences.getValue("appKey", "AppKey")));
		jsonPreferences.put("endpoint", GetterUtil.getString(preferences.getValue("endpoint", "Endpoint")));
		jsonPreferences.put("delay", GetterUtil.getInteger(preferences.getValue("delay", "4")));
		return jsonPreferences;
	}
	
	private JSONObject parsePreferences(ResourceRequest request){		
		JSONObject jsonForm;
		try {
			String data = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getPortletInputStream(),"utf-8"));
			String line = null;  
	        while ((line = br.readLine()) != null) {  
	            data += line;  
	        }  
	        br.close();
	        JSONObject jsonData = JSONFactoryUtil.createJSONObject(data);
			jsonForm = jsonData.getJSONObject("preferences");
		} catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("error", true);
			jsonObject.put("errorStatus", "parsing preferences");
			return jsonObject;
		}
		return jsonForm;
	}
	
	@ResourceMapping(value = "putPortletPreferences")
	@ResponseBody
	public void putPortletPreferences(ResourceRequest request, ResourceResponse response) {
		PortletPreferences preferences = request.getPreferences();
		String setPreferences = this.setPreferences(preferences,this.parsePreferences(request));
		try {
			response.getWriter().write(setPreferences);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}