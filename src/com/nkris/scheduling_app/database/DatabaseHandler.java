package com.nkris.scheduling_app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections; 
import java.util.*;

public class DatabaseHandler
{

  	static Connection conn = null;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String db = "U068iL";
    static String url = "jdbc:mysql://52.206.157.109 /" + db;
    static String user = "U068iL";
    static String pass = "53688698592";
	
	public static void connect() throws ClassNotFoundException
	{
		

	    try
	     {
	          Class.forName(driver);
	          conn = DriverManager.getConnection(url,user,pass);
	      }

	     catch (SQLException e)
	     {
	          System.out.println("SQLException: "+e.getMessage());
	          System.out.println("SQLState: "+e.getSQLState());
	          System.out.println("VendorError: "+e.getErrorCode());
	      }
	   }

	
	public static void disconnect() throws SQLException
	{
		conn.close();
	}
	
	public static Connection getConnection()
	{
		return conn;
	}
	
	
	
	
}
	
	
	
	
	
	
	
	
	

