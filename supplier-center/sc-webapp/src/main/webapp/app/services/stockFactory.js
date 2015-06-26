app.factory('stockFactory', ['$http', function($http) {

	var stockFactory = {};
	
	var productUrlBase = '/suppliercenter/ws/resources/stocks';
	stockFactory.getStocks = function() {
		return $http.get(productUrlBase);
	};	
	
	
	stockFactory.finishTranzaction = function(product) {
		return $http.put(productUrlBase, product);
	};
	stockFactory.updateStock = function(product) {
		return $http.post(productUrlBase, product);
	};
	
	
	stockFactory.setStock = function(stock) {
		stockFactory.stock = stock;
	};

	stockFactory.getStock = function() {
		return stockFactory.stock;
	};
	
	return stockFactory;
}]);