app.factory('orderFactory', [
		'$http',
		function($http) {

			var orderFactory = {};
			var orderUrlBase = '/suppliercenter/ws/resources/orders';
			orderFactory.getAllOrders = function() {
				return $http.get(orderUrlBase);
			};
			orderFactory.getOrders = function(currentPage, ordPerPage) {
				return $http.get(orderUrlBase + '/pageIndex=' + currentPage
						+ '/offset=' + ordPerPage);
			};
			orderFactory.getOrderCount = function() {
				return $http.get(orderUrlBase + '/count');
			};

			orderFactory.addOrder = function(order) {
				return $http.put(orderUrlBase, order);
			};
			orderFactory.updateOrder = function(order) {
				return $http.post(orderUrlBase, order);
			};

			orderFactory.deleteOrder = function(order) {
				return $http({
					url : orderUrlBase,
					dataType : "json",
					method : 'DELETE',
					data : order,
					headers : {
						'Content-Type' : 'application/json'
					}
				});
			};
			orderFactory.setOrder = function(order) {
				orderFactory.order = order;
			};

			orderFactory.getOrder = function() {
				return orderFactory.order;
			};

			return orderFactory;
		} ]);