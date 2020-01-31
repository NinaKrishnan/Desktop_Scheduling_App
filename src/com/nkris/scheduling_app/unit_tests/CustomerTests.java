package com.nkris.scheduling_app.unit_tests;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_City;
import com.nkris.scheduling_app.database.SQL_Customer;
import com.nkris.scheduling_app.models.Customer;

class CustomerTests {

	//@Test
	void printCustomers() throws ClassNotFoundException, SQLException
	{
		for(Customer customer : SQL_Customer.getCustomers()) {
			System.out.println(customer.getName() + ": "+customer.getAddress().getStreetAddress());
		}
	}
	
	@Test
	void customersSize() throws ClassNotFoundException, SQLException
	{
		DatabaseHandler.connect();
		System.out.println(SQL_City.getCityId("Del", 1));
	}
	
	//@Test
	void getConnection() throws ClassNotFoundException, SQLException {
		DatabaseHandler.connect();
		printCustomers();
		System.out.println("L");
	}
}
