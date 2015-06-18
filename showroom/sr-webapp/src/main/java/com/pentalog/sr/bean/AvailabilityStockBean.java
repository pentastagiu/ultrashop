package com.pentalog.sr.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.pentalog.sr.model.AvailabilityStock;
import com.pentalog.sr.service.AvailabilityStockService;
import com.pentalog.sr.service.ProductService;

/**
 * Stock management bean
 * @authors acozma and bpopovici
 *
 */
@ManagedBean
@ViewScoped
public class AvailabilityStockBean {
	
	/**
	 * The stock service - contains methods used to fetch data regarding product stocks from the DAO layer.
	 */
	@ManagedProperty(value = "#{availabilityStockService}")
	private AvailabilityStockService availabilityStockService;
	
	/**
	 * The product service - contains methods used to fetch data regarding products from the DAO layer.
	 */
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	
	/**
	 * List of availability stocks
	 */
	private List<AvailabilityStock> availabilityStocks;
	
	/**
	 * The availability stock currently selected for replenishing.
	 */
	private AvailabilityStock selectedAvailabilityStock;
	
	/**
	 * The amount of new product units to be added to product stocks.
	 */
	private String quantity;
	
	/**
	 * Name of the product to be filtered in the stocks table
	 */
	private String filterName;
	
	/**
	 * Filter products that have a stock greater than a selected minimum stock.
	 */ 
	private String filterMinStock;
	
	/**
	 * Filter products that have a selected stock less than a maximum stock.
	 */
	private String filterMaxStock;
	
	@PostConstruct
	public void init() {
		availabilityStocks = availabilityStockService.getAllAvailabilityStocks_List();
	}

	public void setAvailabilityStockService(
			AvailabilityStockService availabilityStockService) {
		this.availabilityStockService = availabilityStockService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<AvailabilityStock> getAvailabilityStocks() {
		return availabilityStocks;
	}

	public void setAvailabilityStocks(List<AvailabilityStock> availabilityStocks) {
		this.availabilityStocks = availabilityStocks;
	}

	public AvailabilityStock getSelectedAvailabilityStock() {
		return selectedAvailabilityStock;
	}

	public void setSelectedAvailabilityStock(
			AvailabilityStock selectedAvailabilityStock) {
		this.selectedAvailabilityStock = selectedAvailabilityStock;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterMinStock() {
		return filterMinStock;
	}

	public void setFilterMinStock(String filterMinStock) {
		this.filterMinStock = filterMinStock;
	}

	public String getFilterMaxStock() {
		return filterMaxStock;
	}

	public void setFilterMaxStock(String filterMaxStock) {
		this.filterMaxStock = filterMaxStock;
	}

	/**
	 * Method that replenishes the supply of the selected availability stock.
	 */
	public void replenishSupply() {
		availabilityStockService.replenishSupply(selectedAvailabilityStock,Integer.parseInt(quantity));
	}
	
	/**
	 * Navigate to the supplies page.
	 * @return
	 */
	public String supplies() {
		return "supplies?faces-redirect=true";
	}
	
	/**
	 * Method that filters products by product name.
	 */
	public void filterByName() {
		availabilityStocks = availabilityStockService.filterByName(filterName, availabilityStocks);
	}
	
	/**
	 * Method that filters products by stock range.
	 */
	public void filterByStock() {
		availabilityStocks = availabilityStockService.filterByStock(filterMinStock, filterMaxStock, availabilityStocks);
	}
	
	/**
	 * Method that uses all filter methods and sets the filtered list
	 */
	public void filter() {
		availabilityStocks = availabilityStockService.getAllAvailabilityStocks_List();
		filterByName();	
		filterByStock();
	}
}