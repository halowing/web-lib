package com.halowing.spring.data.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {
	
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		
		if (attribute == null ) return null;
		
		return attribute?"Y":"N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		if("Y".equalsIgnoreCase(dbData)) return true;
		
		return false;
	}
	
	

}
