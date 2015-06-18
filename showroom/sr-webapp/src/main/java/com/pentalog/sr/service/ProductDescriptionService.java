package com.pentalog.sr.service;

import com.pentalog.sr.model.ProductDescription;

/**
 * The product description service
 * @authors acozma and bpopovici
 *
 */
public interface ProductDescriptionService {
	
	/**
	 * Method that returns the product description for a product with the given product id
	 * @param productId -	the product id
	 * @return
	 */
	ProductDescription getProductDescription(int productId);

	/**
	 * Method that persists the product description in the database
	 * @param productDescription - the product description
	 * @return
	 */
	ProductDescription saveProductDescription(ProductDescription productDescription);

	/**
	 * Method that returns a product description by its id
	 * @param id - the product description id
	 * @return
	 */
	ProductDescription getProductDescriptionById(int id);
}