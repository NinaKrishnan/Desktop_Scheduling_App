package com.nkris.scheduling_app.controllers;

//imports

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.nkris.scheduling_app.controllers.helpers.EventPopUpController;
import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;
import com.nkris.scheduling_app.models.Calendar;
import com.nkris.scheduling_app.models.Customer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


//TODO: Display day's news feed/schedule on left hand dash
//TODO: Add calendar views for week and day and figure out how to switch between them without s
		//switching scenes
//TODO: Add functionality to user, reports, and database buttons


//*****************************************************************************************************//


public class DashboardController implements Initializable
{
	
	@FXML
	private AnchorPane calendarAnchorPane; //Wraps the calendar grid. Used when switching between month and week view
	
	@FXML
	private GridPane calendarGrid; //The monthly calendar view on dashboard screen
	
	@FXML
	private JFXButton todayButton; //Brings back current calendar
	
	@FXML
	private Label monthAndYearLabel; //Month calendar header: "Month XXXX"
	
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
	private Button prevMonthButton; //Left arrow head to switch to previous month's calendar
	
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
	private AnchorPane dashboardAnchorPane; //Agenda feed
	
	@FXML
	private TableView<Appointment> dashboardTable;
	
	@FXML
	private TableColumn<Appointment, String> appointmentsColumn;
	
	@FXML
	private TableColumn<Appointment, String> timeColumn;
	
	public static LocalDate clickedDate = null;

	private Calendar calendar = new Calendar();
	
	public static LocalDate currentEventStart = null;
	
	/*
	 * Set the clock
	 * Set the main navigation drawer
	 * Set the date
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		setMonthAndYearHeader();
		setMonth();
		setYear();
		setClock();
		setHamburgerTransition();	
		displayCurrentDate();
		try {
			setDays(getMonth(), getYear(), false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setAgendaFeedCellValues();
		try {
			populateAgendaFeed(dashboardTable);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private void setMonth()
	{
		calendar.month = LocalDate.now().getMonthValue();
	}
	
	private void setYear()
	{
		calendar.year = LocalDate.now().getYear();
	}
	
	private int getMonth()
	{
		return LocalDate.now().getMonthValue();
	}
	
	private int getYear()
	{
		return LocalDate.now().getYear();
	}
 
	
	private void setMonthAndYearHeader()
	{
		
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		
		monthAndYearLabel.setText(Calendar.getMonthName(month)+" "+year);
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
	        else if(LocalDateTime.now().getHour() == 0)
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
	
	
	@FXML
	private void goToToday(ActionEvent event) throws SQLException
	{
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		
		setDays(month, year, false);
		monthAndYearLabel.setText(Calendar.getMonthName(month)+" "+year);
		
	}
	


	
	
	private void setDayOfWeekLabels()
	{
		for(int i = 0; i < 1; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				Label day = new Label("     "+Calendar.getDayName(j));
				day.setStyle("-fx-font-size: 18;" + "-fx-text-fill: white;");
				day.setAlignment(Pos.CENTER);
				calendarGrid.add(day, j, i);
			}
		}
			
	}

	/*
	 * Populate the calendar with date labels & disable outlying days before/after
	 * current month
	 * TODO: Add labels to outlying months
	 */
	private void setDays(int month, int year, boolean disable) throws SQLException
	{
		calendar.setMonth(month);
		calendar.setYear(year);
		setDayOfWeekLabels();
		int firstDay = getFirstDay(month, year);
		int numberOfDays = Calendar.getNumberOfDaysInMonth(month);
		int day = 1;
		int nextMonthDay = 1;
			
		for(int i = 1; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
					
				disablePrevMonthDays(firstDay, j, i);
				
				if(j >= firstDay || i > 1)
				{	
					setDayButtons(day, j, i, false);
					day++;
				}
				
				if(day > numberOfDays+1)
				{
					disableNextMonthDays(day, numberOfDays, nextMonthDay, j, i);
					nextMonthDay++;
				}
				if(i == 1 && j < firstDay)
				{
					disablePrevMonthDays(firstDay, j, i);
				}
			}		
		}
	}
	
	//Function of next arrow
	@FXML
	private void goToNextMonth(ActionEvent event) throws SQLException
	{
		int month = calendar.getNextMonth();
		int year = calendar.getYear();
		String strMonth = Calendar.getMonthName(month);
		monthAndYearLabel.setText(strMonth+" " + Integer.toString(year));
		calendarGrid.getChildren().clear();
		setDays(month, year, true);
	}
	
	//Back arrow function
	@FXML
	private void goToPrevMonth(ActionEvent event) throws SQLException
	{
		int month = calendar.getPreviousMonth();
		int year = calendar.getYear();
		String strMonth = Calendar.getMonthName(month);
		monthAndYearLabel.setText(strMonth +" " + Integer.toString(year));
		calendarGrid.getChildren().clear();
		setDays(month, year, true);
	}
	
	
	/*
	 * Create new Calendar instance, get first day through static method and assign
	 * firstDay field to value/return it
	 */
	private int getFirstDay(int month, int year)
	{
		calendar.firstDay = calendar.getFirstDayOfMonth(month, year);
		return calendar.firstDay;
	}
	
	
	
	/*
	 * Disable buttons on days falling after month by covering with label
	 */
	private void disableNextMonthDays(int day, int numberOfDays, int nextMonthDay, int j, int i)
	{
		if(day > numberOfDays+1)
		{
			Label lbl = new Label();
			lbl.setPrefWidth(140);
			lbl.setPrefHeight(126);
			lbl.setStyle("-fx-background-color: #b8b8ba;" + "-fx-border-color: #acacb0;");
			calendarGrid.add(lbl, j, i);
			setNextMonthLabels(numberOfDays, nextMonthDay, j, i);
		}
	}
	
	//Populate the calendar when next month is clicked
	private void setNextMonthLabels(int numberOfDays, int day, int j, int i)
	{
		Label lbl = new Label(Integer.toString(day));
		lbl.setStyle("-fx-font-size: 18;" + "-fx-text-fill: #888a89;");
		GridPane.setHalignment(lbl, HPos.LEFT);
		GridPane.setValignment(lbl, VPos.TOP);
		calendarGrid.add(lbl, j, i);
	
	}
	
	
	/*
	 * Disable buttons on previous months days
	 */
	private void disablePrevMonthDays(int firstDay, int j, int i)
	{
		if(i==1 && j < firstDay)
		{
			ToggleButton btn = new ToggleButton();
			btn.setPrefWidth(140);
			btn.setPrefHeight(126);
			btn.setStyle("-fx-background-color: #b8b8ba;"+"-fx-border-color: #a3a3a3;");
			calendarGrid.add(btn, j, i);
		}
	}
	
	/*
	 * Create labels for days and assign coordinates
	 */
	private void setDayButtons(int day, int j, int i, boolean disable) throws SQLException
	{
		ToggleButton toggleDay = new ToggleButton();
		setToggleSize(toggleDay);
		toggleDay.setToggleGroup(calendarDay); //prevents user from selecting more than one day at a time
		Label lbl = new Label(Integer.toString(day)) {
			public String getDay() {
				return Integer.toString(day);
			}
		}; //Day label
		lbl.setStyle("-fx-font-size: 18");
		GridPane.setHalignment(lbl, HPos.LEFT);
		GridPane.setValignment(lbl, VPos.TOP);
		
		if(disable == true)
		{
			toggleDay.setDisable(true);
			toggleDay.setVisible(false);
		}
		else
		{
			if(isCurrentMonthAndYear())
			{
				findTodayOnCalendar(day, j, i, toggleDay);
			}
			calendarGrid.add(toggleDay, j, i);
			calendarGrid.add(lbl, j, i);
			
			int currDay = Integer.parseInt(lbl.getText());
			int currMonth = calendar.getMonth();
			int currYear = calendar.getYear();
			if(SQL_Appointments.hasEvent(currDay, currMonth, currYear)) {
				addEventToCalendar(j, i);
			}
			toggleDay.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
				currentEventStart = calendar.getDate(Integer.toString(day));
				try {
					handleEventPopup();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(EventPopUpController.currentEvent != null) {
					addEventToCalendar(j, i);
					EventPopUpController.currentEvent = null;
				}
				try {
					populateAgendaFeed(dashboardTable);
				} catch (ClassNotFoundException | SQLException ec) {
					ec.printStackTrace();
				}
				toggleDay.setSelected(false);
			});
			
		}
	}
	
	private LocalDate getEventFlagDate(int day, int month, int year) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		String date = day+"/"+month+"/"+year;
		
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	
	
	/*
	 * Check if the displayed month & year is current
	 */
	private boolean isCurrentMonthAndYear()
	{
		int monthVal = LocalDate.now().getMonthValue();
		int calendarMonthVal = calendar.getMonth();
		
		int yearVal = LocalDate.now().getYear();
		int calendarYearVal = calendar.getYear();
		return(monthVal==calendarMonthVal && yearVal == calendarYearVal);
	}
	
	//Format toggle buttons on monthly calendar view
	private void setToggleSize(ToggleButton tb)
	{
		tb.setPrefWidth(140);
		tb.setPrefHeight(126);
	}
	
	
	
	/*
	 * Find today's coordinates on the calendar; highlight and auto-select it
	 */
	private void findTodayOnCalendar(int day, int j, int i, ToggleButton tb)
	{
		if(day == Calendar.getDateNumber())
		{
			tb.setStyle("-fx-background-color: #fcba03;");
			tb.setSelected(true);
		}	
	}
	
//***************************************EVENT POPUP METHODS********************************************//
	

	private void handleEventPopup() throws SQLException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/nkris/scheduling_app/FXML/Event.fxml"));
		
		EventController eventController = new EventController();
		loader.setController(eventController);
		
		
		Parent layout;
		try 
		{
			layout = loader.load(getClass().getResource("/com/nkris/scheduling_app/FXML/Event.fxml"));
			Scene scene = new Scene(layout);
			Stage eventStage = new Stage();
			eventController.setStage(eventStage);
			
			eventStage.initModality(Modality.WINDOW_MODAL); //Create popup window
			eventStage.setScene(scene);
			eventStage.showAndWait();
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
		
		
		calendarGrid.getChildren().clear();
		setDays(getMonth(), getYear(), false);
		EventPopUpController.currentEvent = null;
		
		
	}
	 
	//Add a flag to the calendar monthly view if an event is created that day
	private void addEventToCalendar(int j, int i) 
	{
		Label label = new Label("Event");
		label.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {
				handleEventPopup();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		label.setStyle("-fx-background-color: #fc0324;");
		GridPane.setHalignment(label, HPos.CENTER);
		GridPane.setValignment(label, VPos.CENTER);
		calendarGrid.add(label, j, i);
		

	}	
	
	
	
	
	//TODO
	private void populateAgendaFeed(TableView<Appointment> table) throws ClassNotFoundException, SQLException
	{
		LocalDate today = LocalDate.now();
		ObservableList<Appointment> appointments = SQL_Appointments.getAppointments(today);
		
		table.getItems().clear();
		table.setItems(appointments);
		setAgendaFeedStyle();
	}
	
	private void setAgendaFeedStyle()
	{
		dashboardTable.setStyle("-fx-background-color: #aae6f2;");
		appointmentsColumn.setStyle("-fx-font-color: #130f94;"+"-fx-font-weight: bold;"+"-fx-font-size: 17.5;");
		timeColumn.setStyle("-fx-font-size: 17;");
	} 
	
	//TODO
	private void greyOutAppointment(Appointment appt) 
	{
		if(appt.isOver()) {
			
		}
	}
	
	
	private void setAgendaFeedCellValues()
	{
		appointmentsColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("timeRange"));

	}
	
	public static void createEventLabel()
	{
		Label label = new Label();
		label.setStyle("-fx-background-color: #e3071d");
	}
	
	/*
	 * This method takes in a day of the month and returns the corresponding coordinates for that day on the 
	 * calendar grid. It calculates this relative to the first day of the month: 
	 */
	private int getEventDay(LocalDate date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");
		String dayFormat = formatter.format(date);
		Integer dayDate = Integer.parseInt(dayFormat);
		return dayDate;
	}
	
	private void addEventFlag(int day)
	{
		int index = 1;
		int firstDay = Calendar.getFirstDayOfMonth(EventPopUpController.currentEventStart);
		
		for(int i = 1; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				if(index == day) {
					addEventToCalendar(j, i);
				}
				if(j > firstDay || i > 1)
				{
					index++;
				}
			}
		}
	}
}
