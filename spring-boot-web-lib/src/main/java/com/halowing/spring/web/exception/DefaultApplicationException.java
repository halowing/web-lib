package com.halowing.spring.web.exception;

public class DefaultApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8732186396685769772L;
	
	private static final Integer DEFAULT_ERROR_STATUS = 500;
	
	private Integer status;
	private String message;
	


	public DefaultApplicationException() {
		this(DEFAULT_ERROR_STATUS,null);
	}
	
	public DefaultApplicationException(Integer status) {
		this(DEFAULT_ERROR_STATUS,null);
	}

	public DefaultApplicationException(String message) {
		this(DEFAULT_ERROR_STATUS,message);
	}

	public DefaultApplicationException(Integer status, String message) {
		super(message);
		this.status = status;
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
