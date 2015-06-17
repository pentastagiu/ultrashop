package com.pentalog.us.service;

import java.util.List;

import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductPresentation;

/**
 * The product presentation service
 * @authors acozma and bpopovici
 *
 */
public interface ProductPresentationService {	
	
	/**
	 * Method that get product presentation by id
	 * @param id
	 * @return
	 */
	ProductPresentation getProductPresentationById(int id);
	
	/**
	 * Method that get product presentations
	 * @return
	 */
	List<ProductPresentation> getProductPresentations();
	
	/**
	 * Method that put product presentation
	 * @param productPresentation
	 */
	void putProductPresentation(ProductPresentation productPresentation);
	
	/**
	 * Method that post product presentation
	 * @param productPresentation
	 */
	void postProductPresentation(ProductPresentation productPresentation);
	
	/**
	 * Method that delete product presentation
	 * @param productPresentation
	 */
	void deleteProductPresentation(ProductPresentation productPresentation);
	
	List<ProductPresentation> getProductPresentationByProductId(Product product);
}