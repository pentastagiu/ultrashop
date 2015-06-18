package com.pentalog.us.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.security.core.GrantedAuthority;
import com.pentalog.us.converter.RoleConverter;

/**
 * The authority database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="authority")
public class Authority {
	
	/**
	 * Authority id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Authority user
	 */
	@NotNull
	private User user;
	
	/**
	 * Authority role
	 */
	@NotNull
	@Convert(converter = RoleConverter.class)
	private Role role;
	
	/**
	 * Role enum type
	 * @author acozma and bpopovici
	 *
	 */
	public enum Role implements GrantedAuthority{
		
		ADMIN("ROLE_ADMIN"), OPERATOR("ROLE_OPERATOR"), USER("ROLE_USER");
		
		private String label;

		private Role(String label) {
			this.label = getAuthority();
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String getAuthority() {
			return label;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}