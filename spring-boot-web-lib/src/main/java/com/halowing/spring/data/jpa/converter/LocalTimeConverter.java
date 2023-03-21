package com.halowing.spring.data.jpa.converter;

import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.halowing.util.TimeUtility;

@Converter
public class LocalTimeConverter implements AttributeConverter<LocalTime, String> {
	
	@Override
	public String convertToDatabaseColumn(LocalTime attribute) {
		
		if (attribute == null ) return null;
		
		return attribute.format(TimeUtility.DB_TIME_FORMATTER);
	}

	@Override
	public LocalTime convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		return LocalTime.parse(dbData, TimeUtility.DB_TIME_FORMATTER);
	}
	
	

}
