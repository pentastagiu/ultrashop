app.controller('productDescriptionController', [ '$scope', 'productDescriptionFactory','$cookieStore', '$location', function($scope, productDescriptionFactory, $cookieStore, $location) {

	$scope.host = $location.host();
	$scope.product = productDescriptionFactory.getProduct();
	$scope.imagesSrc = [];
	$scope.productPresentation = [];
	$scope.productDescription;
	$scope.shoppingCart;
	$scope.productFeatures = [];
	getProductImagery();
	getProductPresentation();
	getProductFeatures();
	getProductDescription();
	
	function getProductImagery() {
		productDescriptionFactory.getProductImagery($scope.product).success(function(imagery) {
			for(var i = 2 ; i <= imagery.numberOfImages ; i++) {
				$scope.imagesSrc.push("http://res.cloudinary.com/ultrashop/sr-content/images/"+$scope.product.id+"/"+i+".jpg");
			}
		});
	}
	
	function getProductFeatures() {
		productDescriptionFactory.getProductFeatures($scope.product).success(function(productFeatures) {
			angular.forEach(productFeatures, function(value, key) {
				for(var i = 0; i < value.length; i++) {
					var valueArr = value[i].value.split("\n");
					value[i].value = valueArr;
				}
	            $scope.productFeatures.push({ key: key, value: value});
	        });
		});
	}
	
	function getProductDescription() {
		productDescriptionFactory.getProductDescription($scope.product).success(function(productDescription) {
			if(productDescription != "") {
				$scope.productDescription = productDescription;
			}
		});
	}
	
	function getProductPresentation() {
		productDescriptionFactory.getProductPresentation($scope.product).success(function(presentation) {
			for(var i = 0; i < presentation.length; i++) {
				var sectionDetail = {
						"title" : "",
						"left" : "",
						"right" : ""
				}
				sectionDetail.title = presentation[i].title;
				if(i%2 == 0) {
					sectionDetail.left = '<img src="http://res.cloudinary.com/ultrashop/sr-content/images/' + $scope.product.id + '/' + presentation[i].imageSrc + '">';
					sectionDetail.right = '<h6>'+ presentation[i].description +'</h6>';
				}
				else {
					sectionDetail.left = '<h6>'+ presentation[i].description +'</h6>';
					sectionDetail.right = '<img src="http://res.cloudinary.com/ultrashop/sr-content/images/' + $scope.product.id + '/' + presentation[i].imageSrc + '">';
				}
				$scope.productPresentation.push(sectionDetail);
			}
		});
	}
	
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