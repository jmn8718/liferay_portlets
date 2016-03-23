(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// Main Controller
	// ////////////////////////////////////////////////////////////////
	getApiEditApp.controller('getApiEditController', [
  			'$scope',
  			'getApiEditService',
  			'$filter',
			'$templateCache',
			'$translate',
  			function($scope, getApiEditService, $filter, $templateCache,
					$translate) {		
  				// Construction
  				$scope.preferences = {
					armadilloEndpoint: armadilloEndpoint,
  					armadilloAuthorization: armadilloAuthorization,
  					armadilloCipherEndpoint: armadilloCipherEndpoint,
					salesforceAuthenticationEndpoint: salesforceAuthenticationEndpoint,
					salesforceJourneyEndpoint: salesforceJourneyEndpoint,
					salesforceContactKey:salesforceContactKey,
					salesforceSubscriberKey:salesforceSubscriberKey,
					salesforceSubscriberKeyUserTemplate:salesforceSubscriberKeyUserTemplate,
					salesforceEventDefinitionKey:salesforceEventDefinitionKey,
  					salesforceClientId: salesforceClientId,
  					salesforceClientSecret: salesforceClientSecret,
  					apisAvailables: apisAvailables
  				}  				
  				
  				$scope.ready = false;
  				$scope.operationSuccess = false;
				$scope.error=false;
				$scope.errorCode=undefined;
				
  				$scope.formSubmit = function(){
					$scope.ready = false;
					getApiEditService.putPrefs($scope.preferences).then(function(result) {	
						console.log('SAVING...',result.data)
						if(result.data.error!=null){
							$scope.operationSuccess = false;
							$scope.error=true;
							$scope.errorCode = result.data.error;
						} else {
							$scope.preferences = result.data.preferences;
							$scope.operationSuccess = true;
							$scope.error=false;	
  							if(result.data.errorCode)
  								$scope.errorCode=result.data.errorCode;					
						}
						$scope.ready = true;
					});
				}
  				
  				$scope.initialize = function() {
					$translate.use(language);
					
					getApiEditService.getPrefs().then(function(result) {	
						console.log('GETTING DATA...',result.data)
  						if(result.data.operationSuccess!=null){
  							$scope.preferences = result.data.preferences;
  						} else {
  			  				$scope.operationSuccess = false;
  							$scope.error=true;
  							if(result.data.errorCode)
  								$scope.errorCode=result.data.erroCode;
  							else
  								$scope.errorCode="Error getting the preferences. Please try again.";
  						}
  						$scope.ready = true;
  					});  					
  				};		
  				
  				// Initialization
  				$scope.initialize();
  			} ]);
})();