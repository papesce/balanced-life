var app = angular.module('app', [ 'ui.grid']);
 
app.controller('MainCtrl', ['$scope', function ($scope) {
	
	//data model
	  $scope.myData = [
	    {
	        "exerciseName": "Bench Press",
	    },
	    {
	        "exerciseName": "Triceps",
	    },
	];
	
 //grid options
	$scope.gridOptions = {
			 enableFiltering: true,
			 data: $scope.myData,
			 columnDefs: [
			  { field: 'exerciseName', headerCellClass: $scope.highlightFilteredHeader }
			  ]
	}

	
 //toggle filrering called from button	
	$scope.toggleFiltering = function(){
	  $scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
	  //$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
	};
	
 
}]);