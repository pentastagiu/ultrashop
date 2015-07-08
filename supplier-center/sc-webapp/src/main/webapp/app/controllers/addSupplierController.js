app.controller('addSupplierController', [
		'$scope',
		'$location',
		'stockFactory',
		'supplierFactory',
		'productFactory',
		function($scope, $location, stockFactory, supplierFactory,
				productFactory) {
			function addSupplier() {
				$scope.supplier.active = true;
				supplierFactory.finishTranzaction($scope.supplier).success(
						function() {
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