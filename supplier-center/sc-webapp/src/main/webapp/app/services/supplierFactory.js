app.factory('supplierFactory', ['$http', function($http) {

	var supplierFactory = {};
	
	var productUrlBase = '/suppliercenter/ws/suppliers';
	supplierFactory.getSuppliers = function() {
		return $http.get(productUrlBase);
	}	
	return supplierFactory;
}]);