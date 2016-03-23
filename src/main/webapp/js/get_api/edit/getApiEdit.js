var getApiDiv = document.getElementById("portlet-get_api_edit");
	angular.bootstrap(getApiDiv, [ 'getApiEditApp' ]);
	
$(document).ready(function () {
	if($('[ng-controller="getApiEditController"]').length > 0) {

        $scopeGetApiEditController = angular.element('[ng-controller="getApiEditController"]').scope();

        $scopeGetApiEditController.$watch('[ready]', function () {

            ready = $scopeGetApiEditController.ready;
            error = $scopeGetApiEditController.error;
            
            if(error!=undefined || error==true){
            	$('.alert.alert-danger').removeClass('hidden');
            	$('.alert.alert-success').addClass('hidden');
            }else {
            	$('.alert.alert-success').removeClass('hidden');
            	$('.alert.alert-danger').addClass('hidden');	
            }
        });
     
	}
	
	
});