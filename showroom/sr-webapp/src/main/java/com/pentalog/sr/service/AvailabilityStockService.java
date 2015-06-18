package com.pentalog.sr.service;

import java.util.List;

import com.pentalog.sr.model.AvailabilityStock;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.model.Wrapper;

/**
 * The availability stock service 
 * @authors acozma and bpopovici
 *
 */
public interface AvailabilityStockService {
	
	/**
	 * Method that gets all availability stocks into a wrapper
	 * @return
	 */
	Wrapper<AvailabilityStock> getAllAvailabilityStocks_Wrapper();
	
	/**
	 * Method that gets all availability stocks into a list
	 * @return
	 */
	List<AvailabilityStock> getAllAvailabilityStocks_List();

	/**
	 * Method that gets an availability stock by product id
	 * @param id -	the product id
	 * @return
	 */
	AvailabilityStock getStockById(int id);
	
	/**
	 * Method that updates a stock for a given availability stock
	 * @param availabilityStock -	the availability stock
	 */
	void updateStock(AvailabilityStock availabilityStock);	
	
	/**
	 * Method that registers an availability stock for a given product
	 * @param product  - the product
	 */
	void registerStock(Product product);
	
	/**
	 * Method that replenishes supply with the given quantity for an availability stock
	 * @param availabilityStock -	the availability stock
	 * @param quantity -	the quantity added
	 */
	void replenishSupply(AvailabilityStock availabilityStock, int quantity);
	
	/**
	 * Method that returns a list of availability stocks filtered by product name
	 * @param filterName - the product name
	 * @param availabilityStocks -	the list of all availability stocks
	 * @return
	 */
	List<AvailabilityStock> filterByName(String filterName, List<AvailabilityStock> availabilityStocks);
	
	/**
	 * Method that returns a list of availability stocks filtered by stock quantity
	 * @param filterMinStock -	the minimum range
	 * @param filterMaxStock -	the maximum range
	 * @param availabilityStocks - the list of all availability stocks
	 * @return
	 */
	List<AvailabilityStock> filterByStock(String filterMinStock, String filterMaxStock, List<AvailabilityStock> availabilityStocks);
}