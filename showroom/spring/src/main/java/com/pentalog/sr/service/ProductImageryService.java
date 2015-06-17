package com.pentalog.sr.service;

import com.pentalog.sr.model.ProductImagery;

/**
 * The product imagery service
 * @authors acozma and bpopovici
 *
 */
public interface ProductImageryService {
	/**
	 * Method that returns the product imagery for a product with the given product id
	 * @param productId -	the product id
	 * @return
	 */
	ProductImagery getProductImageryByProductId(int productId);

	/**
	 * Method that persists a product imagery in the database
	 * @param productImagery - the product imagery
	 * @return
	 */
	ProductImagery saveProductImagery(ProductImagery productImagery);	
	
	/**
	 * Method that returns the image url
	 * @return
	 */
	String getImagesUrl();
}