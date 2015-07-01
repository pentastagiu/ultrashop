app.controller('addStockController', [
		'$scope',
		'$location',
		'stockFactory',
		'supplierFactory',
		'productFactory',
		function($scope, $location, stockFactory, supplierFactory,
				productFactory) {
			$scope.supplier = {};
			getSuppliers();
			function getSuppliers() {
				supplierFactory.getSuppliers().success(function(suppliers) {
					$scope.suppliers = suppliers;
				});

			}
			;
			function getProductsBySupplier() {
				productFactory.getProductBySupplier($scope.stock.supplier.id)
						.success(function(products) {
							$scope.products = products;
						}).error(function() {
							alert('error');
						});
			}
			;
			$scope.getProductsBySupplier = function() {
				getProductsBySupplier();
			};
			$scope.getSuppliers = function() {
				getSuppliers();
			};

			function addStock() {
				stockFactory.finishTranzaction($scope.stock).success(
						function() {
							$location.path('/stocks');
						}).error(function() {
					alert('error');
				});
			}
			;
			$scope.addStock = function() {
				addStock();
			};
			$scope.cancel = function() {
				$location.path('/stocks');
			};

		} ]);