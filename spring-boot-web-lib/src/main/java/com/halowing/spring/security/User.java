package com.halowing.spring.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

/**
 * 로그인한 User의 정보
 *
 */
@Validated
public class User extends LoginUser {

	/*
	 * 사용자 관리 번호
	 */
	private String id;
	
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	private String phone;
	
	private String departmentId;
	
	private String departmentName;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", departmentId="
				+ departmentId + ", departmentName=" + departmentName + ", toString()=" + super.toString() + "]";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
