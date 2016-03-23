var userEntityDiv = document.getElementById("portlet-user_entity_edit");
	angular.bootstrap(userEntityDiv, [ 'userEntityEditApp' ]);
	
$(document).ready(function () {
	if($('[ng-controller="userEntityEditController"]').length > 0) {

        $scopeUserEntityController = angular.element('[ng-controller="userEntityEditController"]').scope();

        $scopeUserEntityController.$watch('[ready]', function () {

            ready = $scopeUserEntityController.ready;
            error = $scopeUserEntityController.error;
            
            if(error==undefined || error==true){
            	$('.alert.alert-danger').removeClass('hidden');
            	$('.alert.alert-success').addClass('hidden');
            }else {
            	$('.alert.alert-success').removeClass('hidden');
            	$('.alert.alert-danger').addClass('hidden');	
            }
        });
     
	}
	
	
});