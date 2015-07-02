app.factory('operatorFactory', [ '$http', function($http) {

	var operatorFactory = {};
	var operatorUrlBase = '/suppliercenter/ws/resources/users/operators';
	operatorFactory.getOperators = function() {
		return $http.get(operatorUrlBase);
	};
	operatorFactory.finishTranzaction = function(operator) {
		return $http.put(operatorUrlBase, operator);
	};
	operatorFactory.deleteOperator = function(operator) {
		return $http({
			url : operatorUrlBase,
			dataType : "json",
			method : 'DELETE',
			data : operator,
			headers : {
				'Content-Type' : 'application/json'
			}
		});
	};

	return operatorFactory;
} ]);