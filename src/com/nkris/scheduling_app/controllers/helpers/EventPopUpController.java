package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.nkris.scheduling_app.calendar.event.Event;

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
	private TextField eventTitleTextField;
	
	@FXML
	private JFXDatePicker startDatePicker;
	
	@FXML
	private JFXDatePicker endDatePicker;
	
	@FXML
	private JFXTimePicker startTimePicker;
	
	@FXML
	private JFXTimePicker endTimePicker;
	
	@FXML
	private TextArea descriptionTextArea;
	
	private Event newEvent;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}

	@FXML
	public void saveEvent(ActionEvent event)
	{
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	    
	    newEvent = new Event();
	    newEvent.setTitle(eventTitleTextField.getText());
	    newEvent.setStartDate(startDatePicker.getValue());
	    newEvent.setEndDate(endDatePicker.getValue());
	    newEvent.setStartTime(startTimePicker.getValue());
	    newEvent.setDescription(descriptionTextArea.getText());   
	}
	
	
	
	@FXML
	private void cancelNewEvent(ActionEvent event)
	{
		
	}
	

	
	

	
	
	
	
	
	
}
