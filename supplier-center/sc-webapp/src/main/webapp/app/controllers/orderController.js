app.controller('orderController', [
		'$scope',
		'$location',
		'$routeParams',
		'orderFactory',
		function($scope, $location, $routeParams, orderFactory) {
			getOrderCount();
			$scope.ordPerPage = 10;
			$scope.currentPage = 1;
			getOrders($scope.currentPage, $scope.ordPerPage);
			function getOrders(currentPage, ordersPerPage) {
				orderFactory.getOrders(currentPage - 1, ordersPerPage).success(
						function(orders) {
							$scope.orders = orders;
						});
			}
			;
			$scope.getOrders = function() {
				getOrders($scope.currentPage, $scope.ordPerPage);
			};
			function getOrderCount() {
				orderFactory.getOrderCount().success(function(totalItems) {
					$scope.totalItems = totalItems;
				});
			}
			function setOrder(order) {
				orderFactory.setOrder(order);
				$location.path('/order/edit');

			}
			;
			$scope.deleteOrder = function(order) {
				orderFactory.deleteOrder(order);
				getOrders($scope.currentPage, $scope.ordPerPage);
			};
			$scope.setOrder = function(order) {
				setOrder(order);
			};

			$scope.cancel = function() {
				$location.path('/orders');
			};
			$scope.pageChanged = function(currentPage) {
				getOrders($scope.currentPage, $scope.ordPerPage);
			};
		} ]);