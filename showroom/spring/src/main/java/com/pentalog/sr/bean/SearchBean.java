package com.pentalog.sr.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.service.ProductImageryService;
import com.pentalog.sr.service.ProductService;

/**
 * Search feature bean based on solr search engine
 * @author acozma
 *
 */
@ManagedBean
@ViewScoped
public class SearchBean {
	
	/**
	 *  The product service - contains methods used to fetch data regarding products from the DAO layer.
	 */
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	
	/**
	 * The product imagery service - contains methods to fetch data regarding product imagery from the DAO layer
	 */
	@ManagedProperty(value = "#{productImageryService}")
	private ProductImageryService productImageryService;
	
	private Product selectedProduct;
	
	/**
	 * The search result list
	 */
	private List<Product> products;
	
	/**
	 * The value that will be searched
	 */
	private String value;
	
	@PostConstruct
	public void init() {
		products = new ArrayList<>();
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setProductImageryService(ProductImageryService productImageryService) {
		this.productImageryService = productImageryService;
	}
	
	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Navigate to product detail page
	 * @return
	 */
	public String showDetails(){
		return "product-detail?faces-redirect=true productid=" + selectedProduct.getId();
	}
	
	/**
	 * Navigate to product search page
	 * @return
	 */
	public String searchPage(){
		return "search?faces-redirect=true";
	}
	
	/**
	 * Method that searches for products that contain the value in their description or features
	 */
	public void searchProduct() {
		products = productService.searchProduct(value);
	}
	
	/**
	 * Method that returns the url for the product front image
	 * @param product - the product that matches the query search
	 * @return
	 */
	public String getImage(Product product){
		return productImageryService.getImagesUrl()+"/"+product.getId()+"/1.jpg";
	}
}