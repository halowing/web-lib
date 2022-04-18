package com.halowing.spring.data.jpa.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDatetimeToStringConverter implements AttributeConverter<LocalDateTime, String> {
	
	 private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	@Override
	public String convertToDatabaseColumn(LocalDateTime attribute) {
		
		if (attribute == null ) return null;
		
		
		
		return attribute.format(FORMATTER);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		return LocalDateTime.parse(dbData, FORMATTER);
	}
	
	

}
