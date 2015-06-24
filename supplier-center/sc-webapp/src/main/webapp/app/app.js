var app = angular.module('supplierCenterApp', ['ngRoute']);
app.config(['$routeProvider',function ($routeProvider) {

	$routeProvider.when("/products", {
		controller: "productController",
		templateUrl: "/suppliercenter/app/views/products.html"
	});
	$routeProvider.when("/stocks", {
		controller: "stockController",
		templateUrl: "/suppliercenter/app/views/stocks.html"
	});  
	$routeProvider.when("/suppliers", {
		controller: "supplierController",
		templateUrl: "/suppliercenter/app/views/suppliers.html"
	});  
	$routeProvider.when("/addProduct", {
		controller: "productController",
		templateUrl: "/suppliercenter/app/views/addProduct.html"
	});
	$routeProvider.when("/products/edit", {
		controller: "editProductController",
		templateUrl: "/suppliercenter/app/views/editProduct.html"
	});  
	
//	$routeProvider.otherwise({ redirectTo: "/products" });
}]);
