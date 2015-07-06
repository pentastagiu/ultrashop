app.controller('editProductController', [
		'$scope',
		'$location',
		'$filter',
		'productFactory',
		'supplierFactory',
		function($scope, $location, $filter, productFactory, supplierFactory) {
			$scope.product = productFactory.getProduct();
			getAllSuppliers();

			function getAllSuppliers() {
				supplierFactory.getAllSuppliers().success(function(suppliers) {
					$scope.suppliers = suppliers;
					// Find supplier by id.This function is used to set the
					// default supplier in the edit product selector
					var supplierId = productFactory.getProduct().supplier.id;
					$scope.supplier = $filter('filter')($scope.suppliers, {
						id : supplierId
					})[0];
				});
			}
			;
			function updateProduct() {
				$scope.product.supplier = $scope.supplier;
				productFactory.updateProduct($scope.product).success(
						function() {
							$location.path('/products');
						}).error(function() {
				});
			}
			;
			$scope.updateProduct = function() {
				updateProduct();
			};
			$scope.cancel = function() {
				$location.path('/products');
			};

		} ]);