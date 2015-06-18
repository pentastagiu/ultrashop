app.controller('productController', [ '$scope', 'productFactory','productDescriptionFactory','$cookieStore', '$location', function($scope, productFactory,productDescriptionFactory, $cookieStore, $location) {
	
	$scope.products = [];
	$scope.categories = [];
	$scope.shoppingCart = [];
	$scope.searchVisibility = false;
	$scope.host = $location.host();
	getProducts();
	getCategories();
	
	function getProducts() {
		productFactory.getProducts().success(function(products) {
			$scope.products = products;
		});
	};
	
	function getCategories() {
		productFactory.getCategories().success(function(categories) {
			$scope.categories = categories;
		});
	};
	
	$scope.getProducts = function() {
		getProducts();
	}
	
	$scope.getProductsByCategoryId = function(id) {
		productFactory.getProductsByCategoryId(id).success(function(products) {
			$scope.products = products;
		});
	};
	
	$scope.showProductsDetails = function(product) {
		productDescriptionFactory.showProductsDetails(product);
		$location.path('/productDescription');
	};
	
	$scope.addToShoppingCart = function(product) {
		var updateQuantity = false;
		var shoppingCart = $cookieStore.get('shoppingCart');
		if(shoppingCart != null) {
			$scope.shoppingCart = shoppingCart;
			for(var i = 0; i < $scope.shoppingCart.length; i++) {
				if($scope.shoppingCart[i].product.id == product.id) {
					$scope.shoppingCart[i].quantity = $scope.shoppingCart[i].quantity + 1;
					updateQuantity = true;
				}
			}
		}
		if(updateQuantity == false) {
			var productQuantity = {product : product, quantity : 1};
			$scope.shoppingCart.push(productQuantity);	
		}
		$cookieStore.put('shoppingCart',$scope.shoppingCart);
	}
}]);