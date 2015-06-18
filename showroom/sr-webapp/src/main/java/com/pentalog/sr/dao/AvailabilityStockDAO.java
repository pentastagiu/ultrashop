package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.AvailabilityStock;

/**
 * The availability stock data access layer
 * @authors acozma and bpopovici
 *
 */
public interface AvailabilityStockDAO extends JpaRepository<AvailabilityStock, Integer>{
	
	/**
	 * Method that returns the availability stock for the given product id.
	 * @param product_Id -	the product id
	 * @return
	 */
	AvailabilityStock findByProduct_Id(int product_Id);
}