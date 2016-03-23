var userEntityDiv = document.getElementById("portlet-user_entity");
	angular.bootstrap(userEntityDiv, [ 'userEntityApp' ]);
	
$(document).ready(function () {	
	if($('[ng-controller="userEntityController"]').length > 0) {

        $scopeUserEntityController = angular.element('[ng-controller="userEntityController"]').scope();

        $scopeUserEntityController.$watch('[ready]', function () {
            if ($scopeUserEntityController.ready && $scopeUserEntityController.jump) {
                setTimeout(function () {
                    var entity = $scopeUserEntityController.entity;
                    if(entity.length > 0)
                    	window.location.href = window.location.origin + "/group/" + entity + "/my-apps";
                }, 100);
            }
        });
	}
});