package com.halowing.spring.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

/**
 * 로그인한 User의 정보
 *
 */
@Validated
public class LoginUser {

	@NotBlank
	private String loginId;
	
	@NotBlank
	private Role role;
	
	@URL
	private String imageUrl;
	
	@URL
	private String profileUrl;

	@Override
	public String toString() {
		return "LoginUser [loginId=" + loginId + ", role=" + role + ", imageUrl=" + imageUrl + ", profileUrl="
				+ profileUrl + "]";
	}

	public String getLoginId() {
		return loginId;
	}

	public Role getRole() {
		return role;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	
	
}
