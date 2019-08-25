package com.nkris.scheduling_app.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment 
{

	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String description;
	private int id;
	private Customer customer;
	private User user;
	private String location;
	private String contact;
	private String type;
	private String url;
	
	
	public Appointment()
	{
		id = this.hashCode();
		location = "";
		contact = "";
		customer = null;
		type = "";
		url = "";
			
	}
	

	
	public int getID()
	{
		return id;
	}
	
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
	
	
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public User getUser()
	{
		return user;
	}
	
	
	public String getLocation()
	{
		return location;
	}
	
	
	public String getContact()
	{
		return contact;
	}
	
	
	public String getType()
	{
		return type;
	}
	
	public String getURL()
	{
		return url; 
	}
	
	
	
	
	public StringProperty getStringDateTime(LocalTime time, LocalDate date)
	{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        formatter.format(localDateTime);
        
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
        ZoneId zoneID = ZoneId.systemDefault();
        ZonedDateTime utcDate = zonedDateTime.withZoneSameInstant(zoneID); 
        StringProperty stringDateTime = new SimpleStringProperty(utcDate.toLocalDateTime().toString());
        System.out.print(stringDateTime);
        return stringDateTime;
	}
	
	
	
	
}
