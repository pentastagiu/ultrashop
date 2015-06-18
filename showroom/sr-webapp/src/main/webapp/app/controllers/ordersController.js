app.controller('ordersController', [ '$scope', 'ordersService','$timeout','$location',
		function($scope, ordersService, $timeout, $location) {
			$scope.savedSuccessfully = false;
			$scope.message = "";
			$scope.orders = [];
			$scope.orderInfo = ordersService.orderInfo;
			$scope.order = ordersService.order;
			$scope.deliveries;
			getDeliveryStock();
			$scope.host = ordersService.host;
			nbOfOrders();
			$scope.currentPage = 1;
			$scope.numPerPage = 10;
			topOrders(($scope.currentPage-1)*$scope.numPerPage,$scope.currentPage*$scope.numPerPage);
			  
			function topOrders(offset,limit) {
				ordersService.getTopOrders(offset,limit).then(function(results) {
					 $scope.orders = results.data;
				}, function(error) {
					//alert(error.data.message);
				});
			}
			
			function nbOfOrders() {
				ordersService.getNbOfOrders().then(function(results) {
					$scope.totalItems = results.data;

				}, function(error) {
					//alert(error.data.message);
				});
			}
			
			$scope.showStatus = function (order) {
				ordersService.selectedOrderInfo(order);
				$location.path('/progressBar');
			};
			
			$scope.showDelivery = function (order) {
				ordersService.selectedOrder(order);
				$location.path('/serial');
			};
			
			function getDeliveryStock() {
				if($scope.order.data != null) {
					ordersService.getDeliveryStock($scope.order.data).then(function(response) {
						$scope.deliveries = response.data;
					});
				}
			};
			
			$scope.sendMail = function (order) {
				ordersService.sendMail(order).then(function(response) {
					$scope.savedSuccessfully = true;
					$scope.message = "Mail has been send!";
					$timeout(function() {
						$scope.savedSuccessfully = false;
						 $scope.message = "";
					    }, 3000);
				});
			};
			
			$scope.update = function (deliveries) {
				ordersService.update($scope.order.data,$scope.deliveries);
				$location.path('/orders');
			};
			
			$scope.cancel = function () {
				$location.path('/orders');
			};
			
			$scope.cancelOrder = function (order) {
				ordersService.cancelOrder(order);
			};
			
			$scope.deliverOrder = function (order) {
				ordersService.deliverOrder(order);
			};
			
			  $scope.pageChanged = function(page) {
				  $scope.currentPage = page;
				  topOrders(($scope.currentPage-1)*$scope.numPerPage+1,$scope.currentPage*$scope.numPerPage);
				  return true;
				};

		} ]);
