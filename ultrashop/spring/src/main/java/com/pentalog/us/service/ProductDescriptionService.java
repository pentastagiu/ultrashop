package com.pentalog.us.service;

import java.util.List;

import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductDescription;

/**
 * The product description service
 * @authors acozma and bpopovici
 *
 */
public interface ProductDescriptionService {
	
	/**
	 * Method that get product description by id
	 * @param id
	 * @return
	 */
	ProductDescription getProductDescriptionById(int id);
	
	/**
	 * Method that get product descriptions
	 * @return
	 */
	List<ProductDescription> getProductDescriptions();
	
	/**
	 * Method that put product description
	 * @param productDescription
	 */
	void putProductDescription(ProductDescription productDescription);
	
	/**
	 * Method that post product description
	 * @param productDescription
	 */
	void postProductDescription(ProductDescription productDescription);
	
	/**
	 * Method that delete product description
	 * @param productDescription
	 */
	void deleteProductDescription(ProductDescription productDescription);
	
	ProductDescription getProductDescriptionByProductId(Product product);
}