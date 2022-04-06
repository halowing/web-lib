package com.halowing.spring.web.dto.response;


import org.springframework.http.HttpStatus;

public class SuccessResponse extends DefaultResponse{

	public SuccessResponse() {
		super(HttpStatus.OK.value(), null);
	}

	private static final long serialVersionUID = -8918304779280171220L;

}
