package com.bbva.apimarket.user_entity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping(value={"VIEW"})
public class UserEntityControllerView {
	@Autowired
	@Qualifier("restTemplateDC")
	private RestTemplate restTemplateDc;
	
	String endpoint = "";
	String appId = "";
	String appKey = "";
	int delay = 4;
	
	private String ADMIN_USERNAME = "admin";
	
	@RenderMapping
	public String defaultView(final RenderRequest renderRequest,
			final RenderResponse renderResponse, final Model model,
			Locale locale) throws PortletException {
		PortletPreferences preferences = renderRequest.getPreferences();		
		this.appId = GetterUtil.getString(preferences.getValue("appId", ""));
		this.appKey = GetterUtil.getString(preferences.getValue("appKey", ""));
		this.endpoint = GetterUtil.getString(preferences.getValue("endpoint", ""));
		this.delay = GetterUtil.getInteger(preferences.getValue("delay", "4"));
		String username = this.getUsername((PortletRequest) renderRequest.getAttribute("javax.portlet.request"));
		if(username.equalsIgnoreCase(ADMIN_USERNAME) && (this.appId.length()<=0 || this.appKey.length()<=0 || this.endpoint.length()<=0 || this.delay<=0)){
			try {
				renderResponse.getWriter().write("<div class=\"alert alert-error\">Set the preferences parameter to use the portlet</div>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(username.equalsIgnoreCase(ADMIN_USERNAME)){
			try {
				renderResponse.getWriter().write("<div class=\"alert alert-success\">URL: "+this.getApiUrl(username)+"<br>Delay:"+this.delay+"</div>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "view";
	}
	
	/*
	 * This function return the screen name of the user logged in the
	 * environment
	 */
	private String getUsername(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.getUser().getScreenName();
	}

	/*
	 * This fuction generate the url for the api resquest
	 */
	private String getApiUrl(String username) {
		if(this.appId.length()<=0)
			this.appId = "ADD appId";
		if(this.appKey.length()<=0)
			this.appKey = "ADD appKey";
		if(this.endpoint.length()<=0)
			this.endpoint = "Add Endpoint";

		String service = "/developer/" + username + "/application";

		return this.endpoint + service + "?app_id=" + this.appId + "&app_key=" + this.appKey;
	}

	/*
	 * 
	 */
	public String selectEntity(String appsList) {
		String entity = "api_market";

		JSONArray arrayApps;
		try {
			arrayApps = JSONFactoryUtil.createJSONArray(appsList);
			if (arrayApps.length() == 1) {
				entity = arrayApps.getJSONObject(arrayApps.length() - 1).getString(
						"entityName");
			} else if (arrayApps.length() > 1) {
				List<String> entityList = new ArrayList<String>();
				entityList.add(0, "bbva");
				entityList.add(1, "compass");
				entityList.add(2, "api_market");
				
				entity = arrayApps.getJSONObject(0).getString("entityName");
				
				int entityIndex = entityList.size()-1;
				for (int i = 0; i < arrayApps.length(); i++) {
					String appEntity = arrayApps.getJSONObject(i).getString(
							"entityName");
					int appEntityIndex = entityList.indexOf(appEntity);
					if(appEntityIndex>-1 && appEntityIndex<entityIndex)
						entityIndex = appEntityIndex;
				}
				entity = entityList.get(entityIndex);
			}
		} catch (JSONException e) {
			entity = "ERROR:parsing json";
		}
		
		return entity;
	}
	
	
	@ResourceMapping(value = "getData")
	@ResponseBody
	public void getData(ResourceRequest request, ResourceResponse response) {
		String username = this.getUsername((PortletRequest) request
				.getAttribute("javax.portlet.request"));
		long delayInMs = this.delay * 1000;
		long startTime = System.currentTimeMillis();		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		if(username.equalsIgnoreCase(ADMIN_USERNAME)){
			jsonObject.put("error", true);	
			jsonObject.put("jump", false );	
			jsonObject.put("url", this.getApiUrl(username));
		}else{
			String url = this.getApiUrl(username);
			String restRequest = "";
			try {
				restRequest = restTemplateDc.getForObject(url, String.class);
			} catch (Exception e) {
				restRequest = "ERROR:"+e.getMessage();
			}			
			if (restRequest.contains("ERROR:")) {
				jsonObject.put("error", true);
				jsonObject.put("errorCode", restRequest.replace("ERROR:", ""));
				jsonObject.put("jump", "true");
				jsonObject.put("entity", "api_market");
			} else {
				jsonObject.put("entity", this.selectEntity(restRequest));
			}
		}		
		
		try {
			if(!jsonObject.has("error")){
//				jsonObject.put("starttime", startTime);
				long endTime = System.currentTimeMillis();
//				jsonObject.put("endtime", endTime);
				if((endTime-startTime)<delayInMs)
					try {
//						jsonObject.put("delay", startTime+delayInMs-endTime);
//						jsonObject.put("beforeDelay", System.currentTimeMillis());
						Thread.sleep(startTime+delayInMs-endTime);
//						jsonObject.put("afterDelay", System.currentTimeMillis());
					} catch (InterruptedException e) {
						jsonObject.put("exception", "exception in try");
					}
			}
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
		}
		
	}

}
