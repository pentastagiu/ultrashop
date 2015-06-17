package com.pentalog.us.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.pentalog.us.model.Order.DeliveryMethod;

/**
 * Delivery method converter
 * @authors acozma and bpopovici
 *
 */
@Converter(autoApply = true)
public class DeliveryMethodConverter implements AttributeConverter<DeliveryMethod, String> {
	
	/**
	 * Convert object to string
	 */
	@Override
	public String convertToDatabaseColumn(DeliveryMethod deliveryMethod) {
		switch (deliveryMethod) {
		case MAIL:
			return "MAIL";
		case CURRIER:
			return "CURRIER";
		case PICKUP:
			return "PICKUP";
		default:
			throw new IllegalArgumentException("Unknown value: " + deliveryMethod);
		}
	}

	/**
	 * Convert string to object
	 */
	@Override
	public DeliveryMethod convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "MAIL":
			return DeliveryMethod.MAIL;
		case "CURRIER":
			return DeliveryMethod.CURRIER;
		case "PICKUP":
			return DeliveryMethod.PICKUP;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}
}