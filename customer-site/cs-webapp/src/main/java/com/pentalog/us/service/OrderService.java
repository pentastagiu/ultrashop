package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.Order;
import com.pentalog.us.model.Order.DeliveryMethod;

/**
 * The order service
 * @authors acozma and bpopovici
 *
 */
public interface OrderService {
	
	/**
	 * Method that get orders
	 * @return
	 */
	List<Order> getOrders();
	
	/**
	 * Method that get order by id
	 * @param id
	 * @return
	 */
	Order getOrderById(int id);
	
	/**
	 * Method that post order
	 * @param order
	 */
	void postOrder(Order order);
	
	/**
	 * Method that put order
	 * @param order
	 */
	void putOrder(Order order);
	
	/**
	 * Method that delete order
	 * @param order
	 */
	void deleteOrder(Order order);
	
	void syncOrdes();
	
	List<Order> getOrderByDeliveryMethod(DeliveryMethod deliveryMethod);
}