package com.pentalog.sc.service;

import java.util.List;

import com.pentalog.sc.model.Stock;

/**
 * Provides REST services for Stock
 *
 */

public interface StockService {

	/**
	 * reads all stocks from database
	 * @return a list with all stocks 
	 */
	public List<Stock> getStocks();
	
//	
//	/**
//	 * finds all stocks from a supplier
//	 * @param id - the supplier id
//	 * @return list of stocks
//	 */
//	public List<Stock> getStocksBySupplier(int id);
//	
	
    /**
     * creates a new stockin database
     * @param stock - the new stock
     * @return the new stock
     */
    public Stock create (Stock stock);
    
    
    /**
     * finds a stock by id
     * @param id - the stock id
     * @return the stock
     */
    public Stock findById(int id);
    
    
    /**
     * updates a stock 
     * @param stock
     * @return the stock updated
     */
    public Stock update(Stock stock);
    
    
    /**
     * removes a stock from database
     * @param id - the stock id
     * @return - the stock deleted
     */
    public Stock delete (Stock stock);
    
	/**
	 * Method that returns all stocks from a supplier
	 * @param id - the supplier id
	 * @return list of stocks
	 */
    public List<Stock> findBySupplierId(int id);
}
