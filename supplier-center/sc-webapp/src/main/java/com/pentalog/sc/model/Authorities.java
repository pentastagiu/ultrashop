package com.pentalog.sc.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;

import com.pentalog.sc.converter.AuthorityConverter;

/**
 * 
 * The authorities dataBase model
 */
@XmlRootElement
@Entity
@Table(name = "authorities")
public class Authorities {

	/**
	 * The authority id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer id;

	/**
	 * The username
	 */
	@NotNull
	@Column(unique = true)
	private String username;

	/**
	 * The authority
	 */
	@Convert(converter = AuthorityConverter.class)
	private Authority authority;

	/**
	 * the authority enum type
	 *
	 */
	public enum Authority implements GrantedAuthority {
		ADMIN("ROLE_ADMIN"), OPERATOR("ROLE_OPERATOR");
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

	public Authorities() {
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}
