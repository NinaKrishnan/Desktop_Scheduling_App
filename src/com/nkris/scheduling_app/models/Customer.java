package com.nkris.scheduling_app.models;

public class Customer
{
	String name;
	Address address;
	int customerID;
	
	
	public Customer()
	{
		
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public Address getAddress()
	{
		return address;
	}
	
	public int getCustomerID()
	{
		return this.hashCode();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class Address
	{
		String address;
		int cityID;
		int countryID;
		
	}
	
	
}
