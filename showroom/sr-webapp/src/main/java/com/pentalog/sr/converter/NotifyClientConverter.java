package com.pentalog.sr.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.sr.model.Order.NotifyClient;

/**
 * A notify client converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class NotifyClientConverter implements AttributeConverter<NotifyClient, String> {
	
	/**
	 * Method that converts a notifyClient object into a string.
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
	 * Method that converts a string into a notifyClient object.
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