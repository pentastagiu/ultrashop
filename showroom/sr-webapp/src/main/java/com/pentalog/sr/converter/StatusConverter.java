package com.pentalog.sr.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.sr.model.Order.Status;

/**
 * A status converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
	
	/**
	 * Method that converts a status object into a string.
	 */
	@Override
	public String convertToDatabaseColumn(Status status) {
		switch (status) {
		case PLACED:
			return "P";
		case WAITING_ARRIVAL:
			return "W";
		case READY_FOR_PICKUP:
			return "R";
		case CANCELED:
			return "C";
		case ARRIVED:
			return "A";
		case DELIVERED:
			return "D";
		default:
			throw new IllegalArgumentException("Unknown value: " + status);
		}
	}

	/**
	 * Method that converts a string into a status object. 
	 */
	@Override
	public Status convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "P":
			return Status.PLACED;
		case "W":
			return Status.WAITING_ARRIVAL;
		case "R":
			return Status.READY_FOR_PICKUP;
		case "C":
			return Status.CANCELED;
		case "A":
			return Status.ARRIVED;
		case "D":
			return Status.DELIVERED;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}
}