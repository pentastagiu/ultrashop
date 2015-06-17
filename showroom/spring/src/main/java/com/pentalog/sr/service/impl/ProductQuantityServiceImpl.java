package com.pentalog.sr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.ProductQuantityDAO;
import com.pentalog.sr.model.ProductQuantity;
import com.pentalog.sr.service.ProductQuantityService;

@Service("productQuantityService")
public class ProductQuantityServiceImpl implements ProductQuantityService {

	@Autowired
	private ProductQuantityDAO productQuantityDAO;
	
	@Override
	public List<ProductQuantity> getProductQuantity() {
		return productQuantityDAO.findAll();
	}

	@Override
	public ProductQuantity saveProductQuantity(ProductQuantity productQuantity) {
		return productQuantityDAO.save(productQuantity);
	}
}