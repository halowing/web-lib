package com.halowing.spring.web.dto.response;


import org.springframework.http.HttpStatus;

public class CreatedResponse extends DefaultResponse{

	public CreatedResponse() {
		super(HttpStatus.CREATED);
	}

	private static final long serialVersionUID = -8918304779280171220L;

}
