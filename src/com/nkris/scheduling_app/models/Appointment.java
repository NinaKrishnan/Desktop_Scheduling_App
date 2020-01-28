package com.nkris.scheduling_app.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.nkris.scheduling_app.controllers.helpers.Type;

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
	//private String type;
	private Type type;
	private String url;
	private int customerId;
	private String timeRange;
	private String sticker;
	
	public Appointment()
	{
		id = this.hashCode();
		location = "";
		contact = "";
		customer = null;
		type = null;
		url = "";
			
	}
	
	public void setID(int id)
	{
		this.id = id;
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
	
	public void setTimeRange()
	{	
		String start = formatTime(startTime);
		String end = formatTime(endTime);
		
		timeRange = start + " - " + end;
		setSticker();
	}
	
	private String formatTime(LocalTime time)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
		if(time.getHour() > 12) {
			return formatter.format(time) + " p.m.";
		}
		return formatter.format(time)+" a.m.";
	}
	
	public String getTimeRange()
	{
		return timeRange;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description; 
	}
	
	public void setCustomerId(int id)
	{
		customerId = id;
	}
	
	public int getCustomerId()
	{
		return customerId;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setContact(String contact)
	{
		this.contact = contact;
	}
	
	public String getContact()
	{
		return contact;
	}
	
	public void setType(String type)
	{
		this.type = Type.valueOf(type.toUpperCase());
	}
	
	public void setType(Type type)
	{
		this.type = type; 
	}
	
	public Type getType()
	{
		return type;
	}
	
	public void setURL(String url)
	{
		this.url = url;
	}
	
	public String getURL()
	{
		return url; 
	}
	
	public void setSticker() 
	{
		 sticker = timeRange + "\n" + title;
	}
	
	public String getSticker()
	{
		return sticker;
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
	
	
	public boolean isOver() {
		return endDate.isBefore(LocalDate.now()) && endTime.isBefore(LocalTime.now());
	}
	
	
	
}
