package com.nkris.scheduling_app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.City;

public class SQL_City 
{
	public static City getCity(int id) throws SQLException
	{
		String cityQuery = "SELECT * FROM city WHERE cityId = ?"; 
        City city = new City();
        
        PreparedStatement statement = Main.connection.prepareStatement(cityQuery);
        statement.setInt(1, id);

        ResultSet set = statement.executeQuery();
        
        if(set.next())
        {
            city.setCityName(set.getString("city"));
            city.setCityID(set.getInt("cityId"));
            city.setCountry(SQL_Country.getCountry(set.getInt("countryId")));
            
            return city;
        }
        
        return null;
        
	}
}
