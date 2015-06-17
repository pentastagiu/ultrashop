package com.pentalog.us.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.OrderDAO;
import com.pentalog.us.model.AvailabilityStock;
import com.pentalog.us.model.Card;
import com.pentalog.us.model.Customer;
import com.pentalog.us.model.Order;
import com.pentalog.us.model.Order.DeliveryMethod;
import com.pentalog.us.model.Order.NotifyClient;
import com.pentalog.us.model.Order.Status;
import com.pentalog.us.model.Product;
import com.pentalog.us.service.AvailabilityStockService;
import com.pentalog.us.service.CardService;
import com.pentalog.us.service.CustomerService;
import com.pentalog.us.service.DeliveryStockService;
import com.pentalog.us.service.OrderService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * The order service implementation
 * @author acozma and bpopovici
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	/**
	 * The order data access object 
	 */
	@Autowired
	private OrderDAO orderDAO;
	
	/**
	 * The availability stock service
	 */
	@Autowired
	private AvailabilityStockService availabilityStockService;
	
	/**
	 * The delivery stock service
	 */
	@Autowired
	private DeliveryStockService deliveryStockService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CustomerService customerService;

	/**
	 * @see {@link OrderService.getOrders}
	 */
	@Override
	public List<Order> getOrders() {
		return orderDAO.findAll();
	}

	/**
	 * @see {@link OrderService.getOrderById}
	 */
	@Override
	public Order getOrderById(int id) {
		return orderDAO.findOne(id);
	}

	/**
	 * @see {@link OrderService.postOrder}
	 */
	@Override
	public void postOrder(Order order) {
		orderDAO.save(order);
	}

	/**
	 * @see {@link OrderService.putOrder}
	 */
	@Override
	public void putOrder(Order order) {
		order.setStatus(Status.PLACED);
		order.setNotifyClient(NotifyClient.NO);
		order.setoDate(new Date());
		order.setQuantum(calculateQuantum(order));
		Customer customer = customerService.getCustomerByCustomer(order.getCustomer());
		if(customer == null) {
			order.getCustomer().setId(null);
			customer = customerService.putCustomer(order.getCustomer());
		}
		order.setCustomer(customer);
		if(order.getCard() != null) {
			Card card = cardService.getCardByCard(order.getCard());
			if(card == null) {
				card = cardService.putCard(order.getCard());
			}
			order.setCard(card);
		}
		order = orderDAO.save(order);
		if(order.getDeliveryMethod().equals(DeliveryMethod.PICKUP)) {
			
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
			String jsonOrderID = "\"id\":" + null;
			String jsonExternalID = "\"externalId\":" + order.getId();
			String jsonOrderQuantum = "\"quantum\":" + order.getQuantum();
			String jsonOrderStatus = "\"status\":\"" + order.getStatus() + "\"";
			long time = order.getoDate().getTime();
			String jsonOrderODate = "\"oDate\":" + time;
			String jsonOrderNotifyClient = "\"notifyClient\":\"" + order.getNotifyClient() + "\"";
			String jsonOrderCustomer = "\"customer\": {\"id\":" + customer.getId() + ",\"email\":\"" + customer.getEmail() + "\",\"name\":\"" + customer.getFirstname() + " " + customer.getLastname() + "\"}";
			
			String jsonOrderProducts = "\"products\":[";
			String jsonProductQuantity = "\"productQuantity\":[";
			
			for(Map.Entry<Product, Integer>  entry : order.getProducts().entrySet()) {
				jsonProductQuantity += "{\"product\":" + "{\"id\":" + entry.getKey().getId() + ",\"name\":\"" + entry.getKey().getName() + "\",\"price\":" + entry.getKey().getPrice() + "}," + "\"quantity\":\"" + entry.getValue() + "\"},";
				jsonOrderProducts += "{\"id\":" + entry.getKey().getId() + ",\"name\":\"" + entry.getKey().getName() + "\",\"price\":" + entry.getKey().getPrice() + "},";
			}
			
			jsonOrderProducts = jsonOrderProducts.substring(0, jsonOrderProducts.length()-1);
			jsonProductQuantity = jsonProductQuantity.substring(0, jsonProductQuantity.length()-1);
			
			jsonProductQuantity += "]";
			jsonOrderProducts += "]";
			
			String jsonOrder = "{" + jsonOrderID + "," + jsonExternalID + "," + jsonOrderQuantum + "," + jsonOrderStatus + "," + jsonOrderODate + "," + jsonOrderNotifyClient + "," + jsonOrderCustomer + "," + jsonProductQuantity + "," + jsonOrderProducts + "}";
			
			webResource = client.resource(URL + "resources/orders");
			response = webResource.type("application/json").header("Authorization", token).put(ClientResponse.class, jsonOrder);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		}
	}
	
	/**
	 * Calculate the payment for an order
	 * @param order
	 * @return
	 */
	private double calculateQuantum(Order order) {
		double quantum = 0;
		for(Product product : order.getProducts().keySet()) {
			quantum += product.getPrice();
		}
		return quantum;
	}
	
	public void updateJobForOrders() {
		List<Order> orders = getOrders();
		for(Order o : orders) {
			updateStatusForOrders(o);
		}
	}
	
	private void updateStatusForOrders(Order order) {
		boolean available = verifyAvailability(order);
		if(available == true) {
			if(order.getStatus() == Status.PLACED) {
				order.setStatus(Status.READY_FOR_PICKUP);
			}
			else {
				order.setStatus(Status.ARRIVED);
			}
		}
		else {
			order.setStatus(Status.WAITING_ARRIVAL);
		}
	}	
	
	private boolean verifyAvailability(Order order) {
		AvailabilityStock availabilityStock;
		boolean available = true;
		for(Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
			availabilityStock = availabilityStockService.getAvailabilityStockByProductId(entry.getKey().getId());
			if(availabilityStock.getStock() < entry.getValue()) {
				available = false;
			}
		}
		return available;
	}
	
	/**
	 * @see {@link OrderService.deleteOrder}
	 */
	@Override
	public void deleteOrder(Order order) {
		orderDAO.delete(order);
	}

	@Override
	public void syncOrdes() {
		List<Order> orders = getOrderByDeliveryMethod(DeliveryMethod.PICKUP);
		if(orders.size() > 0) {
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
			
			String jsonOrders = "[";
			for(Order o : orders) {
				String jsonOrdersID = "\"id\":" + o.getId();
				jsonOrders += "{" + jsonOrdersID + "},";
			}
			jsonOrders = jsonOrders.substring(0, jsonOrders.length()-1);
			jsonOrders += "]";
			
			
			
			webResource = client.resource(URL + "resources/orders/sync");
			response = webResource.type("application/json").header("Authorization", token).post(ClientResponse.class, jsonOrders);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String jsonOrdersInformation = response.getEntity(String.class);
			
			ObjectMapper mapper = new ObjectMapper();
			List<Order> ordersInformationList = null;
			try {
				ordersInformationList = mapper.readValue(jsonOrdersInformation,TypeFactory.defaultInstance().constructCollectionType(List.class, Order.class));
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(Order o : ordersInformationList) {
				Order order = getOrderById(o.getId());
				order.setStatus(o.getStatus());
				postOrder(order);
			}
		}
	}

	@Override
	public List<Order> getOrderByDeliveryMethod(DeliveryMethod deliveryMethod) {
		return orderDAO.findOrderByDeliveryMethod(deliveryMethod);
	}
}