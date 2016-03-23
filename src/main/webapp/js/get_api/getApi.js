function setUpSelector(){
	$("#input_required-apis").click(function () {
        $('.api-options-selector').toggleClass('hidden');
    });

    $('.api-options-selector').mouseleave(function () {	        	
        $('.api-options-selector').addClass('hidden');	
    });
    
    $('.api-options-selector_list_element').click(function () {
        var apiName = $(this).text();
        var apisSelected = $('#input_required-apis').attr('value');
        var newApisSelected = "";
        if ($(this).hasClass('api-selected')) {
            if (apisSelected.indexOf(apiName) > 0) {
                newApisSelected = apisSelected.replace(', ' + apiName, '').trim();
            } else {
                newApisSelected = apisSelected.replace(apiName, '');
                newApisSelected = newApisSelected.replace(", ", "").trim();
            }
        } else {
            if (apisSelected.length > 0) {
                newApisSelected = apisSelected + ", " + apiName;
            } else {
                newApisSelected = apiName;
            }

        }
        $('#input_required-apis').attr('value', newApisSelected);
        $('#input_required-apis').text(newApisSelected);
        $('#input_required-apis').change();
        $(this).toggleClass('api-selected');
    });
}

$(document).ready(function () {
	setUpSelector();
    /*
     * Populate the Required APIs field if we have an api in the params
     */
    if (window.location.search.length > 0) {

        var parametersString,
            paramApi,
            apiToSelect;

        parametersString = window.location.search;

        if (parametersString.indexOf('api=')) {

            paramApi = _.find(window.location.search.split('&'), function (item) {
                return item.indexOf('api=') !== -1
            });
            if (paramApi) {
                apiToSelect = paramApi.split('=')[1];
                $('#' + apiToSelect).click();
            }
        }
    }
    /*
     * Show or hide validation message when focus in/out
     */
    $('.field').focusin(
        function () {
            $(this).parent().children('.vldmsg').addClass('hidden');
        }
    );
    $('.field').focusout(
        function () {
            $(this).parent().children('.vldmsg').removeClass('hidden');
        }
    );
    /*
    * We watch the "ready" to remove the hidden state of the messages
    */
	if($('[ng-controller="getApiController"]').length > 0) {

        $scopeGetApiController = angular.element('[ng-controller="getApiController"]').scope();

        $scopeGetApiController.$watch('[ready]', function () {
        	if ($scopeGetApiController.ready) {
            	if($scopeGetApiController.operationSuccess || $scopeGetApiController.error){
            		setTimeout( function(){
        				if ($('.web-form-portlet.get-api__portlet-view .alert').length > 0) {
        					$('.web-form-portlet.get-api__portlet-view .alert').removeClass('hidden');
        	                $('.web-form-portlet.get-api__portlet-view').addClass('show-message');
        	                $('.get-api__header.form-header__api-market').addClass('show-message');
        	                $('.api-market-form').addClass('show-message');
        	                $('.form-header__api-market').addClass('show-message');
        	                window.scrollTo(0, 0);
        				}
            		},300);
            	}
            	if($scopeGetApiController.operationSuccess){
            		setTimeout( function(){
                		setUpSelector();
            		},300);
            	}
            }
        });
	}
	
});