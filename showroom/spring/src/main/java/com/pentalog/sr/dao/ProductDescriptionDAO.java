package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.ProductDescription;

/**
 * The product description data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductDescriptionDAO  extends JpaRepository<ProductDescription, Integer> {

	/**
	 * Method that returns the product description for the given product id.
	 * @param product_Id -	the product id
	 * @return
	 */
	ProductDescription findByProduct_Id(int product_Id);
}