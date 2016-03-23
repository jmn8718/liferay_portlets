(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// Main Controller
	// ////////////////////////////////////////////////////////////////

	userEntityApp.controller('userEntityController', [
			'$scope',
			'userEntityService',
			'$filter',
			'$templateCache',
			'$translate',
			function($scope, userEntityService, $filter, $templateCache,
					$translate) {
				$scope.ready = false;
				$scope.error = false;
				$scope.entity = "api_market";
				$scope.jump = true;
				
				// Construction
				$scope.initialize = function() {
					$translate.use(language);
					
					userEntityService.getData().then(function(result) {	
						if(result.data.error!=null){
							$scope.error = true;	
							$scope.jump = result.data.jump;
						}else if(result.data.entity.length!=null){
							$scope.error = false;
							$scope.entity = result.data.entity;	
						} else {
							$scope.jump = false;
						}
						$scope.ready = true;
					});
				};			
				// Initialization
				$scope.initialize();
			} ]);
})();