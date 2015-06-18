package com.pentalog.sr.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * The wrapper used for creating objects via web services
 * @authors acozma and bpopovici
 *
 * @param <T>
 */
@XmlRootElement(name = "list")
@XmlSeeAlso({Product.class, Order.class,Feature.class,ProductDescription.class,FeatureDescription.class,Users.class,Authorities.class})
public class Wrapper <T> {

	/**
	 * The list of objects
	 */
	private List<T> list;
	
	public Wrapper() {
		list = new ArrayList<T>();
	}

	@XmlAnyElement(lax=true)
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}