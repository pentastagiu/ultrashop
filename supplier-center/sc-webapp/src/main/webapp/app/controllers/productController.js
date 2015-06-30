app.controller('productController', [ '$scope', '$location', '$routeParams',
		'productFactory',
		function($scope, $location, $routeParams, productFactory) {

			$scope.products = [];
			$scope.product = {
				'name' : '',
				'price' : ''
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

			function setProduct(product) {
				productFactory.setProduct(product);
				$location.path('/product/edit');

			}
			;
			$scope.deleteProduct = function(product) {
				productFactory.deleteProduct(product);
				getProducts();
			};
			$scope.setProduct = function(product) {
				setProduct(product);
			};
			
			$scope.cancel = function() {
				$location.path('/products');
			};
		} ]);