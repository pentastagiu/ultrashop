app.factory('stockFactory', ['$http', function($http) {

	var stockFactory = {};
	
	var productUrlBase = '/suppliercenter/ws/stocks';
	stockFactory.getStocks = function() {
		return $http.get(productUrlBase);
	}	
	return stockFactory;
}]);