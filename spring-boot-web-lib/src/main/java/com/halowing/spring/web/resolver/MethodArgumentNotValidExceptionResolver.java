package com.halowing.spring.web.resolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.halowing.spring.web.code.ErrorCode;


public class MethodArgumentNotValidExceptionResolver extends AbstractHandlerExceptionResolver {
	
	private final LocaleResolver localeResolver;
	private final MessageSource messageSource;
	
	public MethodArgumentNotValidExceptionResolver(LocaleResolver localeResolver, MessageSource messageSource) {
		setOrder(HIGHEST_PRECEDENCE);
		this.localeResolver = localeResolver;
		this.messageSource = messageSource;
	}



	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse servletResponse, Object handler,
			Exception ex) {
		
		if(!( ex instanceof MethodArgumentNotValidException)) return null;
		
		return ResolverUtil.getModelAndView(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETER, localeResolver.resolveLocale(request), messageSource, ex.getMessage());

	}

}
