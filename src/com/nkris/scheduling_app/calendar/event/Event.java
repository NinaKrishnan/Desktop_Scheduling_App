package com.nkris.scheduling_app.calendar.event;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event 
{

	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String description;
	
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setStartDate(LocalDate date)
	{
		startDate = date;
	}
	
	public LocalDate getStartDate()
	{
		return startDate;
	}
	
	public void setEndDate(LocalDate date)
	{
		endDate = date;
	}
	
	public LocalDate getEndDate()
	{
		return endDate;
	}
	
	public void setStartTime(LocalTime time)
	{
		startTime = time;
	}
	
	public LocalTime getStartTime()
	{
		return startTime;
	}
	
	public void setEndTime(LocalTime time)
	{
		endTime = time;
	}
	
	public LocalTime getEndTime()
	{
		return endTime;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description; 
	}
	
	
	
	
	
}
