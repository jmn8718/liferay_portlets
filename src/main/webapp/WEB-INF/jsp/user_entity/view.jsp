<%@include file="init_view.jsp"%>

<div id="portlet-user_entity">	
	<div id="userEntity" ng-controller="userEntityController">
		<div class="spinner">
			<div class="spinner_bar rect1"></div>
			<div class="spinner_bar rect2"></div>
			<div class="spinner_bar rect3"></div>
			<div class="spinner_bar rect4"></div>
			<div class="spinner_bar rect5"></div>
		</div>	
		<div class="entity-navigation__title" data-element="entity_navigation_title">
			{{'userEntity.view.message' | translate}}
		</div>
	</div>	
</div>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_es.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_en.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/view/userEntityApp.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/view/userEntityServices.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/view/userEntityController.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/view/userEntityView.js"></script>
