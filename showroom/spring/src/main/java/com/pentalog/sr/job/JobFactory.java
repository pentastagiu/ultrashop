package com.pentalog.sr.job;

import org.springframework.beans.factory.annotation.Autowired;
import com.pentalog.sr.service.OrderService;
import com.pentalog.sr.service.ProductService;

/**
 * Job management class
 * @author acozma and bpopovici
 *
 */
public class JobFactory {
	
	/**
	 *  The order service - contains methods used to fetch data regarding orders from the DAO layer.
	 */
	@Autowired
	private OrderService orderService;
	
	/**
	 *  The product service - contains methods used to fetch data regarding products from the DAO layer.
	 */
	@Autowired
	private ProductService productService;
	
	/**
	 * Method used for sending notification mail for orders that are ready.
	 */
	public void sendMailForReadyOrder() {
		orderService.sendMailForReadyOrder();
	}
	
	/**
	 * Method used for updating status for orders 
	 */
	public void updateStatusForWaitingOrders() {
		orderService.updateStatusForWaitingOrders();
	}
	
	/**
	 *  Method used for updating solr document
	 */
	public void updateSolrDocs() {
		productService.updateSolrDocs();
	}
	
	public void  updateStatusForPlacedOrders()
	{
		orderService.updateStatusForPlacedOrders();
	}
}