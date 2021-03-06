package com.pentalog.sc.service;

import java.util.List;

import com.pentalog.sc.model.Product;
import com.pentalog.sc.model.Supplier;

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

    /**
     * Return the products of a supplier.
     */
    public List<Product> findBySupplierId(int id);

    /**
     * Return the number of entities from database.
     */
    public long count();

    /**
     * Read products by page.
     */
    public List<Product> readProductsByPage(int pageIndex, int offset);
    
    /**
     * Generate products for a supplier
     * @param supplier
     */
    public void generateProducts(Supplier supplier);
}
