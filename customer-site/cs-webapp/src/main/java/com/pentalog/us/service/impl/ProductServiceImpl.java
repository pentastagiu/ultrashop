package com.pentalog.us.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.model.Feature;
import com.pentalog.us.dao.ProductDAO;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductDescription;
import com.pentalog.us.model.ProductFeature;
import com.pentalog.us.model.ProductImagery;
import com.pentalog.us.model.ProductPresentation;
import com.pentalog.us.service.ProductDescriptionService;
import com.pentalog.us.service.ProductFeatureService;
import com.pentalog.us.service.ProductImageryService;
import com.pentalog.us.service.ProductPresentationService;
import com.pentalog.us.service.ProductService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * The product service implementation
 * @author acozma and bpopovici
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	/**
	 * The product data access object 
	 */
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private ProductDescriptionService productDescriptionService;
	
	@Autowired
	private ProductImageryService productImageryService;
	
	@Autowired
	private ProductFeatureService productFeatureService;
	
	@Autowired
	private ProductPresentationService productPresentationService;

	/**
	 * @see {@link ProductService.getProductById}
	 */
	@Override
	public Product getProductById(int id) {
		return productDao.findOne(id);
	}

	/**
	 * @see {@link ProductService.getProducts}
	 */
	@Override
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	/**
	 * @see {@link ProductService.putProduct}
	 */
	@Override
	public void putProduct(Product product) {
		productDao.save(product);
	}

	/**
	 * @see {@link ProductService.postProduct}
	 */
	@Override
	public void postProduct(Product product) {
		productDao.save(product);
	}

	/**
	 * @see {@link ProductService.deleteProduct}
	 */
	@Override
	public void deleteProduct(Product product) {
		productDao.delete(product);
	}
	
    /**
     * @see {@link ProductService.getAllProducts_List}
     */
    @Override
    public List<Product> getAllProducts_List() {
        return productDao.findAll();
    }
    
    /**
     * The solr server instance
     */
    HttpSolrServer solrServer;
	
	@Override
	public void syncProduct() {
		
		ObjectMapper mapper;
		
		Properties cdnProp = new Properties();
		try {
			cdnProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/cdnConfig.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		String baseURL = cdnProp.getProperty("showroom.cdn.baseURL");
		String host = cdnProp.getProperty("showroom.cdn.port");
		String localContent = cdnProp.getProperty("showroom.local.content.dir");
		String URL = baseURL + host + localContent;
		
		List<Product> products = getProducts();
		
		Client client = Client.create();
		 
		String credentials = "{\"username\":\"ultrashop\",\"password\":\"ultrashop\"}";
		
		WebResource webResource = client.resource(URL + "users/signup");
		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, credentials);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
 		
		webResource = client.resource(URL + "users/authentication");
		response = webResource.type("application/json").post(ClientResponse.class, credentials);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		String token = response.getEntity(String.class);
		
		String jsonProducts = "";
		mapper = new ObjectMapper(); 
		try {
			jsonProducts = mapper.writeValueAsString(products);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		webResource = client.resource(URL + "resources/products/sync");
		response = webResource.type("application/json").header("Authorization", token).post(ClientResponse.class, jsonProducts);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		String jsonProductDescriptions = "";
		mapper = new ObjectMapper();
		List<ProductDescription> productDescription = productDescriptionService.getProductDescriptions();
		try {
			jsonProductDescriptions = mapper.writeValueAsString(productDescription);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		webResource = client.resource(URL + "resources/products/description/sync");
		response = webResource.type("application/json").header("Authorization", token).post(ClientResponse.class, jsonProductDescriptions);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		String jsonProductImageries = "[";
		List<ProductImagery> productImageries  = productImageryService.getProductImageries();
		for(ProductImagery pI : productImageries) {
			String jsonProductImageryID = "\"id\":" + pI.getId();
			String jsonProductImageryNumberOfImages = "\"totalImages\":" + pI.getNumberOfImages();
			String jsonProductImageryProduct = "\"product\":";
			mapper = new ObjectMapper();
			try {
				jsonProductImageryProduct += mapper.writeValueAsString(pI.getProduct());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jsonProductImageries += "{" + jsonProductImageryID + "," + jsonProductImageryNumberOfImages + "," + jsonProductImageryProduct + "},";
		}
		jsonProductImageries = jsonProductImageries.substring(0, jsonProductImageries.length()-1);
		jsonProductImageries += "]";
		
		
		
		webResource = client.resource(URL + "resources/products/images/sync");
		response = webResource.type("application/json").header("Authorization", token).post(ClientResponse.class, jsonProductImageries);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		String jsonProductFeatures = "[";
		List<ProductFeature> productFeatures  = productFeatureService.getProductFeatures();
		for(ProductFeature pF : productFeatures) {
			String jsonProductFeatureID = "\"id\":" + pF.getId();
			String jsonProductFeatureName = "\"name\":\"" + pF.getFeature().getName() + "\"";
			String jsonProductFeatureValue = "\"value\":\"";
			String productFeatureValue = pF.getValue();
			String[] jsonProductFeatureValueLines = productFeatureValue.split("\n");
			
			int i;
			for(i = 0; i < jsonProductFeatureValueLines.length - 1 ; i++) {
				jsonProductFeatureValue += jsonProductFeatureValueLines[i];
				if(jsonProductFeatureValueLines.length > 1) {
					jsonProductFeatureValue += "\\n";
				}
			}
			jsonProductFeatureValue += jsonProductFeatureValueLines[i] + "\"";
			String jsonProductFeatureProduct = "\"product\":";
			mapper = new ObjectMapper();
			try {
				jsonProductFeatureProduct += mapper.writeValueAsString(pF.getProduct());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jsonProductFeatures += "{" + jsonProductFeatureID + "," + jsonProductFeatureName + "," + jsonProductFeatureValue + "," + jsonProductFeatureProduct + "},";
		}
		jsonProductFeatures = jsonProductFeatures.substring(0, jsonProductFeatures.length()-1);
		jsonProductFeatures += "]";
		
		
		
		webResource = client.resource(URL + "resources/products/features/sync");
		response = webResource.type("application/json").header("Authorization", token).post(ClientResponse.class, jsonProductFeatures);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		String jsonProductPresentation = "[";
		List<ProductPresentation> productPresentation  = productPresentationService.getProductPresentations();
		for(ProductPresentation pP : productPresentation) {
			String jsonProductPresentationID = "\"id\":" + pP.getId();
			String jsonProductPresentationTitle = "\"title\":\"" + pP.getTitle() + "\"";
			String jsonProductPresentationDescription = "\"description\":\"";
			String jsonProductPresentationSection = "\"sectionNumber\":" + pP.getSection();
			String jsonProductPresentationImageSrc = "\"imageURL\":\"" + pP.getImageSrc() + "\"";
			String productPresentationDescription = pP.getDescription();
			String[] jsonProductPresentationDescriptionLines = productPresentationDescription.split("\n");
			
			int i;
			for(i = 0; i < jsonProductPresentationDescriptionLines.length - 1 ; i++) {
				jsonProductPresentationDescription += jsonProductPresentationDescriptionLines[i];
				if(jsonProductPresentationDescriptionLines.length > 1) {
					jsonProductPresentationDescription += "\\n";
				}
			}
			jsonProductPresentationDescription += jsonProductPresentationDescriptionLines[i] + "\"";
			String jsonProductPresentationProduct = "\"product\":";
			mapper = new ObjectMapper();
			try {
				jsonProductPresentationProduct += mapper.writeValueAsString(pP.getProduct());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jsonProductPresentation += "{" + jsonProductPresentationID + "," + jsonProductPresentationTitle + "," + jsonProductPresentationDescription + "," + jsonProductPresentationSection + "," + jsonProductPresentationImageSrc + "," + jsonProductPresentationProduct + "},";
		}
		jsonProductPresentation = jsonProductPresentation.substring(0, jsonProductPresentation.length()-1);
		jsonProductPresentation += "]";
		
		
		
		webResource = client.resource(URL + "resources/products/featureDescription/sync");
		response = webResource.type("application/json").header("Authorization", token).post(ClientResponse.class, jsonProductPresentation);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	  /**
     * @see {@link ProductService.updateSolrDocs}
     */
    @Override
    public void updateSolrDocs() {
        List<Product> products = getAllProducts_List();
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        SolrInputDocument doc = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 10);
        Timestamp timeStamp = new Timestamp(calendar.getTimeInMillis());
        if (solrServer == null) {
            solrServer = initializeServer();
        }
        try {
            for (Product p : products) {
                if (p.getTimeStamp().compareTo(timeStamp) >= 0) {
                    doc = new SolrInputDocument();
                    doc.addField("id", p.getId());
                    doc.addField("name", p.getName());
                    doc.addField("price", p.getPrice());
                    doc.addField("type", "product");
                    docs.add(doc);
                }
            }

            if (docs.size() > 0) {
                solrServer.add(docs);
                solrServer.commit();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SolrServerException e1) {
            e1.printStackTrace();
        }
    }

    private HttpSolrServer initializeServer() {
        Properties sorlProp = new Properties();
        try {
            sorlProp.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config/solrConfig.properties"));
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        solrServer = new HttpSolrServer(
                sorlProp.getProperty("ultrashop.solr.URL"));
        return solrServer;
    }
    
    /**
     * Method that verifies if a given string is a numeric sequence
     * @param str - the given string
     * @return
     */
    private static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
    
    /**
     * @see {@link ProductService.searchProduct}
     */
    @Override
    public List<Product> searchProduct(String value) {
        List<Product> products = new ArrayList<>();
        Product product = null;
        String queryString = "";
        boolean ok = true;
        if (solrServer == null) {
            solrServer = initializeServer();
        }
        SolrQuery query = new SolrQuery();
        Properties sorlQueryProp = new Properties();
        try {
            sorlQueryProp.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config/solrQueryConfig.properties"));
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        
        queryString = String.format(sorlQueryProp.getProperty("ultrashop.solr.query"), value);
        
        if (isNumeric(value)) {
            queryString += String.format(sorlQueryProp.getProperty("ultrashop.solr.query.price"), value);
        }
        query.setQuery(queryString);
        QueryResponse rsp = null;
        try {
            rsp = solrServer.query(query);
        } catch (SolrServerException e1) {
            e1.printStackTrace();
        }
        SolrDocumentList docsList = rsp.getResults();
        for (SolrDocument doc : docsList) {
            ok = true;
            String type = (String) doc.getFieldValue("type");
            String id = (String) doc.getFieldValue("id");
            if (type.equals("product")) {
                product = getProductById(Integer.parseInt(id));
                products.add(product);
            } else {
                switch (type) {

               default:
                    break;
                }
                if (products.size() == 0) {
                    products.add(product);
                } else {
                    for (Product p : products) {
                        if (product.getId().equals(p.getId()))
                            ok = false;
                    }
                    if (ok == true) {
                        products.add(product);
                    }
                }
            }
        }
        return products;
    }
}