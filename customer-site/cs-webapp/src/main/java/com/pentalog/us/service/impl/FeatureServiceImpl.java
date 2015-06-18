package com.pentalog.us.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.FeatureDAO;
import com.pentalog.us.model.Feature;
import com.pentalog.us.service.FeatureService;

/**
 * The feature service implementation
 * @author acozma and bpopovici
 *
 */
@Service("featureService")
public class FeatureServiceImpl implements FeatureService {

	/**
	 * The feature data access object 
	 */
	@Autowired
	FeatureDAO featureDao;

	/**
	 * @see {@link FeatureService.getFeatureById}
	 */
	@Override
	public Feature getFeatureById(int id) {
		return featureDao.findOne(id);
	}

	/**
	 * @see {@link FeatureService.getFeatures}
	 */
	@Override
	public List<Feature> getFeatures() {
		return featureDao.findAll();
	}

	/**
	 * @see {@link FeatureService.putFeature}
	 */
	@Override
	public void putFeature(Feature feature) {
		featureDao.save(feature);
	}

	/**
	 * @see {@link FeatureService.postFeature}
	 */
	@Override
	public void postFeature(Feature feature) {
		featureDao.save(feature);
	}

	/**
	 * @see {@link FeatureService.deleteFeature}
	 */
	@Override
	public void deleteFeature(Feature feature) {
		featureDao.delete(feature);
	}
}