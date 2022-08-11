package com.halowing.spring.web.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.halowing.spring.exception.DefaultApplicationException;
import com.halowing.spring.web.code.ErrorCode;
import com.halowing.util.StringUtility;

public class WebApplicationException extends DefaultApplicationException {

	private static final long serialVersionUID = -8732186396685769772L;
	
	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public WebApplicationException() {
		this(null, null , null);
	}
	
	public WebApplicationException(String message) {
		this(null, message, null );
	}
	
	public WebApplicationException(HttpStatus status) {
		this(status, null, null );
	}

	public WebApplicationException(HttpStatus status, String message) {
		this(status, message, null );
	}	

	public WebApplicationException(HttpStatus status, String message, String code, String... args) {
		super(message, code, args);
		
		if(status != null)
			this.status = status ;
	}
	
	public WebApplicationException(String message ,Throwable cause) {
		super(message,cause);
	}
	
	public WebApplicationException(Throwable cause) {
		super(cause);
	}
	
	@Override
	public String toString() {
		return "WebApplicationException [status=" + status + ", getCode()=" + getCode() + ", getArgs()="
				+ Arrays.toString(getArgs()) + ", getMessage()=" + getMessage() + "]";
	}

	public HttpStatus getStatus() {
		return this.status;
	}
}
