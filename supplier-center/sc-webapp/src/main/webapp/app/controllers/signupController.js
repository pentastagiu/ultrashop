app
		.controller(
				'signupController',
				[
						'$scope',
						'$location',
						'$timeout',
						'authService',
						function($scope, $location, $timeout, authService) {

							$scope.savedSuccessfully = false;
							$scope.message = "";

							$scope.registration = {
								userName : "",
								password : "",
								confirmPassword : ""
							};
							$scope.signUp = function() {
								if ($scope.registration.userName.length < 4)
									$scope.message = "Username must be at least 4 characters!";
								else if ($scope.registration.password.length < 5)
									$scope.message = "The password must be at least 5 characters!";
								else if ($scope.registration.password != $scope.registration.confirmPassword)
									$scope.message = "The two passwords are not identical !";
								else {
									authService
											.saveRegistration(
													$scope.registration)
											.then(
													function(response) {
														if (response == "NOT OK") {
															$scope.message = "User with the same username already exists";
														} else {
															$scope.savedSuccessfully = true;
															$scope.registration = {
																	userName : "",
																	password : "",
																	confirmPassword : ""
																};
															$scope.message = "Operator has been registered successfully";
														}

													},
													function(response) {
														var errors = [];
														for ( var key in response.data.modelState) {
															for (var i = 0; i < response.data.modelState[key].length; i++) {
																errors
																		.push(response.data.modelState[key][i]);
															}
														}
														$scope.message = "Failed to register user due to:"
																+ errors
																		.join(' ');
													});
								}
								$timeout(function() {
									$scope.message = "";
								}, 3000);

							};

							var startTimer = function() {
								var timer = $timeout(function() {
									$timeout.cancel(timer);
									$location.path('/login');
								}, 2000);
							};

						} ]);