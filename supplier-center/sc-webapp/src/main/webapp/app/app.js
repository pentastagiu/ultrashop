var app = angular.module('supplierCenterApp', [ 'ngRoute','LocalStorageModule' ]);
app.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when("/login", {
		controller : "loginController",
		templateUrl : "/suppliercenter/app/views/login.html"
	});
	$routeProvider.when("/products", {
		controller : "productController",
		templateUrl : "/suppliercenter/app/views/products.html"
	});
	$routeProvider.when("/stocks", {
		controller : "stockController",
		templateUrl : "/suppliercenter/app/views/stocks.html"
	});
	$routeProvider.when("/suppliers", {
		controller : "supplierController",
		templateUrl : "/suppliercenter/app/views/suppliers.html"
	});
	$routeProvider.when("/addProduct", {
		controller : "productController",
		templateUrl : "/suppliercenter/app/views/addProduct.html"
	});
	$routeProvider.when("/addStock", {
		controller : "stockController",
		templateUrl : "/suppliercenter/app/views/addStock.html"
	});
	$routeProvider.when("/addSupplier", {
		controller : "supplierController",
		templateUrl : "/suppliercenter/app/views/addSupplier.html"
	});
	$routeProvider.when("/products/edit", {
		controller : "editProductController",
		templateUrl : "/suppliercenter/app/views/editProduct.html"
	});
	$routeProvider.when("/suppliers/edit", {
		controller : "editSupplierController",
		templateUrl : "/suppliercenter/app/views/editSupplier.html"
	});
	$routeProvider.when("/stocks/edit", {
		controller : "editStocksController",
		templateUrl : "/suppliercenter/app/views/editStocks.html"
	});

	$routeProvider.otherwise({
		redirectTo : "/products"
	});
	app.run([ 'authService', function(authService) {
		authService.fillAuthData();
	} ]);
} ]);
