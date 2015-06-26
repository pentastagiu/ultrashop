app.controller('loginController', [ '$scope', '$location','$timeout','authService',
		function($scope, $location, $timeout, authService) {
			$scope.loginData = {
				userName : "",
				password : ""
			};
			
			$scope.message = "";
			$scope.savedSuccessfully = false;

			$scope.login = function() {
				
				authService.login($scope.loginData).then(function(response) {
					if(response!="")
						$location.path('/products');
					else
						{
							$scope.message="Username or password incorrect !";
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