package com.pentalog.us.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.us.model.Order.NotifyClient;

/**
 * Notify client converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class NotifyClientConverter implements AttributeConverter<NotifyClient, String> {
	
	/**
	 * Convert object to string
	 */
	@Override
	public String convertToDatabaseColumn(NotifyClient status) {
		switch (status) {
		case YES:
			return "YES";
		case NO:
			return "NO";
		default:
			throw new IllegalArgumentException("Unknown value: " + status);
		}
	}

	/**
	 * Convert string to object
	 */
	@Override
	public NotifyClient convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "NO":
			return NotifyClient.NO;
		case "YES":
			return NotifyClient.YES;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}
}