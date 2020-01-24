package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;
import com.nkris.scheduling_app.models.Calendar;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class WeeklyCalendarController implements Initializable
{
	@FXML
	private TableView<Appointment> table1;
	
	@FXML 
	private TableColumn<Appointment, String> col1;
	
	@FXML 
	private TableView<Appointment> table2;
	
	@FXML
	private TableColumn<Appointment, String> col2;
	
	@FXML 
	private TableView<Appointment> table3;
	
	@FXML
	private TableColumn<Appointment, String> col3;
	
	@FXML 
	private TableView<Appointment> table4;
	
	@FXML
	private TableColumn<Appointment, String> col4;
	
	@FXML 
	private TableView<Appointment> table5;
	
	@FXML
	private TableColumn<Appointment, String> col5;
	
	@FXML 
	private TableView<Appointment> table6;
	
	@FXML
	private TableColumn<Appointment, String> col6;
	
	@FXML 
	private TableView<Appointment> table7;
	
	@FXML
	private TableColumn<Appointment, String> col7;

	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		
	}
	
	
	
	private void populateCalendar(TableView<Appointment> table) 
	{
	
		
	}
	
	private void setCellValues() 
	{
		col1.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
		col2.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
		col3.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
		col4.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
		col5.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
		col6.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
		col7.setCellValueFactory(new PropertyValueFactory<Appointment, String>("sticker"));
	}
	
	
	private void setDayLabels()
	{
		int day = LocalDate.now().getDayOfWeek().getValue();
		if(day == 7) {
			day = 1;
		}
		
		else {
			day++;
		}
		
		day--;
		
		setName(col1, day);
		setName(col2, day);
		setName(col3, day);
		setName(col4, day);
		setName(col5, day);
		setName(col6, day);
		setName(col7, day);
	}
	
	
	private void populateCalendar() throws ClassNotFoundException, SQLException
	{
		setDayLabels();
		
		LocalDate date = LocalDate.now();
		int plus = 1;
		
		populateDays(date, table1);
		populateDays(date.plusDays(plus++), table2);
		populateDays(date.plusDays(plus++), table3);
		populateDays(date.plusDays(plus++), table4);
		populateDays(date.plusDays(plus++), table5);
		populateDays(date.plusDays(plus++), table6);
		populateDays(date.plusDays(plus++), table7);
		
	}
	
	private void populateDays(LocalDate date, TableView<Appointment> table) throws ClassNotFoundException, SQLException
	{
		ObservableList<Appointment> appointments = SQL_Appointments.getAppointments(date);
		table.getItems().clear();
		table.setItems(appointments);
	}
	
	private void setName(TableColumn<Appointment, String>col, int day)
	{
		col.setText(Calendar.getDayName(day++));
	}
	
}
