package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import com.nkris.scheduling_app.models.Appointment;
import javafx.beans.property.StringProperty;

public class SQL_Appointments 
{
	private static Connection connection;
	
	public static void insertAppointment(Appointment appointment) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getConnection();
		

		StringProperty start = appointment.getStringDateTime(appointment.getStartTime(), appointment.getStartDate());
		StringProperty end = appointment.getStringDateTime(appointment.getEndTime(), appointment.getEndDate());
		

		 String addAppointmentQuery = String.join(" ",
		            "INSERT INTO appointment (appointmentId, customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)",
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)"
		        );
		
         PreparedStatement statement = connection.prepareStatement(addAppointmentQuery);
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

		connection.close();
	}
	
	
	
	

	
	
}
