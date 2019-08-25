package com.nkris.scheduling_app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Address;

public class SQL_Address
{
	public static Address getAddress(int id) throws SQLException
	{
		String addressQuery = "SELECT * FROM address WHERE addressId = ?"; 
        Address address = new Address();
        
        PreparedStatement statement = Main.connection.prepareStatement(addressQuery);
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        
       if(set.next())
       {
    	   address.setAddress(set.getString("address"));
           address.setID(set.getInt("addressId"));
           address.setZipcode(set.getString("postalCode"));
           address.setPhoneNumber(set.getString("phone"));
           address.setCity(SQL_City.getCity(set.getInt("cityId")));
           
           return address;
       }
        
        return null;
	}
}
