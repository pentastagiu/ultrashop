app.controller('orderController', [ '$scope', '$location', '$routeParams',
		'orderFactory',
		function($scope, $location, $routeParams, orderFactory) {

			getOrders();
			function getOrders() {
				orderFactory.getOrders().success(function(orders) {
					$scope.orders = orders;
				});
			}
			;
			$scope.getOrders = function() {
				getOrders();
			};

			function setOrder(order) {
				orderFactory.setOrder(order);
				$location.path('/order/edit');

			}
			;
			$scope.deleteOrder = function(order) {
				orderFactory.deleteOrder(order);
				getOrders();
			};
			$scope.setOrder = function(order) {
				setOrder(order);
			};

			$scope.cancel = function() {
				$location.path('/orders');
			};
		} ]);