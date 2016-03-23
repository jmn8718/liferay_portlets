(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// Main Controller
	// ////////////////////////////////////////////////////////////////

	getApiApp.controller('getApiController', [
			'$scope',
			'getApiService',
  			'$filter',
			'$templateCache',
			'$translate',
			function($scope, getApiService, $filter, $templateCache,
					$translate) {
				$scope.ready = false;
				$scope.error = false;
				$scope.errorCode = null;
				$scope.operationSuccess = false;
				$scope.apisAvailables = apisAvailables;
				
				$scope.formFields = {
						company:"",
						country:"",
						website:"",
						requiredApis:"",
						apiBusinessObjective:""
				};
				
				$scope.formSubmit = function(){
					$scope.ready = false;
					getApiService.getData($scope.formFields).then(function(result) {
						console.log('result data',result.data);
						if(result.data.error!=null){
							$scope.operationSuccess = false;
							$scope.error=true;
							$scope.errorCode = result.data.errorCode;
						} else {
							$scope.code = result.data.code;
							$scope.values = result.data.values;
							$scope.operationSuccess = true;
							$scope.error=false;
							$scope.formFields = {
									company:"",
									country:"",
									website:"",
									requiredApis:"",
									apiBusinessObjective:""
							};
						}
						$scope.ready = true;
					});
				}
				// Construction
				$scope.initialize = function() {
					$translate.use(language);
					$scope.ready = true;
				};			
				// Initialization
				$scope.initialize();

			} ]);
})();
