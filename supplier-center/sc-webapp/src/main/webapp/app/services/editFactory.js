app.factory('editFactory',['$http',function($http) {

	var editFactory = {};

	editFactory.setProduct = function(product) {
		editFactory.product = product;
	};

	editFactory.getProduct = function() {
		return editFactory.product;
	};
	return editFactory;
}] );