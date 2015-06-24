app.controller('editProductController', [
		'$scope',
		'$location',
		'productFactory',
		function($scope, $location, productFactory) {
			$scope.product = productFactory.getProduct();

			function updateProduct() {
				productFactory.updateProduct($scope.product).success(
						function() {
							alert('success');
							$location.path('/products');
						}).error(function() {
					alert('error');
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