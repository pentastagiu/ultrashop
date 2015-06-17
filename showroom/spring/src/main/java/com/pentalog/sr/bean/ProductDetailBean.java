package com.pentalog.sr.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.pentalog.sr.model.Feature;
import com.pentalog.sr.model.FeatureDescription;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.model.ProductDescription;
import com.pentalog.sr.model.ProductImagery;
import com.pentalog.sr.service.FeatureDescriptionService;
import com.pentalog.sr.service.FeatureService;
import com.pentalog.sr.service.ProductDescriptionService;
import com.pentalog.sr.service.ProductImageryService;
import com.pentalog.sr.service.ProductService;

/**
 * Product details bean
 * @author acozma
 *
 */
@ManagedBean
@RequestScoped
public class ProductDetailBean {

	/**
	 * The product imagery service - contains methods to fetch data regarding product imagery from the DAO layer.
	 */
	@ManagedProperty(value = "#{productImageryService}")
	private ProductImageryService productImageryService;

	/**
	 * The product description service - contains methods to fetch data regarding the product description from the DAO layer.
	 */
	@ManagedProperty(value = "#{productDescriptionService}")
	private ProductDescriptionService productDescriptionService;
	
	/**
	 * The feature description service - contains methods to fetch data regarding a feature description from the DAO layer.
	 */
	@ManagedProperty(value  = "#{featureDescriptionService}")
	private FeatureDescriptionService featureDescriptionService;
	
	/**
	 * The feature service - contains methods to fetch data regarding a feature from the DAO layer.
	 */
	@ManagedProperty(value = "#{featureService}")
	private FeatureService featureService;
	
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	
	/**
	 * The id of the product
	 */
	@ManagedProperty(value = "#{param.productid}")
	private int productId;

	private ProductImagery productImagery;
	private List<String> images;
	private List<Feature> features;
	private List<FeatureDescription> featureDescriptions;
	private ProductDescription productDescription;
	private String imageUrl;
	private Product product;
	
	@PostConstruct
	public void init() {

		if (productId != 0) {
			product = productService.getProductById(productId);
			productImagery = productImageryService
					.getProductImageryByProductId(productId);
			features = featureService.getAllFeatures_List(productId);
			featureDescriptions = featureDescriptionService
					.getFeatureDescriptions(productId);
			productDescription = productDescriptionService
					.getProductDescription(productId);

			images = new ArrayList<String>();

			imageUrl = productImageryService.getImagesUrl() + productId;
			for (int i = 1; i <= productImagery.getTotalImages(); i++) {
				images.add(i + ".jpg");
			}
		}
	}

	public void setProductImageryService(
			ProductImageryService productImageryService) {
		this.productImageryService = productImageryService;
	}
	
	public void setProductDescriptionService(
			ProductDescriptionService productDescriptionService) {
		this.productDescriptionService = productDescriptionService;
	}
	
	public void setFeatureDescriptionService(FeatureDescriptionService featureDescriptionService) {
		this.featureDescriptionService = featureDescriptionService;
	}

	public void setFeatureService(FeatureService featureService) {
		this.featureService = featureService;
	}	

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public List<FeatureDescription> getFeatureDescriptions() {
		return featureDescriptions;
	}

	public void setFeatureDescriptions(List<FeatureDescription> featureDescriptions) {
		this.featureDescriptions = featureDescriptions;
	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

	public ProductImagery getProductImagery() {

		return productImagery;
	}

	public void setProductImagery(ProductImagery productImagery) {
		this.productImagery = productImagery;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}