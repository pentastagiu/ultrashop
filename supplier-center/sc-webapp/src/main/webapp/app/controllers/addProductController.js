app.controller('addProductController', [
		'$scope',
		'$location',
		'productFactory',
		'supplierFactory',
		function($scope, $location, productFactory, supplierFactory) {

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

			function addProduct() {
				$scope.product.supplier = $scope.supplier;
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