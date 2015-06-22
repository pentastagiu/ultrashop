app.controller('supplierController', [ '$scope', 'supplierFactory',
		function($scope, supplierFactory) {

			$scope.suppliers = [];
			getSuppliers();
			function getSuppliers() {
				supplierFactory.getSuppliers().success(function(suppliers) {
					$scope.suppliers = suppliers;
				});
			}
			;
			$scope.getSuppliers = function() {
				getSuppliers();
			}
		} ]);