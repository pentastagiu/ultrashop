package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.ProductImagery;

/**
 * The product imagery data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductImageryDAO extends JpaRepository<ProductImagery, Integer> {

	/**
	 * Method that returns a product imagery for the given product id.
	 * @param product_id -	the product id
	 * @return
	 */
	ProductImagery findByProduct_Id(int product_id);
}