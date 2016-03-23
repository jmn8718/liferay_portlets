(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// Main Controller
	// ////////////////////////////////////////////////////////////////
	userEntityEditApp.controller('userEntityEditController', [
  			'$scope',
  			'userEntityEditService',
  			'$filter',
			'$templateCache',
			'$translate',
  			function($scope, userEntityEditService, $filter, $templateCache,
					$translate) {		
  				// Construction
  				$scope.preferences = {
  					appId:appId,
  					appKey: appKey,
  					endpoint: endpoint,
  					delay: 4
  				}
  				$scope.preferences.delay = parseInt(delay, 10);
  				
  				
  				$scope.ready = false;
  				$scope.operationSuccess = false;
				$scope.error=false;
				$scope.errorCode=undefined;
				
  				$scope.formSubmit = function(){
					$scope.ready = false;
					userEntityEditService.putPrefs($scope.preferences).then(function(result) {
						if(result.data.error!=null){
							$scope.operationSuccess = false;
							$scope.error=true;
							$scope.errorCode = result.data.error;
						} else {
							$scope.preferences = result.data.preferences;
							$scope.preferences.delay = parseInt($scope.preferences.delay, 10);
							$scope.operationSuccess = true;
							$scope.error=false;							
						}
						$scope.ready = true;
					});
				}
  				
  				$scope.initialize = function() {
					$translate.use(language);
					
  					userEntityEditService.getPrefs().then(function(result) {	
  						if(result.data.operationSuccess!=null){
  							$scope.preferences = result.data.preferences;
							$scope.preferences.delay = parseInt($scope.preferences.delay, 10);
  						} else {
  			  				$scope.operationSuccess = false;
  							$scope.error=true;
  							$scope.errorCode="Error getting the preferences. Please try again.";
  						}
  						$scope.ready = true;
  					});  					
  				};		
  				
  				// Initialization
  				$scope.initialize();
  			} ]);
})();