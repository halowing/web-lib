package com.halowing.spring.web.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "페이징 및 검색을 위한 질의 객체")
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = -4583263585027144214L;
	
	@ApiModelProperty(value = "검색의 시작일")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;
	
	@ApiModelProperty(value = "검색의 종료일")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate endDate;
	
	@ApiModelProperty(value = "검색의 시작 시각")
	@DateTimeFormat(iso = ISO.TIME, fallbackPatterns = "HH:mm:ss")
	private LocalTime startTime;
	
	@ApiModelProperty(value = "검색의 종료 시각")
	@DateTimeFormat(iso = ISO.TIME, fallbackPatterns = "HH:mm:ss")
	private LocalTime endTime;
	
	@ApiModelProperty(value = "검색의 시작 일시")
	@DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss"})
	private LocalDateTime startDateTime;
	
	@ApiModelProperty(value = "검색의 종료 일시")
	@DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss"})
	private LocalDateTime endDateTime;
	
	@ApiModelProperty(value = "검색어")
	private String[] searchWord;
	
	@ApiModelProperty(value = "검색 영역")
	private String[] searchScope;

	@Override
	public String toString() {
		return "SearchRequest [startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
				+ ", searchWord=" + Arrays.toString(searchWord) + ", searchScope=" + Arrays.toString(searchScope) + "]";
	}

	/* GETTERS */
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public String[] getSearchWord() {
		return searchWord;
	}

	public String[] getSearchScope() {
		return searchScope;
	}

	/* SETTERS */
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public void setSearchWord(String[] searchWord) {
		
		if(searchWord == null || searchWord.length == 0) return;
		
		this.searchWord = split(searchWord);
	}

	public void setSearchScope(String[] searchScope) {
		if(searchScope == null || searchScope.length == 0) return;
		this.searchScope = split(searchScope);
	}
	
	private String[] split(String[] searchWord){
		
		List<String> wordList = new ArrayList<>();
		
		Arrays.asList(searchWord).stream()
		.filter(it -> !it.trim().replaceAll("[#,]", "").isBlank())
		.forEach(it -> {
			wordList.addAll(
				Arrays.asList(
					it.trim()
					.replaceAll("[,\\s]", "#")
					.replaceAll("#+", "#")
					.replaceAll("^#", "").split("#")
				)
			);
		})
		;
		
		return (String[]) wordList.toArray();
	}
}
