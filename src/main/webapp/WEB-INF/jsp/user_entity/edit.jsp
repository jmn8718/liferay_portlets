<%@include file="init_edit.jsp"%>

<div id="portlet-user_entity_edit">
	<div id="userEntityEditPortlet" ng-controller="userEntityEditController">
		<div id="userEntityEditPortlet-form">		
			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode == null"
				class="alert alert-danger hidden">{{'apiMarket.edit.error' | translate}}</div>

			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode != null"
				class="alert alert-danger hidden">{{'apiMarket.edit.error' | translate}} - Error: {{errorCode}}</div>

			<div data-alert="operation-success-message"
				ng-if="ready && !error && operationSuccess"
				class="alert alert-success hidden">{{'apiMarket.edit.success' | translate}}</div>
				
			<form name="userEntityEditForm" id="userEntityEditForm" class="form-horizontal userEntityEditForm" novalidate>
				<div class="control-group">
					<div class="controls">
						<label class="userEntityEditForm_label" for="endpoint">{{'userEntity.edit.endpoint' | translate}}: </label>
						<input type="text" id="endpoint" name="endpoint" palceholder="endpoint" ng-model="preferences.endpoint">
							<span class='vldmsg'
							ng-messages="userEntityEditForm.endpoint.$error"
							ng-if="userEntityEditForm.endpoint.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="userEntityEditForm_label" for="appId">{{'userEntity.edit.appId' | translate}}: </label>
						<input type="text" id="appId" name="appId" palceholder="appId" ng-model="preferences.appId">
							<span class='vldmsg'
							ng-messages="userEntityEditForm.appId.$error"
							ng-if="userEntityEditForm.appId.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
					<label class="userEntityEditForm_label" for="appKey">{{'userEntity.edit.appKey' | translate}}: </label>
						<input type="text" id="appKey" name="appKey" palceholder="appKey" ng-model="preferences.appKey">
							<span class='vldmsg'
							ng-messages="userEntityEditForm.appKey.$error"
							ng-if="userEntityEditForm.appKey.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
					<label class="userEntityEditForm_label" for="appKey">{{'userEntity.edit.delay' | translate}}: </label>
						<input type="number" id="delay" name="delay" palceholder="delay" ng-model="preferences.delay">
							<span class='vldmsg'
							ng-messages="userEntityEditForm.delay.$error"
							ng-if="userEntityEditForm.delay.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group control-group_btn-submit">
					<div class="controls">
						<a type="button" class="btn btn-primary btn-form-submit"
							ng-click="userEntityEditForm.$valid && formSubmit()">{{'apiMarket.edit.save' | translate}}</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_es.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_en.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/edit/userEntityEditApp.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/edit/userEntityEditServices.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/edit/userEntityEditController.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/user_entity/edit/userEntityEdit.js"></script>