package com.nkris.scheduling_app.models;

public class Address 
{
	private int id;
	private String streetAddress;
	private City city;
	private Country country;
	private String zipCode;
	private String phoneNumber;
	private String state;
	
	
	
	public Address()
	{
		id = this.hashCode();
	}
	
	public void setPhoneNumber(String number)
	{
		phoneNumber = number;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setZipcode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	public String getZipcode()
	{
		return zipCode;
	}
	
	public void setCountry(Country country)
	{
		this.country = country;
	}
	
	public Country getCountry()
	{
		return country;
	}
	
	public void setCity(City city)
	{
		this.city = city;
	}
	
	public City getCity()
	{
		return city;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setStreetAddress(String address)
	{
		streetAddress = address;
	}
	
	public String getStreetAddress()
	{
		return streetAddress;
	}
	
	
	@Override
	public String toString()
	{
		return streetAddress;
	}
	
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getState()
	{
		return state;
	}
	
}
