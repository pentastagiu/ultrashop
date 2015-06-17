package com.pentalog.us.ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pentalog.us.model.Feature;
import com.pentalog.us.service.FeatureService;

/**
 * Feature web services
 * @authors acozma and bpopovici
 *
 */
@Component
@Path("/features")
public class FeatureWS {

	/**
	 * Feature service
	 */
	@Autowired
	FeatureService featureService;

	/**
	 * Web service that get features
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Feature> getFeatures() {
		return featureService.getFeatures();
	}
	
	/**
	 * Web service that get feature by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Feature getFeatureById(@PathParam("id") int id)
	{
		return featureService.getFeatureById(id);
	}
	
	/**
	 * Web service that post feature
	 * @param order
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postFeature(Feature feature) {
		featureService.postFeature(feature);
	}
	
	/**
	 * Web service that put feature
	 * @param order
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putFeature(Feature feature) {
		featureService.putFeature(feature);
	}
	
	/**
	 * Web service that delete feature
	 * @param order
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteFeature(Feature feature) {
		featureService.deleteFeature(feature);
	}
}