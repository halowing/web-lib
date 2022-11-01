package com.halowing.spring.web.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.halowing.spring.client.RestTemplateAdaptor;


@Configuration
public class RestTemplateConfiguration {
	
	@Value("${app.restTemplate.connect-timeout:30000}")
	private Long connectTimeout;
	
	@Value("${app.restTemplate.read-timeout:30000}")
	private Long readTimeout;

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
		
		restTemplateBuilder
			.additionalMessageConverters(mappingJackson2HttpMessageConverter)
			.setConnectTimeout(Duration.ofMillis(connectTimeout))
			.setReadTimeout(Duration.ofMillis(readTimeout))
			;
		
		return restTemplateBuilder.build();
	}
	
	@Bean
	RestTemplateAdaptor restTemplateAdaptor(RestTemplate restTemplate) {
		return new RestTemplateAdaptor(restTemplate);
	}
	
}
