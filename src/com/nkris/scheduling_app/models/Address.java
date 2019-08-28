package com.nkris.scheduling_app.models;

import java.sql.SQLException;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_Address;

public class Address 
{
	private int id;
	private String streetAddress;
	private City city;
	private Country country;
	private String zipCode;
	private String phoneNumber;
	private String state;
	private static int addressIndex;
	

	public Address()
	{
	
	}
	
	
	public Address(City city, Country country, String streetName)
	{
		this.city = city;
		this.country = country;
		streetAddress = streetName;
	}
	
	public static void setIndex() throws ClassNotFoundException, SQLException
	{
		int index = SQL_Address.getLastIndex(DatabaseHandler.getDBconnection());
		addressIndex = index;
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
	
	public void setId()
	{
		id = addressIndex;
		addressIndex++;
	}
	
	public int getId()
	{
		return id;
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
