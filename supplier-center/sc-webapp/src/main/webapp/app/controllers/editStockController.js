app.controller('editStockController',
		[
				'$scope',
				'$location',
				'$filter',
				'productFactory',
				'supplierFactory',
				'stockFactory',
				function($scope, $location, $filter, productFactory,
						supplierFactory, stockFactory) {
					$scope.stock = stockFactory.getStock();

					getAllSuppliers();

					function getAllSuppliers() {
						supplierFactory.getAllSuppliers()
								.success(
										function(suppliers) {
											$scope.suppliers = suppliers;
											// Find supplier by id.This function
											// is used to set the
											// default supplier in the edit
											// product selector
											var supplierId = stockFactory
													.getStock().supplier.id;
											$scope.stock.supplier = $filter(
													'filter')($scope.suppliers,
													{
														id : supplierId
													})[0];
										});
						productFactory.getProductBySupplier(
								$scope.stock.supplier.id)
								.success(
										function(products) {
											$scope.products = products;
											// Find supplier by id.This function
											// is used to set the
											// default supplier in the edit
											// product selector
											var productId = stockFactory
													.getStock().product.id;
											$scope.stock.product = $filter(
													'filter')($scope.products,
													{
														id : productId
													})[0];
										}).error(function() {
									alert('error');
								});
					}
					;
					function getProductsBySupplier() {
						productFactory.getProductBySupplier(
								$scope.stock.supplier.id).success(
								function(products) {
									$scope.products = products;
								}).error(function() {
							alert('error');
						});
					}
					;
					$scope.getProductsBySupplier = function() {
						alert('changed');
						getProductsBySupplier();
					};
					function updateStock() {
						stockFactory.updateStock($scope.stock).success(
								function() {
									$location.path('/stocks');
								}).error(function() {
							alert('error');
						});
					}
					;
					$scope.updateStock = function() {
						updateStock();
					};
					$scope.cancel = function() {
						$location.path('/stocks');
					};
				} ]);
