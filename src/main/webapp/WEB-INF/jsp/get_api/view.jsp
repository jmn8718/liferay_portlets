<%@include file="init.jsp"%>
<div id="portlet-get_api">
	<div id="getApiPortlet" ng-controller="getApiController">
		<div class="spinner" ng-if="!ready">
			<div class="spinner_bar rect1"></div>
			<div class="spinner_bar rect2"></div>
			<div class="spinner_bar rect3"></div>
			<div class="spinner_bar rect4"></div>
			<div class="spinner_bar rect5"></div>
		</div>
		<div id="getApiPortlet-form" class="web-form-portlet get-api__portlet-view">
			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode == null"
				class="alert alert-danger hidden">{{'getApi.form.send.error' | translate}}</div>

			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode != null"
				class="alert alert-danger hidden">{{'getApi.form.send.error' | translate}}</div>

			<div data-alert="operation-success-message"
				ng-if="ready && !error && operationSuccess"
				class="alert alert-success hidden">{{'getApi.form.send.success' | translate}}</div>

			<form name="getApiForm" id="getApiForm" ng-if="ready"
				class="form-horizontal" novalidate>

				<div class="control-group" data-control-group="get-api-company">
					<div class="controls">
						<input type="text" id="input_company" name="company" class="field"
							ng-model="formFields.company" data-input="company"
							maxlength="255" placeholder="Company *" ng-required="true"></input>
						<span class='vldmsg' ng-messages="getApiForm.company.$error"
							ng-if="getApiForm.company.$touched" data-error="company">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group">
					<div class="controls">
						<input type="text" id="input_country" name="country" class="field"
							ng-model="formFields.country" data-input="country" maxlength="50"
							placeholder="Country *" ng-required="true"></input> <span
							class='vldmsg' data-error="country"
							ng-messages="getApiForm.country.$error"
							ng-if="getApiForm.country.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group" data-control-group="get-api-website">
					<div class="controls">
						<input type="text" id="website" name="website" class="field"
							ng-model="formFields.website" placeholder="Website" maxlength="50"
							data-input="website"></input> <span class='vldmsg'
							data-error="website"></span>

					</div>
				</div>

				<div class="control-group">
					<div class="controls select-controls">
						<input ng-model="formFields.requiredApis" id="input_required-apis"
							name="requiredApis" data-input="required-apis" type="text"
							class="required_apis_disabled field input_selectbox"
							placeholder="Required APIs *" readonly="readonly"
							ng-required="true"></input>
						<div class="api-options-selector hidden"
							data-element="api-options-selector">
							<ul class="api-options-selector_list"
								data-element="api-options-selector_list" ng-if="apisAvailables">
								<li class="api-options-selector_list_element" id="bbva_data_api"
									data-element="api-options-selector_list_element_paystats">PayStats</li>
								<li class="api-options-selector_list_element" id="identity"
									data-element="api-options-selector_list_element_bbva-connect">BBVA Connect</li>
								<li class="api-options-selector_list_element" id="identity"
									data-element="api-options-selector_list_element_identity">Identity</li>
								<li class="api-options-selector_list_element" id="accounts"
									data-element="api-options-selector_list_element_accounts">Accounts</li>
								<li class="api-options-selector_list_element" id="money_transfers"
									data-element="api-options-selector_list_element_money-transfers">Money Transfers</li>
								<li class="api-options-selector_list_element" id="cards"
									data-element="api-options-selector_list_element_cards">Cards</li>
							</ul>
							<ul class="api-options-selector_list"
								data-element="api-options-selector_list" ng-if="apisAvailables">
								<li ng-repeat="api in apisAvailables" class="api-options-selector_list_element" id="{{ api.name | spaceToUnderscore}}"
									data-element="api-options-selector_list_element_{{ api.name | spaceToUnderscore}}">{{api.name}}</li>
							</ul>
						</div>
						<span class='vldmsg' data-error="required-apis"
							ng-messages="getApiForm.requiredApis.$error"
							ng-if="getApiForm.requiredApis.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>


				<div class="control-group"
					data-control-group="get-api-business-objective">
					<div class="controls">
						<textarea id="input_api-business-objective" class="field"
							name="apiBusinessObjective" maxlength="255"
							ng-model="formFields.apiBusinessObjective"
							placeholder="API Business objective *"
							data-input="api-business-objective" ng-required="true"></textarea>
						<span class='vldmsg' data-error="api-business-objective"
							ng-messages="getApiForm.apiBusinessObjective.$error"
							ng-if="getApiForm.apiBusinessObjective.$touched">
							<div ng-message="required">{{'common.validation.required' | translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group" data-control-group="get-api-submit">
					<div class="controls">
						<a type="button" class="btn btn-primary"
							data-submit="send-email-button" ng-disabled="getApiForm.$invalid"
							ng-click="getApiForm.$valid && formSubmit()">Send</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<%@include file="lib.jsp"%>