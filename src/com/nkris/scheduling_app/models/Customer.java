package com.nkris.scheduling_app.models;
import com.nkris.scheduling_app.models.Address;

public class Customer
{
	private String name;
	private Address address;
	private int customerID;
	private int active;
	
	public Customer()
	{
		customerID = this.hashCode();
		active = 1;
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
	
	public int getAddressID()
	{
		return address.getID();
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setCustomerID(int id)
	{
		this.customerID = id;
	}
	
	public int getCustomerID()
	{
		return this.hashCode();
	}
	
	
	public void setActive(int status)
	{
		active = status;
	}
	
	public int getActive()
	{
		return active;
	}
	


	
	
}
