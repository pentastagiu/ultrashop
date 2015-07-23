app.controller('addOrderController', [ '$scope', '$location', 'orderFactory',
		'productFactory',
		function($scope, $location, orderFactory, productFactory) {
			$scope.limit = 30;
			$scope.accept = {};
			hasNextChunk = true, queryString = '';
			var i = 0;
			$scope.requestMoreItems = function() {
				$scope.limit += 10;
			};

			getAllProducts();
			function getAllProducts() {
				productFactory.getAllProducts().success(function(products) {
					$scope.products = products;
				});
			}
			;
			function addOrder() {
				$scope.order.status = "PLACED";
				delete $scope.order.product._uiSelectChoiceDisabled;
				debugger;
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
				$location.path('/orders');
			};
		} ]);