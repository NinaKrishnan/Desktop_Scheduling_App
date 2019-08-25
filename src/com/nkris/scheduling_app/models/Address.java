package com.nkris.scheduling_app.models;

public class Address 
{
	private int id;
	private String address;
	private City city;
	private Country country;
	private String zipCode;
	private String phoneNumber;
	
	
	
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
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	
	@Override
	public String toString()
	{
		return address;
	}
	
	
}
