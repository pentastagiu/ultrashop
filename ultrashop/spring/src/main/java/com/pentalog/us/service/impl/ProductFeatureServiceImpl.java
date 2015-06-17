package com.pentalog.us.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.ProductFeatureDAO;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductFeature;
import com.pentalog.us.service.FeatureService;
import com.pentalog.us.service.ProductFeatureService;

/**
 * The product feature service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productFeatureService")
public class ProductFeatureServiceImpl implements ProductFeatureService {

	/**
	 * The product feature data access object 
	 */
	@Autowired
	ProductFeatureDAO productFeatureDao;
	
	@Autowired
	FeatureService featureService;

	/**
	 * @see {@link ProductFeatureService.getProductFeatureById}
	 */
	@Override
	public ProductFeature getProductFeatureById(int id) {
		return productFeatureDao.findOne(id);
	}

	/**
	 * @see {@link ProductFeatureService.getProductFeatures}
	 */
	@Override
	public List<ProductFeature> getProductFeatures() {
		return productFeatureDao.findAll();
	}

	/**
	 * @see {@link ProductFeatureService.putProductFeature}
	 */
	@Override
	public void putProductFeature(ProductFeature productFeature) {
		productFeatureDao.save(productFeature);
	}

	/**
	 * @see {@link ProductFeatureService.postProductFeature}
	 */
	@Override
	public void postProductFeature(ProductFeature productFeature) {
		productFeatureDao.save(productFeature);
	}

	/**
	 * @see {@link ProductFeatureService.deleteProductFeature}
	 */
	@Override
	public void deleteProductFeature(ProductFeature productFeature) {
		productFeatureDao.delete(productFeature);
	}
	
	@Override
	public Map<String, List<ProductFeature>> getProductFeatureByProductId(Product product) {
		Map<String, List<ProductFeature>> productFeatureMap = new LinkedHashMap<String, List<ProductFeature>>();
		List<ProductFeature> productFeatures = productFeatureDao.findProductFeatureByProduct_IdOrderByPriorityAsc(product.getId());
		for(ProductFeature pF : productFeatures) {
			int parent = pF.getFeature().getParent();
			String featureName = featureService.getFeatureById(parent).getName();
			List<ProductFeature> auxProductFeatures = productFeatureMap.get(featureName);
			if(auxProductFeatures == null) {
				auxProductFeatures = new ArrayList<ProductFeature>();
				auxProductFeatures.add(pF);
				productFeatureMap.put(featureName, auxProductFeatures);
			}
			else {
				auxProductFeatures.add(pF);
			}
		}
		return productFeatureMap;
	}
}