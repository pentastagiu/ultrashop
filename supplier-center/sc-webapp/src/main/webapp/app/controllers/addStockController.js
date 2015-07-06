app.controller('addStockController', [
		'$scope',
		'$location',
		'stockFactory',
		'supplierFactory',
		'productFactory',
		function($scope, $location, stockFactory, supplierFactory,
				productFactory) {
			$scope.supplier = {};
			getAllSuppliers();
			function getAllSuppliers() {
				supplierFactory.getAllSuppliers().success(function(suppliers) {
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
