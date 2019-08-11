package com.nkris.scheduling_app.models;

import java.sql.Date;
import java.sql.Time;

public class EventModel
{
	public static final EventModel instance_event = new EventModel();
	
	public static EventModel getInstance()
	{
		return instance_event;
	}
	
	
	public String title; //Title/name of event
	public String description; //Event description
	public Date start_date; //Event start date
	public Date end_date; //Event end date
	public Time start_time; //Event start time
	public Time end_time; //Event end time
	

}
