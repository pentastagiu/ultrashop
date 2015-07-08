app.factory('supplierFactory', [
		'$http',
		function($http) {

			var supplierFactory = {};

			var supplierUrlBase = '/suppliercenter/ws/resources/suppliers';
			supplierFactory.getAllSuppliers = function() {
				return $http.get(supplierUrlBase);
			};
			supplierFactory.getSuppliers = function(currentPage, supPerPage) {
				return $http.get(supplierUrlBase + '/pageIndex=' + currentPage
						+ '/offset=' + supPerPage);
			};
			supplierFactory.getSuppliersById = function(supplierId) {
				return $http.get(supplierUrlBase + '/' + supplierId);
			};
			supplierFactory.getSupplierCount = function() {
				return $http.get(supplierUrlBase + '/count-active');
			};
			supplierFactory.finishTranzaction = function(supplier) {
				return $http.put(supplierUrlBase, supplier);
			};
			supplierFactory.updateSupplier = function(supplier) {

				return $http.post(supplierUrlBase, supplier);
			};
			supplierFactory.deleteSupplier = function(supplier) {
				debugger;
				return $http({
					url : supplierUrlBase,
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