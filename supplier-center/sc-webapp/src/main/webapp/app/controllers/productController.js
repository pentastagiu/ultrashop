app.controller('productController', [ '$scope', 'productFactory',
		function($scope, productFactory) {

			$scope.products = [];
			getProducts();

			function getProducts() {
				productFactory.getProducts().success(function(products) {
					$scope.products = products;
				});
			}
			;
			$scope.getProducts = function() {
				getProducts();
			}
		} ]);