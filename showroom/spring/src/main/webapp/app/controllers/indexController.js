app.controller('indexController', [ '$scope', '$location', 'authService','$translate',
		function($scope, $location, authService,$translate) {
	$scope.changeLanguage = function (langKey) {
	    $translate.use(langKey);
	    $scope.locale = langKey;
	  };
			$scope.logOut = function() {
				authService.logOut();
				$location.path('/login');
			};

			$scope.authentication = authService.authentication;

		} ]);