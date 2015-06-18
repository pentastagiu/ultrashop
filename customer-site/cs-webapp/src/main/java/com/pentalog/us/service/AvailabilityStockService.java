package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.AvailabilityStock;

/**
 * The availability stock service
 * @authors acozma and bpopovici
 *
 */
public interface AvailabilityStockService {	
	
	/**
	 * Method that get availability stock by id
	 * @return
	 */
	AvailabilityStock getAvailabilityStockById(int id);
	
	/**
	 * Method that get availability stocks
	 * @param id
	 * @return
	 */
	List<AvailabilityStock> getAvailabilityStocks();
	
	/**
	 * Method that put availability stock
	 * @param availabilityStock
	 */
	void putAvailabilityStock(AvailabilityStock availabilityStock);
	
	/**
	 * Method that post availability stock
	 * @param availabilityStock
	 */
	void postAvailabilityStock(AvailabilityStock availabilityStock);
	
	/**
	 * Method that delete availability stock
	 * @param availabilityStock
	 */
	void deleteAvailabilityStock(AvailabilityStock availabilityStock);
	
	/**
	 * Method that get availability stock by product id
	 * @param id
	 * @return
	 */
	AvailabilityStock getAvailabilityStockByProductId(int id);
}