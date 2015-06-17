package com.pentalog.sr.service;

import com.pentalog.sr.model.Order.NotifyClient;
import com.pentalog.sr.model.Order.Status;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.model.Order;
import com.pentalog.sr.model.Product;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The order service
 * @authors acozma and bpopovici
 *
 */
public interface OrderService {

	/**
	 * Method that returns all orders into a wrapper
	 * @return
	 */
	Wrapper<Order> getAllOrders_Wrapper(); 
	
	/**
	 * Method that returns all orders into a list
	 * @return
	 */
	List<Order> getAllOrders_List();
	
	/**
	 * Method that returns all orders into a list ordered descending by order date
	 * @return
	 */
	List<Order> getAllByOrderByODateDesc();
	
	/**
	 * Method that returns an order with the given order id
	 * @param id -	the order id
	 * @return
	 */
	Order getOrderById(int id);

	/**
	 * Method that persists an order into the database
	 * @param order -	the order
	 * @return
	 */
	Order registerOrder(Order order);
	
	/**
	 * Method that persists a placed order into the database
	 * @param order -	the order
	 * @return
	 */
	Order registerPlacedOrder(Order order);
	
	/**
	 * Method that updates order data of a given order
	 * @param order  -	the order
	 */
	void updateOrder(Order order);
	
	/**
	 * Method that calculates the payment for an order 
	 * @param products -	the products on the order
	 * @return
	 */
	double calculateQuantum(Collection<Product> products);
	
	/**
	 * Method that updates the status for an order
	 * @param order - the order
	 * @param status - the new status of the order
	 */
	void updateStatus(Order order, Status status);
	
	/**
	 * Method that updates the notify client for an order
	 * @param order -	the order
	 * @param notifyClient - the new notify Client of the order
	 */
	void updateNotify(Order order, NotifyClient notifyClient);
	
	/**
	 * Method that sends the warranty and order bill to the customer after the order is ready
	 * @param order -	the order
	 */
	void sendDocuments(Order order);
	
	/**
	 * Method that updates the status from Waiting_Arrival to Arrived
	 */
	void updateStatusForWaitingOrders();
	
	/**
	 * Send notification mail to the customer
	 */
	void sendMailForReadyOrder();
	
	/**
	 * Method that returns a list of orders filtered by the customer id
	 * @param filterCustomerId - the customer id
	 * @param orders - the list of all orders
	 * @return
	 */
	List<Order> filterByCustomerId(String filterCustomerId, List<Order> orders);
	
	/**
	 * Method that returns a list of orders filtered by a range of dates
	 * @param filterMinDate - the minimum range date
	 * @param filterMaxDate - the maximum range date
	 * @param orders - the list of all orders
	 * @return
	 */
	List<Order> filterByDate(Date filterMinDate, Date filterMaxDate, List<Order> orders);
	
	/**
	 * Method that returns a list of orders filtered by a range of payments
	 * @param filterMinQuantum - the minimum payment
	 * @param filterMaxQuantum - the maximum payment
	 * @param orders - the list of all orders
	 * @return
	 */
	List<Order> filterByQuantum(String filterMinQuantum, String filterMaxQuantum, List<Order> orders);
	
	/**
	 * Method that returns a list of orders filtered by a list of statuses
	 * @param selectedStatuses - the list of selected statuses
	 * @param orders - the list of all orders
	 * @return
	 */
	List<Order> filterByStatus(List<String> selectedStatuses, List<Order> orders);
	
	/**
	 * Method that translates a status into the current locale language and returns it as a string
	 * @param status - the given status
	 * @param message - the message bundle that contains the current locale
	 * @return
	 */
	String translateStatus(Status status, ResourceBundle message);

	/**
	 * Method that updates status for all placed orders
	 */
	void updateStatusForPlacedOrders();
	
	/**
	 * Method that returns a list of orders between an inferior limit and a superior limit
	 * @param offset - the inferior limit
	 * @param limit  - the superior limit
	 * @return
	 */ 
	List<Order> getTopOrders(Integer offset, Integer limit);
	
	/**
	 * Method that counts the total number of orders
	 * @return
	 */
	Integer getNumberOfOrders();
	
	/**
	 * Method that updates status to READY_FOR_PICKUP when the delivery is ready.
	 */
	void setReadyStatus(Order order);
	
	String syncOrders(List<Order> orders);
	
	Order getOrderByExternalId(int external_id);
}