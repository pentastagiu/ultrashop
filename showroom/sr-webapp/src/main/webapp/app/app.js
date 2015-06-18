var app = angular.module('AngularAuthApp', 
['ui.bootstrap','ngResource','ngRoute', 'LocalStorageModule', 'angular-loading-bar','pascalprecht.translate','ngMdIcons']);

app.directive(
		'loadingPage', [ '$http', function($http) {
			return {
				restrict : 'A',
				link : function(scope, elm, attrs) {
					scope.isLoading = function() {
						return $http.pendingRequests.length > 0;
					};

					scope.$watch(scope.isLoading, function(v) {
						if (v) {
							elm.show();
						} else {
							elm.hide();
						}
					});
				}
			};
		} ]);

app.directive(
		'loadingContent', [ '$http', function($http) {
			return {
				restrict : 'A',
				link : function(scope, elm, attrs) {
					scope.isLoading = function() {
						return $http.pendingRequests.length > 0;
					};

					scope.$watch(scope.isLoading, function(v) {
						if (v) {
							elm.hide();
						} else {
							elm.show();
						}
					});
				}
			};
		} ]);

app.config(function ($routeProvider) {

    $routeProvider.when("/home", {
        controller: "homeController",
        templateUrl: "/showroom/pages/app/views/home.html"
    });

    $routeProvider.when("/login", {
        controller: "loginController",
        templateUrl: "/showroom/pages/app/views/login.html"
    });

    $routeProvider.when("/signup", {
        controller: "signupController",
        templateUrl: "/showroom/pages/app/views/signup.html"
    });
    
    $routeProvider.when("/orders", {
        controller: "ordersController",
        templateUrl: "/showroom/pages/app/views/orders.html"
    });

    $routeProvider.when("/products", {
        controller: "productsController",
        templateUrl: "/showroom/pages/app/views/products.html"
    });
    
    $routeProvider.when("/progressBar", {
        controller: "ordersController",
        templateUrl: "/showroom/pages/app/views/progressBar.html"
    });
    
    $routeProvider.when("/serial", {
        controller: "ordersController",
        templateUrl: "/showroom/pages/app/views/serial.html"
    });

    $routeProvider.otherwise({ redirectTo: "/home" });
});

app.run(['authService', function (authService) {
    authService.fillAuthData();
}]);

app.config(function ($httpProvider) {

     $httpProvider.interceptors.push('authInterceptorService');
});

app.config(function($translateProvider) {
	$translateProvider.useStaticFilesLoader({
		  prefix: '../ws/bundle/',
		  suffix: ''
		});
	$translateProvider.use('en');
});
