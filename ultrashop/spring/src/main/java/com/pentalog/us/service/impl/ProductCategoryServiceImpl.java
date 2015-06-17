package com.pentalog.us.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.ProductCategoryDAO;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductCategory;
import com.pentalog.us.service.ProductCategoryService;

/**
 * The product category service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

	/**
	 * The product category data access object 
	 */
	@Autowired
	ProductCategoryDAO productCategoryDao;

	/**
	 * @see {@link ProductCategoryService.getProductCategoryById}
	 */
	@Override
	public ProductCategory getProductCategoryById(int id) {
		return productCategoryDao.findOne(id);
	}

	/**
	 * @see {@link ProductCategoryService.getProductCategories}
	 */
	@Override
	public List<ProductCategory> getProductCategories() {
		return productCategoryDao.findAll();
	}

	/**
	 * @see {@link ProductCategoryService.putProductCategory}
	 */
	@Override
	public void putProductCategory(ProductCategory productCategory) {
		productCategoryDao.save(productCategory);
	}

	/**
	 * @see {@link ProductCategoryService.postProductCategory}
	 */
	@Override
	public void postProductCategory(ProductCategory productCategory) {
		productCategoryDao.save(productCategory);
	}

	/**
	 * @see {@link ProductCategoryService.deleteProductCategory}
	 */
	@Override
	public void deleteProductCategory(ProductCategory productCategory) {
		productCategoryDao.delete(productCategory);
	}

	@Override
	public List<Product> getProductsByCategoryId(int id) {
		List<Product> products = new ArrayList<Product>();
		for(ProductCategory pc : productCategoryDao.findByCategory_Id(id)) {
			products.add(pc.getProduct());
		}
		return products;
	}
}