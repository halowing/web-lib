package com.halowing.spring.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateAdaptor {
	
	private static final Logger log = LoggerFactory.getLogger(RestTemplateAdaptor.class);
	
	private final RestTemplate restTemplate;
	
	public RestTemplateAdaptor(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public <T, V> T exchange(String url, HttpMethod httpMethod
			, HttpEntity<V> requestEntity, Class<T> resType, Object... urlVariables) {
		
		ResponseEntity<T> responseEntity = null;
		
		try{
			log.debug("restTemplate : {}", restTemplate);
			responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, resType, urlVariables);	
			log.info("api call success : [{}] [{}] [{}] [{}]", url, requestEntity, urlVariables, responseEntity);
		}catch(ResourceAccessException rae){
			log.error("api call error : [{}] [{}] [{}] [{}]", url, requestEntity, urlVariables, rae);
			throw new RestClientException("url="+url+", error ="+ rae.getMessage());
		}catch(RestClientException rce){
			log.error("api call error : [{}] [{}] [{}] [{}]", url, requestEntity, urlVariables, rce);
			throw new RestClientException("url="+url+", error ="+ rce.getMessage());
		}catch(Exception e){
			log.error("api call error : [{}] [{}] [{}] [{}]", url, requestEntity, urlVariables, e);
			throw new RestClientException("url="+url+", error ="+e.getMessage());
		}
		
		return responseEntity.getBody();
	}
	
	public static <T> HttpEntity<T> getJsonRequestEntity(T body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		final HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);
		
		return requestEntity;
	}
}
