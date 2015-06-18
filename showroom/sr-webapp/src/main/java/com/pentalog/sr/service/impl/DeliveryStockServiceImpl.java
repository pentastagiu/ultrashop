package com.pentalog.sr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.DeliveryStockDAO;
import com.pentalog.sr.model.DeliveryStock;
import com.pentalog.sr.service.DeliveryStockService;

/**
 * The delivery stock service implementation
 * @authors acozma and bpopovici
 *
 */
@Service("deliveryStockService")
public class DeliveryStockServiceImpl implements DeliveryStockService{
	/**
	 * The delivery stock data access object
	 */
	@Autowired
	private DeliveryStockDAO deliveryStockDao;
	
	/**
	 * @see {@link DeliveryStockService.getSerialByOrderIdAndProductId}
	 */
	@Override
	public List<DeliveryStock> getSerialByOrderIdAndProductId(int order_Id, int product_Id) {
		return deliveryStockDao.findByOrder_IdAndProduct_Id(order_Id, product_Id);
	}
	
	/**
	 * @see {@link DeliveryStockService.getDeliveryStockByOrderIdAndProductId}
	 */
	@Override
	public List<DeliveryStock> getDeliveryStockByOrderIdAndProductId(int order_Id, int product_Id) {
		return deliveryStockDao.findByOrder_IdAndProduct_Id(order_Id, product_Id);
	}

	/**
	 * @see {@link DeliveryStockService.saveDeliveryStock}
	 */
	@Override
	public void saveDeliveryStock(DeliveryStock deliveryStock) {
		this.deliveryStockDao.save(deliveryStock);
	}

	/**
	 * @see {@link DeliveryStockService.getDeliveryStocksByOrderId}
	 */
	@Override
	public List<DeliveryStock> getDeliveryStocksByOrderId(int order_Id) {
		return deliveryStockDao.findByOrder_Id(order_Id);
	}

	/**
	 * @see {@link DeliveryStockService.updateDeliveryStocks}
	 */
	@Override
	public void updateDeliveryStocks(List<DeliveryStock> deliveryStocks) {
		for (DeliveryStock ds : deliveryStocks) {
			this.deliveryStockDao.save(ds);
		}
	}

	/**
	 * @see {@link DeliveryStockService.deleteDeliveryStock}
	 */
	@Override
	public void deleteDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockDao.delete(deliveryStock);
	}
}