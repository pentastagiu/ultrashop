package com.pentalog.us.service;


import java.util.List;

import com.pentalog.us.model.Supplier;

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
	 * Synchronize suppliers
	 */
	public void syncSuppliers(List<Supplier> suppliers);

	/**
	 * Finds a supplies  by external id
	 */
	public Supplier getSupplierByExternalId(int id);
	
	/**
	 * Finds a supplier by his id
	 * @param - the id
	 * @return - the supplier
	 */
	public Supplier getSupplierById(int id);
	

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
}
