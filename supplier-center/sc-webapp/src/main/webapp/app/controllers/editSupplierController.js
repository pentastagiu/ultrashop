app.controller('editSupplierController', [
		'$scope',
		'$location',
		'supplierFactory',
		function($scope, $location, supplierFactory) {
			$scope.supplier = supplierFactory.getSupplier();
			function updateSupplier() {
				supplierFactory.updateSupplier($scope.supplier).success(
						function() {
							$location.path('/suppliers');
						}).error(function() {
				});
			}
			;
			$scope.updateSupplier = function() {
				updateSupplier();
			};
			$scope.cancel = function() {
				$location.path('/suppliers');
			};

		} ]);