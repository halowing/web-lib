package com.halowing.spring.web.exception;

public class HttpNotFoundException extends DefaultApplicationException  {

	private static final long serialVersionUID = -1702667171228990922L;
	
	public HttpNotFoundException() {
		super(404);
	}
	
	public HttpNotFoundException(String message) {
		super(404,message);
	}

}
