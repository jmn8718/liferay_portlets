(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// User Service Definition
	// ////////////////////////////////////////////////////////////////

	getApiApp.factory('getApiService', [ '$http', function($http) {
		return {

			getData : function(formData) {
				return $http({
					method : 'POST',
					data : { form:formData },
					headers : {
						'Content-Type' : 'application/json'
					},
					url : getDataURL
				});
			}
		}
	} ]);

})();
