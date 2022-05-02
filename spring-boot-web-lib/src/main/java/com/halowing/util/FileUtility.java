package com.halowing.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FileUtility {
	
	private static final Logger log = LoggerFactory.getLogger(FileUtility.class);
	
	private static ObjectMapper objectMapper ;
	
	private static void initObjectMapper() {
		
		if(objectMapper != null) return;
		
		synchronized (log) {
			if(objectMapper != null) return;
			objectMapper = new ObjectMapper();
			
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		}
	}
	
	@Async
	public static void saveFile(String json, String filePath) {
		
		if(json == null || filePath == null) return ;
		
		File file = new File(filePath);
		
		if(!file.exists()) {
			
			File dir = new File(filePath.substring(0, filePath.lastIndexOf("/")));
			dir.mkdirs();
		}

		try(
				FileWriter fw = new FileWriter(file)
				) {
			
			fw.write(json);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Async
	public static <T> void saveFile(T obj, String filePath) {
		
		if(obj == null || filePath == null) return ;
		
		initObjectMapper();
		
		log.debug("Target ={}, path={}",obj, filePath);
		
		try {
			String json = objectMapper.writeValueAsString(obj);
			
			saveFile(json, filePath);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T readFile( String filePath,  TypeReference<T> type) {
		
		if(StringUtility.isBlank(filePath) || type == null)
			return null;
		
		initObjectMapper();
		
		log.debug("filePath={}",filePath);
		
		File file = new File(filePath);
		
		if(file == null || !file.exists()) 
		{
			log.error("File is not exist: {}",filePath);
			return null;
		}
		
		try {
			return objectMapper.readValue(file, type);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
			return null;
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
	}
	
	

	
}
