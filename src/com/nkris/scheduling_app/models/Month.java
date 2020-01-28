package com.nkris.scheduling_app.models;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nkris.scheduling_app.database.SQL_Appointments;

public class Month 
{
	private int year;
	private int val;
	private String name;
	//private ArrayList<Appointment> appointments;
	private String appointmentTypes;
	
	
	
	
	public Month(int val)
	{
		this.val = val;
		name = Calendar.getMonthName(val);
		try {
			appointmentTypes = SQL_Appointments.getAppointmentTypes(Integer.toString(val));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	private void setYear(int year)
	{
		this.year = year;
	}
	
	public int getYear()
	{
		return year;
	}
	
	private void setVal(int val)
	{
		this.val = val;
	}
	
	public int getVal()
	{
		return val;
	}
	
	public String getAppointmentTypes()
	{
		return appointmentTypes;
	}
	
}
