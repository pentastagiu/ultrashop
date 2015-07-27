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
				});
			}
			;
			function updateProduct() {
				delete $scope.product._uiSelectChoiceDisabled;
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