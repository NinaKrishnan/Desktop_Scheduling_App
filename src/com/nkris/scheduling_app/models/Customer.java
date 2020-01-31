package com.nkris.scheduling_app.models;
import java.sql.SQLException;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_Customer;

public class Customer
{
	private String name;
	private String firstName;
	private String lastName;
	private Address address;
	private int customerID;
	private int active;
	private int addressId;
	public static int customerIndex;
	
		
	public Customer()
	{
	
	}
	 
	public static void setIndex() throws ClassNotFoundException, SQLException
	{
		int index = SQL_Customer.getLastIndex(DatabaseHandler.getDBconnection());
		customerIndex = index;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	
	public String getName()
	{
		return name;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
		
	}
	
	
	public Address getAddress()
	{
		return address;
	}
	
	
	
	public Address getCustomerAddress()
	{
		return address;
	}
	
	public void setCustomerID(int id)
	{
		customerID = id;
	}
	
	public void setCustomerID()
	{
		customerID = customerIndex;
		customerIndex++;
	}
	
	public int getCustomerID()
	{
		return customerID;
	}
	
	
	public void setActive(int status)
	{
		active = status;
	}
	
	public int getActive()
	{
		return active;
	}
	
	
	public int getAddressId()
	{
		return address.getId();
	}
	
	public void setFirstName(String name)
	{
		firstName = name;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setLastName(String name)
	{
		lastName = name;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	

	
	
}
