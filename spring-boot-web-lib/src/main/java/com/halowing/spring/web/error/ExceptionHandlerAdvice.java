package com.halowing.spring.web.error;

import java.util.Locale;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Conflict;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.halowing.spring.web.code.ErrorCode;
import com.halowing.spring.web.dto.response.DefaultResponse;
import com.halowing.spring.web.exception.WebApplicationException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	private final MessageSource messageSource;
	
	public ExceptionHandlerAdvice( MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<DefaultResponse> constraintViolationExceptionHandler(ConstraintViolationException ex, Locale locale){
		log.error("ConstraintViolationException : {}",ex.getMessage());
		return getErrorResponseEntity(HttpStatus.BAD_REQUEST,ErrorCode.INVALID_PARAMETER, null,locale, ex.getMessage());
	}
	
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<DefaultResponse> httpNotFoundExceptionHandler(NotFound ex, Locale locale){
		log.error("NotFound : {}",ex.getMessage());
		return getErrorResponseEntity(ex.getStatusCode(),ErrorCode.NOT_FOUND, null,locale, ex.getMessage());
	}
	
	@ExceptionHandler(Conflict.class)
	public ResponseEntity<DefaultResponse> httpConflictExceptionHandler(Conflict ex, Locale locale){
		log.error("Conflict : {}",ex.getMessage());
		return getErrorResponseEntity(ex.getStatusCode(),ErrorCode.CONFLICT, null,locale, ex.getMessage());
	}
	
	@ExceptionHandler(WebApplicationException.class)
	public ResponseEntity<DefaultResponse> webApplicationExceptionHandler(WebApplicationException ex, Locale locale){
		log.error("WebApplicationException : errMsg = {}, args = {}", ex.getMessage(), ex.getArgs());
		
		return getErrorResponseEntity(ex.getStatus(),ex.getCode(), ex.getArgs(),locale, ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DefaultResponse> othersExceptionHandler(Exception ex, Locale locale){
		log.error("Exception : {}",ex.getMessage());
		log.trace("",ex);
//		ex.printStackTrace();
		return getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ETC, null, locale, ex.getMessage());
	}
	
	private ResponseEntity<DefaultResponse> getErrorResponseEntity(
			@NotNull HttpStatus status,
			@NotNull String errorCode,
			@Nullable String[] args,
			@NotNull Locale locale, 
			@Nullable String errorMessage
			
			){
		
		DefaultResponse body = null;
		if(messageSource == null) {
			body = new DefaultResponse(	
						status,
						errorMessage
					);
		}else {
			String responseMsg = messageSource.getMessage(errorCode,args,errorCode,locale);
			body = new DefaultResponse(
						status, 
						String.format("%s (%s)", responseMsg , errorMessage ) 
					);
		}
		
		return new ResponseEntity<DefaultResponse>(body,status);
	}

}
