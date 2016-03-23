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
String appId = GetterUtil.getString(prefs.getValue("appId","AppId_"));
String appKey = GetterUtil.getString(prefs.getValue("appKey","AppKey_"));
String endpoint = GetterUtil.getString(prefs.getValue("endpoint","Endpoint_"));
String delay = GetterUtil.getString(prefs.getValue("delay","0"));
%>

<portlet:resourceURL var='getPortletPreferences' id='getPortletPreferences' />
<portlet:resourceURL var='putPortletPreferences' id='putPortletPreferences' />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/user_entity/user_entity_edit.css"></script>
<script type="text/javascript">

	var basePath = '<%=request.getContextPath()%>';
	var getPrefsURL = '<%=getPortletPreferences%>';
	var putPrefsURL = '<%=putPortletPreferences%>';
	var appId = '<%=appId%>';
	var appKey = '<%=appKey%>';
	var endpoint = '<%=endpoint%>';
	var delay = '<%=delay%>';

</script>

