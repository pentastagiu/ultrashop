app.factory('productsService', [
		'$http',
		'localStorageService',
		'$location',
		function($http, localStorageService, $location) {

			$location.path("/showroom");
			var serviceBase = $location.url();
			$location.path("/products");

			var productsServiceFactory = {};

			var _getProducts = function() {

				return $http.get(serviceBase + '/ws/resources/products').then(
						function(results) {
							return results;
						});
			};
			
			var _getNbOfProducts = function() {

				return $http.get(serviceBase + '/ws/resources/products/total').then(
						function(results) {
							return results;
						});
			};
			
			var _getTopProducts = function(offset,limit){
				
				return $http.get(serviceBase + '/ws/resources/products/'+offset+'/'+limit).then(
						function(results){
							return results;
						});
			};
			
			var _addProducts = function(name, price) {
				return $http({
					method : 'PUT',
					url : serviceBase+ '/ws/resources/products',
					data : '{"name":"' + name + '","price":' + price + '}',
					success : "OK",
					headers : {
								'Content-Type' : 'application/json'
							  }
					});
		};

			productsServiceFactory.getProducts = _getProducts;
			productsServiceFactory.addProducts = _addProducts;
			productsServiceFactory.getNbOfProducts = _getNbOfProducts;
			productsServiceFactory.getTopProducts = _getTopProducts;
			return productsServiceFactory;

		} ]);