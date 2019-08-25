package com.nkris.scheduling_app.models;

public class City 
{
	private Country country;
	
	private String cityName;
	
	private int cityID;
	
	
	
	
	public void setCityName(String name)
	{
		cityName = name;
	}
	
	public String getCityName()
	{
		return cityName;
	}
	
	public void setCountry(Country country)
	{
		this.country = country;
	}
	
	public Country getCountry()
	{
		return country;
	}
	
	
	public void setCityID(int id)
	{
		cityID = id;
	}
	
	public int getCityID()
	{
		return cityID;
	}
	

	
	
	
}
