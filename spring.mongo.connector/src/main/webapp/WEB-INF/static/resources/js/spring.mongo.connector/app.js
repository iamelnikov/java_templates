var app = angular.module('spring.mongo.connector', [ "ngSanitize",
		"com.2fdevs.videogular", "com.2fdevs.videogular.plugins.controls",
		"com.2fdevs.videogular.plugins.overlayplay"
/* "com.2fdevs.videogular.plugins.poster" */]);
var videoService = angular.module('videoSerivce', [ '$http', function($http) {
	
} ]);

app.controller(
				"addNewCompanyController",
				[
						'$scope',
						'$http',
						function($scope, $http) {
							$scope.result = {};

							$scope.addNewCompany = function(company) {
								$scope.result = {};
								/*
								 * company =
								 * {"id":"89e3b80e-d4c0-4d63-90e5-9302a99650bf","fn":"FullName","sn":"ShortName","frn":"friendlyUrl","la":null,"aa":null,"pl":null,"inn":null,"hou":null,"ws":"example.com","ids":{"id":"fb9f9429-0a17-4e14-826e-6607f6cfd37f","value":"val"}};
								 */

								var req = {
									method : 'POST',
									url : '/spring.mongo.connector/spring.mongo/v1/createcompany',
									headers : {
										'Content-Type' : 'application/json'
									},
									data : company
								}

								if ((company != null)
										&& (company !== 'undefined')) {
									$http(req).success(function(data, status) {
										$scope.result = {
											status : status,
											data : data
										};
									}).error(function() {
										$scope.result = {
											status : 'Error'
										};
									});
								}
							}

						} ]);
app
		.controller(
				'HomeCtrl',
				[
						"$sce",
						function($sce) {
							this.config = {
								sources : [
										{
											src : $sce
													.trustAsResourceUrl("/spring.mongo.connector/spring.mongo/v1/getFile/11111"),
											type : "video/mp4"
										},
										{
											src : $sce
													.trustAsResourceUrl("http://static.videogular.com/assets/videos/videogular.webm"),
											type : "video/webm"
										},
										{
											src : $sce
													.trustAsResourceUrl("http://static.videogular.com/assets/videos/videogular.ogg"),
											type : "video/ogg"
										} ],
								tracks : [ {
									src : "/spring.mongo.connector/spring.mongo/v1/getFile/11111",
									kind : "subtitles",
									srclang : "en",
									label : "English",
								/* default: "" */
								} ],
								theme : "../resources/js/videogular-themes-default/videogular.css",
								plugins : {
									poster : "http://www.videogular.com/assets/images/videogular.png"
								}
							};
						} ]);
app
		.controller(
				"fileUploader",
				[
						'$scope',
						'$http',
						function($scope, $http) {
							$scope.uploadFile = function(wrapper, file) {
								var req = {

									method : 'POST',
									url : '/spring.mongo.connector/spring.mongo/v1/uploadFile',
									headers : {
										/*
										 * 'Content-Type':
										 * 'multipart/form-data',
										 */
										'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
										enctype : 'multipart/form-data'
									},
									data : wrapper,
									transformRequest : function(data) {
										var fd = new FormData();
										fd.append("fn", data.fn);
										fd.append("un", data.un);
										fd.append("file", angular.toJson(file));
										return fd;
									}
								}

								$http(req).success(function(data, status) {
									$scope.result = {
										status : status,
										data : data
									};
								}).error(function() {
									$scope.result = {
										status : 'Error'
									};
								});

							}
						} ]);

app.directive("fileread", [ function() {
	return {
		scope : {
			fileread : "="
		},
		link : function(scope, element, attributes) {
			element.bind("change", function(changeEvent) {
				var reader = new FileReader();
				reader.onload = function(loadEvent) {
					scope.$apply(function() {
						scope.fileread = loadEvent.target.result;
					});
				}
				reader.readAsDataURL(changeEvent.target.files[0]);
			});
		}
	}
} ]);
/*
 * app.directive('location', function(){ return { restrict: 'E', templateUrl:
 * 'directive/location.html', controller: function(){ } , controllerAs:
 * 'participantListController' };
 */
