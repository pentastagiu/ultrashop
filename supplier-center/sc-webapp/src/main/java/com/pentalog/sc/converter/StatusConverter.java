package com.pentalog.sc.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.pentalog.sc.model.Order.Status;


/**
 * Status converter.
 * @author Abacioaiei
 *
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String>{


    /**
     * Convert object to string
     */
    @Override
    public String convertToDatabaseColumn(Status status) {
        switch (status) {
        case PLACED:
            return "PLACED";
        case WAITING_ARRIVAL:
            return "WAITING_ARRIVAL";
        case CANCELED:
            return "CANCELED";
        case ARRIVED:
            return "ARRIVED";
        case DELIVERED:
            return "DELIVERED";
        default:
            throw new IllegalArgumentException("Unknown value: " + status);
        }
    }

    /**
     * Convert string to object
     */
    @Override
    public Status convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "PLACED":
            return Status.PLACED;
        case "WAITING_ARRIVAL":
            return Status.WAITING_ARRIVAL;
        case "CANCELED":
            return Status.CANCELED;
        case "ARRIVED":
            return Status.ARRIVED;
        case "DELIVERED":
            return Status.DELIVERED;
        default:
            throw new IllegalArgumentException("Unknown value: " + dbData);
        }
    }
}
