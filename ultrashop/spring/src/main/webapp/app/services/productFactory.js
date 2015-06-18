app.factory('productFactory', ['$http', function($http) {

	var productFactory = {};
	
	var productUrlBase = '/ultrashop/ws/products';
	
	var categoriesUrlBase = '/ultrashop/ws/categories/map';

	productFactory.getProducts = function() {
		return $http.get(productUrlBase);
	}
	
	productFactory.getCategories = function() {
		return $http.get(categoriesUrlBase);
	}
	
	productFactory.getProductsByCategoryId = function(id) {
		return $http.get(productUrlBase + '/categories/category/' + id);
	}
	
	return productFactory;

}]);