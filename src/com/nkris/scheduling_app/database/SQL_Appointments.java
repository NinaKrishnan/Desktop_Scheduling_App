package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.nkris.scheduling_app.models.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQL_Appointments 
{
	private static Connection connection;
	
	public static ObservableList<Appointment> appointments;
	
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
		
	}
	
	
	public static ObservableList<Appointment> getAppointments(LocalDate date) throws SQLException, ClassNotFoundException
	{
		appointments = FXCollections.observableArrayList();
		connection = DatabaseHandler.getDBconnection();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		System.out.println(date.toString());
		Timestamp dateTimestamp = Timestamp.valueOf(date.toString()+" "+LocalDateTime.now().format(formatter));
		Date truncDate = new Date(dateTimestamp.getTime());
		String query = "SELECT * FROM appointment WHERE DATE(start) = "+"\""+truncDate.toString()+"\";";
		System.out.println(query);
		PreparedStatement statement = connection.prepareStatement(query);
				
		ResultSet set = statement.executeQuery();

		
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
			appointment.setStartTime(set.getTimestamp("start").toLocalDateTime().toLocalTime());
			appointment.setEndTime(set.getTimestamp("end").toLocalDateTime().toLocalTime());
			appointment.setTimeRange();
			
			appointments.add(appointment);
		}
			
		
		return appointments;
	}
	
	
	public static Timestamp getTimestamp(Appointment appointment, LocalDate date, LocalTime time)
	{
		LocalDateTime ldt = LocalDateTime.of(date, time);
		Timestamp timestamp = Timestamp.valueOf(ldt);
		
		return timestamp;
	}
	
	
	public static boolean hasEvent(int day, int month, int year) throws SQLException {
		connection = DatabaseHandler.getDBconnection();
		String strDay = Integer.toString(day);
		if(day/10 < 1) {
			strDay = "0"+day;
		}
		
		String strMonth = Integer.toString(month);
		if(month/10 < 1) {
			strMonth = "0"+month;
		}
		String date = year+"-"+strMonth+"-"+strDay;
		String query = "SELECT * FROM appointment WHERE start LIKE '%"+date+"%';";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		if(set.next()) {
			return true;
		}
		
		return false;
		
	}
	
	
	
	

	
	
}
