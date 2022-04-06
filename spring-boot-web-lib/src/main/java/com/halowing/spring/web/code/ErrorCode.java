package com.halowing.spring.web.code;

public enum ErrorCode {

	NOT_FOUND					("response.error.notfound"), 
	MEMBER_NOT_FOUND			("response.error.member.notfound"), 
	DUPLICATED_LOGINID			("response.error.member.loginId.duplicated"),
	SECURITY_INVALID			("response.error.security.invalid"), 
	EMPTY_INPUT					("response.error.input.empty"),
	
	;
	
	String code;
	
	ErrorCode(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
