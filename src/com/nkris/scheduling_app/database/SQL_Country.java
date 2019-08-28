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
		String addCountryQuery = String.join(" ",
	            "INSERT INTO country (countryId, country, createDate, "
	            + "createdBy, lastUpdate, lastUpdateBy)",
	            "VALUES (?, ?, NOW(), ?, NOW(), ?)");
		String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
		String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
		PreparedStatement statement = connection.prepareStatement(addCountryQuery);
		statement.setInt(1, country.getCountryId());
		statement.setString(2, country.getCountryName());
		statement.setString(3, Main.user.getUserName());
		statement.setString(4, Main.user.getUserName());
		PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
	    PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
	    fkStatement.executeUpdate();
		statement.executeUpdate();
		fkStatement2.executeUpdate();
	}
	
	
	public static int getLastIndex(Connection connection) throws SQLException, ClassNotFoundException
	{
		int index = 0;
		String query = "SELECT MAX(customerId)FROM customer";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		if(set.next())
		{
			index = set.getInt(1);
		}
		return index + 1;
	}
	
	public static Country getCountry(int countryID) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		
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
