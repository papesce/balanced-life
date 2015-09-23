// excerciseController.js
(function() {
    'use strict';

    angular.module('app')
    .controller('ExcerciseController', function($scope, $http, GymService) {
    	GymService.readOne().
        	then(function(response) {
        		$scope.excercise = response.data.name;
        });
});
})();