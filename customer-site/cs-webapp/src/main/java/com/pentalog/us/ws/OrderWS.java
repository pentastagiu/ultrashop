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
import com.pentalog.us.model.DeliveryStock;
import com.pentalog.us.model.Order;
import com.pentalog.us.model.ProductGuarantee;
import com.pentalog.us.service.DeliveryStockService;
import com.pentalog.us.service.OrderService;
import com.pentalog.us.service.ProductGuaranteeService;

/**
 * Order web services
 * @authors acozma and bpopovici
 *
 */
@Component
@Path("/orders")
public class OrderWS {
	
	/**
	 *  Order service
	 */
	@Autowired
	OrderService orderService;
	
	/**
	 *  Delivery stock service
	 */
	@Autowired
	DeliveryStockService deliveryStockService;
	
	/**
	 *  Product guarantee service
	 */
	@Autowired
	ProductGuaranteeService productGuaranteeService;

	/**
	 * Web service that get orders
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Order> getOrders() {
		return orderService.getOrders();
	}
	
	/**
	 * Web service that get order by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Order getOrderById(@PathParam("id") int id)
	{
		return orderService.getOrderById(id);
	}
	
	/**
	 * Web service that post order
	 * @param order
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postOrder(Order order) {
		orderService.postOrder(order);
	}
	
	/**
	 * Web service that put order
	 * @param order
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putOrder(Order order) {
		orderService.putOrder(order);
	}
	
	/**
	 * Web service that delete order
	 * @param order
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteOrder(Order order) {
		orderService.deleteOrder(order);
	}
	
	/**
	 * Web service that get delivery stocks
	 * @return
	 */
	@GET
	@Path("/stocks")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<DeliveryStock> getDeliveryStocks() {
		return deliveryStockService.getDeliveryStocks();
	}
	
	/**
	 * Web service that get delivery stock by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/stocks/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public DeliveryStock getDeliveryStockById(@PathParam("id") int id)
	{
		return deliveryStockService.getDeliveryStockById(id);
	}
	
	/**
	 * Web service that post delivery stock
	 * @param order
	 */
	@POST
	@Path("/stocks")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockService.postDeliveryStock(deliveryStock);
	}
	
	/**
	 * Web service that put delivery stock
	 * @param order
	 */
	@PUT
	@Path("/stocks")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockService.putDeliveryStock(deliveryStock);
	}
	
	/**
	 * Web service that delete delivery stock
	 * @param order
	 */
	@DELETE
	@Path("/stocks")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteDeliveryStock(DeliveryStock deliveryStock) {
		deliveryStockService.deleteDeliveryStock(deliveryStock);
	}
	
	/**
	 * Web service that get products guarantees
	 * @return
	 */
	@GET
	@Path("/guarantees")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductGuarantee> getProductGuarantees() {
		return productGuaranteeService.getProductGuarantees();
	}
	
	/**
	 * Web service that get product guarantee by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/guarantees/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductGuarantee getProductGuaranteeById(@PathParam("id") int id)
	{
		return productGuaranteeService.getProductGuaranteeById(id);
	}
	
	/**
	 * Web service that post product guarantee
	 * @param order
	 */
	@POST
	@Path("/guarantees")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProductGuarantee(ProductGuarantee productGuarantee) {
		productGuaranteeService.postProductGuarantee(productGuarantee);
	}
	
	/**
	 * Web service that put product guarantee
	 * @param order
	 */
	@PUT
	@Path("/guarantees")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProductGuarantee(ProductGuarantee productGuarantee) {
		productGuaranteeService.putProductGuarantee(productGuarantee);
	}
	
	/**
	 * Web service that delete product guarantee
	 * @param order
	 */
	@DELETE
	@Path("/guarantees")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProductGuarantee(ProductGuarantee productGuarantee) {
		productGuaranteeService.deleteProductGuarantee(productGuarantee);
	}
}