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
				class="alert alert-danger hidden">{{'getApi.form.send.error'|translate}}</div>

			<div data-alert="operation-error-message"
				ng-if="ready && error && errorCode != null"
				class="alert alert-danger hidden">{{'getApi.form.send.error'|translate}}</div>

			<div data-alert="operation-success-message"
				ng-if="ready && !error && operationSuccess"
				class="alert alert-success hidden">{{'getApi.form.send.success'|translate}}</div>

			<form name="getApiForm" id="getApiForm" ng-if="ready"
				class="form-horizontal" novalidate>

				<div class="control-group" data-control-group="get-api-user-name">
					<div class="controls">
						<input type="text" id="input_name" name="name" class="field"
							ng-model="formFields.username" data-input="name" maxlength="50"
							placeholder="Name *" ng-required="true"></input> <span
							class='vldmsg' data-error="name"
							ng-messages="getApiForm.name.$error"
							ng-if="getApiForm.name.$touched">
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group" data-control-group="get-api-email">
					<div class="controls">
						<input type="email" id="input_email" name="email" class="field"
							ng-model="formFields.email" data-input="email" maxlength="50"
							placeholder="Email *" ng-required="true" ng-pattern=" /[\w-\.]{1,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/"></input> <span
							class='vldmsg' data-error="email"
							ng-messages="getApiForm.email.$error"
							ng-if="getApiForm.email.$touched">
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
							<div ng-message="pattern">{{'getApi.form.validation.email.invalid'|translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group" data-control-group="get-api-company">
					<div class="controls">
						<input type="text" id="input_company" name="company" class="field"
							ng-model="formFields.company" data-input="company"
							maxlength="255" placeholder="Company *" ng-required="true"></input>
						<span class='vldmsg' ng-messages="getApiForm.company.$error"
							ng-if="getApiForm.company.$touched" data-error="company">
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
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
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
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
							class="required_apis_disabled field"
							placeholder="Required APIs *" readonly="readonly"
							ng-required="true"></input>
						<div class="api-options-selector hidden"
							data-element="api-options-selector">
							<ul class="api-options-selector_list"
								data-element="api-options-selector_list">
								<li class="api-options-selector_list_element" id="bbva_data_api"
									data-element="api-options-selector_list_element_bbva-data-api">BBVA Data API</li>
								<li class="api-options-selector_list_element" id="bbva_connect"
									data-element="api-options-selector_list_element_bbva-connect">BBVA Connect</li>
								<li class="api-options-selector_list_element" id="identity"
									data-element="api-options-selector_list_element_identity">Identity</li>
								<li class="api-options-selector_list_element" id="accounts"
									data-element="api-options-selector_list_element_accounts">Accounts</li>
								<li class="api-options-selector_list_element" id="money_transfers"
									data-element="api-options-selector_list_element_money-transfers">Money transfers</li>
								<li class="api-options-selector_list_element" id="cards"
									data-element="api-options-selector_list_element_cards">Cards</li>
							</ul>
						</div>
						<span class='vldmsg' data-error="required-apis"
							ng-messages="getApiForm.requiredApis.$error"
							ng-if="getApiForm.requiredApis.$touched">
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
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
							ng-if="getApiForm.apiBusinessObjetive.$touched">
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group" data-control-group="get-api-phone">
					<div class="controls">
						<input type="text" id="input_contact-phone" class="field"
							ng-model="formFields.phone" name="contactPhone"
							placeholder="Contact Phone *" data-input="contact-phone"
							ng-required="true" maxlength="50"></input> <span class='vldmsg'
							data-error="contact-phone"
							ng-messages="getApiForm.contactPhone.$error"
							ng-if="getApiForm.contactPhone.$touched">
							<div ng-message="required">{{'getApi.form.validation.required'|translate}}</div>
						</span>
					</div>
				</div>

				<div class="control-group" data-control-group="get-api-submit">
					<div class="controls">
						<a type="button" class="btn btn-primary"
							data-submit="send-email-button" ng-disabled="getApiForm.$invalid"
							ng-click="getApiForm.$valid && formSubmit()">
							<div ng-message="required">{{'getApi.form.send.action'|translate}}</div></a>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<%@include file="lib.jsp"%>