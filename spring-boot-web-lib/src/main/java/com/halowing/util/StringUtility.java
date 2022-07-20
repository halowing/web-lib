package com.halowing.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtility {

	public static boolean isEmpty(String str) {
		if(str == null || str.isEmpty()) return true;
		return false;
	}
	
	public static boolean isBlank(String str) {
		if(str == null || str.trim().isEmpty()) return true;
		return false;
	}
	
	public static String[] splitBySharp(String[] searchWord){
		
		List<String> wordList = new ArrayList<>();
		
		Arrays.asList(searchWord).stream()
		.filter(it -> !it.trim().replaceAll("[#,]", "").isBlank())
		.forEach(it -> {
			wordList.addAll(
				Arrays.asList( split(it) )
			);
		})
		;
		
		return (String[]) wordList.toArray();
	}
	
	public static String[] splitBySharp(String searchWord){
		
		if(searchWord == null 
				|| searchWord.trim().replaceAll("[#,]", "").isBlank())
			return new String[0];
		
		return split(searchWord);
	}
	
	private static String[] split(String searchWord) {
		
		return searchWord.trim()
				.replaceAll("[,\\s]", "#")
				.replaceAll("#+", "#")
				.replaceAll("^#", "").split("#");
	}
}
