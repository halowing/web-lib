package com.halowing.spring.web.exception;

import org.springframework.http.HttpStatus;

@Deprecated
public class HttpConflictException extends WebApplicationException  {

	private static final long serialVersionUID = -1702667171228990922L;
	
	public HttpConflictException() {
		super(HttpStatus.CONFLICT);
	}
	
	public HttpConflictException(String id) {
		super(HttpStatus.CONFLICT,"ID is "+id+". and It is conflict.");
	}

}
