package com.pentalog.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.ProductImageryDAO;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductImagery;
import com.pentalog.us.service.ProductImageryService;

/**
 * The product imagery service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productImageryService")
public class ProductImageryServiceImpl implements ProductImageryService {

	/**
	 * The product imagery data access object 
	 */
	@Autowired
	ProductImageryDAO productImageryDao;

	/**
	 * @see {@link ProductImageryService.getProductImageryById}
	 */
	@Override
	public ProductImagery getProductImageryById(int id) {
		return productImageryDao.findOne(id);
	}

	/**
	 * @see {@link ProductImageryService.getProductImageries}
	 */
	@Override
	public List<ProductImagery> getProductImageries() {
		return productImageryDao.findAll();
	}

	/**
	 * @see {@link ProductImageryService.putProductImagery}
	 */
	@Override
	public void putProductImagery(ProductImagery productImagery) {
		productImageryDao.save(productImagery);
	}

	/**
	 * @see {@link ProductImageryService.postProductImagery}
	 */
	@Override
	public void postProductImagery(ProductImagery productImagery) {
		productImageryDao.save(productImagery);
	}

	/**
	 * @see {@link ProductImageryService.deleteProductImagery}
	 */
	@Override
	public void deleteProductImagery(ProductImagery productImagery) {
		productImageryDao.delete(productImagery);
	}

	@Override
	public ProductImagery getProductImageryByProductId(Product product) {
		return productImageryDao.findProductImageryByProduct_Id(product.getId());
	}
}