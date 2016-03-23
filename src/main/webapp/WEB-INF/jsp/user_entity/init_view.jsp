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
<%@page import="com.liferay.portal.kernel.util.*" %>
<%@page import="com.liferay.portal.kernel.util.*" %>
<%@page import="com.liferay.portal.theme.ThemeDisplay" %>
<%@page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@page import="com.liferay.portlet.*" %>

<%@page import="javax.portlet.*" %> 

<portlet:defineObjects />
<liferay-theme:defineObjects />

<portlet:resourceURL var='getDataURL' id='getData' />

<script type="text/javascript">
	var basePath = '<%=request.getContextPath()%>';
	var getDataURL = '<%=getDataURL%>';
</script>

