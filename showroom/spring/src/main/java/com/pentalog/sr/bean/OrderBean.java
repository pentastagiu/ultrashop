package com.pentalog.sr.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.pentalog.sr.model.DeliveryStock;
import com.pentalog.sr.model.Order;
import com.pentalog.sr.model.Order.Status;
import com.pentalog.sr.service.DeliveryStockService;
import com.pentalog.sr.service.OrderService;

/**
 * Orders management bean
 * @authors acozma and bpopovici
 *
 */
@ManagedBean
@ViewScoped
public class OrderBean {

	/**
	 *  The order service - contains methods used to fetch data regarding orders from the DAO layer.
	 */
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	
	/**
	 *  The delivery stock service - contains methods used to fetch data regarding deliveries from the DAO layer.
	 */
	@ManagedProperty(value = "#{deliveryStockService}")
	private DeliveryStockService deliveryStockService;
	
	/**
	 * The list of all registered orders
	 */
	private List<Order> orders;
	
	/**
	 * The currently selected order
	 */
	private Order selectedOrder;
	
	/**
	 * The list of all delivery stocks
	 */
	private List<DeliveryStock> deliveryStocks;
	
	/**
	 * Filter criteria 
	 * filterMinDate    -	Filter orders by oldest registration date
	 * filterMaxDate    -	Filter orders by most recent registration date
	 * filterMinQuantum -	Filter orders by minimum payment
	 * filterMaxQuantum -	Filter orders by maximum payment	
	 */
	private Date filterMinDate;
	private Date filterMaxDate;
	private String filterCustomerId;
	private String filterMinQuantum;
	private String filterMaxQuantum;
	
	/**
	 * List of all possible statuses 
	 */
	private List<Status> statuses;
	
	/**
	 * Filter orders by selected statuses
	 */
	private List<String> selectedStatuses;

	@PostConstruct
	public void init() {
		orders = orderService.getAllOrders_List();
		statuses = new ArrayList<Status>();
		statuses.add(Status.PLACED);
		statuses.add(Status.WAITING_ARRIVAL);
		statuses.add(Status.ARRIVED);
		statuses.add(Status.READY_FOR_PICKUP);
		statuses.add(Status.DELIVERED);
		statuses.add(Status.CANCELED);
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setDeliveryStockService(DeliveryStockService deliveryStockService) {
		this.deliveryStockService = deliveryStockService;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Order getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Order selectedOrder) {
		this.selectedOrder = selectedOrder;
	}
	
	public List<DeliveryStock> getDeliveryStocks() {
		return deliveryStocks;
	}

	public void setDeliveryStocks(List<DeliveryStock> deliveryStocks) {
		this.deliveryStocks = deliveryStocks;
	}

	public Date getFilterMinDate() {
		return filterMinDate;
	}

	public void setFilterMinDate(Date filterMinDate) {
		this.filterMinDate = filterMinDate;
	}

	public Date getFilterMaxDate() {
		return filterMaxDate;
	}

	public void setFilterMaxDate(Date filterMaxDate) {
		this.filterMaxDate = filterMaxDate;
	}

	public String getFilterCustomerId() {
		return filterCustomerId;
	}

	public void setFilterCustomerId(String filterCustomerId) {
		this.filterCustomerId = filterCustomerId;
	}

	public String getFilterMinQuantum() {
		return filterMinQuantum;
	}

	public void setFilterMinQuantum(String filterMinQuantum) {
		this.filterMinQuantum = filterMinQuantum;
	}

	public String getFilterMaxQuantum() {
		return filterMaxQuantum;
	}

	public void setFilterMaxQuantum(String filterMaxQuantum) {
		this.filterMaxQuantum = filterMaxQuantum;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	
	public List<String> getSelectedStatuses() {
		return selectedStatuses;
	}

	public void setSelectedStatuses(List<String> selectedStatuses) {
		this.selectedStatuses = selectedStatuses;
	}

	public void getDeliveryStocksBySelectedProduct() {
		deliveryStocks = deliveryStockService.getDeliveryStocksByOrderId(selectedOrder.getId());
	}
	
	/**
	 * Method that updates status to READY_FOR_PICKUP when the delivery is ready.
	 */
	public void setReadyStatus() {	
		deliveryStockService.updateDeliveryStocks(deliveryStocks);
		orderService.updateStatus(selectedOrder, Status.READY_FOR_PICKUP);
	}
	
	/**
	 * Method that updates status to DELIVERED when the delivery is taken by client.
	 */
	public void setDeliveredStatus() {	
		orderService.updateStatus(selectedOrder, Status.DELIVERED);
	}
	
	/**
	 * Method that updates status to CANCELED when an order is canceled.
	 */
	public void setCanceledStatus() {
		orderService.updateStatus(selectedOrder, Status.CANCELED);
	}

	/**
	 * Method that sends a mail to the customer with the warranty and order bill.
	 */
	public void sendDocuments() {
		orderService.sendDocuments(selectedOrder);
	}
	
	/**
	 * Date converter - converts date from Date to String format
	 * @param date -	The date to be converted
	 * @return
	 */
	public String convertDate(Date date) {
		if (date != null) {
			return new SimpleDateFormat("dd/MM/yyyy").format(date);
		}
		return null;
	}
	
	/**
	 * Method that filters orders by customer id.
	 */
	public void filterByCustomerId() {
		orders = orderService.filterByCustomerId(filterCustomerId, orders);
	}
	
	/**
	 * Method that filters orders by date.
	 */
	public void filterByDate() {
		orders = orderService.filterByDate(filterMinDate, filterMaxDate, orders);
	}
	
	/**
	 * Method that filters orders by payment.
	 */
	public void filterByQuantum() {
		orders = orderService.filterByQuantum(filterMinQuantum, filterMaxQuantum, orders);
	}
	
	/**
	 * Method that filters orders by current status
	 */
	public void filterByStatus() {
		orders = orderService.filterByStatus(selectedStatuses, orders);
	}
	
	/**
	 * Method that uses all filter methods and sets the filtered list 
	 */
	public void filter() {
		orders = orderService.getAllOrders_List();
		filterByCustomerId();	
		filterByDate();
		filterByQuantum();
		filterByStatus();
	}

	/**
	 * Navigate to the orders page
	 * @return
	 */
	public String page() {  
      return "orders?faces-redirect=true";   
    }
}