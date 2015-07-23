app.controller('editProductController', [
		'$scope',
		'$location',
		'$filter',
		'productFactory',
		'supplierFactory',
		function($scope, $location, $filter, productFactory, supplierFactory) {
			$scope.limit = 30;
			$scope.accept = {};
			hasNextChunk = true, queryString = '';
			var i = 0;
			$scope.requestMoreItems = function() {
				$scope.limit += 10;
			};
			$scope.product = productFactory.getProduct();
			getAllSuppliers();

			function getAllSuppliers() {
				supplierFactory.getAllSuppliers().success(function(suppliers) {
					$scope.suppliers = suppliers;
					// Find supplier by id.This function is used to set the
					// default supplier in the edit product selector
					// var supplierId = productFactory.getProduct().supplier.id;
					// $scope.supplier = $filter('filter')($scope.suppliers, {
					// id : supplierId
					// })[0];
				});
			}
			;
			function updateProduct() {
				delete $scope.order.product._uiSelectChoiceDisabled;
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