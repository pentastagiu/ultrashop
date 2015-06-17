package com.pentalog.sr.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.ProductDescriptionDAO;
import com.pentalog.sr.model.ProductDescription;
import com.pentalog.sr.service.ProductDescriptionService;
import com.pentalog.sr.service.ProductService;

/**
 * The product description service implementation
 * @authors acozma and bpopovici
 *
 */
@Service(value = "productDescriptionService")
public class ProductDescriptionServiceImpl implements ProductDescriptionService {
	/**
	 * The product description data access object
	 */
	@Autowired
	private ProductDescriptionDAO productDescriptionDao;

	/**
	 * The product service
	 */
	@Autowired
	private ProductService productService;


	/**
	 * @see {@link ProductDescriptionService.getProductDescription}
	 */
	@Override
	public ProductDescription getProductDescription(int id) {
		return productDescriptionDao.findByProduct_Id(id);
	}

	/**
	 * @see {@link ProductDescriptionService.saveProductDescription}
	 */
	@Override
	public ProductDescription saveProductDescription(ProductDescription productDescription) {
		Date date = new Date();
		productDescription.setTimeStamp(new Timestamp(date.getTime()));
		return productDescriptionDao.save(productDescription);
	}

	/**
	 * @see {@link ProductDescriptionService.getProductDescriptionById}
	 */
	@Override
	public ProductDescription getProductDescriptionById(int id) {
		return productDescriptionDao.findOne(id);
	}
}