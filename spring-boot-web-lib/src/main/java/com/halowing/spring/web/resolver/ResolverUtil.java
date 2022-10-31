package com.halowing.spring.web.resolver;

import java.util.Locale;

import javax.validation.constraints.NotNull;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.halowing.spring.web.dto.response.DefaultResponse;

@Validated
public class ResolverUtil {
	
	public static ModelAndView getModelAndView(
			@NotNull HttpStatus status,
			@NotNull String errorCode,
			@NotNull Locale locale, 
			@Nullable MessageSource messageSource, 
			@Nullable String errorMessage
			) {
		
		DefaultResponse response = null;
		if(messageSource == null) {
			response = new DefaultResponse(	status,errorMessage);
		}else {
			response = new DefaultResponse(
						status, 
						String.format("%s (%s)",  messageSource.getMessage(errorCode,null,"Error",locale), errorMessage ) 
					);
		}
		
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.setStatus(status);
		mav.addObject("message",response.getMessage());
		mav.addObject("status",response.getStatus());
		mav.addObject("requestDateTime",response.getRequestDateTime());
		mav.addObject("responseDateTime",response.getResponseDateTime());
		
		return mav;
	}

	public static ModelAndView getModelAndView(
			@NotNull HttpStatus status, 
			@NotNull String errorCode, 
			@Nullable String[] args, 
			@Nullable Locale locale,
			@Nullable MessageSource messageSource, 
			@Nullable String errorMessage
			) {

		DefaultResponse response = null;
		if(messageSource == null) {
			response = new DefaultResponse(	status,errorMessage);
		}else {
			response = new DefaultResponse(
						status, 
						String.format("%s (%s)",  messageSource.getMessage(errorCode,args,"Error",locale), errorMessage ) 
					);
		}
		
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),"response",response);
		mav.setStatus(status);
		
		return mav;
	}

}
