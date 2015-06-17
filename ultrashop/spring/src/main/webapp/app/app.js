var app = angular.module('ultraShopApp', ['ngRoute','ngCookies','ngSanitize']);

app.directive('modal', function () {
	return {
		template: '<div class="modal fade">' + '<div class="modal-dialog">' + '<div class="modal-content">' + '<div class="modal-header">' + '<h4 class="modal-title">{{ title }}</h4>'
		+ '</div>' + '<div class="modal-body" ng-transclude></div>' + '</div>' + '</div>' + '</div>',
		restrict: 'E',
		transclude: true,
		replace:true,
		scope:true,
		link: function postLink(scope, element, attrs) {
			scope.title = attrs.title;
			
			scope.$watch(attrs.visible, function(value){
				if(value == true)
					$(element).modal('show');
				else
					$(element).modal('hide');
			});
				
			$(element).on('shown.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = true;
				});
			});
			
			$(element).on('hidden.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = false;
				});
			});
		}
	};
});

app.config(['$routeProvider',function ($routeProvider) {

	$routeProvider.when("/products", {
		controller: "productController",
		templateUrl: "/ultrashop/app/views/products.html"
	});
	
	$routeProvider.when("/shoppingCart", {
		controller: "shoppingCartController",
		templateUrl: "/ultrashop/app/views/shoppingCart.html"
	});
	
	$routeProvider.when("/productDescription", {
		controller: "productDescriptionController",
		templateUrl: "/ultrashop/app/views/productDescription.html"
	});

	$routeProvider.otherwise({ redirectTo: "/products" });
}]);