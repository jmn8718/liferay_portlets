<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_es.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_en.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/getApi.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/getApiApp.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/getApiServices.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/getApiController.js"></script>
	
<script type="text/javascript">
	var getApi = document.getElementById("portlet-get_api");
	angular.bootstrap(getApi, [ 'getApiApp' ]);	
</script>