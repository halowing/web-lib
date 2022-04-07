package com.halowing.spring.web.exception;

import org.springframework.http.HttpStatus;

public class HttpNotFoundException extends DefaultApplicationException  {

	private static final long serialVersionUID = -1702667171228990922L;
	
	public HttpNotFoundException() {
		super(HttpStatus.NOT_FOUND);
	}
	
	public HttpNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND,message);
	}

}
