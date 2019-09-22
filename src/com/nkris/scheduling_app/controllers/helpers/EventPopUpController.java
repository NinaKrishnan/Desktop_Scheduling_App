package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.nkris.scheduling_app.controllers.EventController;
import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;
import com.nkris.scheduling_app.models.Customer;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
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
	private TextField locationTextField;
	
	@FXML
	private TextField typeTextField;
	
	
	public static Appointment currentEvent;
	
	public static ObservableList<Appointment> eventContainer;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}
 
	@FXML
	public void saveEvent(ActionEvent event)
	{
	    
	    Appointment newEvent = new Appointment();
	    newEvent = new Appointment();
	    newEvent.setCustomer(CustomerSelectionController.selectedCustomer);
	    newEvent.setTitle(eventTitleTextField.getText());
	    newEvent.setStartDate(startDatePicker.getValue());
	    newEvent.setEndDate(endDatePicker.getValue());
	    newEvent.setStartTime(startTimePicker.getValue());
	    newEvent.setEndTime(endTimePicker.getValue());
	    newEvent.setDescription(descriptionTextArea.getText());   
	    newEvent.setType(typeTextField.getText());
	    
	    try {
			SQL_Appointments.insertAppointment(newEvent);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    finally {
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		    currentEvent = newEvent;
	    }
	}
	
	
	
	@FXML
	private void showCustomerSelection(MouseEvent event)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource
				("/com/nkris/scheduling_app/FXML/helpers/CustomerSelectionTable.fxml"));
		
		EventController eventController = new EventController();
		loader.setController(eventController);
		
		
		Parent layout;
		try 
		{
			layout = loader.load(getClass().getResource
					("/com/nkris/scheduling_app/FXML/helpers/CustomerSelectionTable.fxml"));
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
		
		CustomerSelectionController.setSelectedCustomerTextfield(customerTextField);
	}

	
	public static Appointment getEvent()
	{
		return currentEvent;
	}
	
	@FXML
	private void cancelNewEvent(ActionEvent event)
	{
		
	}
	

	
	

	
	
	
	
	
	
}
