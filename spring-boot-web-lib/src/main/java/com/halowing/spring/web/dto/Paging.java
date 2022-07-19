package com.halowing.spring.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author "sgkim"
 *
 */
@ApiModel(description = "페이징 객체")
public class Paging {

	@ApiModelProperty(value = "검색할 컨텐트의 시작 위치, 첫페이지는 0 , offset = (page-1)*limit")
	private Integer offset;
	
	@ApiModelProperty(value = "검색할 컨텐트의 갯 수")
	private Integer limit;
	
	@Override
	public String toString() {
		return "Paging [offset=" + offset + ", limit=" + limit + "]";
	}

	/*
	 * GETTERS
	 */
	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}
	
	/*
	 * SETTERS
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
		
}
