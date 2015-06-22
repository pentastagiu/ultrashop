package com.pentalog.sc.service;

import java.util.List;

import com.pentalog.sc.model.Product;

/**
 * Provides services for rest services.
 *
 */
public interface ProductService {

    /**
     * Return a list with all products from database.
     * 
     * @return
     */
    public List<Product> getProducts();

    /**
     * finds a product by id
     * 
     * @return - the product
     */
    public Product findById(int id);

    /**
     * Insert a product in database.
     * 
     * @param product
     * @return
     */
    public Product create(Product product);

    /**
     * Updates a product in database.
     * 
     * @param product
     * @return
     */
    public Product update(Product product);

    /**
     * Delete a product from database.
     * 
     * @param product
     * @return
     */
    public Product delete(Product product);
}
