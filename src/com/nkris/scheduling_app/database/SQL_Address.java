package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Address;
import com.nkris.scheduling_app.models.City;
import com.nkris.scheduling_app.models.Country;

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
		 String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
		 String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
		 
		 PreparedStatement statement = connection.prepareStatement(addAddressQuery);
		 statement.setInt(1, address.getId());
		 statement.setString(2, address.getStreetAddress());
		 statement.setString(3,"N/A");
		 statement.setInt(4, address.getCity().getCityID());
		 statement.setString(5, address.getZipcode());
		 statement.setString(6, address.getPhoneNumber());
		 statement.setString(7, Main.user.getUserName());
		 statement.setString(8, Main.user.getUserName());
		 PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
	     PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
	     fkStatement.executeUpdate();
		 statement.executeUpdate();
		 fkStatement2.executeUpdate();
	}
	
	public static int getLastIndex(Connection connection) throws SQLException, ClassNotFoundException
	{
		int index = 0;
		String query = "SELECT MAX(addressId)FROM address";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		if(set.next())
		{
			index = set.getInt(1);
		}
		return index + 1;
	}
	
	
	public static Address getAddress(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
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
	
	public static String getZipcode(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		String zipcode = "";
		String query = "SELECT * FROM address WHERE addressId = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next())
		{
			zipcode = set.getString("postalCode");
		}
		return zipcode;
	}
	
	public static int getAddressId(int customerId) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		int id = 0;
		
		String query = "SELECT * FROM customer WHERE customerId = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, customerId);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next())
		{
			id = set.getInt("addressId");
		}
		return id;
	}
	
	public static String getPhoneNumber(int addressId) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		String number = "";
		
		String query = "SELECT * FROM address WHERE addressId = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, addressId);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next())
		{
			number = set.getString("phone");
		}
		return number;
	}
	
	public static Address getAddressFromId(int id) throws SQLException
	{
		connection = DatabaseHandler.getDBconnection();
		Address address = new Address();
		
		String query = "SELECT * FROM address WHERE addressId = ?";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		
		ResultSet set = statement.executeQuery();
		
		if(set.next()) {
			address.setStreetAddress(set.getString("address"));
			String cityQuery = "SELECT * FROM city WHERE cityId = ?";
    		PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
    		cityStatement.setInt(1, set.getInt("cityId"));
    		ResultSet citySet = cityStatement.executeQuery();
			
    		if(citySet.next()) {
    			City city = new City();
    			city.setCityName(citySet.getString("city"));
    			String countryQuery = "SELECT * FROM country WHERE countryId = ?";
    			PreparedStatement countryStatement = connection.prepareStatement(countryQuery);
    			countryStatement.setInt(1, citySet.getInt("countryId"));
    			ResultSet countrySet = countryStatement.executeQuery();
    			
    			if(countrySet.next()) {
    				Country country = new Country();
    				country.setCountryName(countrySet.getString("country"));
    				city.setCountry(country);
    				address.setCity(city);
    			}
    		}
		}
	return address;	
	}
	
	public static void updateAddress(int id, String streetName, String cityName, 
			String countryName,String phoneNumber) throws SQLException, ClassNotFoundException
	{
		String query = "UPDATE address SET address = ?, cityId = ?, phone = ?";
		
		String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
		String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
		 
		PreparedStatement statement = connection.prepareStatement(query);
		int countryId = SQL_Country.getCountryId(countryName);
		int cityId = SQL_City.getCityId(cityName, countryId);
		
		statement.setString(1, streetName);
		statement.setInt(2, cityId);
		statement.setString(3, phoneNumber);
		PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
	    PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
		
	    fkStatement.executeUpdate();
		statement.executeUpdate();
		fkStatement2.executeUpdate();
	}
	
}
