app.factory('stockFactory', [ '$http', function($http) {
	var stockFactory = {};

	var productUrlBase = '/suppliercenter/ws/resources/stocks';
	stockFactory.getStocks = function() {
		return $http.get(productUrlBase);
	};

	stockFactory.finishTranzaction = function(stock) {
		return $http.put(productUrlBase, stock);
	};
	stockFactory.updateStock = function(stock) {
		return $http.post(productUrlBase, stock);
	};
	stockFactory.deleteStock = function(stock) {
		return $http({
			url : productUrlBase,
			dataType : "json",
			method : 'DELETE',
			data : stock,
			headers : {
				'Content-Type' : 'application/json'
			}
		});
	};

	stockFactory.setStock = function(stock) {
		stockFactory.stock = stock;
	};

	stockFactory.getStock = function() {
		return stockFactory.stock;
	};

	return stockFactory;
} ]);
