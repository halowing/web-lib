package com.halowing.spring.web.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.halowing.util.StringUtility;

public class DefaultResponse implements Serializable {

	private static final long serialVersionUID = -1234462789808842557L;
	
	private final Integer status;
	private final String message;
	
	private LocalDateTime requestDateTime;
	private LocalDateTime responseDateTime;

	public DefaultResponse(HttpStatus httpStatus, String message) {
		this.status = httpStatus.value();
		this.message = StringUtility.isBlank(message) ? httpStatus.getReasonPhrase() : message;
	}
	
	public DefaultResponse(HttpStatus httpStatus) {
		this(httpStatus,null);
	}

	@Override
	public String toString() {
		return "DefaultResponse [status=" + status + ", message=" + message + ", requestDateTime=" + requestDateTime
				+ ", responseDateTime=" + responseDateTime + "]";
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}

	public LocalDateTime getResponseDateTime() {
		return responseDateTime;
	}

	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public void setResponseDateTime(LocalDateTime responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
}
