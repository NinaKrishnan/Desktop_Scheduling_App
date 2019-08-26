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
		 String addCityQuery = String.join(" ",
		            "INSERT INTO city (cityId, city, countryId, createDate, "
		            + "createdBy, lastUpdate, lastUpdateBy)",
		            "VALUES (?, ?, ?, NOW(), ?, NOW(), ?)");
		 PreparedStatement statement = connection.prepareStatement(addCityQuery);
		 statement.setInt(1, city.getCityID());
		 statement.setString(2, city.getCityName());
		 statement.setInt(3, city.getCountry().getCountryId());
		 statement.setString(4, Main.user.getUserName());
		 statement.setString(5, Main.user.getUserName());
		 statement.executeUpdate();
	}
	
	
	
	
	public static City getCity(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getConnection();
		
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
}
