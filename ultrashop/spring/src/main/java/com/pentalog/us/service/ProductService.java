package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.Product;

/**
 * The product service
 * @authors acozma and bpopovici
 *
 */
public interface ProductService {	
	
	/**
	 * Method that get product by id
	 * @param id
	 * @return
	 */
	Product getProductById(int id);
	
	/**
	 * Method that get products
	 * @return
	 */
	List<Product> getProducts();
	
	/**
	 * Method that put product
	 * @param product
	 */
	void putProduct(Product product);
	
	/**
	 * Method that post product
	 * @param product
	 */
	void postProduct(Product product);
	
	/**
	 * Method that delete product
	 * @param product
	 */
	void deleteProduct(Product product);
	
	void syncProduct();
}