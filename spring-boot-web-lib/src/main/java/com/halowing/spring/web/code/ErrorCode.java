package com.halowing.spring.web.code;

import javax.validation.constraints.NotNull;

public class ErrorCode {

	public static final String ETC					= 	"error.etc";
	public static final String NOT_FOUND			=	"error.notfound";
	public static final String SECURITY_INVALID		=	"error.security.invalid"; 
	public static final String EMPTY_INPUT			=	"error.input.empty";
	
	public static final String MEMBER_NOT_FOUND		=	"error.member.notfound"; 
	public static final String DUPLICATED_LOGINID	=	"error.member.loginId.duplicated";
	
	public static final String INVALID_PARAMETER 	= 	"error.request.parameter.invalid";
	public static final String CONFLICT 			= 	"error.request.conflict";
	
	public static final String DUPLICATE_KEY 		= 	"error.sql.duplicateKey";
	public static final String SQL 					= 	"error.sql.etc";
	
}