package com.halowing.spring.web.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.halowing.spring.web.code.ErrorCode;

public class DefaultExceptionResolver extends AbstractHandlerExceptionResolver {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionResolver.class);
	
	private final LocaleResolver localeResolver;
	private final MessageSource messageSource;
	
	public DefaultExceptionResolver(LocaleResolver localeResolver, MessageSource messageSource) {
		this.localeResolver = localeResolver;
		this.messageSource = messageSource;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse servletResponse, Object handler,
			Exception ex) {
	
		log.error("Exception is \n",ex);
		
		return ResolverUtil.getModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ETC, localeResolver.resolveLocale(request), messageSource, ex.getMessage());
	}
}
