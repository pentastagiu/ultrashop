app.controller('addProductController', [
		'$scope',
		'$location',
		'productFactory',
		'supplierFactory',
		function($scope, $location, productFactory, supplierFactory) {
			$scope.limit = 30;
			$scope.accept = {};
			hasNextChunk = true, queryString = '';
			var i = 0;
			$scope.requestMoreItems = function() {
				$scope.limit += 10;
			};
			getAllSuppliers();
			function getAllSuppliers() {
				supplierFactory.getAllSuppliers().success(function(suppliers) {
					$scope.suppliers = suppliers;
				});
			}
			;
			function addProduct() {
				delete $scope.product.supplier._uiSelectChoiceDisabled;
				productFactory.finishTranzaction($scope.product).success(
						function() {
							$location.path('/products');
						}).error(function() {
				});
			}
			;
			$scope.addProduct = function() {
				addProduct();
			};
			$scope.cancel = function() {
				$location.path('/products');
			};
		} ]);