package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Address;
import com.nkris.scheduling_app.models.City;
import com.nkris.scheduling_app.models.Country;
import com.nkris.scheduling_app.models.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQL_Customer
{
	
	
	//public static ObservableList<Customer> customers = FXCollections.observableArrayList();

	private static Connection connection;
	
	public static void insertCustomer(Customer customer, Connection connection) throws SQLException, ClassNotFoundException
	{	
		 String addCustomerQuery = String.join(" ",
		            "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)",
		            "VALUES (?, ?, ?, 1, NOW(), ?, NOW(), ?)"
		        );
		 String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
		 String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
         PreparedStatement statement = connection.prepareStatement(addCustomerQuery);
         statement.setInt(1, customer.getCustomerID());
         statement.setString(2, customer.getName());
         statement.setInt(3, customer.getAddress().getId());
         statement.setString(4, Main.user.getUserName());
         statement.setString(5, Main.user.getUserName());
         PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
         PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
         fkStatement.executeUpdate();
         statement.executeUpdate();
         fkStatement2.executeUpdate();
         
        SQL_Address.insertAddress(customer.getAddress(), connection);
         SQL_City.insertCity(customer.getAddress().getCity(), connection);
         SQL_Country.insertCountry(customer.getAddress().getCity().getCountry(), connection);
	}
	
	
	
	public static ObservableList<Customer> getCustomers() throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		ObservableList<Customer> customers = FXCollections.observableArrayList();
	    String customersQuery = "SELECT * FROM customer"; 
	    String addressQuery = "SELECT * FROM address WHERE addressId = ?";
	    
	    PreparedStatement statement = connection.prepareStatement(customersQuery);
	    PreparedStatement addressStatement = connection.prepareStatement(addressQuery);
	    
	    ResultSet set = statement.executeQuery();
	    
	    while(set.next())
	    {
	    	Customer customer = new Customer();
	    	customer.setName(set.getString("customerName"));
	    	customer.setCustomerID(set.getInt("customerId"));
	    	customer.setActive(set.getInt("active"));
	    	addressStatement.setInt(1, set.getInt("addressId"));
	    	ResultSet addressSet = addressStatement.executeQuery();
	    	if(addressSet.next())
	    	{
	    		Address address = new Address();
	    		address.setStreetAddress(addressSet.getString("address"));
	    		String cityQuery = "SELECT * FROM city WHERE cityId = ?";
	    		PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
	    		cityStatement.setInt(1, addressSet.getInt("cityId"));
	    		ResultSet citySet = cityStatement.executeQuery();
	    		if(citySet.next())
	    		{
	    			City city = new City();
	    			city.setCityName(citySet.getString("city"));
	    			String countryQuery = "SELECT * FROM country WHERE countryId = ?";
	    			PreparedStatement countryStatement = connection.prepareStatement(countryQuery);
	    			countryStatement.setInt(1, citySet.getInt("countryId"));
	    			ResultSet countrySet = countryStatement.executeQuery();
	    			if(countrySet.next())
	    			{
	    				Country country = new Country();
	    				country.setCountryName(countrySet.getString("country"));
	    				city.setCountry(country);
	    				address.setCity(city);
	    				customer.setAddress(address);
	    		    	customers.add(customer);

	    			}
	    			
	    		}
	    	}
	    	
	    }
	    return customers;
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
	
	public static Customer getCustomer(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		
		String customerQuery =  "SELECT * FROM customer WHERE customerId = ?";
	    String addressQuery = "SELECT * FROM address WHERE addressId = ?";
	    System.out.println("ID: "+id);
		Customer customer = new Customer();

		PreparedStatement statement = connection.prepareStatement(customerQuery);
	    PreparedStatement addressStatement = connection.prepareStatement(addressQuery);

		statement.setInt(1, id);
		ResultSet set = statement.executeQuery();
		
		if(set.next()) {
			customer.setName(set.getString("customerName"));
	    	customer.setCustomerID(set.getInt("customerId"));
	    	customer.setActive(set.getInt("active"));
	    	addressStatement.setInt(1, set.getInt("addressId"));
	    	ResultSet addressSet = addressStatement.executeQuery();
	    	
	    	if(addressSet.next()) {
	    		Address address = new Address();
	    		address.setId(addressSet.getInt("addressId"));
	    		address.setStreetAddress(addressSet.getString("address"));
	    		address.setPhoneNumber(addressSet.getString("phone"));
	    		String cityQuery = "SELECT * FROM city WHERE cityId = ?";
	    		PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
	    		cityStatement.setInt(1, addressSet.getInt("cityId"));
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
	    				customer.setAddress(address);
	    			}
	    		}
	    	}
	    	
		}
		return customer;
	}
	
	public static int getAddressId(int customerId) throws SQLException
	{
		connection = DatabaseHandler.getDBconnection();
		
		String query = "SELECT * FROM customer WHERE customerId = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setInt(1, customerId);
		
		ResultSet set = statement.executeQuery();
		int addressId = -1;
		if(set.next()) {
			return set.getInt("addressId");
		}
		
		return addressId;
	}
	
	
	public static Customer getSelectedCustomer(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		String customerQuery = "SELECT * FROM customer WHERE customerId = ?";
		Customer customer = new Customer();
		
		PreparedStatement statement = connection.prepareStatement(customerQuery);
		statement.setInt(1, id);
		ResultSet set = statement.executeQuery();
		
		if(set.next())
		{ 
			String addressQuery = "SELECT * FROM address WHERE addressId = ?";
			PreparedStatement addressStatement = connection.prepareStatement(addressQuery);
	    	addressStatement.setInt(1, set.getInt("addressId"));
	    	ResultSet addressSet = addressStatement.executeQuery();
	    	if(addressSet.next())
	    	{
	    		Address address = new Address();
	    		address.setStreetAddress(addressSet.getString("address"));
	    		String cityQuery = "SELECT * FROM city WHERE cityId = ?";
	    		PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
	    		cityStatement.setInt(1, addressSet.getInt("cityId"));
	    		ResultSet citySet = cityStatement.executeQuery();
	    		if(citySet.next())
	    		{
	    			City city = new City();
	    			city.setCityName(citySet.getString("city"));
	    			String countryQuery = "SELECT * FROM country WHERE countryId = ?";
	    			PreparedStatement countryStatement = connection.prepareStatement(countryQuery);
	    			countryStatement.setInt(1, citySet.getInt("countryId"));
	    			ResultSet countrySet = countryStatement.executeQuery();
	    			if(countrySet.next())
	    			{
	    				Country country = new Country();
	    				country.setCountryName(countrySet.getString("country"));
	    				city.setCountry(country);
	    				address.setCity(city);
	    				customer.setAddress(address);
	    		    	//scustomers.add(customer);

	    			}
	    			
	    		}
	    	}
	    	
		}
		return customer;
	 
	}
	
	public static void deleteCustomer(int id) throws SQLException
	{
		connection = DatabaseHandler.getDBconnection();
		
		 String foreignKeyQuery = "SET FOREIGN_KEY_CHECKS=0";
		 String foreignKeyQuery2 = "SET FOREIGN_KEY_CHECKS=1";
		 
		 String customerQuery = "DELETE FROM customer WHERE customerId = ?";
		 PreparedStatement statement = connection.prepareStatement(customerQuery);
		 statement.setInt(1, id);
		 
		 PreparedStatement fkStatement1 = connection.prepareStatement(foreignKeyQuery);
		 PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
		 
		 fkStatement1.executeUpdate();
		 statement.executeUpdate();
		 fkStatement2.executeUpdate();
	}
	
	public static void updateCustomer(int id, String name) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getDBconnection();
		
		String query = "UPDATE customer SET customerName = ? WHERE customerId = ?";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, name);
		statement.setInt(2, id);
		
		statement.executeUpdate();
	}
	

}
