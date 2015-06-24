app.controller('productController', [
		'$scope',
		'$location',
		'$routeParams',
		'productFactory',
		// 'editFactory',
		function($scope, $location, $routeParams, productFactory) {

			$scope.products = [];
			$scope.product = {
				'name' : '',
				'price' : 0
			};
			getProducts();
			function getProducts() {
				productFactory.getProducts().success(function(products) {
					$scope.products = products;
				});
			}
			;
			$scope.getProducts = function() {
				getProducts();
			};
			function addProduct() {
				productFactory.finishTranzaction($scope.product).success(
						function() {
							getProducts();
							$location.path('/products');
						}).error(function() {
					alert('error');
				});
			}
			;
			$scope.addProduct = function() {
				addProduct();
			};
			function setProduct(product) {
				productFactory.setProduct(product);
				$location.path('/products/edit');

			}
			;
			$scope.setProduct = function(product) {
				setProduct(product);
			};
		} ]);