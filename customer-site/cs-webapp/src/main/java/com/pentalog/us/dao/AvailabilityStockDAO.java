package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.AvailabilityStock;

/**
 * The availability stock data access layer
 * @authors acozma and bpopovici
 *
 */
public interface AvailabilityStockDAO extends JpaRepository<AvailabilityStock, Integer> {
	
	/**
	 * Get the availability stock by product id
	 * @param id
	 * @return
	 */
	AvailabilityStock findByProduct_Id(int id);
}