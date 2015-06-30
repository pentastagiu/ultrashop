app.controller('loginController', [
		'$scope',
		'$location',
		'$timeout',
		'authService',
		function($scope, $location, $timeout, authService) {
			$scope.loginData = {
				userName : "",
				password : ""
			};
			$scope.message = "";
			$scope.savedSuccessfully = false;
			debugger;
			$scope.login = function() {
				authService.login($scope.loginData).then(
						function(response) {
							debugger;
							if (response != "Incorrect username!"
									&& response != "Incorrect password!"
									&& response != "")
								$location.path('/products');
							else {
								$scope.message = response;
								$timeout(function() {
									$scope.message = "";
									$scope.savedSuccessfully = false;
								}, 3000);
							}

						}, function(err) {
							$scope.message = err.error_description;
							$scope.savedSuccessfully = true;

						});
			};

		} ]);