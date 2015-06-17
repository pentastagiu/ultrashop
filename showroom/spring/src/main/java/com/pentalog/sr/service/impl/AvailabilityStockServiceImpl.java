package com.pentalog.sr.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.AvailabilityStockDAO;
import com.pentalog.sr.model.AvailabilityStock;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.service.AvailabilityStockService;

/**
 * The availability stock service implementation
 * @authors acozma and bpopovici
 *
 */
@Service("availabilityStockService")
public class AvailabilityStockServiceImpl implements AvailabilityStockService{

	/**
	 * The availability stock data access object
	 */
	@Autowired
	private AvailabilityStockDAO availabilityStockDao;
	
	/**
	 * @see {@link AvailabilityStockService.getAllAvailabilityStocks_Wrapper}
	 */
	@Override
	public Wrapper<AvailabilityStock> getAllAvailabilityStocks_Wrapper() {
		Wrapper<AvailabilityStock> availabilityStock = new Wrapper<AvailabilityStock>();
		availabilityStock.setList(availabilityStockDao.findAll());
		return availabilityStock;
	}

	/**
	 * @see {@link AvailabilityStockService.getAllAvailabilityStocks_List}
	 */
	@Override
	public List<AvailabilityStock> getAllAvailabilityStocks_List() {
		return availabilityStockDao.findAll();
	}
	
	/**
	 * @see {@link AvailabilityStockService.getStockById}
	 */
	@Override
	public AvailabilityStock getStockById(int id) {
		return this.availabilityStockDao.findByProduct_Id(id);
	}
	
	/**
	 * @see {@link AvailabilityStockService.updateStock}
	 */
	@Override
	public void updateStock(AvailabilityStock availabilityStock) {
		this.availabilityStockDao.save(availabilityStock);
	}

	/**
	 * @see {@link AvailabilityStockService.registerStock}
	 */
	@Override
	public void registerStock(Product product) {
		AvailabilityStock availabilityStock = new AvailabilityStock();
		availabilityStock.setProduct(product);
		availabilityStock.setStock(0);
		this.availabilityStockDao.save(availabilityStock);
	}
	
	/**
	 * @see {@link AvailabilityStockService.replenishSupply}
	 */
	@Override
	public void replenishSupply(AvailabilityStock availabilityStock, int quantity) {
		availabilityStock.setStock(availabilityStock.getStock() + quantity);
		updateStock(availabilityStock);
	}

	/**
	 * @see {@link AvailabilityStockService.filterByName}
	 */
	@Override
	public List<AvailabilityStock> filterByName(String filterName, List<AvailabilityStock> availabilityStocks) {
		List<AvailabilityStock> filterAvailabilityStocks;
		if(!filterName.isEmpty()) {
			filterAvailabilityStocks = new ArrayList<AvailabilityStock>();
			for(AvailabilityStock as : availabilityStocks) {
				if(as.getProduct().getName().toLowerCase().contains(filterName.toLowerCase())) {
					filterAvailabilityStocks.add(as);
				}
			}
			availabilityStocks = filterAvailabilityStocks;
		}
		return availabilityStocks;
	}

	/**
	 * @see {@link AvailabilityStockService.filterByStock}
	 */
	@Override
	public List<AvailabilityStock> filterByStock(String filterMinStock, String filterMaxStock, List<AvailabilityStock> availabilityStocks) {
		List<AvailabilityStock> filterAvailabilityStocks;
		if (!filterMinStock.isEmpty() && isNumeric(filterMinStock)) {
			filterAvailabilityStocks = new ArrayList<AvailabilityStock>();
			int minStock = Integer.parseInt(filterMinStock);
			for(AvailabilityStock as : availabilityStocks) {
				if (as.getStock() >= minStock) {
					filterAvailabilityStocks.add(as);
				}
			}
			availabilityStocks = filterAvailabilityStocks;
		}
		if (!filterMaxStock.isEmpty() && isNumeric(filterMaxStock)) {
			filterAvailabilityStocks = new ArrayList<AvailabilityStock>();
			int maxStock = Integer.parseInt(filterMaxStock);
			for(AvailabilityStock as : availabilityStocks) {
				if (as.getStock() <= maxStock) {
					filterAvailabilityStocks.add(as);
				}
			}
			availabilityStocks = filterAvailabilityStocks;
		}
		return availabilityStocks;
	}
	
	/**
	 * Method that verifies if a given string is a numeric sequencce
	 * @param str -	the given string
	 * @return
	 */
	private static boolean isNumeric(String str)
	{
		return str.matches("\\d+(\\.\\d+)?");
	}
}