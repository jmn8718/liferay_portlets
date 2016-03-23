package com.bbva.apimarket.get_api.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;

@Controller
@RequestMapping(value={"EDIT"})
public class GetApiEditControllerView {

	@Autowired
	@Qualifier("restTemplateDC")
	private RestTemplate restTemplateDc;

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
			if(myPreferences.has("armadilloEndpoint"))
				preferences.setValue("armadilloEndpoint", myPreferences.getString("armadilloEndpoint"));
			if(myPreferences.has("armadilloAuthorization"))
				preferences.setValue("armadilloAuthorization", myPreferences.getString("armadilloAuthorization"));
			if(myPreferences.has("armadilloCipherEndpoint"))
				preferences.setValue("armadilloCipherEndpoint", myPreferences.getString("armadilloCipherEndpoint"));
			if(myPreferences.has("salesforceAuthenticationEndpoint"))
				preferences.setValue("salesforceAuthenticationEndpoint", myPreferences.getString("salesforceAuthenticationEndpoint"));
			if(myPreferences.has("salesforceJourneyEndpoint"))
				preferences.setValue("salesforceJourneyEndpoint", myPreferences.getString("salesforceJourneyEndpoint"));
			if(myPreferences.has("salesforceClientId"))
				preferences.setValue("salesforceClientId", myPreferences.getString("salesforceClientId"));
			if(myPreferences.has("salesforceClientSecret"))
				preferences.setValue("salesforceClientSecret", myPreferences.getString("salesforceClientSecret"));
			if(myPreferences.has("salesforceContactKey"))
				preferences.setValue("salesforceContactKey", myPreferences.getString("salesforceContactKey"));
			if(myPreferences.has("salesforceSubscriberKey"))
				preferences.setValue("salesforceSubscriberKey", myPreferences.getString("salesforceSubscriberKey"));
			if(myPreferences.has("salesforceSubscriberKeyUserTemplate"))
				preferences.setValue("salesforceSubscriberKeyUserTemplate", myPreferences.getString("salesforceSubscriberKeyUserTemplate"));
			if(myPreferences.has("salesforceEventDefinitionKey"))
				preferences.setValue("salesforceEventDefinitionKey", myPreferences.getString("salesforceEventDefinitionKey"));
			if(myPreferences.has("apisAvailables"))
				preferences.setValue("apisAvailables", myPreferences.getString("apisAvailables"));
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
		jsonPreferences.put("armadilloEndpoint", GetterUtil.getString(preferences.getValue("armadilloEndpoint", "armadilloEndpoint")));
		jsonPreferences.put("armadilloAuthorization", GetterUtil.getString(preferences.getValue("armadilloAuthorization", "armadilloAuthorization")));
		jsonPreferences.put("armadilloCipherEndpoint", GetterUtil.getString(preferences.getValue("armadilloCipherEndpoint", "armadilloCipherEndpoint")));
		jsonPreferences.put("salesforceAuthenticationEndpoint", GetterUtil.getString(preferences.getValue("salesforceAuthenticationEndpoint", "salesforceAuthenticationEndpoint")));
		jsonPreferences.put("salesforceJourneyEndpoint", GetterUtil.getString(preferences.getValue("salesforceJourneyEndpoint", "salesforceJourneyEndpoint")));
		jsonPreferences.put("salesforceClientId", GetterUtil.getString(preferences.getValue("salesforceClientId", "salesforceClientId")));
		jsonPreferences.put("salesforceClientSecret", GetterUtil.getString(preferences.getValue("salesforceClientSecret", "salesforceClientSecret")));
		jsonPreferences.put("salesforceContactKey", GetterUtil.getString(preferences.getValue("salesforceContactKey", "salesforceContactKey")));
		jsonPreferences.put("salesforceSubscriberKey", GetterUtil.getString(preferences.getValue("salesforceSubscriberKey", "salesforceSubscriberKey")));
		jsonPreferences.put("salesforceSubscriberKeyUserTemplate", GetterUtil.getString(preferences.getValue("salesforceSubscriberKeyUserTemplate", "salesforceSubscriberKeyUserTemplate")));
		jsonPreferences.put("salesforceEventDefinitionKey", GetterUtil.getString(preferences.getValue("salesforceEventDefinitionKey", "salesforceEventDefinitionKey")));
		jsonPreferences.put("apisAvailables", GetterUtil.getString(preferences.getValue("apisAvailables", "apisAvailables")));
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