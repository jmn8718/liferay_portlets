var MANAGEMENT_MODE_FRONT_PAGE_REST = "FRONT_PAGE_REST";

// ////////////////////////////////////////////////////////////////
// Application definition.
// ////////////////////////////////////////////////////////////////
var userEntityEditApp = angular.module('userEntityEditApp', ['ngSanitize',
                                                      		'pascalprecht.translate', 'ngAnimate', 'mgcrea.ngStrap', 'ui.select',
                                                    		'tmh.dynamicLocale', 'ngMessages', 'GenoaUtilities' ]);

(function() {
	"use strict";
	// ////////////////////////////////////////////////////////////////
	// Configuration definition.
	// ////////////////////////////////////////////////////////////////
	userEntityEditApp
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
	userEntityEditApp.run(function($templateCache, tmhDynamicLocale) {
		tmhDynamicLocale.set(language.substring(0, 2));
	});
	

})();