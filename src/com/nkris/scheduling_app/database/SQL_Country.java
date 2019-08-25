package com.nkris.scheduling_app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Country;

public class SQL_Country 
{
	
	public static Country getCountry(int countryID) throws SQLException
	{
		 String countryQuery = "SELECT * FROM country WHERE countryId = ?"; 
	     Country country = new Country();
	     
	     PreparedStatement statement = Main.connection.prepareStatement(countryQuery);
	     statement.setInt(1, countryID);

	     ResultSet set = statement.executeQuery();
	     
	     if(set.next())
	     {
	    	 country.setCountryName(set.getString("country"));
		     country.setCountryID(set.getInt("countryId"));
		     
		     return country;
	     }
	     
	     else return null;
	}
	
	
	
}
