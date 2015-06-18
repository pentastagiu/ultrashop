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
import com.pentalog.us.model.Authority;
import com.pentalog.us.model.Card;
import com.pentalog.us.model.Customer;
import com.pentalog.us.model.User;
import com.pentalog.us.service.AuthorityService;
import com.pentalog.us.service.CardService;
import com.pentalog.us.service.CustomerService;
import com.pentalog.us.service.UserService;

/**
 * User web services
 * @authors acozma and bpopovici
 *
 */
@Component
@Path("/users")
public class UserWS {

	/**
	 * User service
	 */
	@Autowired
	UserService userService;
	
	/**
	 * Authority service
	 */
	@Autowired
	AuthorityService authorityService;
	
	/**
	 * Customer service
	 */
	@Autowired
	CustomerService customerService;
	
	/**
	 * Card service
	 */
	@Autowired
	CardService cardrService;

	/**
	 * Web service that get users
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	/**
	 * Web service that get user by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUserById(@PathParam("id") int id)
	{
		return userService.getUserById(id);
	}
	
	@POST
	@Path("/email")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUserByEmail(User user)
	{
		return userService.getUserByEmail(user);
	}
	
	/**
	 * Web service that post user
	 * @param order
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postUser(User user) {
		userService.postUser(user);
	}
	
	/**
	 * Web service that put user
	 * @param order
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putUser(User user) {
		userService.putUser(user);
	}
	
	/**
	 * Web service that delete user
	 * @param order
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteUser(User user) {
		userService.deleteUser(user);
	}
	
	/**
	 * Web service that get autorities
	 * @return
	 */
	@GET
	@Path("/authorities")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Authority> getAutorities() {
		return authorityService.getAuthorities();
	}
	
	/**
	 * Web service that get autority by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/authorities/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Authority getAuthorityById(@PathParam("id") int id)
	{
		return authorityService.getAuthorityById(id);
	}
	
	/**
	 * Web service that post autority
	 * @param order
	 */
	@POST
	@Path("/authorities")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postAuthority(Authority authority) {
		authorityService.postAuthority(authority);
	}
	
	/**
	 * Web service that put autority
	 * @param order
	 */
	@PUT
	@Path("/authorities")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putAuthority(Authority authority) {
		authorityService.putAuthority(authority);
	}
	
	/**
	 * Web service that delete autority
	 * @param order
	 */
	@DELETE
	@Path("/authorities")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteAuthority(Authority authority) {
		authorityService.deleteAuthority(authority);
	}
	
	/**
	 * Web service that get customer
	 * @return
	 */
	@GET
	@Path("/customers")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	/**
	 * Web service that get customer by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/customers/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer getCustomerById(@PathParam("id") int id)
	{
		return customerService.getCustomerById(id);
	}
	
	@POST
	@Path("/customers/user")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer getCustomerByUser(User user)
	{
		return customerService.getCustomerByUser(user);
	}

	/**
	 * Web service that post customer
	 * @param order
	 */
	@POST
	@Path("/customers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postCustomer(Customer customer) {
		customerService.postCustomer(customer);
	}
	
	/**
	 * Web service that put customer
	 * @param order
	 */
	@PUT
	@Path("/customers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putCustomer(Customer customer) {
		customerService.putCustomer(customer);
	}
	
	/**
	 * Web service that delete customer
	 * @param order
	 */
	@DELETE
	@Path("/customers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteCustomer(Customer customer) {
		customerService.deleteCustomer(customer);
	}
	
	/**
	 * Web service that get cards
	 * @return
	 */
	@GET
	@Path("/cards")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Card> getCards() {
		return cardrService.getCards();
	}
	
	/**
	 * Web service that get card by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/cards/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Card getCardById(@PathParam("id") int id)
	{
		return cardrService.getCardById(id);
	}
	
	/**
	 * Web service that post card
	 * @param order
	 */
	@POST
	@Path("/cards")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postCard(Card card) {
		cardrService.postCard(card);
	}
	
	/**
	 * Web service that put card
	 * @param order
	 */
	@PUT
	@Path("/cards")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putCard(Card card) {
		cardrService.putCard(card);
	}
	
	/**
	 * Web service that delete card
	 * @param order
	 */
	@DELETE
	@Path("/cards")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteCard(Card card) {
		cardrService.deleteCard(card);
	}
}