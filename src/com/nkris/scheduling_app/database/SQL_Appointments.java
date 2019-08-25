package com.nkris.scheduling_app.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Appointment;

import javafx.beans.property.StringProperty;

public class SQL_Appointments 
{
	public static void insertAppointment(Appointment appointment) throws SQLException
	{
		String title = appointment.getTitle();
		String description = appointment.getDescription();
		StringProperty start = appointment.getStringDateTime(appointment.getStartTime(), appointment.getStartDate());
		StringProperty end = appointment.getStringDateTime(appointment.getEndTime(), appointment.getEndDate());
		String id = Integer.toString(appointment.getID());
		String customerId = Integer.toString(appointment.getCustomer().getCustomerID());
		String userId = Integer.toString(appointment.getUser().getUserID());
		String location = appointment.getLocation();
		String contact = appointment.getContact();
		String type = appointment.getType();
		
		
		
		
		 String addAppointmentQuery = String.join(" ",
		            "INSERT INTO appointment (appointmentId, customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)",
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)"
		        );
		
         PreparedStatement statement = Main.connection.prepareStatement(addAppointmentQuery);
         statement.setInt(1, appointment.getID());
         statement.setInt(2, appointment.getCustomer().getCustomerID());
         statement.setString(3, appointment.getTitle());
         statement.setString(4, appointment.getDescription());
         statement.setString(5, appointment.getLocation());
         statement.setString(6, appointment.getContact());
         statement.setString(7, appointment.getURL());
         statement.setTimestamp(8, Timestamp.valueOf(start.toString()));
         statement.setTimestamp(9, Timestamp.valueOf(end.toString()));
         statement.setString(10, appointment.getUser().getUserName());
         statement.setString(11, appointment.getUser().getUserName());
         
         statement.executeUpdate();

		DatabaseHandler.disconnect();
	}
	
	
	
	

	
	
}
