app.factory('shoppingCartFactory', ['$http', function($http) {

	var shoppingCartFactory = {};
	
	var userUrlBase = '/ultrashop/ws/users';
	var orderUrlBase = '/ultrashop/ws/orders';
	
	shoppingCartFactory.getUserInformation = function(user) {
		return $http.post(userUrlBase + '/email', user);
	}
	
	shoppingCartFactory.getCustomerInformation = function(user) {
		return $http.post(userUrlBase + '/customers/user', user);
	}
	
	shoppingCartFactory.finishTranzaction = function(order) {
		return $http.put(orderUrlBase, order);
	}
	
	return shoppingCartFactory;

}]);