package com.nkris.scheduling_app.unit_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.nkris.scheduling_app.calendar.Calendar;

class CalendarTests {

	
	@Test
	void testCalendarDayGetter() 
	{
		
		assertEquals(6, Calendar.getDayOfWeekToday());
	}

	@Test
	void testFirstDayOfMonthGetter()
	{
		assertEquals(4, Calendar.getFirstDayOfMonth());
	}
	
	@Test
	void testDayDateGetter()
	{
		assertEquals(10, Calendar.getDateNumber());
	}
}
