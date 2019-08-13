package com.nkris.scheduling_app.calendar.event;

import java.sql.Date;
import java.sql.Time;

public class Event 
{

	private String title;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;
	private String description;
	
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setStartDate(Date date)
	{
		startDate = date;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public void setEndDate(Date date)
	{
		endDate = date;
	}
	
	public Date getEndDate()
	{
		return endDate;
	}
	
	public void setStartTime(Time time)
	{
		startTime = time;
	}
	
	public Time getStartTime()
	{
		return startTime;
	}
	
	public void setEndTime(Time time)
	{
		endTime = time;
	}
	
	public Time getEndTime()
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
