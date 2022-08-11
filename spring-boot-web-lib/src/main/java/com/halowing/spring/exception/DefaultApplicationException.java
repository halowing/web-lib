package com.halowing.spring.exception;

import java.util.Arrays;

import com.halowing.spring.web.code.ErrorCode;
import com.halowing.util.StringUtility;

public class DefaultApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8732186396685769772L;
	
	private String code = ErrorCode.ETC;
	private String[] args;
	
	public DefaultApplicationException() {
		this(null , null);
	}
	
	public DefaultApplicationException(String message) {
		this(message, null );
	}

	public DefaultApplicationException(String message, String code, String... args) {
		super(message == null? ErrorCode.ETC :message);
		
		if(!StringUtility.isBlank(code))
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
		return "DefaultApplicationException [code=" + code + ", args=" + Arrays.toString(args) + "]";
	}

	public String getCode() {
		return code;
	}

	public String[] getArgs() {
		return args;
	}
	
	
	
}
