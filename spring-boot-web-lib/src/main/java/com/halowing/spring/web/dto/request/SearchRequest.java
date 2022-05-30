package com.halowing.spring.web.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "페이징 및 검색을 위한 질의 객체")
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = -4583263585027144214L;
	
//	Search
	@ApiModelProperty(value = "검색의 시작 시점 ")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime searchStartDateTime;
	
	@ApiModelProperty(value = "검색의 종료 시점")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime searchEndDateTime;
	
	@ApiModelProperty(value = "검색어")
	private String searchWord;
	
	@ApiModelProperty(value = "검색 영역")
	private String[] scope;
	
//	paging
	@ApiModelProperty(value = "검색할 컨텐트의 시작 위치, 첫페이지는 0 , offset = (page-1)*limit")
	private Integer offset;
	@ApiModelProperty(value = "검색할 컨텐트의 갯 수")
	private Integer limit;
	
	public SearchRequest() {
		super();
	}
	
	
	
//	toString
	
	@Override
	public String toString() {
		return "SearchRequest [searchStartDateTime=" + searchStartDateTime + ", searchEndDateTime=" + searchEndDateTime
				+ ", searchWord=" + searchWord + ", scope=" + Arrays.toString(scope) + ", offset=" + offset + ", limit="
				+ limit + "]";
	}

//	Getters And Setters	

	public LocalDateTime getSearchStartDateTime() {
		return searchStartDateTime;
	}

	public void setSearchStartDateTime(LocalDateTime searchEndDateTime) {
		this.searchStartDateTime = searchEndDateTime;
	}

	public LocalDateTime getSearchEndDateTime() {
		return searchEndDateTime;
	}

	public void setSearchEndDateTime(LocalDateTime searchEndDateTime) {
		this.searchEndDateTime = searchEndDateTime;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}



	public String[] getScope() {
		return scope;
	}



	public void setScope(String[] scope) {
		this.scope = scope;
	}

}
