<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages" %> 
<%@page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@page import="com.liferay.portal.kernel.util.*" %>
<%@page import="com.liferay.portlet.*" %>
<%@page import="javax.portlet.*" %> 

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
PortletPreferences prefs = renderRequest.getPreferences();
String portletResource = ParamUtil.getString(request, "portletResource");
if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}
String armadilloEndpoint = GetterUtil.getString(prefs.getValue("armadilloEndpoint","ArmadilloEndpoint_"));
String armadilloAuthorization = GetterUtil.getString(prefs.getValue("armadilloAuthorization","ArmadilloAuthorization_"));
String armadilloCipherEndpoint = GetterUtil.getString(prefs.getValue("ArmadilloCipherEndpoint","ArmadilloCipherEndpoint_"));
String salesforceAuthenticationEndpoint = GetterUtil.getString(prefs.getValue("salesforceAuthenticationEndpoint","SalesforceAuthenticationEndpoint_"));
String salesforceJourneyEndpoint = GetterUtil.getString(prefs.getValue("salesforceJourneyEndpoint","SalesforceJourneyEndpoint_"));
String salesforceClientId = GetterUtil.getString(prefs.getValue("salesforceClientId","ClientId_"));
String salesforceClientSecret = GetterUtil.getString(prefs.getValue("salesforceClientSecret","ClientSecret_"));
String salesforceContactKey = GetterUtil.getString(prefs.getValue("salesforceContactKey","ContactKey_"));
String salesforceEventDefinitionKey = GetterUtil.getString(prefs.getValue("salesforceEventDefinitionKey","EventDefinitionKey_"));
String salesforceSubscriberKey = GetterUtil.getString(prefs.getValue("salesforceSubscriberKey","SubscriberKey_"));
String salesforceSubscriberKeyUserTemplate = GetterUtil.getString(prefs.getValue("salesforceSubscriberKeyUserTemplate","SubscriberKeyUserTemplate_"));
String apisAvaliables = GetterUtil.getString(prefs.getValue("apisAvaliables","ApisAvaliables_"));
%>

<portlet:resourceURL var='getPortletPreferences' id='getPortletPreferences' />
<portlet:resourceURL var='putPortletPreferences' id='putPortletPreferences' />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/user_entity/user_entity_edit.css"></script>
<script type="text/javascript">

	var basePath = '<%=request.getContextPath()%>';
	var getPrefsURL = '<%=getPortletPreferences%>';
	var putPrefsURL = '<%=putPortletPreferences%>';
	var armadilloEndpoint = '<%=armadilloEndpoint%>';
	var armadilloAuthorization = '<%=armadilloAuthorization%>';
	var armadilloCipherEndpoint = '<%=armadilloCipherEndpoint%>';
	var salesforceAuthenticationEndpoint = '<%=salesforceAuthenticationEndpoint%>';
	var salesforceJourneyEndpoint = '<%=salesforceJourneyEndpoint%>';
	var salesforceClientId = '<%=salesforceClientId%>';
	var salesforceClientSecret = '<%=salesforceClientSecret%>';
	var salesforceContactKey = '<%=salesforceContactKey%>';
	var salesforceEventDefinitionKey = '<%=salesforceEventDefinitionKey%>';
	var salesforceSubscriberKey = '<%=salesforceSubscriberKey%>';
	var salesforceSubscriberKeyUserTemplate = '<%=salesforceSubscriberKeyUserTemplate%>';
	var apisAvaliables = '<%=apisAvaliables%>';

</script>

