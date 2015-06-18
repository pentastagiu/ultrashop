app.controller('productsController', [ '$scope', 'productsService','$timeout','$location',
		function($scope, productsService,$timeout,$location) {
			$scope.savedSuccessfully = false;
			$scope.message = "";
			$scope.products = [];
			 nbOfProducts();
			  $scope.currentPage = 1;
			  $scope.numPerPage = 10;
			  topProducts(($scope.currentPage-1)*$scope.numPerPage,$scope.currentPage*$scope.numPerPage);
			  
			function topProducts(offset,limit) {
				productsService.getTopProducts(offset,limit).then(function(results) {
					 $scope.products = results.data;
				}, function(error) {
					//alert(error.data.message);
				});
			}
			
			function nbOfProducts() {
				productsService.getNbOfProducts().then(function(results) {
					$scope.totalItems = results.data;

				}, function(error) {
					//alert(error.data.message);
				});
			}
			
			$scope.addNewProduct = function() {
				productsService.addProducts($scope.product.name,
						$scope.product.price).then(function(response) {
							$scope.savedSuccessfully = true;
							$scope.message = "Product has been registered successfully !";
							topProducts(($scope.currentPage-1)*$scope.numPerPage,$scope.currentPage*$scope.numPerPage);
							$timeout(function() {
								 $scope.message = "";
								 $scope.savedSuccessfully = false;
							    }, 3000);
						});
			};
			
			 $scope.pageChanged = function(page) {
				  $scope.currentPage = page;
				  topProducts(($scope.currentPage-1)*$scope.numPerPage+1,$scope.currentPage*$scope.numPerPage);
				  return true;
				};
				
		} ]);