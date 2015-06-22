package com.pentalog.sc.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.pentalog.sc.model.Authorities.Authority;

/**
 * 
 * Authority converter
 */
@Converter(autoApply = true)
public class AuthorityConverter implements
		AttributeConverter<Authority, String> {
	/**
	 * Method that converts an authority object into a string
	 */
	@Override
	public String convertToDatabaseColumn(Authority attribute) {
		switch (attribute) {
		case ADMIN:
			return "ROLE_ADMIN";
		case OPERATOR:
			return "ROLE_OPERATOR";
		default:
			throw new IllegalArgumentException("Unknown value: " + attribute);
		}
	}

	/**
	 * Method that converts an sting into an authorityObject
	 */
	@Override
	public Authority convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "ROLE_ADMIN":
			return Authority.ADMIN;
		case "ROLE_OPERATOR":
			return Authority.OPERATOR;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}

}
