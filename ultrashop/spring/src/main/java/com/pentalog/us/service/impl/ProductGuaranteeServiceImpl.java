package com.pentalog.us.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.ProductGuaranteeDAO;
import com.pentalog.us.model.ProductGuarantee;
import com.pentalog.us.service.ProductGuaranteeService;

/**
 * The product guarantee service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productGuaranteeService")
public class ProductGuaranteeServiceImpl implements ProductGuaranteeService {
	
	/**
	 * The product guarantee data access object 
	 */
	@Autowired
	private ProductGuaranteeDAO productGuaranteeDAO;

	/**
	 * @see {@link ProductGuaranteeService.getProductGuarantees}
	 */
	@Override
	public List<ProductGuarantee> getProductGuarantees() {
		return productGuaranteeDAO.findAll();
	}

	/**
	 * @see {@link ProductGuaranteeService.ProductGuarantee}
	 */
	@Override
	public ProductGuarantee getProductGuaranteeById(int id) {
		return productGuaranteeDAO.findOne(id);
	}

	/**
	 * @see {@link ProductGuaranteeService.postProductGuarantee}
	 */
	@Override
	public void postProductGuarantee(ProductGuarantee productGuarantee) {
		productGuaranteeDAO.save(productGuarantee);
	}

	/**
	 * @see {@link ProductGuaranteeService.putProductGuarantee}
	 */
	@Override
	public void putProductGuarantee(ProductGuarantee ProductGuarantee) {
		productGuaranteeDAO.save(ProductGuarantee);
	}

	/**
	 * @see {@link ProductGuaranteeService.deleteProductGuarantee}
	 */
	@Override
	public void deleteProductGuarantee(ProductGuarantee ProductGuarantee) {
		productGuaranteeDAO.delete(ProductGuarantee);
	}
}