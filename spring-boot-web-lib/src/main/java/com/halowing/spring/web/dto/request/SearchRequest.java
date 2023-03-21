package com.halowing.spring.web.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.halowing.util.StringUtility;


/**
 * 페이징 및 검색을 위한 질의 객체
 * @author halow
 *
 */
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = -4583263585027144214L;
	
	/**
	 * 검색의 시작일
	 */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;
	
	/**
	 * 검색의 종료일
	 */
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate endDate;
	
	/**
	 * 검색의 시작 시각
	 */
	@DateTimeFormat(iso = ISO.TIME, fallbackPatterns = "HH:mm:ss")
	private LocalTime startTime;
	
	/**
	 * 검색의 종료 시각
	 */
	@DateTimeFormat(iso = ISO.TIME, fallbackPatterns = "HH:mm:ss")
	private LocalTime endTime;
	
	/**
	 * 검색의 시작 일시
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss"})
	private LocalDateTime startDateTime;
	
	/**
	 * 검색의 종료 일시
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss"})
	private LocalDateTime endDateTime;
	
	/**
	 * 검색어
	 */
	private String searchWord;
	
	/**
	 * 검색영역
	 */
	private String searchScope;
	
	/**
	 * 검색어들
	 */
	private String[] searchWords;
	
	/**
	 * 검색영역들
	 */
	private String[] searchScopes;

	

	/* GETTERS */
	
	@Override
	public String toString() {
		return "SearchRequest [startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
				+ ", searchWord=" + searchWord + ", searchScope=" + searchScope + ", searchWords="
				+ Arrays.toString(searchWords) + ", searchScopes=" + Arrays.toString(searchScopes) + "]";
	}

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

	public String getSearchWord() {
		return searchWord;
	}

	public String getSearchScope() {
		return searchScope;
	}
	
	public String[] getSearchWords() {
		return searchWords;
	}

	public String[] getSearchScopes() {
		return searchScopes;
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
	
	public void setSearchWord(String searchWord) {
		
		this.searchWord = searchWord;
	}
	
	
	public void setSearchScope(String searchScope) {
		this.searchScope = searchScope;
	}

	public void setSearchWords(String[] searchWords) {
		
		if(searchWords == null || searchWords.length == 0) return;
		
		this.searchWords = StringUtility.getTags(searchWord);
	}

	public void setSearchScopes(String[] searchScopes) {
		if(searchScopes == null || searchScopes.length == 0) return;
		this.searchScopes = StringUtility.getTags(searchWord);
	}
	
}
