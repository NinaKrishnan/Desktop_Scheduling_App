package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Address;

public class SQL_Address
{
	private static Connection connection;
	
	
	public static void insertAddress(Address address, Connection connection) throws SQLException, ClassNotFoundException
	{
		//connection = DatabaseHandler.getConnection();
		 String addAddressQuery = String.join(" ",
		            "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, "
		            + "createDate, createdBy, lastUpdate, lastUpdateBy)",
		            "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?)");
		 
		 PreparedStatement statement = connection.prepareStatement(addAddressQuery);
		 statement.setInt(1, address.getId());
		 statement.setString(2, address.getStreetAddress());
		 statement.setString(3,"N/A");
		 statement.setInt(4, address.getCity().getCityID());
		 statement.setString(5, address.getZipcode());
		 statement.setString(6, address.getPhoneNumber());
		 statement.setString(7, Main.user.getUserName());
		 statement.setString(8, Main.user.getUserName());
		 
		 statement.executeUpdate();
	}
	
	
	
	public static Address getAddress(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getConnection();
		String addressQuery = "SELECT * FROM address WHERE addressId = ?";
        Address address = new Address();
        
        PreparedStatement statement = connection.prepareStatement(addressQuery);
        statement.setInt(1, id);

        ResultSet set = statement.executeQuery();
        
        
       if(set.next())
       {
    	   address.setStreetAddress(set.getString("address"));
           address.setId(set.getInt("addressId"));
           address.setZipcode(set.getString("postalCode"));
           address.setPhoneNumber(set.getString("phone"));
           address.setCity(SQL_City.getCity(set.getInt("cityId")));
           
           
       }
        connection.close();
        return address;
	}
}
