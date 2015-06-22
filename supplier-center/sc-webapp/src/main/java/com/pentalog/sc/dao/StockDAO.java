package com.pentalog.sc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Stock;
/**
 * The stock data access layer
 *
 */
public interface StockDAO extends JpaRepository<Stock, Integer>{

	/**
	 * Method that returns all stocks from a supplier
	 * @param id - the supplier id
	 * @return list of stocks
	 */
	List<Stock> findBySupplierId(int id);
}

