package com.pentalog.sr.bean;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.pentalog.sr.model.Order.Status;
import com.pentalog.sr.service.OrderService;

/**
 * Locale management bean 
 * @authors acozma and bpopovici
 *
 */
@ManagedBean
@SessionScoped
public class LocaleBean {

	/**
	 *  The order service - contains methods used to fetch data regarding orders from the DAO layer.
	 */
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	
	/**
	 * The current locale used by the web application
	 */
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Set current locale to English 
	 */
	public void setEnglish() {
		this.locale = new Locale("en");
	}
	
	/**
	 * Set current locale to French 
	 */
	public void setFrench() {
		this.locale = new Locale("fr");
	}

	/**
	 * Set current locale to Romanian 
	 */
	public void setRomanian() {
		this.locale = new Locale("ro");
	}
	
	/**
	 * Method that translates the order status in the current locale
	 * @param status -	The status that will be translated
	 * @return
	 */
	public String translateStatus(Status status)
	{
		return orderService.translateStatus(status, ResourceBundle.getBundle("bundle.messages",locale));
	}
}