package com.pentalog.sr.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.FeatureDescriptionDAO;
import com.pentalog.sr.model.FeatureDescription;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.service.FeatureDescriptionService;

/**
 * The feature description service implementation
 * @authors acozma and bpopovici
 *
 */
@Service(value = "featureDescriptionService")
public class FeatureDescriptionServiceImpl implements FeatureDescriptionService {
	
	/**
	 * The feature description data access object
	 */
	@Autowired
	private FeatureDescriptionDAO featureDescriptionDao;

	/**
	 * @see {@link FeatureDescriptionService.getFeatureDescriptionsInWrapper}
	 */
	@Override
	public Wrapper<FeatureDescription> getFeatureDescriptionsInWrapper(int id) {
		Wrapper<FeatureDescription> featureDescriptions = new Wrapper<FeatureDescription>();
		featureDescriptions.setList(featureDescriptionDao.findByProduct_IdOrderBySectionNumberAsc(id));
		return featureDescriptions;
	}

	/**
	 * @see {@link FeatureDescriptionService.saveFeatureDescription}
	 */
	@Override
	public FeatureDescription saveFeatureDescription(
			FeatureDescription featureDescription) {
		Date date = new Date();
		featureDescription.setTimeStamp(new Timestamp(date.getTime()));
		return featureDescriptionDao.save(featureDescription);
	}

	/**
	 * @see {@link FeatureDescriptionService.getFeatureDescriptions}
	 */
	@Override
	public List<FeatureDescription> getFeatureDescriptions(int productId) {
		return featureDescriptionDao.findByProduct_IdOrderBySectionNumberAsc(productId);
	}

	/**
	 * @see {@link FeatureDescriptionService.getFeatureById}
	 */
	@Override
	public FeatureDescription getFeatureById(int id) {
		return featureDescriptionDao.findOne(id);
	}
}