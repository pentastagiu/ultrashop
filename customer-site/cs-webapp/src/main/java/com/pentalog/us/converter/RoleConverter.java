package com.pentalog.us.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.us.model.Authority.Role;

/**
 * Role converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

	/**
	 * Convert object to string
	 */
	@Override
	public String convertToDatabaseColumn(Role attribute) {
		switch (attribute) {
		case ADMIN:
			return "ADMIN";
		case OPERATOR:
			return "OPERATOR";
		case USER:
			return "USER";
		default:
			throw new IllegalArgumentException("Unknown value: " + attribute);
		}
	}

	/**
	 * Convert string to object
	 */
	@Override
	public Role convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "ADMIN":
			return Role.ADMIN;
		case "OPERATOR":
			return Role.OPERATOR;
		case "USER":
			return Role.USER;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}
}