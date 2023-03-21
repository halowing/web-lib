package com.halowing.util;

import java.io.File;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
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

public class JacksontUtility {
	
	private static final Logger log = LoggerFactory.getLogger(JacksontUtility.class);
	
	private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	private static ObjectMapper mapper;
	
	public static ObjectMapper getObjectMapper() {
		
		if(mapper != null)
			return mapper;
		
		synchronized (JacksontUtility.class) {
			
			if(mapper != null)
				return mapper;
			
			mapper = new ObjectMapper();
			
			/* LocalDateTime class 맵핑하기 위해 필요 */
			mapper.registerModule(new JavaTimeModule());
			
			/* LocalDateTime을 ISO 형식의 String으로 출력하기 위해 사용, yyyy-MM-ddTHH:mm:ss.SSS */
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			
			/* File에 쓸 떼 json이 pretty print 하게 쓰여지도록 하기 위해 사용, 그렇지 않으면 json String이 한줄로 나옴 */
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			/* naming 규칙 설정  */
			mapper.setPropertyNamingStrategy( PropertyNamingStrategies.SNAKE_CASE );
			
			return mapper;
		}
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
	
	public static <T> File saveFile(T obj, String filePath) throws IOException {
		
		log.debug("Target ={}, path={}",obj, filePath);
		
		if(obj == null ) 
			throw new NullPointerException("Input Object can't be null.");
		
		if(filePath == null || filePath.isBlank()) 
			throw new NullPointerException("File path cant't be null or blank");
		
		if(mapper == null)
			getObjectMapper();
		
		String json = mapper.writeValueAsString(obj);
		
		return StringUtility.writeToFile(json, filePath);
	}
	
	public static <T> T readFile( String filePath,  TypeReference<T> type) throws StreamReadException, DatabindException, IOException {
		
		log.debug("filePath={}",filePath);
		
		if(StringUtility.isBlank(filePath) )
			throw new NullPointerException("filePath cant't be null or blank");
		
		if(mapper == null)
			getObjectMapper();
		
		File file = new File(filePath);
		
		if(file == null || !file.exists()) 
		{
			throw new NullPointerException(String.format("file is not exist: %s", filePath));
		}
		
		return mapper.readValue(file, type);
		
	}

}
