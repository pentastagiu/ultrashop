package com.pentalog.us.job;

import org.springframework.beans.factory.annotation.Autowired;
import com.pentalog.us.service.OrderService;
import com.pentalog.us.service.ProductService;

public class JobFactory {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	public void syncProducts() {
		productService.syncProduct();
	}
	
	public void syncOrders() {
		orderService.syncOrdes();
	}
	
    /**
     * Method used for updating solr document
     */
    public void updateSolrDocs() {
        productService.updateSolrDocs();
    }
}