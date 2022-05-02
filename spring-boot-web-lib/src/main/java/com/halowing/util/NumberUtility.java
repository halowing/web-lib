package com.halowing.util;

public class NumberUtility {
	
	public static Float round(Float number, int position) {
		if(number == null) return null;
		
		String pow = "" + Math.pow(10, position);
		
		return Math.round(number * Float.valueOf(pow)) / Float.valueOf(pow); 
		
	}

}
