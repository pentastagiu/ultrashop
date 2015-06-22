package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody List<Stock> getAllStocks(){
		return stockService.getStocks();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Stock createStock(@RequestBody Stock stock){
		return stockService.create(stock);
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public @ResponseBody Stock readStock(@PathVariable int id){
		return stockService.findById(id);
	}
	
	@RequestMapping(value= "/supplier/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Stock> readStockBySupplierId(@PathVariable int id){
		return stockService.findBySupplierId(id);
	}
	
	@RequestMapping(method= RequestMethod.POST)
	public @ResponseBody Stock updateStock(@RequestBody Stock stock){
		return stockService.update(stock);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Stock deleteStock(@RequestBody Stock stock){
		return stockService.delete(stock);
	}

}
