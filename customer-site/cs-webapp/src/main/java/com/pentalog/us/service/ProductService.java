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
	
    /**
     * Method that updates the solr document
     */
    void updateSolrDocs();
    
    /**
     * Method that returns all the products into a list
     * @return
     */
    List<Product> getAllProducts_List();
    
    /**
     * Method that returns a list of products that contain in their description or feature descriptions a given search value
     * @param value -   the searched value
     * @return
     */
    List<Product> searchProduct(String value);
}