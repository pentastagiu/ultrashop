package com.pentalog.sr.service.impl;

import java.io.IOException;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.ProductImageryDAO;
import com.pentalog.sr.model.ProductImagery;
import com.pentalog.sr.service.ProductImageryService;
import com.pentalog.sr.service.ProductService;

/**
 * The product imagery service implementation
 * @author acozma
 *
 */
@Service("productImageryService")
public class ProductImageryServiceImpl implements ProductImageryService {

	/**
	 * The product imagery data access object
	 */
	@Autowired
	private ProductImageryDAO productImageryDao;
	
	/**
	 * The product service
	 */
	@Autowired
	private ProductService productService;

	/**
	 * @see {@link ProductImageryService.getProductImageryByProductId}
	 */
	@Override
	public ProductImagery getProductImageryByProductId(int id) {
		return productImageryDao.findByProduct_Id(productService.getProductById(id).getId());
	}
	
	/**
	 * @see {@link ProductImageryService.saveProductImagery}
	 */
	@Override
	public ProductImagery saveProductImagery(ProductImagery productImagery) {
		return productImageryDao.save(productImagery);
	}

	/**
	 * @see {@link ProductImageryService.getImagesUrl}
	 */
	@Override
	public String getImagesUrl() {
		Properties cdnProp = new Properties();

		try {
			cdnProp.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config/cdnConfig.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		return cdnProp.getProperty("showroom.cdn.baseURL") + cdnProp.getProperty("showroom.local.content.dir");
	}
	
}