package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nkris.scheduling_app.main.Main;
import com.nkris.scheduling_app.models.Address;
import com.nkris.scheduling_app.models.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQL_Customer
{
	
	
	public static ObservableList<Customer> customers = FXCollections.observableArrayList();

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
         statement.setInt(3, customer.getAddressID());
         statement.setString(4, Main.user.getUserName());
         statement.setString(5, Main.user.getUserName());
         PreparedStatement fkStatement = connection.prepareStatement(foreignKeyQuery);
         PreparedStatement fkStatement2 = connection.prepareStatement(foreignKeyQuery2);
         fkStatement.executeUpdate();
         statement.executeUpdate();
         fkStatement2.executeUpdate();
	}
	
	
	
	public static ObservableList<Customer> getCustomers() throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getConnection();
		ObservableList<Customer> customers = FXCollections.observableArrayList();
	    String customersQuery = "SELECT * FROM customer"; 
	    
	    PreparedStatement statement = connection.prepareStatement(customersQuery);
	    ResultSet set = statement.executeQuery();
	    
	    while(set.next())
	    {
	    	Customer customer = new Customer();
	    	customer.setName(set.getString("customerName"));
	    	customer.setCustomerID(set.getInt("customerId"));
	    	customer.setAddress(SQL_Address.getAddress(set.getInt("addressId")));;
	    	customer.setActive(set.getInt("active"));
	    	
	    	customers.add(customer);
	    }
    	connection.close();

	    return customers;
	}
	
	public static Customer getSelectedCustomer(int id) throws SQLException, ClassNotFoundException
	{
		connection = DatabaseHandler.getConnection();
		String customerQuery = "SELECT * FROM customer WHERE customerId = ?";
		Customer customer = new Customer();
		
		PreparedStatement statement = DatabaseHandler.getConnection().prepareStatement(customerQuery);
		statement.setInt(1, id);
		ResultSet set = statement.executeQuery();
		
		if(set.next())
		{ 
			customer.setName(set.getString("customerName"));
			customer.setCustomerID(set.getInt("customerId"));
	    	customer.setAddress(SQL_Address.getAddress(set.getInt("addressId")));
	    	customer.setActive(set.getInt("active"));
	    	
		}
		connection.close();
		return customer;
	 
	}
	

}