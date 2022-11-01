package com.halowing.spring.web.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.halowing.spring.web.dto.Paging;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class SearchResponseDTO<T> extends Paging {
	
	private static final long serialVersionUID = -3403534010549394462L;
	
	private Integer totalCount;
	private List<T> data;
	
	public SearchResponseDTO() {
		super();
	}
	
	public SearchResponseDTO( int offset, int limit) {
		super(offset, limit);
	}
	
	public SearchResponseDTO( Paging page) {
		super(page.getOffset(), page.getLimit());
	}

	@Override
	public String toString() {
		return "SearchResponseDTO [totalCount=" + totalCount + ", data=" + data + "]";
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
	
}
