package com.bbva.apimarket.get_api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;

@Controller
@RequestMapping("VIEW")
public class GetApiControllerView {

	@Autowired
	@Qualifier("restTemplateDC")
	private RestTemplate restTemplateDc;

	private String email;
	private String username;
	private long userId;

	private String ADMIN_USERNAME = "equipogenoa";

	private String armadilloEndpoint;
	private String armadilloAuthorization;
	private String armadilloCipherEndpoint;
	private String salesforceAuthenticationEndpoint;
	private String salesforceClientId;
	private String salesforceClientSecret;
	private String salesforceJourneyEndpoint;
	private String salesforceContactKey;
	private String salesforceSubscriberKey;
	private String salesforceSubscriberKeyUserTemplate;
	private String salesforceEventDefinitionKey;
	private String apisAvailables;
	
	@RenderMapping
	public String defaultView(final RenderRequest renderRequest,
			final RenderResponse renderResponse, final Model model,
			Locale locale) throws PortletException {
		PortletRequest portletRequest = (PortletRequest)renderRequest.getAttribute("javax.portlet.request");
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest
				.getAttribute(com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
		
		this.email = themeDisplay.getUser().getEmailAddress();
		this.userId = themeDisplay.getUser().getUserId();
		this.username = themeDisplay.getUser().getScreenName();
		
		PortletPreferences preferences = renderRequest.getPreferences();		
		this.armadilloAuthorization = GetterUtil.getString(preferences.getValue("armadilloAuthorization", ""));
		this.armadilloEndpoint = GetterUtil.getString(preferences.getValue("armadilloEndpoint", ""));
		this.armadilloCipherEndpoint = GetterUtil.getString(preferences.getValue("armadilloCipherEndpoint", ""));
		this.salesforceAuthenticationEndpoint = GetterUtil.getString(preferences.getValue("salesforceAuthenticationEndpoint", ""));
		this.salesforceJourneyEndpoint = GetterUtil.getString(preferences.getValue("salesforceJourneyEndpoint", ""));
		this.salesforceClientId = GetterUtil.getString(preferences.getValue("salesforceClientId", ""));
		this.salesforceClientSecret = GetterUtil.getString(preferences.getValue("salesforceClientSecret", ""));
		this.salesforceContactKey = GetterUtil.getString(preferences.getValue("salesforceContactKey", ""));
		this.salesforceSubscriberKey = GetterUtil.getString(preferences.getValue("salesforceSubscriberKey", ""));
		this.salesforceSubscriberKeyUserTemplate = GetterUtil.getString(preferences.getValue("salesforceSubscriberKeyUserTemplate", ""));
		this.salesforceEventDefinitionKey = GetterUtil.getString(preferences.getValue("salesforceEventDefinitionKey", ""));
		this.apisAvailables = GetterUtil.getString(preferences.getValue("apisAvailables", ""));
		
		if(username.equalsIgnoreCase(ADMIN_USERNAME) && (this.armadilloAuthorization.length()<=0 ||
				this.armadilloEndpoint.length()<=0 || this.armadilloCipherEndpoint.length()<=0 || 
				this.salesforceSubscriberKey.length()<=0 || this.salesforceSubscriberKeyUserTemplate.length()<=0 || 
				this.salesforceAuthenticationEndpoint.length()<=0 || this.salesforceClientId.length()<=0 || 
				this.salesforceClientSecret.length()<=0 ||this.salesforceJourneyEndpoint.length()<=0 || 
				this.salesforceContactKey.length()<=0 || this.salesforceEventDefinitionKey.length()<=0 ||
				this.apisAvailables.length()<=0)){
			try {
				renderResponse.getWriter().write("<div class=\"alert alert-error\">Set the preferences to use the portlet</div>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(username.equalsIgnoreCase(ADMIN_USERNAME)){
			try {
				//String response = "<div class=\"alert alert-success\">Setting are saved in the system</div>";
				String response = "<ul><li>Armadillo authorization: "+this.armadilloAuthorization+"</li>";
				response += "<li>Armadillo endpoint: "+this.armadilloEndpoint+"</li>";
				response += "<li>Armadillo Cipher endpoint: "+this.armadilloCipherEndpoint+"</li></ul>";
				response += "<ul><li>Salesforce Authentication endpoint: "+this.salesforceAuthenticationEndpoint+"</li>";
				response += "<li>Salesforce Journey endpoint: "+this.salesforceJourneyEndpoint+"</li>";
				response += "<li>Salesforce ClientId: "+this.salesforceClientId+"</li>";
				response += "<li>Salesforce ClientSecret: "+this.salesforceClientSecret+"</li>";
				response += "<li>Salesforce Contact Key: "+this.salesforceContactKey+"</li>";
				response += "<li>Salesforce Subscriber Key: "+this.salesforceSubscriberKey+"</li>";
				response += "<li>Salesforce Subscriber Key User Template: "+this.salesforceSubscriberKeyUserTemplate+"</li>";
				response += "<li>Salesforce event definition key: "+this.salesforceEventDefinitionKey+"</li>";
				response += "<li>Apis Availables: "+this.apisAvailables+"</li></ul>";
				renderResponse.getWriter().write(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "view";
	}

	private boolean validateForm(JSONObject formData){
		boolean isValid = true;
		
		if(!formData.has("company") || formData.getString("company").length()<=0)
			isValid = false;
//		if(!formData.has("website") || formData.getString("website").length()<=0)
//			isValid = false;
		if(!formData.has("requiredApis") || formData.getString("requiredApis").length()<=0)
			isValid = false;
		if(!formData.has("country") || formData.getString("country").length()<=0)
			isValid = false;
		if(!formData.has("apiBusinessObjective") || formData.getString("apiBusinessObjective").length()<=0)
			isValid = false;
//		if(!formData.has("username") || formData.getString("username").length()<=0)
//			isValid = false;
//		if(!formData.has("email") || formData.getString("email").length()<=0)
//			isValid = false;
		
		return isValid;
	}
		
	private JSONObject parse(ResourceRequest request){		
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
			jsonForm = jsonData.getJSONObject("form");
		} catch (Exception e) {
			return JSONFactoryUtil.createJSONObject();
		}
		return jsonForm;
	}
	@ResourceMapping(value = "getData")
	@ResponseBody
	public void getData(ResourceRequest request, ResourceResponse response)
			throws PortalException, SystemException, IOException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONObject data = this.parse(request);
		
		if(validateForm(data)){
			/*First, cipher email*/
			ArmadilloServices armadillo = new ArmadilloServices(this.armadilloEndpoint, this.armadilloAuthorization, this.armadilloCipherEndpoint);
			JSONObject cipheredEmailData = armadillo.cipherEmail(this.email);
			if(!cipheredEmailData.has("error") && cipheredEmailData.has("cipheredEmail") && !cipheredEmailData.isNull("cipheredEmail")){
				/*Second, send the journey*/
				JourneyData journeyData = new JourneyData(this.salesforceSubscriberKey, this.salesforceSubscriberKeyUserTemplate, 
						cipheredEmailData.getString("cipheredEmail"), this.username, this.userId, data);
				SalesforceJourneySendEmail salesforce = 
						new SalesforceJourneySendEmail(this.salesforceAuthenticationEndpoint, this.salesforceJourneyEndpoint, 
								this.salesforceClientId, this.salesforceClientSecret, 
								this.salesforceContactKey, this.salesforceEventDefinitionKey, journeyData.generateJourneyDataBody());
				JSONObject journey = salesforce.launchJourney();
				if(journey.has("error")){
					result.put("error", true);
					result.put("errorCode", journey.getString("message","Error in salesforce"));
					result.put("values",journey.getString("values"));
				} else {
					result.put("code", journey.getString("code"));
					result.put("values",journey.getString("values"));
				}
			} else {
				result.put("error", true);
				result.put("errorCode", cipheredEmailData.getString("message","Error in armadillo"));
			}
		} else {
			result.put("error", true);
			result.put("errorCode", "Missing parameters in the body form");
			result.put("data", data);
		}
		response.getWriter().write(result.toString());
	}

}