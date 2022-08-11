package com.halowing.spring.security.exception;

import org.springframework.http.HttpStatus;

import com.halowing.spring.web.code.ErrorCode;
import com.halowing.spring.web.exception.WebApplicationException;

public class AuthenticationException extends WebApplicationException{

	private static final long serialVersionUID = -2486241735160690747L;
	
	public AuthenticationException(String id) {
		super( HttpStatus.FORBIDDEN , ErrorCode.INVALID_AUTHENTICATION, id  );
	}
}
