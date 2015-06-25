app.controller('supplierController', [ '$scope','$location', 'supplierFactory',
		function($scope,$location, supplierFactory) {

			$scope.suppliers = [];
			$scope.supplier = {
					'name':'',
					'contactDetails':'',
					'email':'',
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
			
			function setSupplier(supplier) {
				supplierFactory.setSupplier(supplier);
				$location.path('/suppliers/edit');

			}
			;
			$scope.setSupplier = function(supplier) {
				setSupplier(supplier);
			};
			
		} ]);