package com.pentalog.sc.service;

import java.util.List;

import com.pentalog.sc.model.Supplier;

/**
 * Provides services for rest services.
 *
 */
public interface SupplierService {

    /**
     * Return a list with all products from database.
     * 
     * @return
     */
    public List<Supplier> getSuppliers();

    /**
     * finds a product by id
     * 
     * @return - the product
     */
    public Supplier findById(int id);

    /**
     * Insert a product in database.
     * 
     * @param product
     * @return
     */
    public Supplier create(Supplier supplier);

    /**
     * Updates a product in database.
     * 
     * @param product
     * @return
     */
    public Supplier update(Supplier supplier);

    /**
     * Delete a product from database.
     * 
     * @param product
     * @return
     */
    public Supplier delete(Supplier supplier);

}
