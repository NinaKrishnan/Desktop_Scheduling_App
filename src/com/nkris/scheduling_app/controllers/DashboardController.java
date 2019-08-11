package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.nkris.scheduling_app.calendar.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


//TODO: Make calendar days interactive; light up upon mouse hover and allow for selection
//TODO:Add event and scheduling functionality
//TODO: Auto fill monthy calendar upon selection of month via arrows
//TODO: Display day's news feed/schedule on left hand dash
//TODO: Add calendar views for week and day and figure out how to switch between them without s
		//switching scenes
//TODO: Add functionality to user, reports, and database buttons


//*****************************************************************************************************//


public class DashboardController implements Initializable
{
	
	@FXML
	private GridPane calendarGrid; //The monthly calendar view on dashboard screen
	
	@FXML
	private HBox weekdayLabels; //Days of the week headers over grid columns
	
	@FXML
	private Label timeClock; //Live animated clock; localized to timezone
	
	@FXML
	private Label dateLabel; //Label to display date on side panel schedule
	
	@FXML
	private ToggleGroup calendarView; //Toggle button group for calendar view options
									//(month, week, or day)
	
	@FXML
	private ToggleButton monthToggleButton; //Toggle button to select a month calendar view
	
	@FXML
	private ToggleButton weekToggleButton; //Toggle button to select a week calendar view
	
	@FXML
	private ToggleButton dayToggleButton; //Toggle button to select a day calendar view
	
	@FXML
	private Label monthHeader; //The header of the monthly calendar; displays the month
	
	@FXML
	private Button previousMonthButton; //Left arrow head to switch to previous month's calendar
	
	@FXML
	private Button nextMonthButton; //Right arrow head to switch to next month's calendar

	@FXML
	private ToggleGroup calendarDay; //Toggle button group for calendar days on monthly view calendar
	
	
	@FXML
	private ToggleButton row1col0; //TODO
	
	@FXML
	private ToggleButton row1col1; //TODO
	
	@FXML
	private ToggleButton row1col2; //TODO
	
	@FXML
	private ToggleButton row1col3; //TODO
	
	@FXML
	private JFXHamburger jfxHamburger;
	
	@FXML
	private JFXDrawer mainDrawer; //Main navigation drawer
	
	@FXML
	private AnchorPane dashboardAnchorPane;
	
	private Calendar calendar;
	
	
	
	/*
	 * Set the clock
	 * Set the main navigation drawer
	 * Set the date
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		setClock();
		setHamburgerTransition();	
		displayCurrentDate();
		setDays();
	}
	
	
 
	
	/*
	 * Creates a 12-hour clock in the left hand corner of dashboard schedule. Clock is localized to
	 * time zone and includes a second counter.
	 */
	
	private void setClock() 
	{
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss"); //For a.m. times only
	        DateTimeFormatter pmFormatter = DateTimeFormatter.ofPattern("mm:ss"); //for p.m. times only
	       
	        //If it is past noon, subtract twelve from the time and add a "p.m." string 
	        if(LocalDateTime.now().getHour()>12) 
	        {
	        	timeClock.setText("\n"+(LocalDateTime.now().getHour()-12 ) +
	        			":"+LocalDateTime.now().format(pmFormatter)+" p.m.");
	        }
	        if(LocalDateTime.now().getHour() == 0)
	        {
	        	timeClock.setText("\n"+(LocalDateTime.now().getHour()+12) + 
	        			":"+LocalDateTime.now().format(pmFormatter)+" a.m.");
	        }
	       
	        //If it is noon or earlier, add an "a.m." string 
	        else timeClock.setText("\n"+LocalDateTime.now().format(formatter)+" a.m.");
	    }), new KeyFrame(Duration.seconds(1)));
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	}
	 
	
	/*
	 * Set date on top of schedule feed 
	 */
	private void displayCurrentDate()
	{
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		dateLabel.setText("Your agenda: "+df.format(new Date()));
	}
	
	/*
	 *  Controls the main navigation drawer. If drawer is open, hamburger icon is an "x"
	 *  and clicking it closes the drawer.
	 *  
	 *  If the drawer is closed, the drawer is set to contain the contents of a separate, helper FXML file
	 *  and the hamburger appears as three lines
	 *  
	 */
	private void setHamburgerTransition() throws NullPointerException
	{
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHamburger);
		transition.setRate(-1);
		jfxHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();
			
			if(mainDrawer.isOpened())
			{
				mainDrawer.close();
			}
			else
			{
				try
				{
					setMainDrawerContent();
					mainDrawer.open();
				}
				
				catch(IOException error)
				{
					error.printStackTrace();
				}
			}
			
		});
		
	}
	  
	
	/*
	 * Sets the contents of the navigation drawer to the helper FXML file, "DrawerContent.fxml"
	 */
	private void setMainDrawerContent() throws IOException
	{
		AnchorPane drawerContent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/helpers/DrawerContent.fxml"));
		mainDrawer.setSidePane(drawerContent);
	}
	
	/*
	 * Close drawer if it is opened
	 */
	@FXML
	private void closeDrawer(MouseEvent event)
	{
		if(mainDrawer.isOpened())
		{
			mainDrawer.close();
		}
	}
	
	/*
	 * Create new Calendar instance, get first day through static method and assign
	 * firstDay field to value/return it
	 */
	private int getFirstDay()
	{
		calendar = new Calendar();
		calendar.firstDay = Calendar.getFirstDayOfMonth();
		return calendar.firstDay;
	}
	

	/*
	 * Populate the calendar with date labels & disable outlying days before/after
	 * current month
	 * TODO: Add labels to outlying months
	 */
	private void setDays()
	{
		int firstDay = getFirstDay();
		int numberOfDays = Calendar.getNumberOfDaysInMonth(LocalDate.now().getMonthValue());
		int day = 1;
			
		for(int i = 1; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				findTodayOnCalendar(day, j, i);
					
				if(j >= firstDay || i > 1)
				{	
					setDayLabels(day, j, i);
					day++;
				}
					
				disablePrevMonthDays(firstDay, j, i);
					
				disableNextMonthDays(day, numberOfDays, j, i);
			}		
		}
	}
	
	
	/*
	 * Disable buttons on days falling after month by covering with label
	 */
	private void disableNextMonthDays(int day, int numberOfDays, int j, int i)
	{
		if(day > numberOfDays)
		{
			Label lbl = new Label();
			lbl.setPrefWidth(140);
			lbl.setPrefHeight(126);
			lbl.setStyle("-fx-background-color: #b8b8ba;" + "-fx-border-color: #acacb0;");
			calendarGrid.add(lbl, j, i);
		}
	}
	
	
	/*
	 * Disable buttons on previous months days
	 */
	private void disablePrevMonthDays(int firstDay, int j, int i)
	{
		if(j < firstDay && i ==1)
		{
			Button btn = new Button();
			btn.setPrefWidth(140);
			btn.setPrefHeight(126);
			btn.setDisable(true);
			btn.setVisible(false);
			calendarGrid.add(btn, j, i);
		}
	}
	
	/*
	 * Create labels for days and assign coordinates
	 */
	private void setDayLabels(int day, int j, int i)
	{
		Label lbl = new Label(Integer.toString(day));
		lbl.setStyle("-fx-font-size: 18");
		GridPane.setHalignment(lbl, HPos.LEFT);
		GridPane.setValignment(lbl, VPos.TOP);
		calendarGrid.add(lbl, j, i);
	}
	
	/*
	 * Find today's coordinates on the calendar; highlight and auto-select it
	 */
	private void findTodayOnCalendar(int day, int j, int i)
	{
		if(day == Calendar.getDateNumber())
		{
			ToggleButton today = new ToggleButton();
			today.setToggleGroup(calendarDay);
			today.setStyle("-fx-background-color: #beebed");
			today.setStyle("-fx-border-color: #0d0dd6;" +"-fx-background-color: #beebed");
			today.setSelected(true);
			today.setPrefWidth(140);
			today.setPrefHeight(126);
			calendarGrid.add(today, j, i);
		}	
	}
	
	
	
	
}
