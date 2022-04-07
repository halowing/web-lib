package com.halowing.spring.web.exception;

import org.springframework.http.HttpStatus;

public class DefaultApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8732186396685769772L;
	
	private static final Integer DEFAULT_ERROR_STATUS = 500;
	
	private Integer status;
	private String message;
	


	public DefaultApplicationException() {
		this.status = DEFAULT_ERROR_STATUS;
	}
	
	public DefaultApplicationException(String message) {
		this.status = DEFAULT_ERROR_STATUS;
		this.message = message;
	}
	
	public DefaultApplicationException(HttpStatus status) {
		this(status,null);
	}

	public DefaultApplicationException(HttpStatus status, String message) {
		super(message);
		this.status = status.value();
		this.message = message;
	}	

	public DefaultApplicationException(Throwable cause) {
		super(cause);
		this.status = DEFAULT_ERROR_STATUS;
	}

	@Override
	public String toString() {
		return "DefaultApplicationException [status=" + status + ", message=" + message + "]";
	}
	
}
