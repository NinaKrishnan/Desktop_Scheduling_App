package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;


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
	private ToggleGroup calendarDay;
	
	
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
	private JFXDrawer jfxDrawer;
	
	
	/*
	 * Call the setClock() method to display local time
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		setClock();
		setHamburgerTransition();	
		displayCurrentDate();
		
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
	       
	        //If it is noon or earlier, add an "a.m." string 
	        else timeClock.setText("\n"+LocalDateTime.now().format(formatter)+" a.m.");
	    }), new KeyFrame(Duration.seconds(1)));
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	}
	
	

	private void displayCurrentDate()
	{
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		dateLabel.setText("Your agenda: "+df.format(new Date()));
	}
	
	/*
	 *  
	 */
	private void setHamburgerTransition()
	{
		try 
		{
			setDrawerContent();
		
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHamburger);
		transition.setRate(-1);
		jfxHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();
			
			if(jfxDrawer.isOpened())
			{
				jfxDrawer.close();
			}
			else jfxDrawer.open();
		});
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void setDrawerContent() throws IOException
	{
		AnchorPane drawerContent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/helpers/DrawerContent.fxml"));
		jfxDrawer.setSidePane(drawerContent);
	}
	
	
	

	
	/*
	 * Function to retrieve any individual cell in the calendar grid using the cell's coordinates
	 */
	private Node getCalendarDay(int row, int col)
	{
		for(Node node : calendarGrid.getChildren())
		{
			if(calendarGrid.getRowIndex(node) == row && calendarGrid.getColumnIndex(node) == col)
			{
				return node;
			}
		}
		return null;
	}
	



}
