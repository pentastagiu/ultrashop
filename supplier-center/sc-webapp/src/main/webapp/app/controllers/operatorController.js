app.controller('operatorController', [ '$scope', '$location',
		'operatorFactory', function($scope, $location, operatorFactory) {

			$scope.operators = [];
			$scope.operators = {};
			getOperators();

			function getOperators() {
				operatorFactory.getOperators().success(function(operators) {
					$scope.operators = operators;
				});
			}
			;
			$scope.getOperators = function() {
				getOperators();
			};

			$scope.deleteOperator = function(operator) {
				operatorFactory.deleteOperator(operator);
				getOperators();
			};
		} ]);