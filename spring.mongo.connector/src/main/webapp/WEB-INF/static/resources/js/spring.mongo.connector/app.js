var app = angular.module('spring.mongo.connector', []);

app.controller("addNewCompanyController", [ '$scope', '$http', function($scope, $http) {
	$scope.result = {};

	$scope.addNewCompany = function(company) {
		$scope.result = {};
		/*company = {"id":"89e3b80e-d4c0-4d63-90e5-9302a99650bf","fn":"FullName","sn":"ShortName","frn":"friendlyUrl","la":null,"aa":null,"pl":null,"inn":null,"hou":null,"ws":"example.com","ids":{"id":"fb9f9429-0a17-4e14-826e-6607f6cfd37f","value":"val"}};*/
		
		var req = {
			method : 'POST',
			url : '/spring.mongo.connector/spring.mongo/v1/createcompany',
			headers: {
				'Content-Type': 'application/json'
			},
			data : company
		}

		if ((company != null) && (company !== 'undefined')) {
			$http(req).success(function(data, status) {
				$scope.result = {status : status, data: data};
			}).error(function() {
				$scope.result = { status: 'Error'};
				});
		}
	}

} ]);

app.controller("fileUploader", [ '$scope', '$http', function($scope, $http) {
	$scope.uploadFile = function(wrapper) {
		var req = {
			method : 'POST',
			url : '/spring.mongo/v1/uploadFile',
			headers: {
				'Content-Type': 'application/json'
			},
			data : wrapper
		}

		/*if ((company != null) && (company !== 'undefined')) {
			$http(req).success(function(data, status) {
				$scope.result = {status : status, data: data};
			}).error(function() {
				$scope.result = { status: 'Error'};
				});
		}*/
	}
} ]);
/*
 * app.directive('location', function(){ return { restrict: 'E', templateUrl:
 * 'directive/location.html', controller: function(){ } , controllerAs:
 * 'participantListController' };
 */
