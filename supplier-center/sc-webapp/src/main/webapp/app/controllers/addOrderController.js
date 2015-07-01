app.controller('addOrderController', [ '$scope', '$location', 'orderFactory',
		'productFactory',
		function($scope, $location, orderFactory, productFactory) {
			getProducts();
			function getProducts() {
				productFactory.getProducts().success(function(products) {
					$scope.products = products;
				});
			}
			;
			function addOrder() {
				$scope.order.status = "PLACED";
				orderFactory.addOrder($scope.order).success(function() {
					$location.path('/orders');
				}).error(function() {
				});
			}
			;
			$scope.addOrder = function() {
				addOrder();
			};
			$scope.cancel = function() {
				$location.path('/suppliers');
			};
		} ]);