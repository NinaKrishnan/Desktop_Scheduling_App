package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Country;

public class SQL_Country 
{
	private static Connection connection;
	
	
	public static void insertCountry(Country country, Connection connection) throws SQLException, ClassNotFoundException
	{
		//connection = DatabaseHandler.getConnection();
		String addCountryQuery = String.join(" ",
	            "INSERT INTO city (countryId, country, createDate, "
	            + "createdBy, lastUpdate, lastUpdateBy)",
	            "VALUES (?, ?, NOW(), ?, NOW(), ?)");
		PreparedStatement statement = connection.prepareStatement(addCountryQuery);
		statement.setInt(1, country.getCountryId());
		statement.setString(2, country.getCountryName());
		statement.setString(3, Main.user.getUserName());
		statement.setString(4, Main.user.getUserName());
		
		statement.executeUpdate();
	}
	
	
	
	
	public static Country getCountry(int countryID) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getConnection();
		
		 String countryQuery = "SELECT * FROM country WHERE countryId = ?"; 
	     Country country = new Country();
	     
	     PreparedStatement statement = connection.prepareStatement(countryQuery);
	     statement.setInt(1, countryID);

	     ResultSet set = statement.executeQuery();
	     
	     if(set.next())
	     {
	    	 country.setCountryName(set.getString("country"));
		     country.setCountryID(set.getInt("countryId"));
		     
	     }
	     connection.close();
	     return country;
	}
	
	
	
}
