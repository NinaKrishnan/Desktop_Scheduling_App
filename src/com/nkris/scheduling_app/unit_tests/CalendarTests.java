package com.nkris.scheduling_app.unit_tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nkris.scheduling_app.controllers.helpers.ViewCustomerController;
import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_Address;

class CalendarTests {

	
	ViewCustomerController ncc = new ViewCustomerController();
	
	@Test
	void firstNameText()
	{
		assertEquals(ncc.getFirstName("Nina Krishnan"), "Nina");
	}
	 
	@Test
	void lastNameTest()
	{
		assertEquals(ncc.getLastName("Nina Krishnan"), "Krishnan");
	}
	
	
	@Test
	void getZipCode() throws SQLException, ClassNotFoundException
	{
		DatabaseHandler.connect();
		assertEquals(SQL_Address.getZipcode(43), "19382");
	}
	
	@Test
	void getAddressId() throws SQLException, ClassNotFoundException
	{
		DatabaseHandler.connect();
		assertEquals(SQL_Address.getAddressId(2), 1);
	}
	
	@Test
	void testzipcode() throws ClassNotFoundException, SQLException
	{
		assertEquals(SQL_Address.getZipcode(2), "28274");
	}
	
	
	
	
	
	
	@Test
	void getId() throws SQLException, ClassNotFoundException
	{
		DatabaseHandler.connect();
		assertEquals(SQL_Address.getAddressId(1), 1);
	}
	
	
	
	
	
	
}
