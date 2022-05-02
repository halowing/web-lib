package com.halowing.util;

public class StringUtility {

	public static boolean isEmpty(String str) {
		if(str == null || str.isEmpty()) return true;
		return false;
	}
	
	public static boolean isBlank(String str) {
		if(str == null || str.trim().isEmpty()) return true;
		return false;
	}
}
