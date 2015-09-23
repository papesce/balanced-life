// tableController.js
(function() {
    'use strict';

    angular.module('app').controller('TableController', 
    		function ($scope, GymService) {
	
	
 //grid options
	$scope.gridOptions = {
			 enableFiltering: true,
			 data: [],
			 columnDefs: [
			  { field: 'name', headerCellClass: $scope.highlightFilteredHeader }
			  ]
	}

	
	//toggle filrering called from button	
	$scope.toggleFiltering = function(){
	  $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
	  //$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
	};
	
	
	GymService.readAll().
	then(function(response) {
		 $scope.gridOptions.data = response.data;
	});

	
});
})();