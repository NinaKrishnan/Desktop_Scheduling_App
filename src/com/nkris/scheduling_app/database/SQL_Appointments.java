package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import com.nkris.scheduling_app.models.Appointment;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQL_Appointments 
{
	private static Connection connection;
	
	public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
	
	public static void insertAppointment(Appointment appointment) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		

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
	
	
	public static ObservableList<Appointment> getAppointments(LocalDate date) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		
		String query = "SELECT * FROM appointment WHERE start = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		
		ResultSet set = statement.executeQuery();
		
		Timestamp start = Timestamp.valueOf(date.toString());
		
		statement.setTimestamp(1, start);
		while(set.next())
		{
			Appointment appointment = new Appointment();
			appointment.setTitle(set.getString("title"));
			appointment.setID(set.getInt("appointmentId"));
			appointment.setCustomer(SQL_Customer.getSelectedCustomer(set.getInt("customerId")));
			appointment.setDescription(set.getString("description"));
			appointment.setLocation(set.getString("location"));
			appointment.setContact(set.getString("contact"));
			appointment.setType(set.getString("type"));
			appointment.setURL(set.getString("url"));
			
			appointments.add(appointment);
		}
			
		
		return appointments;
	}
	
	
	
	
	
	

	
	
}
