package com.pentalog.us.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.AvailabilityStockDAO;
import com.pentalog.us.model.AvailabilityStock;
import com.pentalog.us.service.AvailabilityStockService;

/**
 * The availability stock service implementation
 * @author acozma and bpopovici
 *
 */
@Service("availabilityStockService")
public class AvailabilityStockServiceImpl implements AvailabilityStockService {

	/**
	 * The availability stock data access object 
	 */
	@Autowired
	AvailabilityStockDAO availabilityStockDao;

	/**
	 * @see {@link AvailabilityStockService.getAvailabilityStockById}
	 */
	@Override
	public AvailabilityStock getAvailabilityStockById(int id) {
		return availabilityStockDao.findOne(id);
	}

	/**
	 * @see {@link AvailabilityStockService.getAvailabilityStocks}
	 */
	@Override
	public List<AvailabilityStock> getAvailabilityStocks() {
		return availabilityStockDao.findAll();
	}

	/**
	 * @see {@link AvailabilityStockService.putAvailabilityStock}
	 */
	@Override
	public void putAvailabilityStock(AvailabilityStock availabilityStock) {
		availabilityStockDao.save(availabilityStock);
	}

	/**
	 * @see {@link AvailabilityStockService.postAvailabilityStock}
	 */
	@Override
	public void postAvailabilityStock(AvailabilityStock availabilityStock) {
		availabilityStockDao.save(availabilityStock);
	}

	/**
	 * @see {@link AvailabilityStockService.deleteAvailabilityStock}
	 */
	@Override
	public void deleteAvailabilityStock(AvailabilityStock availabilityStock) {
		availabilityStockDao.delete(availabilityStock);
	}

	/**
	 * @see {@link AvailabilityStockService.getAvailabilityStockByProductId}
	 */
	@Override
	public AvailabilityStock getAvailabilityStockByProductId(int id) {
		return availabilityStockDao.findByProduct_Id(id);
	}
}