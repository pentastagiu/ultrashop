package com.pentalog.sr.controller;

import java.util.List;
import com.pentalog.sr.model.DeliveryStock;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.model.Order;
import com.pentalog.sr.model.Order.Status;
import com.pentalog.sr.service.DeliveryStockService;
import com.pentalog.sr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for web  services regarding orders operations
 * @authors acozma and bpopovici
 *
 */
@Controller
@RequestMapping("/resources/orders")
public class OrderController {

	/**
	 *  The order service - contains methods used to fetch data regarding orders from the DAO layer.
	 */
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DeliveryStockService deliveryStockService;
	
	/**
	 * Web service that retrieves all of the orders using the service provided by orderService. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Wrapper<Order> getAllOrders() {
		return orderService.getAllOrders_Wrapper();
	}
	
	/**
	 * Web service that retrieves an order by its id using the service provided by orderService. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 * @param id -	The order id
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Order getOrderById(@PathVariable int id) {
		return this.orderService.getOrderById(id);
	}
	
	/**
	 * Web service that persists an order in the database using the service provided by orderService. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 * @param order -	the order that is saved in the database
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Order registerOrder(@RequestBody Order order) {
		return orderService.registerOrder(order);	
	}
	
	/**
	 * Web service that retrieves a list of orders between a superior and inferior limit, using the service provided by orderService. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value = "/{offset}/{limit}",method = RequestMethod.GET)
	public @ResponseBody List<Order> getTopOrders(@PathVariable Integer offset,@PathVariable Integer limit) {
		return orderService.getTopOrders(offset,limit);
	}
	
	/**
	 * Web service that counts the total number of orders using the service provided by orderService. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/total",method = RequestMethod.GET)
	public @ResponseBody String getNumberOfOrders() {
		return orderService.getNumberOfOrders().toString();
	}
	
	/**
	 * Web service that sends a mail to the customer with the warranty and order bill. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/send",method = RequestMethod.POST)
	public @ResponseBody void sendDocuments(@RequestBody Order order) {
		orderService.sendDocuments(order);
	}
	
	/**
	 * Web service that updates status to CANCELED when an order is canceled. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public @ResponseBody void setCanceledStatus(@RequestBody Order order) {
		orderService.updateStatus(order, Status.CANCELED);
	}
	
	/**
	 * Web service that updates DeliveryStocks with product serial. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/serial",method = RequestMethod.POST)
	public @ResponseBody void setSerial(@RequestBody List<DeliveryStock> deliveryStocks) {
		deliveryStockService.updateDeliveryStocks(deliveryStocks);
	}
	
	/**
	 * Web service that updates status to READY_FOR_PICKUP when the delivery is ready. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/ready",method = RequestMethod.POST)
	public @ResponseBody void setReadyStatus(@RequestBody Order order) {
		orderService.setReadyStatus(order);
	}
	
	/**
	 * Web service that retrieves delivery stock by order id using the service provided by deliveryStockService. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/delivery",method = RequestMethod.POST)
	public @ResponseBody List<DeliveryStock> getDelivery(@RequestBody Order order) {
		return deliveryStockService.getDeliveryStocksByOrderId(order.getId());
	}
	
	/**
	 * Web service that updates status to DELIVERED when the delivery is taken by client. Secured with spring security, accessed only by
	 * users with admin or operator privilege.
	 */
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/finish",method = RequestMethod.POST)
	public @ResponseBody void setDeliveredStatus(@RequestBody Order order) {
		orderService.updateStatus(order, Status.DELIVERED);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
	@RequestMapping(value="/sync",method = RequestMethod.POST)
	public @ResponseBody String syncOrders(@RequestBody List<Order> orders) {
		return orderService.syncOrders(orders);
	}
}