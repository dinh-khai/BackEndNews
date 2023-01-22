package com.news.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	/**
	 * Convert date to String
	 * 
	 * @param dateFormat
	 * @param date
	 * @return str
	 */
	public static String converDateToStr(String dateFormat,Date date) {
		String str =null;
		try {
			SimpleDateFormat format=new SimpleDateFormat(dateFormat);
			str=format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
