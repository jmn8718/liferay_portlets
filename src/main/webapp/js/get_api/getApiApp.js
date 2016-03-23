// ////////////////////////////////////////////////////////////////
// Application definition.
// ////////////////////////////////////////////////////////////////
var getApiApp = angular.module('getApiApp', ['ngSanitize',
                                       		'pascalprecht.translate', 'ngAnimate', 'mgcrea.ngStrap', 'ui.select',
                                    		'tmh.dynamicLocale', 'ngMessages', 'GenoaUtilities' ]);

(function() {
	"use strict";

	getApiApp.filter('spaceToUnderscore',function(){
		return function(text){
			return text.trim().split(' ').join('_').toLowerCase();
		};
	});
	// ////////////////////////////////////////////////////////////////
	// Configuration definition.
	// ////////////////////////////////////////////////////////////////
	getApiApp
		.config([
				'$compileProvider',
				'tmhDynamicLocaleProvider',
				'$translateProvider',
				'$tooltipProvider',
				function($compileProvider, tmhDynamicLocaleProvider,
						$translateProvider, $tooltipProvider) {
					tmhDynamicLocaleProvider
							.localeLocationPattern('/html/js/angular/i18n/angular-locale_{{locale}}.js');
					$translateProvider.translations('es-ES',
							LANGUAGE_i18_ES);
					$translateProvider.translations('en-US',
							LANGUAGE_i18_EN);
					$translateProvider.preferredLanguage('es-ES');
					$compileProvider
							.aHrefSanitizationWhitelist(/^\s*(https?|blob):/);
				} ]);
	
	// ////////////////////////////////////////////////////////////////
	// Starting the Locale.
	// ////////////////////////////////////////////////////////////////
	
	getApiApp.run(function($templateCache, tmhDynamicLocale) {
		tmhDynamicLocale.set(language.substring(0, 2));
	});
	

})();
