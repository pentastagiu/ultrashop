package com.pentalog.sr.service.impl;

import com.pentalog.sr.dao.OrderDAO;
import com.pentalog.sr.model.Customer;
import com.pentalog.sr.model.Order.NotifyClient;
import com.pentalog.sr.model.Order.Status;
import com.pentalog.sr.model.AvailabilityStock;
import com.pentalog.sr.model.DeliveryStock;
import com.pentalog.sr.model.ProductQuantity;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.model.Order;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.service.AvailabilityStockService;
import com.pentalog.sr.service.CustomerService;
import com.pentalog.sr.service.DeliveryStockService;
import com.pentalog.sr.service.OrderService;
import com.pentalog.sr.service.ProductQuantityService;
import com.pentalog.sr.service.ProductService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The order service implementation
 * @authors acozma and bpopovici
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	/**
	 * The order data access object
	 */
	@Autowired
	private OrderDAO orderDao;
	
	/**
	 * The product service
	 */
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductQuantityService productQuantityService;
	
	/**
	 * The customer service
	 */
	@Autowired
	private CustomerService customerService;
	
	/**
	 * The delivery stock service
	 */
	@Autowired
	private DeliveryStockService deliveryStockService;
	
	/**
	 * The availability stock service
	 */
	@Autowired
	private AvailabilityStockService availabilityStockService;
	
	/**
	 * @see {@link OrderService.getAllOrders_Wrapper}
	 */
	@Override
	public Wrapper<Order> getAllOrders_Wrapper() {
		Wrapper<Order> orders = new Wrapper<Order>();
		orders.setList(orderDao.findAll());
		return orders;
	}
	
	/**
	 * @see {@link OrderService.getAllOrders_List}
	 */
	@Override
	public List<Order> getAllOrders_List() {
		return orderDao.findAll();
	}
	
	/**
	 * @see {@link OrderService.getOrderById}
	 */
	@Override
	public Order getOrderById(int id) {
		return this.orderDao.findOne(id);
	}

	/**
	 * @see {@link OrderService.registerPlacedOrder}
	 */
	@Override
	public Order registerOrder(Order order) {
		Collection<Product> productsId = order.getProducts();
		Collection<ProductQuantity> productsQuantityId = order.getProductQuantity();
		List<Product> orderProducts = new ArrayList<Product>();
		List<ProductQuantity> orderProductQuantity = new ArrayList<ProductQuantity>();
		for(Product p : productsId) {
			Product orderProduct = productService.getProductById(p.getId());
			orderProducts.add(orderProduct);
		}
		for(ProductQuantity pQ : productsQuantityId) {
			ProductQuantity productQuantity = productQuantityService.saveProductQuantity(pQ);
			orderProductQuantity.add(productQuantity);
		}
		order.setProductQuantity(orderProductQuantity);
		order.setProducts(orderProducts);
		Customer customer = customerService.getCustomerById(order.getCustomer().getId());
		if(customer == null) {
			customer = customerService.registerCustomer(order.getCustomer());
		}
		order.setCustomer(customer);
		order.setQuantum(calculateQuantum(orderProducts));
		order.setNotifyClient(NotifyClient.NO);
	    order.setStatus(Status.PLACED);
		orderDao.save(order);
		return order;
	}
	
	/**
	 * @see {@link OrderService.registerPlacedOrder}
	 */
	@Override
	public Order registerPlacedOrder(Order order) {
		boolean ok = true;
		AvailabilityStock availabilityStock;
		DeliveryStock deliveryStock;
		Collection<Product> products = order.getProducts();	
		List<DeliveryStock> deliveryStocks = new ArrayList<>();
		
		for (Product p : products) {
			availabilityStock = availabilityStockService.getStockById(p.getId());
			for(ProductQuantity pQ : order.getProductQuantity()) {
				if(pQ.getProduct().getId() == p.getId()) {
					if(availabilityStock.getStock() >= pQ.getQuantity()) {
						availabilityStock.setStock(availabilityStock.getStock() - pQ.getQuantity());
						availabilityStockService.updateStock(availabilityStock);
						for(int i = 0; i < pQ.getQuantity(); i++) {
							deliveryStock = new DeliveryStock();
							deliveryStock.setProduct(p);
							deliveryStock.setRandomSerial();
							deliveryStock.setStock(1);
							deliveryStocks.add(deliveryStock);
						}
					}
					else {
						ok = false;
						if(availabilityStock.getStock() != 0)  {
							int availabilityStockLeft = availabilityStock.getStock() - pQ.getQuantity();
							int availabilityStockRequire = pQ.getQuantity() + availabilityStockLeft;
							availabilityStock.setStock(availabilityStock.getStock() - availabilityStockRequire);
							availabilityStockService.updateStock(availabilityStock);
							for(int i = 0; i < availabilityStockRequire; i++) {
								deliveryStock = new DeliveryStock();
								deliveryStock.setProduct(p);
								deliveryStock.setRandomSerial();
								deliveryStock.setStock(1);
								deliveryStocks.add(deliveryStock);
							}
						}
					}
				}
			}
		}
		

		if (ok == true) {
			order.setStatus(Status.READY_FOR_PICKUP);
		} else {
			order.setStatus(Status.WAITING_ARRIVAL);
		}
		
		orderDao.save(order);
		
		for (DeliveryStock ds : deliveryStocks) {
			ds.setOrder(order);
			deliveryStockService.saveDeliveryStock(ds);
		}
		return order;
	}
	
	/**
	 * @see {@link OrderService.updateOrder}
	 */
	@Override
	public void updateOrder(Order order) {
		orderDao.save(order);
	}
	
	@Override
	public void updateStatusForPlacedOrders()
	{
		List<Order> orders = new ArrayList<>();
		orders = orderDao.findByStatus(Status.PLACED);
		for(Order o : orders)
			registerPlacedOrder(o);
	}
	
	/**
	 * @see {@link OrderService.calculateQuantum}
	 */
	@Override
	public double calculateQuantum(Collection<Product> products) {
		double quantum = 0;
		for (Product p : products) {
			quantum += productService.getProductById(p.getId()).getPrice();
		}
		return quantum;
	}

	/**
	 * @see {@link OrderService.updateStatus}
	 */
	@Override
	public void updateStatus(Order order, Status status) {	
		boolean ok = true;
		switch (status) {
			case ARRIVED:
				ok = updateToArrived(order);
				break;
			case CANCELED:
				ok = updateToCancel(order);
				break;
			case READY_FOR_PICKUP:
				ok = updateToReady(order);
				break;
			case DELIVERED:
				ok = updateToDelivered(order);
				break;
			default:
				ok = false;
				break;
		}
		if(ok == true) {
			order.setStatus(status);
			updateOrder(order);
		}
	}
	
	/**
	 * Method that updates an order from ARRIVED to READY_FOR_PICKUP
	 * @param order - the order
	 */
	private boolean updateToReady(Order order) {
		List<DeliveryStock> deliveryStocks;
		if(order.getStatus().equals(Status.ARRIVED)) {
			deliveryStocks = deliveryStockService.getDeliveryStocksByOrderId(order.getId());
			for (DeliveryStock ds : deliveryStocks) {
				if(ds.getSerial() == null || ds.getSerial().isEmpty()) {
					return false;
				}
			}			
		}
		else {
			return false;
		}
		return true;
	}
	
	private boolean updateToDelivered(Order order){
		if(order.getStatus().equals(Status.READY_FOR_PICKUP)){
			return true;
		}
		return false;
	}
	
	/**
	 * Method that verifies if an order changes the status to CANCELED and all the available product stocks will be updated
	 * @param order - the order
	 */
	private boolean updateToCancel(Order order) {
		List<DeliveryStock> deliveryStocks;
		AvailabilityStock availabilityStock;
		if (!order.getStatus().equals(Status.CANCELED)) {
			deliveryStocks = deliveryStockService.getDeliveryStocksByOrderId(order.getId());
			for (DeliveryStock ds : deliveryStocks) {
				deliveryStockService.deleteDeliveryStock(ds);
				availabilityStock = availabilityStockService.getStockById(ds.getProduct().getId());
				availabilityStock.setStock(availabilityStock.getStock() + 1);
				availabilityStockService.updateStock(availabilityStock);
			}
			deliveryStocks = deliveryStockService.getDeliveryStocksByOrderId(order.getId());
			if (deliveryStocks.size() != 0) {
				return false;
			}
		} 
		else {
			return false;
		}
		return true;
	}
	
	/**
	 * Method that verifies if an order changes status from WAITING_ARRIVAL to ARRIVED
	 * @param order - the order
	 */
	private boolean updateToArrived(Order order) {
		if(order.getStatus().equals(Status.WAITING_ARRIVAL)) {
			List<Product> products = getWaitingProductsFromOrder(order);
			updateStocks(products,order);
			products = getWaitingProductsFromOrder(order);
			if(products.size() != 0) {
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}
	
	/**
	 * Method that gets the list of products that were waiting for arrival on an order
	 * @param order - the order
	 */
	private List<Product> getWaitingProductsFromOrder(Order order) {
		List<Product> products = new ArrayList<Product>();
		for (Product p : order.getProducts()) {
			List<DeliveryStock> deliveryStocks = deliveryStockService.getDeliveryStockByOrderIdAndProductId(order.getId(), p.getId());
			for(ProductQuantity pQ : order.getProductQuantity()) {
				if(pQ.getProduct().getId() == p.getId()) {
					if(deliveryStocks.size() != pQ.getQuantity()) {
						int availabilityStockRequire = pQ.getQuantity() - deliveryStocks.size();
						for(int i = 0; i < availabilityStockRequire; i++) {
							products.add(p);
						}
					}
				}
			}
		}
		return products;
	}
	
	/**
	 * Method that updates available stocks of products on an order
	 * @param products - list of products on the order
	 * @param order - the order
	 */
	private void updateStocks(List<Product> products, Order order) {
		List<DeliveryStock> deliveryStocks = new ArrayList<>();
		AvailabilityStock availabilityStock;
		DeliveryStock deliveryStock;
		for(Product p : products) {
			availabilityStock = availabilityStockService.getStockById(p.getId());
			for(ProductQuantity pQ : order.getProductQuantity()) {
				if(availabilityStock.getStock() >= pQ.getQuantity()) {
					availabilityStock.setStock(availabilityStock.getStock() - pQ.getQuantity());
					availabilityStockService.updateStock(availabilityStock);
					for(int i = 0; i < pQ.getQuantity(); i++) {
						deliveryStock = new DeliveryStock();
						deliveryStock.setProduct(p);
						deliveryStock.setStock(1);
						deliveryStocks.add(deliveryStock);
					}
				}
				else {
					if(availabilityStock.getStock() != 0)  {
						int availabilityStockLeft = availabilityStock.getStock() - pQ.getQuantity();
						int availabilityStockRequire  = pQ.getQuantity() + availabilityStockLeft;
						availabilityStock.setStock(availabilityStock.getStock() - availabilityStockRequire);
						availabilityStockService.updateStock(availabilityStock);
						for(int i = 0; i < availabilityStockRequire; i++) {
							deliveryStock = new DeliveryStock();
							deliveryStock.setProduct(p);
							deliveryStock.setStock(1);
							deliveryStocks.add(deliveryStock);
						}
					}
				}
			}
		}
		for (DeliveryStock ds : deliveryStocks) {
			ds.setOrder(order);
			deliveryStockService.saveDeliveryStock(ds);
		}
	}

	/**
	 * @see {@link OrderService.sendDocuments}
	 */
	@Override
	public void sendDocuments(Order order) {
		if (order.getStatus() == Status.READY_FOR_PICKUP) {
			Properties smtpProp = new Properties();
			final Properties authProp = new Properties();

			try {
				smtpProp.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("config/smtpConfig.properties"));
				authProp.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("config/authConfig.properties"));
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}

			Session session = Session.getInstance(smtpProp,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(authProp
									.getProperty("username"), authProp
									.getProperty("password"));
						}
					});
			
			try {
				Message message = new MimeMessage(session);
				message.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse(order.getCustomer().getEmail()));
				message.setSubject("Order documents");

				createWarranty(order);
				createBill(order);

				Multipart multipart = new MimeMultipart();
				addAttachment(multipart, "warranty.txt");
				addAttachment(multipart, "bill.txt");
				message.setContent(multipart);

				Transport.send(message);

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Order status not ready.");
		}
	}
	/**
	 * Builds message for emailing customer
	 * @param multipart
	 * @param filename
	 * @throws MessagingException
	 */
	private void addAttachment(Multipart multipart, String filename)
			throws MessagingException {
		DataSource source = new FileDataSource(filename);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
	}

	/**
	 * Method that creates the warranty file
	 * @param order - the order
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private void createWarranty(Order order) throws FileNotFoundException,
			UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		String dt = sdf.format(c.getTime());
		PrintWriter writer = new PrintWriter("warranty.txt", "UTF-8");
		writer.println("Customer name: "
				+ order.getCustomer().getName());
		writer.println();
		writer.println("Product list: ");
		List<DeliveryStock> deliveryStocks;
		for (Product p : order.getProducts()) {
			deliveryStocks = deliveryStockService.getSerialByOrderIdAndProductId(order.getId(), p.getId());
			for(DeliveryStock ds : deliveryStocks) {
				writer.println("\t -- " + p.getName() + " - " + ds.getSerial() + ";");
			}
		}
		writer.println("Start Date: " + dt);
		c.add(Calendar.YEAR, 2);
		dt = sdf.format(c.getTime());
		writer.println("End Date: " + dt);
		writer.close();
	}

	/**
	 * Method that creates the bill file
	 * @param Order
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private void createBill(Order Order) throws FileNotFoundException,
			UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		String dt = sdf.format(c.getTime());
		PrintWriter writer = new PrintWriter("bill.txt", "UTF-8");
		writer.println("Customer name: "
				+ Order.getCustomer().getName());
		writer.println();
		writer.println("Product list: ");
		for (Product p : Order.getProducts()) {
			writer.println("\t -- " + p.getName() + " - " + p.getPrice() + ";");
		}
		writer.println("Total" + " - " + Order.getQuantum() + ";");
		writer.println("Date: " + dt);
		writer.close();
	}
	
	/**
	 * @see {@link OrderService.updateStatusForWaitingOrders}
	 */
	@Override
	public void updateStatusForWaitingOrders() {
		List<Order> orders = getAllByOrderByODateDesc();
		for (Order order : orders) {
			updateStatus(order, Status.ARRIVED);
		}
	}

	/**
	 * @see {@link OrderService.sendMailForReadyOrder}
	 */
	@Override
	public void sendMailForReadyOrder() {
		List<Order> orders = getAllOrders_Wrapper().getList();
		for (Order order : orders) {
			if (order.getStatus().equals(Status.READY_FOR_PICKUP)
					&& order.getNotifyClient().equals(NotifyClient.NO)) {
				System.out.println("Sending mail...");
				sendMail(order);
				updateNotify(order, NotifyClient.YES);
				System.out.println("Done");
			}
		}
	}
	

	/**
	 * @see {@link OrderService.updateNotify}
	 */
	@Override
	public void updateNotify(Order order, NotifyClient notifyClient) {
		order.setNotifyClient(notifyClient);
		updateOrder(order);	
	}
	
	/**
	 * Method that sends a mail to the customer
	 * @param order
	 */
	private void sendMail(Order order) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		String dt = sdf.format(c.getTime());

		Properties smtpProp = new Properties();
		Properties cdnProp = new Properties();
		final Properties authProp = new Properties();
		
		try {
			smtpProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/smtpConfig.properties"));
			cdnProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/cdnConfig.properties"));
			authProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/authConfig.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		Session session = Session.getInstance(smtpProp,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(authProp.getProperty("username"), authProp.getProperty("password"));
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(order.getCustomer().getEmail()));
			message.setSubject("Order ready");
			
			String productList = "<p>Product list: <ul>";
			for(Product p : order.getProducts()) {
				productList += "<li><a href=\"" + "http://localhost:8080/showroom/products/" + p.getId() + "\">" + p.getName() + "</a></li>";
			}
			productList += "</ul></p>";
	
	        message.setContent("<dir><h1>Dear " 
	        		+ order.getCustomer().getName().toString() 
	        		+ "," 
	        		+ "</h1>" 
	        		+ "<br>" 
	        		+ "<p>" 
	        		+ "Thank you for choosing to buy from us." 
	        		+ "</p>"
	        		+ "<p>" 
	        		+ "Products ordered are ready." 
	        		+ "</p>"
	        		+ "<p>" 
	        		+ " You can come to take them starting on " 
	        		+ dt
	        		+ "</p>"
	        		+ "<p>" 
	        		+ "The products will be in stock within 10 days of the date specified." 
	        		+ "</p>"
	        		+ productList
	        		+ "</dir>", "text/html");

	        Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * @see {@link OrderService.filterByCustomerId}
	 */
	@Override
	public List<Order> filterByCustomerId(String filterCustomerId, List<Order> orders) {
		List<Order> filterOrders;
		if(!filterCustomerId.isEmpty() && isNumeric(filterCustomerId)) {
			filterOrders = new ArrayList<Order>();
			int id = Integer.parseInt(filterCustomerId);
			for(Order o : orders) {
				if(o.getCustomer().getId() == id) {
					filterOrders.add(o);
				}
			}
			orders = filterOrders;
		}
		return orders;
	}


	/**
	 * @see {@link OrderService.filterByDate}
	 */
	@Override
	public List<Order> filterByDate(Date filterMinDate, Date filterMaxDate, List<Order> orders) {
		List<Order> filterOrders;
		if (filterMinDate != null) {
			filterOrders = new ArrayList<Order>();
			for (Order o : orders) {
				if (o.getoDate().compareTo(filterMinDate) >= 0) {
					filterOrders.add(o);
				}
			}
			orders = filterOrders;
		}
		if (filterMaxDate != null) {
			filterOrders = new ArrayList<Order>();
			for (Order o : orders) {
				if (o.getoDate().compareTo(filterMaxDate) <= 0) {
					filterOrders.add(o);
				}
			}
			orders = filterOrders;
		}
		return orders;
	}


	/**
	 * @see {@link OrderService.filterByQuantum}
	 */
	@Override
	public List<Order> filterByQuantum(String filterMinQuantum, String filterMaxQuantum, List<Order> orders) {
		List<Order> filterOrders;
		if (!filterMinQuantum.isEmpty() && isNumeric(filterMinQuantum)) {
			filterOrders = new ArrayList<Order>();
			double minQuantum = Double.parseDouble(filterMinQuantum);
			for (Order o : orders) {
				if (o.getQuantum() >= minQuantum) {
					filterOrders.add(o);
				}
			}
			orders = filterOrders;
		}
		if (!filterMaxQuantum.isEmpty() && isNumeric(filterMaxQuantum)) {
			filterOrders = new ArrayList<Order>();
			double maxQuantum = Double.parseDouble(filterMaxQuantum);
			for (Order o : orders) {
				if (o.getQuantum() <= maxQuantum) {
					filterOrders.add(o);
				}
			}
			orders = filterOrders;
		}

		return orders;
	}
	
	/**
	 * Method that verifies if a given string is a numeric sequence
	 * @param str - the verified string
	 * @return
	 */
	private static boolean isNumeric(String str)
	{
		return str.matches("\\d+(\\.\\d+)?");
	}


	/**
	 * @see {@link OrderService.filterByStatus}
	 */
	@Override
	public List<Order> filterByStatus(List<String> selectedStatuses, List<Order> orders) {
		List<Order> filterOrders;
		if(selectedStatuses.size() > 0) {
			filterOrders = new ArrayList<Order>();
			for (Order o : orders) {
				for(String s : selectedStatuses) {
					String t = o.getStatus().getLabel();
					if(t.compareTo(s) == 0) {
						filterOrders.add(o);
					}
				}
			}
			orders = filterOrders;
		}
		return orders;
	}


	/**
	 * @see {@link OrderService.translateStatus}
	 */
	@Override
	public String translateStatus(Status status, ResourceBundle message) {
		switch(status)
		{
			case WAITING_ARRIVAL:
				return message.getString("WAITING_ARRIVAL");
			case ARRIVED:
				return message.getString("ARRIVED");
			case CANCELED:
				return message.getString("CANCELED");
			case READY_FOR_PICKUP:
				return message.getString("READY_FOR_PICKUP");
			case PLACED:
				return message.getString("PLACED");
			case DELIVERED:
				return message.getString("DELIVERED");
			default : return null;
		}
	}


	/**
	 * @see {@link OrderService.getAllByOrderByODateDesc}
	 */
	@Override
	public List<Order> getAllByOrderByODateDesc() {
		return orderDao.findAllByOrderByODateAsc();
	}

	/**
	 * @see {@link OrderService.getTopOrders}
	 */
	@Override
	public List<Order> getTopOrders(Integer offset, Integer limit) {
		List<Order> wrapper= orderDao.findAndLimit(offset,limit);
		return wrapper;
	}

	/**
	 * @see {@link OrderService.getNumberOfOrders}
	 */
	@Override
	public Integer getNumberOfOrders() {
		return orderDao.getNumberOfOrders();
	}
	
	/**
	 * @see {@link OrderService.setReadyStatus}
	 */
	@Override
	public void setReadyStatus(Order order) {
		updateStatus(order, Status.READY_FOR_PICKUP);
	}

	@Override
	public String syncOrders(List<Order> orders) {
		String ordersInformation = "[";
		for(Order o : orders) {
			Order order = getOrderByExternalId(o.getId());
			String jsonOrderID = "\"id\":" + o.getId();
			String jsonOrderStatus = "\"status\":\"" + order.getStatus() + "\"";
			ordersInformation += "{" + jsonOrderID + "," + jsonOrderStatus + "},";
		}
		ordersInformation = ordersInformation.substring(0, ordersInformation.length()-1);
		ordersInformation += "]";
		return ordersInformation;
	}

	@Override
	public Order getOrderByExternalId(int external_id) {
		return orderDao.findByExternalId(external_id);
	}
}