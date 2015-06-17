package com.pentalog.sr.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.sr.model.Authorities.Authority;

/**
 * An authority converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class AuthorityConverter implements AttributeConverter<Authority, String> {

	/**
	 * Method that converts an authority object into a string.
	 */
	@Override
	public String convertToDatabaseColumn(Authority attribute) {
		switch (attribute) {
		case ADMIN:
			return "ROLE_ADMIN";
		case OPERATOR:
			return "ROLE_OPERATOR";
		case USER:
			return "ROLE_USER";
		default:
			throw new IllegalArgumentException("Unknown value: " + attribute);
		}
	}

	/**
	 * Method that converts a string into an authority object.
	 */
	@Override
	public Authority convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "ROLE_ADMIN":
			return Authority.ADMIN;
		case "ROLE_OPERATOR":
			return Authority.OPERATOR;
		case "ROLE_USER":
			return Authority.USER;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}
}