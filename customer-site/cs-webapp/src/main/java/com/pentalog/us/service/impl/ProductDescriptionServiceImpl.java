package com.pentalog.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.ProductDescriptionDAO;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductDescription;
import com.pentalog.us.service.ProductDescriptionService;

/**
 * The product description service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productDescriptionService")
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

	/**
	 * The product description data access object 
	 */
	@Autowired
	ProductDescriptionDAO productDescriptionDao;

	/**
	 * @see {@link ProductDescriptionService.getProductDescriptionById}
	 */
	@Override
	public ProductDescription getProductDescriptionById(int id) {
		return productDescriptionDao.findOne(id);
	}

	/**
	 * @see {@link ProductDescriptionService.getProductDescriptions}
	 */
	@Override
	public List<ProductDescription> getProductDescriptions() {
		return productDescriptionDao.findAll();
	}

	/**
	 * @see {@link ProductDescriptionService.putProductDescription}
	 */
	@Override
	public void putProductDescription(ProductDescription productDescription) {
		productDescriptionDao.save(productDescription);
	}

	/**
	 * @see {@link ProductDescriptionService.postProductDescription}
	 */
	@Override
	public void postProductDescription(ProductDescription productDescription) {
		productDescriptionDao.save(productDescription);
	}

	/**
	 * @see {@link ProductDescriptionService.deleteProductDescription}
	 */
	@Override
	public void deleteProductDescription(ProductDescription productDescription) {
		productDescriptionDao.delete(productDescription);
	}

	@Override
	public ProductDescription getProductDescriptionByProductId(Product product) {
		return productDescriptionDao.findProductDescriptionByProduct_Id(product.getId());
	}
}