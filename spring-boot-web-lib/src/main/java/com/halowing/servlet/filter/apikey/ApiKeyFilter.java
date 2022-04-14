package com.halowing.servlet.filter.apikey;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ApiKeyFilter implements Filter{
	
	private final static String HEADER_NAME = "api-key";
	
	private final ApiKeyService service;
	
	public ApiKeyFilter(ApiKeyService service) {
		super();
		this.service = service;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Enumeration<String> headers = httpRequest.getHeaderNames();
		String headerName = null;
		String value = null;
		while(headers.hasMoreElements()) {
			headerName = headers.nextElement();
			
			if(HEADER_NAME.equalsIgnoreCase(headerName))
			{
				value = httpRequest.getHeader(headerName);
				break;
			}
			else
				headerName = null;
		}
		
		if(headerName == null || value == null || value.isBlank()  )
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,"api_key is not present.");
			
		if(!service.hasApiKey(value))
//			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,"It is not granted to access this system.\n Api-key="+value);
			throw new ServletException("It is not granted to access this system.\n Api-key="+value);
		
		chain.doFilter(request, response);
	}

}
