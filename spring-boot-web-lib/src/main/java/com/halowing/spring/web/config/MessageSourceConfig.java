package com.halowing.spring.web.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@ConditionalOnProperty(name = "app.message-source.enable",  matchIfMissing = false)
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
	
	@Bean
	public LocaleResolver sessionLocaleResolver() {
		
		SessionLocaleResolver resolver = new SessionLocaleResolver();
//		resolver.setLocaleAttributeName(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		return resolver;
	}
	
	@Bean
	LocaleResolver cookieLocaleResolver() {
		
		CookieLocaleResolver resolver = new CookieLocaleResolver();
//		resolver.setCookieName(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		return resolver;
	}
	
	@Bean
	LocaleResolver headerLocaleResolver() {
		
		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		List<Locale> localeList = new ArrayList<>();
		localeList.add(Locale.US);
		localeList.add(Locale.KOREA);
		resolver.setSupportedLocales(localeList);
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}
	
	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor it = new LocaleChangeInterceptor();
		it.setParamName("lang");
		return it;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
