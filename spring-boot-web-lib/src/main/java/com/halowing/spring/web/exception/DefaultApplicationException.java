package com.halowing.spring.web.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.halowing.spring.web.code.ErrorCode;
import com.halowing.util.StringUtility;

public class DefaultApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8732186396685769772L;
	
	private String code = ErrorCode.ETC;
	private String[] args;
	
	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public DefaultApplicationException() {
		this(null, null , null);
	}
	
	public DefaultApplicationException(String message) {
		this(null, message, null );
	}
	
	public DefaultApplicationException(HttpStatus status) {
		this(status, null, null );
	}

	public DefaultApplicationException(HttpStatus status, String message) {
		this(status, message, null );
	}	

	public DefaultApplicationException(HttpStatus status, String message, String code, String... args) {
		super(message == null? status.getReasonPhrase() :message);
		
		if(status != null)
			this.status = status ;
		
		if(StringUtility.isBlank(code))
			this.code = code;
		
		this.args = args;
	}
	
	public DefaultApplicationException(String message ,Throwable cause) {
		super(message,cause);
	}
	
	public DefaultApplicationException(Throwable cause) {
		super(cause);
	}
	
	@Override
	public String toString() {
		return "DefaultApplicationException [code=" + code + ", args=" + Arrays.toString(args) + ", status=" + status
				+ "]";
	}

	public HttpStatus getStatus() {
		return this.status;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String[] getArgs() {
		return this.args;
	}

	
	
}
