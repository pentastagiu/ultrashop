app.controller('productController', [
		'$scope',
		'$location',
		'$routeParams',
		'productFactory',
		function($scope, $location, $routeParams, productFactory) {
			$scope.prodPerPage = 10;
			$scope.currentPage = 1;
			$scope.products = [];
			getProductCount();
			getProducts($scope.currentPage, $scope.prodPerPage);
			function getProducts(currentPage, prodPerPage) {
				productFactory.getProducts(currentPage-1, prodPerPage).success(
						function(products) {
							$scope.products = products;
						});
			}
			;
			function getProductCount() {
				productFactory.getProductCount().success(function(totalItems) {
					$scope.totalItems = totalItems;
				});
			}
			;
			$scope.pageChanged = function(currentPage) {
				getProducts($scope.currentPage, $scope.prodPerPage);
			};
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