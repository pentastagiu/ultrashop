package com.pentalog.us.service;

import java.util.List;

import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductImagery;

/**
 * The product imagery service
 * @authors acozma and bpopovici
 *
 */
public interface ProductImageryService {	
	
	/**
	 *  Method that get product imagery by id
	 * @param id
	 * @return
	 */
	ProductImagery getProductImageryById(int id);
	
	/**
	 * Method that get product imageries
	 * @return
	 */
	List<ProductImagery> getProductImageries();
	
	/**
	 * Method that put product imagery
	 * @param productImagery
	 */
	void putProductImagery(ProductImagery productImagery);
	
	/**
	 * Method that post product imagery
	 * @param productImagery
	 */
	void postProductImagery(ProductImagery productImagery);
	
	/**
	 * Method that delete product imagery
	 * @param productImagery
	 */
	void deleteProductImagery(ProductImagery productImagery);
	
	ProductImagery getProductImageryByProductId(Product product);
}