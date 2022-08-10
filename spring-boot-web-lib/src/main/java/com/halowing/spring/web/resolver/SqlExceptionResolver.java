package com.halowing.spring.web.resolver;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.halowing.spring.web.code.ErrorCode;

public class SqlExceptionResolver extends AbstractHandlerExceptionResolver {
	
	private final LocaleResolver localeResolver;
	private final MessageSource messageSource;
	
	public SqlExceptionResolver(
			LocaleResolver localeResolver,
			MessageSource messageSource
			) {
		
		setOrder(HIGHEST_PRECEDENCE);
		
		this.localeResolver = localeResolver;
		this.messageSource = messageSource;
		
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse servletResponse, Object handler,
			Exception ex) {
		
		if(!(ex.getCause() instanceof SQLException)) return null;
		
		String code = null;
		if(ex instanceof DuplicateKeyException)
			code = ErrorCode.DUPLICATE_KEY;
		else
			code = ErrorCode.SQL;
	
		return ResolverUtil.getModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, code, localeResolver.resolveLocale(request), messageSource, ex.getMessage());
	}
}
