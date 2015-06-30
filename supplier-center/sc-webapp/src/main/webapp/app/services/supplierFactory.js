app.factory('supplierFactory', [ '$http', function($http) {

	var supplierFactory = {};

	var productUrlBase = '/suppliercenter/ws/resources/suppliers';
	supplierFactory.getSuppliers = function() {
		return $http.get(productUrlBase);
	};
	supplierFactory.getSuppliersById = function(supplierId) {
		return $http.get(productUrlBase + '/' + supplierId);
	};
	supplierFactory.finishTranzaction = function(supplier) {
		return $http.put(productUrlBase, supplier);
	};
	supplierFactory.updateSupplier = function(supplier) {

		return $http.post(productUrlBase, supplier);
	};
	supplierFactory.deleteSupplier = function(supplier) {

		return $http({
			url : productUrlBase,
			dataType : "json",
			method : 'DELETE',
			data : supplier,
			headers : {
				'Content-Type' : 'application/json'
			}
		});
	};
	supplierFactory.setSupplier = function(supplier) {
		supplierFactory.supplier = supplier;
	};

	supplierFactory.getSupplier = function() {
		return supplierFactory.supplier;
	};
	return supplierFactory;
} ]);