app.controller('stockController', [
		'$scope',
		'$location',
		'stockFactory',
		function($scope, $location, stockFactory) {
			getStockCount();
			$scope.stockPerPage = 10;
			$scope.currentPage = 1;
			$scope.stocks = [];
			$scope.stocks = {};
			getStocks($scope.currentPage, $scope.stockPerPage);

			function getStocks(currentPage, stocksPerPage) {
				stockFactory.getStocks(currentPage - 1, stocksPerPage).success(
						function(stocks) {
							$scope.stocks = stocks;
						});
			}
			;
			$scope.getStocks = function() {
				getStocks($scope.currentPage, $scope.stockPerPage);
			};
			function getStockCount() {
				stockFactory.getStockCount().success(function(totalItems) {
					$scope.totalItems = totalItems;
				});
			}
			$scope.setStock = function(stock) {
				stockFactory.setStock(stock);
				$location.path('/stock/edit');
			};

			$scope.deleteStock = function(stock) {
				stockFactory.deleteStock(stock);
				getStocks($scope.currentPage, $scope.stockPerPage);
			};
			$scope.pageChanged = function(currentPage) {
				getStocks($scope.currentPage, $scope.stockPerPage);
			};
		} ]);