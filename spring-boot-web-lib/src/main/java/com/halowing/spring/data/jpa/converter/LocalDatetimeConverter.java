package com.halowing.spring.data.jpa.converter;

import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.halowing.util.TimeUtility;

@Converter
public class LocalDatetimeConverter implements AttributeConverter<LocalDateTime, String> {
	
	@Override
	public String convertToDatabaseColumn(LocalDateTime attribute) {
		
		if (attribute == null ) return null;
		
		
		
		return attribute.format(TimeUtility.DB_DATE_TIME_FORMATTER);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		return LocalDateTime.parse(dbData, TimeUtility.DB_DATE_TIME_FORMATTER);
	}
	
	

}
