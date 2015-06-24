app.factory('productFactory', [ '$http', function($http) {

	var productFactory = {};

	var productUrlBase = '/suppliercenter/ws/products';
	productFactory.getProducts = function() {
		return $http.get(productUrlBase);
	};
	productFactory.getProductById = function() {
		return $http.get(productUrlBase, {
			params : {
				user_id : user.id
			}
		});
	};
	productFactory.finishTranzaction = function(product) {
		return $http.put(productUrlBase, product);
	};
	productFactory.updateProduct = function(product) {
		return $http.post(productUrlBase, product);
	};
	
	
	productFactory.setProduct = function(product) {
		productFactory.product = product;
	};

	productFactory.getProduct = function() {
		return productFactory.product;
	};
	
	productFactory.setStock = function(stock) {
		productFactory.stock = stock;
	};

	productFactory.getStock = function() {
		return productFactory.stock;
	};
	
	productFactory.setSupplier = function(supplier) {
		productFactory.supplier = supplier;
	};

	productFactory.getSupplier = function() {
		return productFactory.supplier;
	};

	return productFactory;
} ]);