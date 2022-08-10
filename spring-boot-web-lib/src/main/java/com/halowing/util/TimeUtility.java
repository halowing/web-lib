package com.halowing.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtility {
	
	public final static DateTimeFormatter DB_DATE_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	
	public final static DateTimeFormatter DB_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	public final static DateTimeFormatter DB_DATE_FORMATTER =  DateTimeFormatter.ofPattern("yyyyMMdd");
	
	public final static DateTimeFormatter DB_TIME_FORMATTER =  DateTimeFormatter.ofPattern("HHmmss");
	
	public final static DateTimeFormatter DB_HOUR_MINUTE_FORMATTER =  DateTimeFormatter.ofPattern("HHmm");

	public final static DateTimeFormatter JSON_DATE_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
	
	public final static DateTimeFormatter JSON_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public final static DateTimeFormatter JSON_DATE_FORMATTER =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public final static DateTimeFormatter JSON_HOUR_MINUTE_FORMATTER =  DateTimeFormatter.ofPattern("HH:mm");

	public final static DateTimeFormatter JSON_TIME_FORMATTER =  DateTimeFormatter.ofPattern("HH:mm:ss");

	
	public static ZonedDateTime parseZonedDateTimeFromUtcTime(String utcTime, String formatPattern, ZoneId zoneId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
		LocalDateTime localDateTime = LocalDateTime.parse(utcTime, formatter);
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(localDateTime, ZoneOffset.UTC, zoneId);
		return zonedDateTime;
	}
	
	/**
	 * 
	 * @param utcTime
	 * @param formatPattern
	 * @return
	 */
	public static ZonedDateTime parseZonedDateTimeOfSystem(String utcTime,String formatPattern) {
		return parseZonedDateTimeFromUtcTime(utcTime, formatPattern, ZoneId.systemDefault());
	}
	
	public static String toString(Date date, String formatPattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
		return sdf.format(date);
	}
	
	public static String toString(LocalDateTime time, String formatPattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
		return time.format(formatter);
	}
	
	
	/**
	 * 주어진 시간값을 요청한 formatPattern 형태로 출력
	 * @param time
	 * @param formatPattern
	 * @return
	 */
	public static String toString(ZonedDateTime time, String formatPattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
		return time.format(formatter);
	}
	
	/**
	 * 주어진 UTC 시간을 파싱하여 주어진 로컬 지역시간으로 변환하여 요청한 formatPatternOut 형태로 반환
	 * @param utcTime
	 * @param formatPatternIn
	 * @param zoneId
	 * @param formatPatternOut
	 * @return
	 */
	public static String toString (String utcTime, String formatPatternIn, ZoneId zoneId, String formatPatternOut ) {
		return toString(parseZonedDateTimeFromUtcTime(utcTime, formatPatternIn, zoneId),formatPatternOut);
	}
	
	/**
	 * 현재 시간을 UTC time으로 반환, 
	 * pattern 예제 : yyyyMMddHHmmss, yyyy-MM-dd HH:mm:ss.SSS
	 * @param pattern
	 * @return
	 */
	public static String getUtcSysdate(String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		ZonedDateTime time = ZonedDateTime.now(ZoneId.of("UTC"));
		return time.format(formatter);
	}

	public static String getLocalSysdate(String pattern) {
		
		return toString(LocalDateTime.now(), pattern);
	}
}
