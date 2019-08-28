package com.nkris.scheduling_app.models;

import java.sql.SQLException;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_Country;

public class Country
{
	private int countryID;
	
	private String countryName;
	
	private static int countryIndex;
	
	

	public Country()
	{
		
	}
	
	public Country(String countryName)
	{
		this.countryName = countryName;
	}	
	
	
	public static void setIndex() throws ClassNotFoundException, SQLException
	{
		int index = SQL_Country.getLastIndex(DatabaseHandler.getDBconnection());
		countryIndex = index;
	}
	
	
	public void setCountryID(int id)
	{
		countryID = id;
	}
	
	public void setCountryID()
	{
		countryID = countryIndex;
		countryIndex++;
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
