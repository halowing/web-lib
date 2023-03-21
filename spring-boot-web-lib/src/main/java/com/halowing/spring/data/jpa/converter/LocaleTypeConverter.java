package com.halowing.spring.data.jpa.converter;

import java.util.Locale;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Locale.Class 를 VARCHAR로 변환
 * ko_KR, ko 형태로 입력
 * @author sgkim <sgkim@halowing.com>
 *
 */
@Converter
public class LocaleTypeConverter implements AttributeConverter<Locale, String> {
	
	@Override
	public String convertToDatabaseColumn(Locale attribute) {
		
		if (attribute == null ) return null;
		
		return attribute.toString();
	}

	@Override
	public Locale convertToEntityAttribute(String dbData) {
		
		if(dbData == null || dbData.isBlank()) return null;
		
		return getLocale(dbData);
	}
	
	private Locale getLocale(String str) {
		if(str == null) return null;
		String[] strs = str.split("_");
		if(strs.length > 1) return new Locale(strs[0], strs[1]);
		else return new Locale(strs[0]);
	}

}
