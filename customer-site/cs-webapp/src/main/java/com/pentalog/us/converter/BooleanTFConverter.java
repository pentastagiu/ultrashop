package com.pentalog.us.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convert boolean to string for database.
 *
 */
@Converter
public class BooleanTFConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "true".equals(value);
    }
}