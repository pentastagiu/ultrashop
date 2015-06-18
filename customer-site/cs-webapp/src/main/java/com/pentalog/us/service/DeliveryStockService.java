package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.DeliveryStock;

/**
 * The delivery stock service
 * @authors acozma and bpopovici
 *
 */
public interface DeliveryStockService {
	
	/**
	 * Method that get delivery stocks
	 * @return
	 */
	List<DeliveryStock> getDeliveryStocks();
	
	/**
	 * Method that get delivery stock by id
	 * @param id
	 * @return
	 */
	DeliveryStock getDeliveryStockById(int id);
	
	/**
	 * Method that post delivery stock
	 * @param deliveryStock
	 */
	void postDeliveryStock(DeliveryStock deliveryStock);
	
	/**
	 * Method that put delivery stock
	 * @param deliveryStock
	 */
	void putDeliveryStock(DeliveryStock deliveryStock);
	
	/**
	 * Method that delete delivery stock
	 * @param deliveryStock
	 */
	void deleteDeliveryStock(DeliveryStock deliveryStock);
}