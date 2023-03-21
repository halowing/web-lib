package com.halowing.spring.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * 페이징 객체
 * @author "sgkim"
 *
 */
public class Paging implements Serializable {
	
	private static final long serialVersionUID = -4779942866088281302L;

	/*
	 * 검색할 컨텐트의 시작 위치, 첫페이지는 0 , offset = (page-1)*limit
	 */
	@NotNull
	@Min(value = 0)
	private Integer offset;
	
	/*
	 * 검색할 컨텐트의 갯 수
	 */
	@NotNull
	@Min(value = 1)
	private Integer limit;
	
	
	public Paging() {
		
	}
	
	public Paging(@NotNull @Min(0) Integer offset, @NotNull @Min(1) Integer limit) {
		this.offset = offset;
		this.limit = limit;
	}

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
