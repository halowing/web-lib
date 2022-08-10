package com.halowing.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

public class JacksonUtil {
	
	private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	public static ObjectMapper jsonMapperJava8DateTimeMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
//		mapper.registerModule(jsonMapperJava8DateTimeModule());
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		return mapper;
	}
	
	public static Module jsonMapperJava8DateTimeModule() {
		
		SimpleModule module = new SimpleModule();
		
//		LocalDate
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
		module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
//		LocalTime
		module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
		module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
//		LocalDateTime
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//		OffsetDateTime
		module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {

			@Override
			public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt)
					throws IOException, JsonProcessingException {
				return OffsetDateTime.parse(p.getValueAsString(), DateTimeFormatter.ISO_DATE_TIME);
			}
			
		});
		module.addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {

			@Override
			public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException {
				gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
			}
			
		});
//		Date
		module.addDeserializer(Date.class, new JsonDeserializer<Date>() {

			@Override
			public Date deserialize(JsonParser p, DeserializationContext ctxt)
					throws IOException, JsonProcessingException {
				DateFormat df = new SimpleDateFormat(PATTERN);
				try {
					return df.parse(p.getValueAsString());
				} catch (ParseException e) {
					e.printStackTrace();
					throw new IOException(e.getCause());
				} 
			}
			
		});
		module.addSerializer(Date.class, new JsonSerializer<Date>() {

			@Override
			public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException {
				DateFormat df = new SimpleDateFormat(PATTERN);
				
				gen.writeString(df.format(value));
			}
			
		});
		
		return module;
	}
}
