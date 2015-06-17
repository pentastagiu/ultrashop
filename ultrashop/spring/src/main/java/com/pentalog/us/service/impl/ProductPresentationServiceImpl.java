package com.pentalog.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.ProductPresentationDAO;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductPresentation;
import com.pentalog.us.service.ProductPresentationService;

/**
 * The product presentation service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productPresentationService")
public class ProductPresentationServiceImpl implements ProductPresentationService {

	/**
	 * The product presentation data access object 
	 */
	@Autowired
	ProductPresentationDAO productPresentationDao;

	/**
	 * @see {@link ProductPresentationService.getProductPresentationById}
	 */
	@Override
	public ProductPresentation getProductPresentationById(int id) {
		return productPresentationDao.findOne(id);
	}

	/**
	 * @see {@link ProductPresentationService.getProductPresentations}
	 */
	@Override
	public List<ProductPresentation> getProductPresentations() {
		return productPresentationDao.findAll();
	}

	/**
	 * @see {@link ProductPresentationService.putProductPresentation}
	 */
	@Override
	public void putProductPresentation(ProductPresentation productPresentation) {
		productPresentationDao.save(productPresentation);
	}

	/**
	 * @see {@link ProductPresentationService.postProductPresentation}
	 */
	@Override
	public void postProductPresentation(ProductPresentation productPresentation) {
		productPresentationDao.save(productPresentation);
	}

	/**
	 * @see {@link ProductPresentationService.deleteProductPresentation}
	 */
	@Override
	public void deleteProductPresentation(ProductPresentation productPresentation) {
		productPresentationDao.delete(productPresentation);
	}

	@Override
	public List<ProductPresentation> getProductPresentationByProductId(Product product) {
		return productPresentationDao.findProductPresentationByProduct_IdOrderBySectionAsc(product.getId());
	}
}