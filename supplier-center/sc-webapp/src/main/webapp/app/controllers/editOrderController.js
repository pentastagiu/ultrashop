app.controller('editOrderController', [
		'$scope',
		'$filter',
		'$location',
		'productFactory',
		'orderFactory',
		function($scope, $filter, $location, productFactory, orderFactory) {
			// $scope.supplier = supplierFactory.getSupplier();

			$scope.orders = {
				status : [ 'PLACED', 'WAITING_ARRIVAL', 'ARRIVED', 'DELIVERED',
						'CANCELED' ]
			};
			$scope.order = orderFactory.getOrder();

			getProducts();

			function getProducts() {
				productFactory.getProducts().success(
						function(products) {
							$scope.products = products;

							// Find supplier by id.This function is used to set
							// the
							// default supplier in the edit product selector
							var orderId = orderFactory.getOrder().product.id;
							$scope.order.product = $filter('filter')(
									$scope.products, {
										id : orderId
									})[0];

						});
			}

			function updateOrder() {
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