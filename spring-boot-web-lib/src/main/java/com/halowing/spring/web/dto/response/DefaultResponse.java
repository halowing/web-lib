package com.halowing.spring.web.dto.response;

import java.io.Serializable;

public class DefaultResponse implements Serializable {

	private static final long serialVersionUID = -1234462789808842557L;
	
	protected final Integer status;
	
	protected final String message;

	public DefaultResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
