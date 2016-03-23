<%@include file="init_edit.jsp"%>

<div id="portlet-get_api_edit">
	<div id="getApiEditPortlet" ng-controller="getApiEditController">
		<div id="getApiEditPortlet-form">		
			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode == null"
				class="alert alert-danger hidden">{{'apiMarket.edit.error' | translate}}</div>

			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode != null"
				class="alert alert-danger hidden">{{'apiMarket.edit.error' | translate}} - Error: {{errorCode}}</div>

			<div data-alert="operation-success-message"
				ng-if="ready && !error && operationSuccess"
				class="alert alert-success hidden">{{'apiMarket.edit.success' | translate}}</div>
				
			<form name="getApiEditForm" id="getApiEditForm" class="form-horizontal getApiEditForm" novalidate>
				<h1 class=editFormTitle>{{'getApi.edit.armadillo.settings' | translate}}</h1>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="armadilloEndpoint">{{'getApi.edit.armadillo.endpoint' | translate}}: </label>
						<input type="text" id="armadilloEndpoint" name="armadilloEndpoint" palceholder="Armadillo Endpoint" ng-model="preferences.armadilloEndpoint">
							<span class='vldmsg'
							ng-messages="getApiEditForm.armadilloEndpoint.$error"
							ng-if="getApiEditForm.armadilloEndpoint.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="armadilloAuthorization">{{'getApi.edit.armadillo.authorization' | translate}}: </label>
						<input type="text" id="armadilloAuthorization" name="armadilloAuthorization" palceholder="Armadillo Authorization" ng-model="preferences.armadilloAuthorization">
							<span class='vldmsg'
							ng-messages="getApiEditForm.armadilloAuthorization.$error"
							ng-if="getApiEditForm.armadilloAuthorization.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="armadilloCipherEndpoint">{{'getApi.edit.armadillo.cipherUrl' | translate}}: </label>
						<input type="text" id="armadilloCipherEndpoint" name="armadilloCipherEndpoint" palceholder="Armadillo Cipher Url" ng-model="preferences.armadilloCipherEndpoint">
							<span class='vldmsg'
							ng-messages="getApiEditForm.armadilloCipherEndpoint.$error"
							ng-if="getApiEditForm.armadilloCipherEndpoint.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				
				<h1 class=editFormTitle>{{'getApi.edit.salesforce.settings' | translate}}</h1>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceAuthenticationEndpoint">{{'getApi.edit.salesforce.authenticationEndpoint' | translate}}: </label>
						<input type="text" id="salesforceAuthenticationEndpoint" name="salesforceAuthenticationEndpoint" palceholder="Salesforce Authentication Endpoint" ng-model="preferences.salesforceAuthenticationEndpoint">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceAuthenticationEndpoint.$error"
							ng-if="getApiEditForm.salesforceAuthenticationEndpoint.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div><div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceJourneyEndpoint">{{'getApi.edit.salesforce.journeyEndpoint' | translate}}: </label>
						<input type="text" id="salesforceJourneyEndpoint" name="salesforceJourneyEndpoint" palceholder="Salesforce Journey Endpoint" ng-model="preferences.salesforceJourneyEndpoint">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceJourneyEndpoint.$error"
							ng-if="getApiEditForm.salesforceJourneyEndpoint.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceClientId">{{'getApi.edit.salesforce.clientId' | translate}}: </label>
						<input type="text" id="salesforceClientId" name="salesforceClientId" palceholder="Client Id" ng-model="preferences.salesforceClientId">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceClientId.$error"
							ng-if="getApiEditForm.salesforceClientId.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceClientSecret">{{'getApi.edit.salesforce.clientSecret' | translate}}: </label>
						<input type="text" id="salesforceClientSecret" name="salesforceClientSecret" palceholder="Client Secret" ng-model="preferences.salesforceClientSecret">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceClientSecret.$error"
							ng-if="getApiEditForm.salesforceClientSecret.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceContactKey">{{'getApi.edit.salesforce.contactKey' | translate}}: </label>
						<input type="text" id="salesforceContactKey" name="salesforceContactKey" palceholder="Contact Key" ng-model="preferences.salesforceContactKey">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceContactKey.$error"
							ng-if="getApiEditForm.salesforceContactKey.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceEventDefinitionKey">{{'getApi.edit.salesforce.eventDefinitionKey' | translate}}: </label>
						<input type="text" id="salesforceEventDefinitionKey" name="salesforceEventDefinitionKey" palceholder="Event Definition Key" ng-model="preferences.salesforceEventDefinitionKey">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceEventDefinitionKey.$error"
							ng-if="getApiEditForm.salesforceEventDefinitionKey.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceSubscriberKey">{{'getApi.edit.salesforce.subscriberKey' | translate}}: </label>
						<input type="text" id="salesforceSubscriberKey" name="salesforceSubscriberKey" palceholder="Subscriber Key" ng-model="preferences.salesforceSubscriberKey">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceSubscriberKey.$error"
							ng-if="getApiEditForm.salesforceSubscriberKey.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="salesforceSubscriberKeyUserTemplate">{{'getApi.edit.salesforce.subscriberKeyUserTemplate' | translate}}: </label>
						<input type="text" id="salesforceSubscriberKeyUserTemplate" name="salesforceSubscriberKeyUserTemplate" palceholder="Subscriber Key User Template" ng-model="preferences.salesforceSubscriberKeyUserTemplate">
							<span class='vldmsg'
							ng-messages="getApiEditForm.salesforceSubscriberKeyUserTemplate.$error"
							ng-if="getApiEditForm.salesforceSubscriberKeyUserTemplate.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				
				
				
				<h1 class=editFormTitle>{{'getApi.edit.form.apisTitle' | translate}}</h1>
				<div class="control-group">
					<div class="controls">
						<label class="getApiEditForm_label" for="apisAvailables">{{'getApi.edit.form.apisInput' | translate}}: </label>
						<input type="text" id="apisAvailables" name="apisAvailables" palceholder="APIS" ng-model="preferences.apisAvailables">
							<span class='vldmsg'
							ng-messages="getApiEditForm.apisAvailables.$error"
							ng-if="getApiEditForm.apisAvailables.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>
				<div class="control-group control-group_btn-submit">
					<div class="controls">
						<a type="button" class="btn btn-primary btn-form-submit"
							ng-click="getApiEditForm.$valid && formSubmit()">{{'apiMarket.edit.save' | translate}}</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<h1>Example of journey</h1>
<pre>
{
     "ContactKey":         "APIMARKETBUZON",
     "EventDefinitionKey": "KEY_API_EV_407_UPGRADE_USER",
     "Data":  
           {  
  	           "subscriberKey": "APIMARKETBUZON",
               "desCompany": "BEEVA",
               "desCountry": "ESP",
               "desWebsite": "www.beeva.com",
               "desRequiredAPIs": "API_Market",
               "desAPIBizzGoal": "Why not",
               "subscriberKeyUser": "APIMARKET532",
               "emailUser": "hufaypi=ema@fake.com",
               "timeStamp": "2015/11/16 11:34:15"
           }
}
</pre>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_es.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/i18n/language_en.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/edit/getApiEditApp.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/edit/getApiEditServices.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/edit/getApiEditController.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/get_api/edit/getApiEdit.js"></script>