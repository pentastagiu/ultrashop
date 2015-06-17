package com.pentalog.us.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.DeliveryStockDAO;
import com.pentalog.us.model.DeliveryStock;
import com.pentalog.us.service.DeliveryStockService;

/**
 * The delivery stock service implementation
 * @author acozma and bpopovici
 *
 */
@Service("deliveryStockService")
public class DeliveryStockServiceImpl implements DeliveryStockService {

	/**
	 * The delivery stock data access object 
	 */
	@Autowired
	private DeliveryStockDAO deliveryStockDAO;

	/**
	 * @see {@link DeliveryStockService.getDeliveryStocks}
	 */
	@Override
	public List<DeliveryStock> getDeliveryStocks() {
		return deliveryStockDAO.findAll();
	}

	/**
	 * @see {@link DeliveryStockService.getDeliveryStockById}
	 */
	@Override
	public DeliveryStock getDeliveryStockById(int id) {
		return deliveryStockDAO.findOne(id);
	}

	/**
	 * @see {@link DeliveryStockService.postDeliveryStock}
	 */
	@Override
	public void postDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockDAO.save(deliveryStock);
	}

	/**
	 * @see {@link DeliveryStockService.putDeliveryStock}
	 */
	@Override
	public void putDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockDAO.save(deliveryStock);
	}

	/**
	 * @see {@link DeliveryStockService.deleteDeliveryStock}
	 */
	@Override
	public void deleteDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockDAO.delete(deliveryStock);
	}
}