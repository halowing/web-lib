package com.halowing.spring.web.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.halowing.spring.web.code.ErrorCode;
import com.halowing.spring.web.exception.DefaultApplicationException;

public class DefaultApplicationExceptionResolver extends AbstractHandlerExceptionResolver {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultApplicationExceptionResolver.class);
	
	private final LocaleResolver localeResolver;
	private final MessageSource messageSource;
	
	public DefaultApplicationExceptionResolver(LocaleResolver localeResolver, MessageSource messageSource) {
		this.localeResolver = localeResolver;
		this.messageSource = messageSource;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse servletResponse, Object handler,
			Exception ex) {
	
		if(!( ex instanceof DefaultApplicationException)) return null;
		
		DefaultApplicationException daex = (DefaultApplicationException) ex;
		
		return ResolverUtil.getModelAndView(daex.getStatus(), daex.getCode(), daex.getArgs(),localeResolver.resolveLocale(request), messageSource, daex.getLocalizedMessage());
	}
}
