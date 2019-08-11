package com.nkris.scheduling_app.models;

public class CalendarModel
{

	private static final CalendarModel instance_cal = new CalendarModel();
	
	private static CalendarModel getInstance()
	{
		return instance_cal;
	}
	
	
	//Create and get events
	public EventModel event_model;
	
	
	
	public void addEvent(EventModel event)
	{
		event_model = event;
	}
	
	
	   
}
