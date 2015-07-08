app.factory('stockFactory', [
		'$http',
		function($http) {
			var stockFactory = {};

			var stockUrlBase = '/suppliercenter/ws/resources/stocks';
			stockFactory.getAllStocks = function() {
				return $http.get(stockUrlBase);
			};
			stockFactory.getStocks = function(currentPage, stockPerPage) {
				return $http.get(stockUrlBase + '/pageIndex=' + currentPage
						+ '/offset=' + stockPerPage);
			};
			stockFactory.getStockCount = function() {
				return $http.get(stockUrlBase + '/count');
			};
			stockFactory.finishTranzaction = function(stock) {
				return $http.put(stockUrlBase, stock);
			};
			stockFactory.updateStock = function(stock) {
				return $http.post(stockUrlBase, stock);
			};
			stockFactory.deleteStock = function(stock) {
				return $http({
					url : stockUrlBase,
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
