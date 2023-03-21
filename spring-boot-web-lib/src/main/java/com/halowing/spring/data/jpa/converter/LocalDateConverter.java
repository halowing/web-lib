package com.halowing.spring.data.jpa.converter;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.halowing.util.TimeUtility;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
	
	@Override
	public String convertToDatabaseColumn(LocalDate attribute) {
		
		if (attribute == null ) return null;
		
		
		
		return attribute.format(TimeUtility.DB_DATE_FORMATTER);
	}

	@Override
	public LocalDate convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		return LocalDate.parse(dbData, TimeUtility.DB_DATE_FORMATTER);
	}
	
	

}
