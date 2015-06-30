app.factory('productFactory', [ '$http', function($http) {

	var productFactory = {};
	debugger;
	var productUrlBase = '/suppliercenter/ws/resources/products';
	productFactory.getProducts = function() {
		return $http.get(productUrlBase);
	};
	productFactory.getProductBySupplier = function(id) {
		return $http.get(productUrlBase + '/supplierId=' + id);
	};
	productFactory.finishTranzaction = function(product) {
		debugger;
		return $http.put(productUrlBase, product);
	};
	productFactory.updateProduct = function(product) {
		return $http.post(productUrlBase, product);
	};

	productFactory.deleteProduct = function(product) {
		return $http({
			url : productUrlBase,
			dataType : "json",
			method : 'DELETE',
			data : product,
			headers : {
				'Content-Type' : 'application/json'
			}
		});
	};
	productFactory.setProduct = function(product) {
		productFactory.product = product;
	};

	productFactory.getProduct = function() {
		return productFactory.product;
	};

	return productFactory;
} ]);