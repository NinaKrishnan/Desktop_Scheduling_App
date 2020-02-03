package com.nkris.scheduling_app.controllers.helpers;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.nkris.scheduling_app.controllers.EventController;
import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;

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


public class ViewEventController implements Initializable
{
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

	    @FXML
	    private Button cancelEventButton;

	    @FXML
	    private Button updateEventButton;

	    @FXML
	    private TextField customerTextField;

	    @FXML
	    private TextField contactTextField;

	    @FXML
	    private TextField locationTextField;

	    @FXML
	    private JFXComboBox<Type> typeComboBox;

	   
	    
	    
	    @Override
		public void initialize(URL url, ResourceBundle rb) 
	    {
	    	for(Type type : Arrays.asList(Type.values())) {
				typeComboBox.getItems().add(type);
			}
	    	
	    	
	    	Appointment appointment = EventController.selectedAppointment;
	    	System.out.println(appointment.getCustomer().getName());
	    	eventTitleTextField.setText(appointment.getTitle());
	    	startDatePicker.setValue(appointment.getStartDate());
	    	endDatePicker.setValue(appointment.getEndDate());
	    	startTimePicker.setValue(appointment.getStartTime());
	    	endTimePicker.setValue(appointment.getEndTime());
	    	descriptionTextArea.setText(appointment.getDescription());
	    	customerTextField.setText(appointment.getCustomer().getName());
	    	contactTextField.setText(appointment.getContact());
	    	locationTextField.setText(appointment.getLocation());
	    	typeComboBox.setPromptText(appointment.getType().toString());
		}
	    
	    @FXML
	    void cancelUpdate(ActionEvent event) 
	    {
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

	    }

	    
	    
	    @FXML
	    void updateEvent(ActionEvent event) throws SQLException
	    {
	    	Appointment appointment = EventController.selectedAppointment;
	    	int id = appointment.getID();
	    	
	    	String title = eventTitleTextField.getText();
	    	LocalDate startDate = startDatePicker.getValue();
	    	LocalDate endDate = endDatePicker.getValue();
	    	LocalTime startTime = startTimePicker.getValue();
	    	LocalTime endTime = endTimePicker.getValue();
	    	int customerId = appointment.getCustomer().getCustomerID();
	    	if(CustomerSelectionController.selectedCustomer != null) {
	    		customerId = CustomerSelectionController.selectedCustomer.getCustomerID();
	    	}
	    	String contact = contactTextField.getText();
	    	String location = locationTextField.getText();
	    	String type = appointment.getType().toString();
	    	if(typeComboBox.getValue() != null) {
		    	 type = typeComboBox.getValue().toString();
	    	}
	    	String description = descriptionTextArea.getText();
	    	
	    	SQL_Appointments.updateAppointment(id, title, startDate, endDate, startTime, endTime, 
	    			customerId, contact, location, type, description);
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

	    }

	    
	    
	    @FXML
	    void showCustomerSelection(MouseEvent event) 
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



	
}
