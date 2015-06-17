package com.pentalog.sr.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;

import com.pentalog.sr.converter.AuthorityConverter;

/**
 * The authority database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "authorities")
public class Authorities  {

	/**
	 * The authority id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * The username granted the authority
	 */
	@NotNull
	@Column(unique=true)
	private String username;
	
	/**
	 * The granted authority 
	 */
	@Convert(converter = AuthorityConverter.class)
	private Authority authority;
	
	/**
	 * Authority enum type
	 * @author acozma
	 *
	 */
	public enum Authority implements GrantedAuthority{
		ADMIN("ROLE_ADMIN"), OPERATOR("ROLE_OPERATOR"), USER("ROLE_USER");
		private String label;

		private Authority(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String getAuthority() {
			return label;
		}
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}