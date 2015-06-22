app.factory('productFactory', ['$http', function($http) {

	var productFactory = {};
	
	var productUrlBase = '/suppliercenter/ws/products';
	productFactory.getProducts = function() {
		return $http.get(productUrlBase);
	}	
	return productFactory;
}]);