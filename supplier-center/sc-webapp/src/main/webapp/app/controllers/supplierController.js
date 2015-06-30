app.controller('supplierController', [
		'$scope',
		'$location',
		'supplierFactory',
		function($scope, $location, supplierFactory) {
			$scope.supplier = {
				"name" : "",
				"contactDetails" : "",
				"email" : "",
				"active" : true
			};
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

			function deleteSupplier(supplier) {
				supplier.active = false;
				supplierFactory.deleteSupplier(supplier).success(function() {
					getSuppliers();
				});	
			}
			;
			$scope.deleteSupplier = function(supplier) {
				deleteSupplier(supplier);
			};

			function setSupplier(supplier) {
				supplierFactory.setSupplier(supplier);
				$location.path('/supplier/edit');

			}
			;
			$scope.setSupplier = function(supplier) {
				setSupplier(supplier);
			};

		} ]);