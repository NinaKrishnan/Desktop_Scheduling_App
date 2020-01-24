package com.nkris.scheduling_app.models;

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
	
	public void setMonth(int month)
	{
		this.month = month;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public int getMonth()
	{
		return this.month;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	
	
	public static String getDayName(int day)
	{
		switch(day)
		{
		case 0:
			return "Sun";
		case 1: 
			return "Mon";
		case 2:
			return "Tues";
		case 3:
			return "Wed";
		case 4:
			return "Thurs";
		case 5:
			return "Fri";
		case 6:
			return "Sat";
		}
		return "";
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
	


	
	public static int getFirstDayOfMonth(LocalDate localDate)
	{
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate date = LocalDate.parse(month+"/1/"+year, formatter);
		DayOfWeek dow = date.getDayOfWeek();
		String output = dow.getDisplayName(TextStyle.FULL, Locale.US);
		return day_codes.get(output);
	}
	
	
	public int getFirstDayOfMonth(int month, int year)
	{
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

	public int getNextMonth()
	{
		int nextMonth = 0;
		int currentMonth = this.month;
		if(currentMonth == 12)
		{
			nextMonth = 1;
			this.year++;
		}
		else
		{
			nextMonth = currentMonth +1;
		}
		return nextMonth;
	}
	
	
	public int getPreviousMonth()
	{
		int previousMonth = 0;
		int currentMonth = this.month;
		if(currentMonth == 1)
		{
			previousMonth = 12;
			this.year--;
		}
		else
		{
			previousMonth = currentMonth - 1;
		}
		return previousMonth;
	}
	
	
	
	public static String getMonthName(int month)
	{
		switch(month)
		{
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
					
		}
		return "";
	}
	
	public static int getMonthIndex(String month)
	{
		switch(month)
		{
		case "January":
			return 1;
		case "February":
			return 2;
		case "March":
			return 3;
		case "April":
			return 4;
		case "May":
			return 5;
		case "June":
			return 6;
		case "July":
			return 7;
		case "August":
			return 8;
		case "September":
			return 9;
		case "October":
			return 10;
		case "November":
			return 11;
		case "December":
			return 12;
		}
		return 1;
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
	
	public LocalDate getDate(String day)
	{
		String month = Integer.toString(getMonth());
		String year = Integer.toString(getYear());
		String dateFormat = month+"/"+day+"/"+year;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate localDate = LocalDate.parse(dateFormat, formatter);
		
		return localDate;
	}
	
	
	
	
	
	
}
		