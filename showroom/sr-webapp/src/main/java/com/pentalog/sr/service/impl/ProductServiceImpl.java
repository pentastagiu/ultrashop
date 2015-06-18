package com.pentalog.sr.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.ProductDAO;
import com.pentalog.sr.model.Feature;
import com.pentalog.sr.model.FeatureDescription;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.model.ProductDescription;
import com.pentalog.sr.model.ProductImagery;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.service.AvailabilityStockService;
import com.pentalog.sr.service.FeatureDescriptionService;
import com.pentalog.sr.service.FeatureService;
import com.pentalog.sr.service.ProductDescriptionService;
import com.pentalog.sr.service.ProductImageryService;
import com.pentalog.sr.service.ProductService;

/**
 * The product service implementation
 * @authors acozma and bpopovici
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	/**
	 * The product data access object
	 */
	@Autowired
	private ProductDAO productDao;

	/**
	 * The availability stock service
	 */
	@Autowired
	private AvailabilityStockService availabilityStockService;

	/**
	 * The product imagery service
	 */
	@Autowired
	private ProductImageryService productImageryService;

	/**
	 * The feature description service
	 */
	@Autowired
	private FeatureDescriptionService featureDescriptionService;

	/**
	 * The product description service
	 */
	@Autowired
	private ProductDescriptionService productDescriptionService;

	/**
	 * The feature service
	 */
	@Autowired
	private FeatureService featureService;

	/**
	 * The solr server instance
	 */
	HttpSolrServer solrServer;

	/**
	 * @see {@link ProductService.getAllProducts_Wrapper}
	 */
	@Override
	public Wrapper<Product> getAllProducts_Wrapper() {
		Wrapper<Product> products = new Wrapper<Product>();
		products.setList(productDao.findAll());
		return products;
	}

	/**
	 * @see {@link ProductService.getAllProducts_List}
	 */
	@Override
	public List<Product> getAllProducts_List() {
		return productDao.findAll();
	}

	/**
	 * @see {@link ProductService.getProductById}
	 */
	@Override
	public Product getProductById(int id) {
		return this.productDao.findOne(id);
	}

	/**
	 * @see {@link ProductService.getProductByName}
	 */
	@Override
	public Product getProductByName(String name) {
		return this.productDao.findByName(name);
	}

	/**
	 * @see {@link ProductService.registerProduct}
	 */
	@Override
	public Product registerProduct(Product product) {
		Date date = new Date();
		product.setTimeStamp(new Timestamp(date.getTime()));
		productDao.save(product);
		availabilityStockService.registerStock(product);

		// Create a product image location for the new product
		ProductImagery productImagery = new ProductImagery();
		productImagery.setProduct(product);
		productImageryService.saveProductImagery(productImagery);
		return product;
	}

	/**
	 * @see {@link ProductService.modifyProduct}
	 */
	@Override
	public Product modifyProduct(Product product) {
		Date date = new Date();
		product.setTimeStamp(new Timestamp(date.getTime()));
		productDao.save(product);
		return product;
	}

	/**
	 * @see {@link ProductService.filterByName}
	 */
	@Override
	public List<Product> filterByName(String filterName, List<Product> products) {
		List<Product> filterProducts;
		if (!filterName.isEmpty()) {
			filterProducts = new ArrayList<Product>();
			for (Product p : products) {
				if (p.getName().toLowerCase()
						.contains(filterName.toLowerCase())) {
					filterProducts.add(p);
				}
			}
			products = filterProducts;
		}
		return products;
	}

	/**
	 * @see {@link ProductService.filterByPrice}
	 */
	@Override
	public List<Product> filterByPrice(String filterMinPrice,
			String filterMaxPrice, List<Product> products) {
		List<Product> filterProducts;
		if (!filterMinPrice.isEmpty() && isNumeric(filterMinPrice)) {
			filterProducts = new ArrayList<Product>();
			double minPrice = Double.parseDouble(filterMinPrice);
			for (Product p : products) {
				if (p.getPrice() >= minPrice) {
					filterProducts.add(p);
				}
			}
			products = filterProducts;
		}
		if (!filterMaxPrice.isEmpty() && isNumeric(filterMaxPrice)) {
			filterProducts = new ArrayList<Product>();
			double maxPrice = Double.parseDouble(filterMaxPrice);
			for (Product p : products) {
				if (p.getPrice() <= maxPrice) {
					filterProducts.add(p);
				}
			}
			products = filterProducts;
		}
		return products;
	}

	/**
	 * Method that verifies if a given string is a numeric sequence
	 * @param str -	the given string
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
		
		queryString = String.format(sorlQueryProp.getProperty("showroom.solr.query"), value);
		
		if (isNumeric(value)) {
			queryString += String.format(sorlQueryProp.getProperty("showroom.solr.query.price"), value);
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
				case "featureDescription":
					FeatureDescription featureDescription = featureDescriptionService
							.getFeatureById(Integer.parseInt(id));
					product = featureDescription.getProduct();
					break;
				case "description":
					ProductDescription productDescription = productDescriptionService
							.getProductDescriptionById(Integer.parseInt(id));
					product = productDescription.getProduct();
					break;
				case "feature":
					Feature feature = featureService.getById(Integer
							.parseInt(id));
					product = feature.getProduct();
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

	/**
	 * @see {@link ProductService.updateSolrDocs}
	 */
	@Override
	public void updateSolrDocs() {
		List<Product> products = getAllProducts_List();
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		SolrInputDocument doc = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
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

				ProductDescription pd = productDescriptionService
						.getProductDescription(p.getId());
				if (pd != null && pd.getTimeStamp().compareTo(timeStamp) >= 0) {
					doc = new SolrInputDocument();
					doc.addField("id", pd.getId());
					doc.addField("description", pd.getDescription());
					doc.addField("type", "productDescription");
					docs.add(doc);
				}
				List<FeatureDescription> featureDescriptions = featureDescriptionService
						.getFeatureDescriptions(p.getId());
				for (FeatureDescription fd : featureDescriptions) {
					if (fd.getTimeStamp().compareTo(timeStamp) >= 0) {
						doc = new SolrInputDocument();
						doc.addField("id", fd.getId());
						doc.addField("title", fd.getTitle());
						doc.addField("featureDescription", fd.getDescription());
						doc.addField("type", "featureDescription");
						docs.add(doc);
					}
				}
				List<Feature> features = featureService.getAllFeatures_List(p
						.getId());
				for (Feature f : features) {
					if (f.getTimeStamp().compareTo(timeStamp) >= 0) {
						doc = new SolrInputDocument();
						doc.addField("id", f.getId());
						doc.addField("featureName", f.getName());
						doc.addField("featureValue", f.getValue());
						doc.addField("type", "feature");
						docs.add(doc);
					}
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
				sorlProp.getProperty("showroom.solr.URL"));
		return solrServer;
	}
	
	/**
	 * @see {@link ProductService.getTopProducts}
	 */
	@Override
	public List<Product> getTopProducts(Integer offset, Integer limit) {
		List<Product> wrapper= productDao.findAndLimit(offset,limit);
		return wrapper;
	}

	/**
	 * @see {@link ProductService.getNumberOfProducts}
	 */
	@Override
	public Integer getNumberOfProducts() {
		return productDao.getNumberOfProducts();
	}

	@Override
	public void syncProducts(List<Product> products) {
		for(Product p : products) {
			Product product = getProductById(p.getId());
			if(product == null) {
				Date date = new Date();
				p.setTimeStamp(new Timestamp(date.getTime()));
				product = productDao.save(p);
				availabilityStockService.registerStock(product);
			}
		}
	}

	@Override
	public void syncProductsDescription(List<ProductDescription> productsDescriptions) {
		for(ProductDescription pD : productsDescriptions) {
			productDescriptionService.saveProductDescription(pD);
		}
	}

	@Override
	public void syncProductsImagery(List<ProductImagery> productsImageries) {
		for(ProductImagery pI : productsImageries) {
			productImageryService.saveProductImagery(pI);
		}
	}

	@Override
	public void syncProductsFeature(List<Feature> features) {
		for(Feature f : features) {
			featureService.saveProductFeature(f);
		}
	}

	@Override
	public void syncProductsFeatureDescription(List<FeatureDescription> featureDescriptions) {
		for(FeatureDescription fD : featureDescriptions) {
			featureDescriptionService.saveFeatureDescription(fD);
		}
	}
}