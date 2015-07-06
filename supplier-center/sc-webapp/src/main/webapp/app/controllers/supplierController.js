app.controller('supplierController', [
		'$scope',
		'$location',
		'supplierFactory',
		function($scope, $location, supplierFactory) {
			getSupplierCount();
			$scope.supPerPage = 10;
			$scope.currentPage = 1;
			$scope.supplier = {
				"name" : "",
				"contactDetails" : "",
				"email" : "",
				"active" : true
			};
			getSuppliers($scope.currentPage, $scope.supPerPage);
			function getSuppliers(currentPage, suppliersPerPage) {
				supplierFactory.getSuppliers(currentPage - 1, suppliersPerPage)
						.success(function(suppliers) {
							$scope.suppliers = suppliers;
						});
			}
			;
			function getSupplierCount() {
				supplierFactory.getSupplierCount().success(function(totalItems) {
					$scope.totalItems = totalItems;
				});
			}

			function deleteSupplier(supplier) {
				//supplier.active = false;
				supplierFactory.deleteSupplier(supplier).success(function() {
					getSuppliers($scope.currentPage, $scope.supPerPage);
				});
			}
			;
			$scope.deleteSupplier = function(supplier) {
				deleteSupplier(supplier);
				getSuppliers($scope.currentPage, $scope.supPerPage);
			};

			function setSupplier(supplier) {
				supplierFactory.setSupplier(supplier);
				$location.path('/supplier/edit');

			}
			;
			$scope.setSupplier = function(supplier) {
				setSupplier(supplier);
			};
			$scope.pageChanged = function(currentPage) {
				getSuppliers($scope.currentPage, $scope.supPerPage);
			};

		} ]);