package com.pentalog.us.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.us.model.Order.PaymentMethod;

/**
 * Payment method converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {
	
	/**
	 * Convert object to string
	 */
	@Override
	public String convertToDatabaseColumn(PaymentMethod status) {
		switch (status) {
		case CARD:
			return "CARD";
		case DOOR:
			return "DOOR";
		default:
			throw new IllegalArgumentException("Unknown value: " + status);
		}
	}

	/**
	 * Convert string to object
	 */
	@Override
	public PaymentMethod convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "CARD":
			return PaymentMethod.CARD;
		case "DOOR":
			return PaymentMethod.DOOR;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}
}