app.factory('productDescriptionFactory', ['$http', function($http) {

	var productDescriptionFactory = {};
	
	var productBaseUrl = "/ultrashop/ws/products";
	
	productDescriptionFactory.showProductsDetails = function(product) {
		productDescriptionFactory.product = product;
	}
	
	productDescriptionFactory.getProduct = function() {
		return productDescriptionFactory.product;
	}
	
	productDescriptionFactory.getProductImagery = function(product) {
		return $http.post(productBaseUrl + '/images/product', product);
	}
	
	productDescriptionFactory.getProductPresentation = function(product) {
		return $http.post(productBaseUrl + '/presentations/product', product);
	}
	
	productDescriptionFactory.getProductFeatures = function(product) {
		return $http.post(productBaseUrl + '/features/product', product);
	}
	
	productDescriptionFactory.getProductDescription = function(product) {
		return $http.post(productBaseUrl + '/descriptions/product', product);
	}
	
	return productDescriptionFactory;

}]);