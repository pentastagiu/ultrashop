app.controller('addSupplierController', [
		'$scope',
		'$location',
		'stockFactory',
		'supplierFactory',
		'productFactory',
		function($scope, $location, stockFactory, supplierFactory,
				productFactory) {
			getSuppliers();
			function getSuppliers() {
				supplierFactory.getSuppliers().success(function(suppliers) {
					$scope.suppliers = suppliers;
				});
			}
			;
			$scope.getSuppliers = function() {
				getSuppliers();
			};
			function addSupplier() {
				$scope.supplier.active = true;
				supplierFactory.finishTranzaction($scope.supplier).success(
						function() {
							getSuppliers();
							$location.path('/suppliers');
						}).error(function() {
				});
			}
			;
			$scope.addSupplier = function() {
				addSupplier();
			};
			$scope.cancel = function() {
				$location.path('/suppliers');
			};
		} ]);