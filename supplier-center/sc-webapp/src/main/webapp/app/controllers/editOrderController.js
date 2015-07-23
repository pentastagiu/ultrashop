app.controller('editOrderController', [
		'$scope',
		'$filter',
		'$location',
		'productFactory',
		'orderFactory',
		function($scope, $filter, $location, productFactory, orderFactory) {
			// $scope.supplier = supplierFactory.getSupplier();
			$scope.limit = 30;
			hasNextChunk = true, queryString = '';
			var i = 0;
			$scope.requestMoreItems = function() {
				$scope.limit += 10;
			};

			$scope.orders = {
				status : [ 'PLACED', 'WAITING_ARRIVAL', 'ARRIVED', 'DELIVERED',
						'CANCELED' ]
			};
			$scope.order = orderFactory.getOrder();

			getAllProducts();

			function getAllProducts() {
				productFactory.getAllProducts().success(function(products) {
					$scope.products = products;

//					// Find supplier by id.This function is used to set
//					// the
//					// default supplier in the edit product selector
//					var orderId = orderFactory.getOrder().product.id;
//					$scope.order.product = $filter('filter')($scope.products, {
//						id : orderId
//					})[0];

				});
			}

			function updateOrder() {
				debugger;
				delete $scope.order.product._uiSelectChoiceDisabled;
				if ($scope.order.status === 'DELIVERED'
						&& $scope.order.deliveredDate === null) {
					debugger;
					$scope.order.deliveredDate = $filter('date')(new Date(),
							'yyyy-MM-dd');
				} else if ($scope.order.status != 'DELIVERED') {
					debugger;
					$scope.order.deliveredDate = null;
				}
				orderFactory.updateOrder($scope.order).success(function() {
					$location.path('/orders');
				}).error(function() {
				});
			}
			;
			$scope.updateOrder = function() {
				updateOrder();
			};
			$scope.cancel = function() {
				$location.path('/orders');
			};

		} ]);