package com.halowing.spring.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import com.halowing.spring.web.resolver.ConstraintViolationExceptionResolver;
import com.halowing.spring.web.resolver.DefaultExceptionResolver;
import com.halowing.spring.web.resolver.MethodArgumentNotValidExceptionResolver;
import com.halowing.spring.web.resolver.SqlExceptionResolver;

@Configuration
public class ExceptionResolverConfiguration {
	
//	@Bean
	public SqlExceptionResolver sqlExceptionResolver(
				LocaleResolver localeResolver,
				MessageSource messageSource
			) {
		return new SqlExceptionResolver(localeResolver, messageSource);
	}
	
//	@Bean
	public MethodArgumentNotValidExceptionResolver methodArgumentNotValidExceptionResolver(
			LocaleResolver localeResolver,
			MessageSource messageSource
		) {
		return new MethodArgumentNotValidExceptionResolver(localeResolver, messageSource);
	}
	
//	@Bean
	public ConstraintViolationExceptionResolver constraintViolationExceptionResolver(
			LocaleResolver localeResolver,
			MessageSource messageSource
		){
		return new ConstraintViolationExceptionResolver(localeResolver, messageSource);
	}
	
//	@Bean
	public DefaultExceptionResolver defaultExceptionResolver(
			LocaleResolver localeResolver,
			MessageSource messageSource
		) {
		return new DefaultExceptionResolver(localeResolver, messageSource);
	}
}
