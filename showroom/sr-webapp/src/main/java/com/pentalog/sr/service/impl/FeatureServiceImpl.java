package com.pentalog.sr.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.FeatureDAO;
import com.pentalog.sr.model.Feature;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.service.FeatureService;
import com.pentalog.sr.service.ProductService;

/**
 * The feature service implementation
 * @authors acozma and bpopovici
 *
 */
@Service(value="featureService")
public class FeatureServiceImpl implements FeatureService {

    /**
     * The feature data access object
     */
    @Autowired
    private FeatureDAO featureDao;
    
    /**
     * The product service
     */
    @Autowired
    private ProductService productService;
    
    /**
     * @see {@link FeatureService.getAllFeatures_Wrapper}
     */
    @Override
    public Wrapper<Feature> getAllFeatures_Wrapper(int id) {
        Wrapper<Feature> features = new Wrapper<Feature>();
        features.setList(featureDao.findByProduct_Id(id));
        return features;
    }

    /**
     * @see {@link FeatureService.getAllFeatures_List}
     */
    @Override
    public List<Feature> getAllFeatures_List(int id) {
        return featureDao.findByProduct_Id(id);
    }

    /**
     * @see {@link FeatureService.saveProductFeature}
     */
    @Override
    public Feature saveProductFeature(Feature productFeature) {
        Date date = new Date();
        productFeature.setTimeStamp(new Timestamp(date.getTime()));
        return featureDao.save(productFeature);
    }

    /**
     * @see {@link FeatureService.getById}
     */
    @Override
    public Feature getById(int id) {
        return featureDao.findOne(id);
    }
}