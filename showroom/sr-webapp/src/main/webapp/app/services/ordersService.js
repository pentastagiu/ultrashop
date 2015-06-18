app.factory('ordersService', [
		'$http',
		'$location',
		function($http, $location) {

			$location.path("/showroom");
			var serviceBase = $location.url();
			var _host = $location.host();
			
			$location.path("/orders");
			
			var _orderInfo = {
					placed : false,
					waiting_arrival : false,
					arrived : false,
					ready_for_pickup : false,
					delivered : false,
					canceled : false,
					customer : "",
					date : "",
					quantum : 0,
					products : []
			};
			
			var _order = {data : null};
			
			var ordersServiceFactory = {};

			var _getNbOfOrders = function() {

				return $http.get(serviceBase + '/ws/resources/orders/total').then(
						function(results) {
							return results;
						});
			};
			
			var _getTopOrders = function(offset,limit){
				
				return $http.get(serviceBase + '/ws/resources/orders/'+offset+'/'+limit).then(
						function(results){
							return results;
						});
			};
			

			var _cancelOrder = function(order) {
				return $http.post(serviceBase + '/ws/resources/orders/cancel',
						order).then(function(results) {
					return results;
				});

				return ordersServiceFactory;
			};

			var _deliverOrder = function(order) {
				return $http.post(serviceBase + '/ws/resources/orders/finish',
						order).then(function(results) {
					return results;
				});

				return ordersServiceFactory;
			};

			var _selectedOrderInfo = function(o){
				if(o.status === "CANCELED") {
					_orderInfo.placed = false;
					_orderInfo.waiting_arrival = false;
					_orderInfo.arrived = false;
					_orderInfo.ready_for_pickup = false;
					_orderInfo.delivered = false;
					_orderInfo.canceled = true;
				}
				if(o.status === "DELIVERED") {
					_orderInfo.placed = true;
					_orderInfo.waiting_arrival = true;
					_orderInfo.arrived = true;
					_orderInfo.ready_for_pickup = true;
					_orderInfo.delivered = true;
					_orderInfo.canceled = false;
				}
				if(o.status === "READY_FOR_PICKUP") {
					_orderInfo.placed = true;
					_orderInfo.waiting_arrival = true;
					_orderInfo.arrived = true;
					_orderInfo.ready_for_pickup = true;
					_orderInfo.delivered = false;
					_orderInfo.canceled = false;
				}
				if(o.status === "ARRIVED") {
					_orderInfo.placed = true;
					_orderInfo.waiting_arrival = true;
					_orderInfo.arrived = true;
					_orderInfo.ready_for_pickup = false;
					_orderInfo.delivered = false;
					_orderInfo.canceled = false;
				}
				if(o.status === "WAITING_ARRIVAL") {
					_orderInfo.placed = true;
					_orderInfo.waiting_arrival = true;
					_orderInfo.arrived = false;
					_orderInfo.ready_for_pickup = false;
					_orderInfo.delivered = false;
					_orderInfo.canceled = false;
				}
				if(o.status === "PLACED") {
					_orderInfo.placed = true;
					_orderInfo.waiting_arrival = false;
					_orderInfo.arrived = false;
					_orderInfo.ready_for_pickup = false;
					_orderInfo.delivered = false;
					_orderInfo.canceled = false;
				}
				_orderInfo.customer = o.customer.name;
				_orderInfo.date = o.oDate;
				_orderInfo.products = o.products;
				_orderInfo.quantum = o.quantum;
			};
			
			var _selectedOrder = function(o){
				_order.data = o;
			};
			
			var _sendMail = function(order) {
				return $http({
					method : 'POST',
					url : serviceBase+ '/ws/resources/orders/send',
					data : order,
					success : "OK",
					headers : {
								'Content-Type' : 'application/json'
							  }
					});
			};
			
			var _getDeliveryStock = function(order) {
				
				return $http({
					method : 'POST',
					url : serviceBase+ '/ws/resources/orders/delivery',
					data : order,
					success : "OK",
					headers : {
								'Content-Type' : 'application/json'
							  }
					});
			};
			
			var _update = function(order,deliveryStocks) {
				$http({
					method : 'POST',
					url : serviceBase+ '/ws/resources/orders/serial',
					data : deliveryStocks,
					success : "OK",
					headers : {
								'Content-Type' : 'application/json'
							  }
					}).then(function(){
						$http({
							method : 'POST',
							url : serviceBase+ '/ws/resources/orders/ready',
							data : order,
							success : "OK",
							headers : {
										'Content-Type' : 'application/json'
									  }
						});
					});
			};

			ordersServiceFactory.selectedOrderInfo = _selectedOrderInfo;
			ordersServiceFactory.selectedOrder = _selectedOrder;
			ordersServiceFactory.orderInfo = _orderInfo;
			ordersServiceFactory.order = _order;
			ordersServiceFactory.host = _host;
			ordersServiceFactory.getDeliveryStock = _getDeliveryStock;
			ordersServiceFactory.sendMail = _sendMail;
			ordersServiceFactory.update = _update;
			ordersServiceFactory.getTopOrders = _getTopOrders;
			ordersServiceFactory.getNbOfOrders = _getNbOfOrders;
			ordersServiceFactory.cancelOrder = _cancelOrder;
			ordersServiceFactory.deliverOrder = _deliverOrder;
			return ordersServiceFactory;

		} ]);