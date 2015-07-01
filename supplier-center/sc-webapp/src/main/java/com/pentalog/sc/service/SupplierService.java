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

    /**
     * Return a list of suppliers filtered by active.
     * 
     * @param active
     * @return
     */
    public List<Supplier> findByActive(Boolean active);

    /**
     * Return the number of entities from database filtered by active.
     * 
     * @return
     */
    public long countByActive(Boolean active);

    /**
     * Return a list of suppliers coresponding to pageIndex and offset.
     * 
     * @param pageIndex
     * @param offset
     * @return
     */
    public List<Supplier> readSuppliersByPage(int pageIndex, int offset);
    
    /**
     * Synchronize suppliers
     */
    public void syncSuppliers ();


}
