app.controller('stockController', [ '$scope', 'stockFactory',
		function($scope, stockFactory) {

			$scope.stocks = [];
			getStocks();

			function getStocks() {
				stockFactory.getStocks().success(function(stocks) {
					$scope.stocks = stocks;
				});
			}
			;
			$scope.getStocks = function() {
				getStocks();
			}
		} ]);