var app = angular.module('supplierCenterApp',
    [ 'ngRoute', 'LocalStorageModule','ui.bootstrap']);
app.config([ '$routeProvider', function($routeProvider) {
  
  $routeProvider.when("/home", {
    controller : "homeController",
    templateUrl : "/suppliercenter/app/views/home.html"
  });
  $routeProvider.when("/login", {
    controller : "loginController",
    templateUrl : "/suppliercenter/app/views/login.html"
  });
  $routeProvider.when("/signup", {
    controller : "signupController",
    templateUrl : "/suppliercenter/app/views/signup.html"
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
  $routeProvider.when("/orders", {
    controller : "orderController",
    templateUrl : "/suppliercenter/app/views/orders.html"
  });
  $routeProvider.when("/product/add", {
    controller : "addProductController",
    templateUrl : "/suppliercenter/app/views/addProduct.html"
  });
  $routeProvider.when("/stock/add", {
    controller : "addStockController",
    templateUrl : "/suppliercenter/app/views/addStock.html"
  });
  $routeProvider.when("/supplier/add", {
    controller : "addSupplierController",
    templateUrl : "/suppliercenter/app/views/addSupplier.html"
  });
  $routeProvider.when("/order/add", {
    controller : "addOrderController",
    templateUrl : "/suppliercenter/app/views/addOrder.html"
  });
  $routeProvider.when("/product/edit", {
    controller : "editProductController",
    templateUrl : "/suppliercenter/app/views/editProduct.html"
  });
  $routeProvider.when("/supplier/edit", {
    controller : "editSupplierController",
    templateUrl : "/suppliercenter/app/views/editSupplier.html"
  });
  $routeProvider.when("/stock/edit", {
    controller : "editStocksController",
    templateUrl : "/suppliercenter/app/views/editStocks.html"
  });
  $routeProvider.when("/order/edit", {
    controller : "editOrderController",
    templateUrl : "/suppliercenter/app/views/editOrder.html"
  });

  $routeProvider.otherwise({
    redirectTo : "/home"
  });
} ]);
app.run([ 'authService', function(authService) {
  authService.fillAuthData();
} ]);
app.config(function($httpProvider) {

  $httpProvider.interceptors.push('authInterceptorService');
});
