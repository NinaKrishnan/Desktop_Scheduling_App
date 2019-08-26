package com.nkris.scheduling_app.unit_tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.nkris.scheduling_app.controllers.helpers.NewCustomerController;
import com.nkris.scheduling_app.database.SQL_Customer;

class CalendarTests {

	
	NewCustomerController ncc = new NewCustomerController();
	
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
	void getCustomerSelection() throws SQLException, ClassNotFoundException
	{
		assertEquals(SQL_Customer.getSelectedCustomer(901126450).getName(), "Shell Silverado");
	}
	
	@Test
	void getAddressID() throws SQLException, ClassNotFoundException
	{
		assertEquals(SQL_Customer.getSelectedCustomer(901126450).getAddress().getId(), 1165285614);
	}
	
	
}
