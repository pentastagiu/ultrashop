app.controller('stockController', [ '$scope', '$location','stockFactory',
		function($scope,$location, stockFactory) {

			$scope.stocks = [];
			$scope.stocks = {};
			getStocks();

			function getStocks() {
				stockFactory.getStocks().success(function(stocks) {
					$scope.stocks = stocks;
				});
			}
			;
			$scope.getStocks = function() {
				getStocks();
			};

			$scope.setStock = function(stock) {
				stockFactory.setStock(stock);
				$location.path('/stock/edit');
			};

			$scope.deleteStock = function(stock) {
				stockFactory.deleteStock(stock);
				getStocks();
			};
		} ]);