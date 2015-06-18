package com.pentalog.sr.service;

import java.util.List;
import com.pentalog.sr.model.DeliveryStock;

/**
 * The delivery stock service
 * @authors acozma and bpopovici
 *
 */
public interface DeliveryStockService {
	
	/**
	 * Method that updates the delivery stocks for a given list
	 * @param deliveryStocks -	the delivery stocks  to be updated
	 */
	void updateDeliveryStocks(List<DeliveryStock> deliveryStocks);

	/**
	 * Method that returns the serial of a delivery stock by order id and product id
	 * @param order_Id   -	the order id
	 * @param product_Id -	the product id
	 * @return
	 */
	List<DeliveryStock> getSerialByOrderIdAndProductId(int order_Id, int product_Id);
	
	/**
	 * Method that returns the delivery stock  by order id and product id
	 * @param order_Id -	the order id
	 * @param product_Id -	the product id
	 * @return
	 */
	List<DeliveryStock> getDeliveryStockByOrderIdAndProductId(int order_Id, int product_Id);
	
	/**
	 * Method that returns the delivery stock by order id
	 * @param order_Id
	 * @return
	 */
	List<DeliveryStock> getDeliveryStocksByOrderId(int order_Id);
	
	/**
	 * Method that persists a delivery stock in the database
	 * @param deliveryStock -	the delivery stock
	 */
	void saveDeliveryStock(DeliveryStock deliveryStock);
	
	/**
	 * Method that removes a delivery stock from the database
	 * @param deliveryStock - the delivery stock
	 */
	void deleteDeliveryStock(DeliveryStock deliveryStock);
}