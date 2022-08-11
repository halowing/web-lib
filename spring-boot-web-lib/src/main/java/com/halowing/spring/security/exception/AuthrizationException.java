package com.halowing.spring.security.exception;

import org.springframework.http.HttpStatus;

import com.halowing.spring.web.code.ErrorCode;
import com.halowing.spring.web.exception.WebApplicationException;

public class AuthrizationException extends WebApplicationException{

	private static final long serialVersionUID = -2486241735160690747L;
	
//	private static final String MESSAGE_FORMAT = "Login user '%s' don't have authority for this resource.";

	public AuthrizationException(String id, String... targets) {
		super( HttpStatus.FORBIDDEN , ErrorCode.INVALID_AUTHORIZATION, id + targets );
	}
}
