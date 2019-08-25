package com.nkris.scheduling_app.models;

public class Country
{
	private int countryID;
	
	private String countryName;
	
	
	public void setCountryID(int id)
	{
		countryID = id;
	}
	
	
	public int getCountryId()
	{
		return countryID;
	}
	
	public void setCountryName(String name)
	{
		countryName = name;
	}
	
	public String getCountryName()
	{
		return countryName;
	}
	
	
}
