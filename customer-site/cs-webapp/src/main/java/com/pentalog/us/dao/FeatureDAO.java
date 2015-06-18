package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.Feature;

/**
 * The feature data access layer
 * @authors acozma and bpopovici
 *
 */
public interface FeatureDAO extends JpaRepository<Feature, Integer> {
}