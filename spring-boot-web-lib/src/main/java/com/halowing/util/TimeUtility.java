package com.halowing.util;

import java.time.format.DateTimeFormatter;

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

}
