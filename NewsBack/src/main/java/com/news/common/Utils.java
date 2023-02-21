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
	
	/**
	 * 
	 * @param fileName
	 * @param arrExtentions
	 * @return type of result
	 */
	public static boolean checkFileExtention(String fileName,String[] arrExtentions) {
		try {
			String[] arrs=fileName.split("[.]");
			String fileExtention=arrs[arrs.length-1];
			for(int i=0;i<arrExtentions.length;i++) {
				if(fileExtention.equals(arrExtentions[i])) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
}
