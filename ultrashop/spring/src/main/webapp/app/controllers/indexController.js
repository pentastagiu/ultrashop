app.controller('indexController', [ '$scope','$cookieStore', function($scope, $cookieStore) {
	
	$scope.getNrOfProducts = function() {
		var shoppingCart = $cookieStore.get('shoppingCart');
		var nrOfProducts = 0;
		if(shoppingCart != null) {
			for(var i = 0; i < shoppingCart.length; i++) {
				nrOfProducts = nrOfProducts + shoppingCart[i].quantity;
			}
		}
		return nrOfProducts;
	}
}]);