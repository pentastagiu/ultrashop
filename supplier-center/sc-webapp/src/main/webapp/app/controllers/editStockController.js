app.controller('editStockController', [
		'$scope',
		'$location',
		'productFactory',
		function($scope, $location, productFactory) {
			$scope.stock = productFactory.getStock();

			function updateProduct() {
				productFactory.updateStock($scope.stock).success(
						function() {
							$location.path('/stocks');
						}).error(function() {
					alert('error');
				});
			}
			;
			$scope.updateStock = function() {
				updateProduct();
			};
			$scope.cancel = function() {
				$location.path('/stocks');
			};

		} ]);