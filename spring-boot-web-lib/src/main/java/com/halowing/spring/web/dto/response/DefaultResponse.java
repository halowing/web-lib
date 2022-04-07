package com.halowing.spring.web.dto.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class DefaultResponse implements Serializable {

	private static final long serialVersionUID = -1234462789808842557L;
	
	protected final Integer status;
	
	protected final String message;

	public DefaultResponse(HttpStatus httpStatus, String message) {
		this.status = httpStatus.value();
		this.message = message;
	}
	
	public DefaultResponse(HttpStatus httpStatus) {
		this.status = httpStatus.value();
		this.message = null;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "DefaultResponse [status=" + status + ", message=" + message + "]";
	}
	
	

}
