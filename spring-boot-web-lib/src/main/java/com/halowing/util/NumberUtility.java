package com.halowing.util;

public class NumberUtility {
	
	public static Float round(Float number, int offset) {
		if(number == null) return null;
		
		int multiple = 1;
		for(int i=0;i<offset;i++) {
			multiple *= 10;
		}
		
		return  Math.round(number * multiple) / (multiple * 1f);
		
		
	}
	
	@Deprecated
	public static String round(String number, int offset, String defaultValue) {
		
		if(StringUtility.isBlank(number)) return defaultValue;
		
		if(!number.contains("."))
			return number;
		
		float a = Float.valueOf(number) ;
	
		
		return "" +  round(a,offset);
	}
	
	public static String toString(String number, int offset, String defaultValue) {
		
		if(StringUtility.isBlank(number)) return defaultValue;
		
		float a = Float.valueOf(number) ;
	
		
//		return  String.format("%."+offset+"f", round(a,offset));
		
		return  String.format("%."+offset+"f", a);
		
	}
	
	public static String toString(Float number, int offset) {
		
		if(number == null) return null;
		
		return  String.format("%."+offset+"f", number);
		
	}

}
