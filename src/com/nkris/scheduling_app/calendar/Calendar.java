package com.nkris.scheduling_app.calendar;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;

public class Calendar
{
	
	public int month;
	public int year;
	public int firstDay;
	

	public static final HashMap<String, Integer> day_codes = new HashMap<String, Integer>();		
	
	public Calendar()
	{
		addDayIndices();
	}
	
	private static void addDayIndices()
	{
		day_codes.put("Sunday", 0);
		day_codes.put("Monday", 1);
		day_codes.put("Tuesday", 2);
		day_codes.put("Wednesday", 3);
		day_codes.put("Thursday", 4);
		day_codes.put("Friday", 5);
		day_codes.put("Saturday", 6);
	}
	
	//Get today's day of the week (by index)
	public static int getDayOfWeekToday()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		String dateFormat = formatter.format(LocalDate.now());
		LocalDate date = LocalDate.parse(dateFormat, formatter);
		DayOfWeek dow = date.getDayOfWeek();
		String output = dow.getDisplayName(TextStyle.FULL, Locale.US);
		addDayIndices();
		return day_codes.get(output);
	}
	


	
	public static int getFirstDayOfMonth()
	{
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate date = LocalDate.parse(month+"/1/"+year, formatter);
		DayOfWeek dow = date.getDayOfWeek();
		String output = dow.getDisplayName(TextStyle.FULL, Locale.US);
		return day_codes.get(output);
	}
	
	
	public static int getDateNumber()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");
		String dayFormat = formatter.format(LocalDate.now());
		Integer dayDate = Integer.parseInt(dayFormat);
		return dayDate;
	}

	public static int getNumberOfDaysInMonth(int month)
	{
		switch(month)
		{
		case 1:
			return 31;
		case 2:
			return 28;
		case 3: 
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		}
		return 31;
	}
	
	
	
	
}
		