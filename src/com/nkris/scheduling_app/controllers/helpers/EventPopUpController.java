package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventPopUpController implements Initializable
{
	

	
	@FXML
	private Button saveEventButton;
		
	@FXML
	private Button cancelEventButton;
	
	@FXML
	private  TextField eventTitleTextField;
	
	@FXML
	private  JFXDatePicker startDatePicker;
	
	@FXML
	private  JFXDatePicker endDatePicker;
	
	@FXML
	private  JFXTimePicker startTimePicker;
	
	@FXML
	private  JFXTimePicker endTimePicker;
	
	@FXML
	private  TextArea descriptionTextArea;
	
	@FXML
	private TextField customerTextField;
	
	@FXML
	private TextField contactTextField;
	
	@FXML
	private TextField typeTextField;
	
	public static Appointment newEvent;
	
	public static ObservableList<Appointment> eventContainer;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}
 
	@FXML
	public void saveEvent(ActionEvent event)
	{
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	    newEvent = new Appointment();
	    newEvent.setTitle(eventTitleTextField.getText());
	    newEvent.setStartDate(startDatePicker.getValue());
	    newEvent.setEndDate(endDatePicker.getValue());
	    newEvent.setStartTime(startTimePicker.getValue());
	    newEvent.setEndTime(endTimePicker.getValue());
	    newEvent.setDescription(descriptionTextArea.getText());   
	    
	    try {
			SQL_Appointments.insertAppointment(newEvent);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	

	
	public static Appointment getEvent()
	{
		return newEvent;
	}
	
	@FXML
	private void cancelNewEvent(ActionEvent event)
	{
		
	}
	

	
	

	
	
	
	
	
	
}
