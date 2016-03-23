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
String apisAvailables = GetterUtil.getString(prefs.getValue("apisAvailables",""));
%>
<portlet:resourceURL var='getDataURL' id='getData' />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-messages.js"></script>
<script type="text/javascript">

	var basePath = '<%=request.getContextPath()%>';
	var getDataURL = '<%=getDataURL%>';
	var apis = '<%=apisAvailables%>'.split(',');
	var apisAvailables = [];
	for(var index in apis)
		apisAvailables.push({name:apis[index]})	

</script>