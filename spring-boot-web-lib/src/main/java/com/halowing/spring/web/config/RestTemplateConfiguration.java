package com.halowing.spring.web.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.halowing.spring.client.RestTemplateAdaptor;


@Configuration
public class RestTemplateConfiguration {
	
	private static final long readTimeout = 30000;

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
		
		restTemplateBuilder
			.additionalMessageConverters(mappingJackson2HttpMessageConverter)
			.setConnectTimeout(Duration.ofMillis(readTimeout))
			.setReadTimeout(Duration.ofMillis(readTimeout))
			;
		
		return restTemplateBuilder.build();
	}
	
	@Bean
	RestTemplateAdaptor restTemplateAdaptor(RestTemplate restTemplate) {
		return new RestTemplateAdaptor(restTemplate);
	}
	
}
