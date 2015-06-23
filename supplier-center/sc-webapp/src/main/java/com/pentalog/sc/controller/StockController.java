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

	@Autowired
	StockService stockService;
	
	@Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody List<Stock> getAllStocks(){
		return stockService.getStocks();
	}
	
	@Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Stock createStock(@RequestBody Stock stock){
		return stockService.create(stock);
	}
	
	@Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public @ResponseBody Stock readStock(@PathVariable int id){
		return stockService.findById(id);
	}
	
	@Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
	@RequestMapping(value= "/supplier/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Stock> readStockBySupplierId(@PathVariable int id){
		return stockService.findBySupplierId(id);
	}
	
	@Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
	@RequestMapping(method= RequestMethod.POST)
	public @ResponseBody Stock updateStock(@RequestBody Stock stock){
		return stockService.update(stock);
	}
	
	@Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Stock deleteStock(@RequestBody Stock stock){
		return stockService.delete(stock);
	}

}
