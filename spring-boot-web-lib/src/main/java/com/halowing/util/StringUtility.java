package com.halowing.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtility {

	/**
	 * Checking input String is null or empty 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str.isEmpty()) return true;
		return false;
	}
	
	/**
	 * Checking input String is null or only have white space
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if(str == null || str.trim().isEmpty()) return true;
		return false;
	}
	
	/**
	 * Return Tags from Input String.
	 * @param searchWord
	 * @return
	 */
	public static String[] getTags(String[] searchWord){
		
		List<String> wordList = new ArrayList<>();
		
		Arrays.asList(searchWord).stream()
		.filter(it -> !it.trim().replaceAll("[#,]", "").isBlank())
		.forEach(it -> {
			wordList.addAll(
				Arrays.asList( getTags(it) )
			);
		})
		;
		
		return wordList.toArray(new String[wordList.size()]);
	}
	
	/**
	 * 입력 문자열에서 hash tag가 있는 문자들을 추출 
	 * @param searchWord
	 * @return
	 */
	public static String[] getTags(String searchWord) {
		
		if(searchWord == null 
				|| searchWord.trim().replaceAll("[#,]", "").isBlank()
				|| !searchWord.contains("#"))
			return new String[0];
		
		String[] arr = searchWord.trim().replaceAll("\\s+", " ").replaceAll("#", " #").split("[,\\s]");
		List<String> rs = new ArrayList<>();
		
		Arrays.asList(arr).stream()
		.filter(it -> it.matches("^#\\S+"))
		.map(it -> it.replaceAll("", ""))
		.forEach(it -> {
			rs.add(it.replaceAll("[#.]", ""));
		});
		;
		
		return rs.toArray(new String[rs.size()]);
	}
	
	
	public static File writeToFile(String str, String filePath) throws IOException {
		
		if(str == null || filePath == null) return null;
		
		File file = new File(filePath);
		
		if(!file.exists()) {
			
			File dir = new File(filePath.substring(0, filePath.lastIndexOf("/")));
			dir.mkdirs();
		}
		
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(file);
			
			fw.write(str);
			
			return file;
		} catch (Exception e) {
			throw e;
		} finally {
			if(fw != null)
				fw.close();
		}
	}
}
