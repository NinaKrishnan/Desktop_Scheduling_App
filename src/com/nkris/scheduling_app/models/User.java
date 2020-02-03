package com.nkris.scheduling_app.models;

public class User
{

	private String name;
	private String userName;
	private String password;
	private int id;

	
	
	
	public User(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	
	public void setUserID(int id)
	{
		this.id = id;
	}
	
	public int getUserID()
	{
		return id;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getUserName()
	{
		return userName;
	}
}
