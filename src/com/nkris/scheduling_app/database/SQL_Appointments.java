package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
		

		Timestamp start = getTimestamp(appointment, appointment.getStartDate(), appointment.getStartTime());
		Timestamp end = getTimestamp(appointment, appointment.getEndDate(), appointment.getEndTime());

		 String addAppointmentQuery = String.join(" ",
		            "INSERT INTO appointment (appointmentId, customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)",
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)"
		        );
		
         PreparedStatement statement = connection.prepareStatement(addAppointmentQuery);
         String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
		 String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
         statement.setInt(1, appointment.getID());
         statement.setInt(2, appointment.getCustomer().getCustomerID());
         
         //TODO: SET USER ID
         statement.setInt(3, 98001);
         statement.setString(4, appointment.getTitle());
         statement.setString(5, appointment.getDescription());
         statement.setString(6, appointment.getLocation());
         statement.setString(7, appointment.getContact());
         statement.setString(8, appointment.getType());
         statement.setString(9, appointment.getURL());
         statement.setTimestamp(10, start);
         statement.setTimestamp(11, end);
         
         //TODO: CREATE USER/PASSWORD 
         statement.setString(12, "nkris007");
         statement.setString(13, "nkris007");
         PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
         PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
         fkStatement.executeUpdate();
         statement.executeUpdate();
         fkStatement2.executeUpdate();
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
	
	
	private static Timestamp getTimestamp(Appointment appointment, LocalDate date, LocalTime time)
	{
		LocalDateTime ldt = LocalDateTime.of(date, time);
		Timestamp timestamp = Timestamp.valueOf(ldt);
		
		return timestamp;
	}
	
	
	
	
	

	
	
}
