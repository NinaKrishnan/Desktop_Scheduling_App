package com.nkris.scheduling_app.models;

import java.sql.SQLException;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_City;

public class City 
{
	private Country country;
	
	private String cityName;
	
	private int cityID;
	
	private static int cityIndex;
	
	

	public City()
	{
		
	}
	
	
	
	public City(Country country, String cityName)
	{
		this.country = country;
		this.cityName = cityName;
	}
	
	public static void setIndex() throws ClassNotFoundException, SQLException
	{
		int index = SQL_City.getLastIndex(DatabaseHandler.getDBconnection());
		cityIndex = index;
	}
	
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
	
	public void setCityID()
	{
		cityID = cityIndex;
		cityIndex++;
	}
	
	public int getCityID()
	{
		return cityID;
	}
	

	
	
	
}
