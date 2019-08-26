package com.nkris.scheduling_app.models;
import com.nkris.scheduling_app.models.Address;

public class Customer
{
	private String name;
	private String firstName;
	private String lastName;
	private Address address;
	private int customerID;
	private int active;
	private int addressId;
	
	
	public Customer(Address address)
	{
		customerID = this.hashCode();
		active = 1;
		this.address = address;
	}
	
	public Customer()
	{
		
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
	
	public int getAddressID()
	{
		return address.hashCode();
	}
	
	public Address getCustomerAddress()
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
	
	
	public int getAddressId()
	{
		return addressId;
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
