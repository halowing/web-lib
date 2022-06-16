package com.halowing.spring.web.exception;

import org.springframework.http.HttpStatus;

public class DefaultApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8732186396685769772L;
	
	private static final HttpStatus DEFAULT_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	private HttpStatus status;
//	private String message;
	


	public DefaultApplicationException() {
		this.status = DEFAULT_ERROR_STATUS;
	}
	
	public DefaultApplicationException(String message) {
		super(message);
		this.status = DEFAULT_ERROR_STATUS;
	}
	
	public DefaultApplicationException(HttpStatus status) {
		this(status,null);
	}

	public DefaultApplicationException(HttpStatus status, String message) {
		super(message == null? status.getReasonPhrase() :message);
		this.status = status;
	}	

	public DefaultApplicationException(Throwable cause) {
		super(cause);
		this.status = DEFAULT_ERROR_STATUS;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return "DefaultApplicationException [status=" + status + "]";
	}
	
}
