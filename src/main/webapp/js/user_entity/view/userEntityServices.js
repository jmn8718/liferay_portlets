(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// User Service Definition
	// ////////////////////////////////////////////////////////////////

	userEntityApp.factory('userEntityService', [ '$http', function($http) {

		return {
			getData : function() {
				return $http({
					method : 'GET',
					url : getDataURL
				});
			}
		}

	} ]);

})();