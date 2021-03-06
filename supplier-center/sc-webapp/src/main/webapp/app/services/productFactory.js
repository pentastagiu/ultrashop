app.factory('productFactory', [
		'$http',
		function($http) {

			var productFactory = {};
			var productUrlBase = '/suppliercenter/ws/resources/products';
			productFactory.getAllProducts = function() {
				return $http.get(productUrlBase);
			};
			productFactory.getProducts = function(currentPage, prodPerPage) {
				return $http.get(productUrlBase + '/pageIndex=' + currentPage
						+ '/offset=' + prodPerPage);
			};
			productFactory.getProductCount = function() {
				return $http.get(productUrlBase + '/count');
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