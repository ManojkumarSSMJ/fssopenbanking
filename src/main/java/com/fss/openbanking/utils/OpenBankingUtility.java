package com.fss.openbanking.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenBankingUtility {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OpenBankingUtility.class);

	public static String validateNullReturnEmptyIfNull(String data) {
		return data == null || "".equals(data.trim()) || "-".equals(data.trim()) ? "":data;
	}
	public static List<String> getLast6MonthDates() {
		List<String> dateList = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		calendar.add(Calendar.MONTH, -6);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateList.add(dateFormat.format(calendar.getTime()));
		dateList.add(dateFormat.format(currentDate));
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, 6);
		dateList.add(dateFormat.format(calendar.getTime()));
		return dateList;
	}
	
	public static String mask(String input) {
		StringBuffer stringBuffer = new StringBuffer("");
		List<Integer> lastThreeIndex = new ArrayList<Integer>();
		lastThreeIndex.add(input.length()-1);
		lastThreeIndex.add(input.length()-2);
		lastThreeIndex.add(input.length()-3);
	    for(int i=0; i < input.toCharArray().length;i++) {
	    	if(i==0 || i==1 || i==2 || lastThreeIndex.contains(i)) {
	    		stringBuffer.append(input.charAt(i));
	    	} else {
	    		stringBuffer.append("X");
	    	}
	    	
	    }
	   


	    return stringBuffer.toString();
	}
	
	public static String convertDateToDDMMYYYYToYYYYMMDD(String date) {
		String requiredDate = "";
		try {
			SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date convertDate = currentDateFormat.parse(date);
			SimpleDateFormat requiredDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			requiredDate = requiredDateFormat.format(convertDate);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return requiredDate;
	}
	
	public static String convertDateAsISO(String date) {
		String requiredDate = "";
		try {
			requiredDate = date+"T00:00:00+00:00";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return requiredDate;
	}
	
	public static List<String> getLast6MonthDatesInISO() {
		List<String> dateList = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		calendar.add(Calendar.MONTH, -6);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateList.add(convertDateAsISO(dateFormat.format(calendar.getTime())));
		dateList.add(convertDateAsISO(dateFormat.format(currentDate)));
		calendar.setTime(currentDate);
		calendar.add(Calendar.MONTH, 6);
		dateList.add(dateFormat.format(calendar.getTime()));
		return dateList;
	}
	
	public static String get90DaysBeforeDate(String date) {
		String requiredDate = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -90);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			requiredDate = convertDateAsISO(dateFormat.format(calendar.getTime()));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return requiredDate;
	}
}
