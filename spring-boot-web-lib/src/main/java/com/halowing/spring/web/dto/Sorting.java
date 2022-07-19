package com.halowing.spring.web.dto;

import java.util.Arrays;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "List 졍렬 참조 객체")
public class Sorting {

	@ApiModelProperty(value = "내림차순 정렬 항목")
	private String[] desc;
	
	@ApiModelProperty(value = "오름차순 정렬 항목")
	private String[] asc;

	@Override
	public String toString() {
		return "Sorting [desc=" + Arrays.toString(desc) + ", asc=" + Arrays.toString(asc) + "]";
	}

	/* GETTERS */
	public String[] getDesc() {
		return desc;
	}

	public String[] getAsc() {
		return asc;
	}

	/* SETTERS */
	public void setDesc(String[] desc) {
		this.desc = desc;
	}

	public void setAsc(String[] asc) {
		this.asc = asc;
	}
	
	
}
