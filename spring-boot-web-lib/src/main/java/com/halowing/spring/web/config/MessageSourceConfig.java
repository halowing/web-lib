package com.halowing.spring.web.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Configuration
public class MessageSourceConfig implements WebMvcConfigurer  {

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setDefaultLocale(Locale.US);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(false);
		messageSource.setCacheSeconds(180);
		return messageSource;
	}
	
//	@Bean(name = "localeResolver")
//	public LocaleResolver sessionLocaleResolver() {
//		
//		SessionLocaleResolver resolver = new SessionLocaleResolver();
//		resolver.setLocaleAttributeName(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
//		return resolver;
//	}
	
	@Bean(name = "localeResolver")
	LocaleResolver localeResolver() {
		
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		return resolver;
	}
	
//	@Bean(name = "localeResolver")
//	LocaleResolver headerLocaleResolver() {
//		
//		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
//		List<Locale> localeList = new ArrayList<>();
//		localeList.add(Locale.US);
//		localeList.add(Locale.KOREA);
//		resolver.setSupportedLocales(localeList);
//		resolver.setDefaultLocale(Locale.US);
//		return resolver;
//	}
	
	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor it = new LocaleChangeInterceptor();
		it.setParamName("lang");
		return it;
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
