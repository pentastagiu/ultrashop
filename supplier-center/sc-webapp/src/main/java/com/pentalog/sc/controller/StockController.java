package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Stock;
import com.pentalog.sc.service.StockService;

/**
 * Controller for web services
 * 
 */
@Controller
@RequestMapping("/stocks")
public class StockController {

	/**
	 * The stock service
	 */
	@Autowired
	StockService stockService;

	/**
	 * Gets all stocks from database
	 * 
	 * @return the list of all stocks
	 */
	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Stock> getAllStocks() {
		return stockService.getStocks();
	}

	/**
	 * Add a new stock in database
	 * 
	 * @param stock
	 * @return the new stock
	 */
	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Stock createStock(@RequestBody Stock stock) {
		return stockService.create(stock);
	}

	/**
	 * Finds a stock by id
	 * 
	 * @param id
	 * @return the stock
	 */
	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Stock readStock(@PathVariable int id) {
		return stockService.findById(id);
	}

	/**
	 * Gets all stocks from a supplier
	 * 
	 * @param id
	 *            - the supplier id
	 * @return list of stocks
	 */
	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Stock> readStockBySupplierId(@PathVariable int id) {
		return stockService.findBySupplierId(id);
	}

	/**
	 * Updates a stock
	 * 
	 * @param stock
	 * @return the new stock
	 */
	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Stock updateStock(@RequestBody Stock stock) {
		return stockService.update(stock);
	}

	/**
	 * Delete a stock
	 * 
	 * @param stock
	 * @return
	 */
	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Stock deleteStock(@RequestBody Stock stock) {
		return stockService.delete(stock);
	}

}
