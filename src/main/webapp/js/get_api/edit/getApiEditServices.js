(function() {
	"use strict";

	// ////////////////////////////////////////////////////////////////
	// Get Api Service Definition
	// ////////////////////////////////////////////////////////////////

	getApiEditApp.factory('getApiEditService', [ '$http', function($http) {

		return {
			getPrefs : function() {
				return $http({
					method : 'GET',
					url : getPrefsURL
				});
			},
			putPrefs : function(preferencesData) {
				return $http({
					method : 'POST',
					data : { preferences:preferencesData },
					headers : {
						'Content-Type' : 'application/json'
					},
					url : putPrefsURL
				});
			}
		}

	} ]);

})();