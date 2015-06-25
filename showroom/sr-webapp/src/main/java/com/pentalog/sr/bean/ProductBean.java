package com.pentalog.sr.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.service.ProductImageryService;
import com.pentalog.sr.service.ProductService;

/**
 * Product management bean
 * @author acozma
 *
 */
@ManagedBean
@ViewScoped
public class ProductBean {

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
	 * List of all products
	 */
	private List<Product> products;
	
	
	/**
	 * Filter criteria 
	 * filterName        -	Filter products by product name
	 * filterMinPrice    -	Filter products by minimum price
	 * filterMaxPrice    -	Filter products by maximum price
	 */
	private String filterName;
	private String filterMinPrice;
	private String filterMaxPrice;


	@PostConstruct
	public void init() {
		products = productService.getAllProducts_List();
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
	
	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
		
	public String getFilterMinPrice() {
		return filterMinPrice;
	}

	public void setFilterMinPrice(String filterMinPrice) {
		this.filterMinPrice = filterMinPrice;
	}

	public String getFilterMaxPrice() {
		return filterMaxPrice;
	}

	public void setFilterMaxPrice(String filterMaxPrice) {
		this.filterMaxPrice = filterMaxPrice;
	}

	/**
	 * Method that filters products by product name.
	 */
	public void filterByName() {
		products = productService.filterByName(filterName, products);
	}
	
	/**
	 * Method that filters products by product price range.
	 */
	public void filterByPrice() {
		products = productService.filterByPrice(filterMinPrice, filterMaxPrice, products);
	}
	
	/**
	 * Method that uses all filter methods and sets the filtered list.
	 */
	public void filter() {
		products = productService.getAllProducts_List();
		filterByName();	
		filterByPrice();
	}
	
	/**
	 * Navigate to products page
	 * @return
	 */
	public String page() {
		return "products?faces-redirect=true";
	}
}