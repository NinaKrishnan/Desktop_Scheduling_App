package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.City;

public class SQL_City 
{
	private static Connection connection;
	
	
	public static void insertCity(City city, Connection connection) throws SQLException, ClassNotFoundException
	{		
		//if(!containsCity(city.getCityName(), city.getCountry().getCountryId())) {
			 String addCityQuery = String.join(" ",
			            "INSERT INTO city (cityId, city, countryId, createDate, "
			            + "createdBy, lastUpdate, lastUpdateBy)",
			            "VALUES (?, ?, ?, NOW(), ?, NOW(), ?)");
			 String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
			 String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
			 
			 PreparedStatement statement = connection.prepareStatement(addCityQuery);
			 statement.setInt(1, city.getCityID());
			 statement.setString(2, city.getCityName());
			 statement.setInt(3, city.getCountry().getCountryId());
			 statement.setString(4, Main.user.getUserName());
			 statement.setString(5, Main.user.getUserName());
			 PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
		     PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
		     fkStatement.executeUpdate();
			 statement.executeUpdate();
			 fkStatement2.executeUpdate();
		//}
		 
	}
	
	
	
	
	public static City getCity(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		
		String cityQuery = "SELECT * FROM city WHERE cityId = ?"; 
        City city = new City();
        
        PreparedStatement statement = connection.prepareStatement(cityQuery);
        statement.setInt(1, id);

        ResultSet set = statement.executeQuery();
        
        if(set.next())
        {
            city.setCityName(set.getString("city"));
            city.setCityID(set.getInt("cityId"));
            city.setCountry(SQL_Country.getCountry(set.getInt("countryId")));
            
        }
        connection.close();
        return city;
        
	}
	
	public static int getLastIndex(Connection connection) throws SQLException, ClassNotFoundException
	{
		int index = 0;
		String query = "SELECT MAX(cityId)FROM city";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		if(set.next())
		{
			index = set.getInt(1);
		}
		return index + 1;
	}
	
	
	public static boolean containsCity(String cityName, int countryID) throws SQLException
	{
		connection = DatabaseHandler.getDBconnection();
		String query = "SELECT * FROM city WHERE city = ? AND countryId = ?";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, cityName);
		statement.setInt(2, countryID);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next()) {
			return true;
		}
		return false;
	}
	
	public static int getCityId(String cityName, int countryID) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		
		String query = "SELECT * FROM city WHERE city = ? AND countryId = ?";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, cityName);
		statement.setInt(2, countryID);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next()) {
			return set.getInt("cityId");
		}
		return getLastIndex(connection);
	}
	
	public static void updateCity(String cityName, int countryId) throws SQLException
	{
		connection = DatabaseHandler.getDBconnection();
		
		
	}
	
}
