package com.nkris.scheduling_app.controllers.helpers;

//imports
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.nkris.scheduling_app.controllers.DashboardController;
import com.nkris.scheduling_app.controllers.EventController;
import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;




//*****************************************************************************************************************//
//****************************************************************************************************************//



/*
 * This is the controller class for the appointment creator popup window. When a calendar day is clicked (on the 
 * dashboard), a popup window is displayed with the current agenda for that day, as well as a button for creating a 
 * new appointment. When "new appointment" button is clicked, an appointment creator popup takes the place of the 
 * initial popup window. This popup contains fields for user to enter required information about the appointment
 * being created: title, start date & time, end date & time, description, customer, contact info, location, and type.
 * When the "save" button is clicked, after ensuring all fields have been adequately filled, a new appointment object
 * is created with the information provided by the user. The appointment is then added to the appropriate day on the
 * dashboard calendar, and saved to the MySQL database.
 */



//*****************************************************************************************************************//
//****************************************************************************************************************//



public class EventPopUpController implements Initializable
{
	

	
	@FXML
	private Button saveEventButton; //Button to create and save new event and add it to DB
		
	@FXML
	private Button cancelEventButton; //Button to cancel creating a new event
	
	@FXML
	private  TextField eventTitleTextField; //Textfield for appointment title (String)
	
	@FXML
	private  JFXDatePicker startDatePicker; //Date Picker for appointment start date (LocalDate)
	
	@FXML
	private  JFXDatePicker endDatePicker; //Date Picker for appointment end date (LocalDate)
	
	@FXML
	private  JFXTimePicker startTimePicker; //Time Picker for appointment start time (LocalTime)
	
	@FXML
	private  JFXTimePicker endTimePicker; //Time Picker for appointment end time (LocalTime)
	
	@FXML
	private  TextArea descriptionTextArea; //Textfield for appointment description (String)
	
	@FXML
	private TextField customerTextField; //Textfield for appointment customer; brings up a table of customers in 
										//DB to select from. Textfield is filled with customer's name. (Customer)
	
	@FXML
	private TextField contactTextField; //Textfield for customer's contact info for appointment (String)
	
	@FXML
	private TextField locationTextField; //Textfield for appointment location (String)
	
	@FXML
	private JFXComboBox<Type> typeComboBox; //Drop down menu for selecting appointment type
	 
	
	public static Appointment currentEvent = null; //When this is non-null, it means an appointment has just been created.
											//The DashboardController class will use this to add a flag to the
											//calendar on the right day, then sets this back to null. 
	
	public static LocalDate currentEventStart = null; //This stores the start date of the appointment that was just created
											   //and saved so that the DashboardController class can access it and 
											   //save an event flag to the appropriate cell on the calendar grid.
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		for(Type type : Arrays.asList(Type.values())) {
			typeComboBox.getItems().add(type);
		}
		
		System.out.print(DashboardController.currentEventStart);
		startDatePicker.setValue(DashboardController.currentEventStart);
	}
	
	
 /*
  * This instantiates a new appointment with the information the user entered in the textfield. The appointment 
  * is then added to the database.
  */
	@FXML
	public void saveEvent(ActionEvent event)
	{
		
		if(CustomerSelectionController.selectedCustomer == null || eventTitleTextField.getText() == null
			||startDatePicker.getValue() == null || endDatePicker.getValue() == null || startTimePicker.getValue() == null
			||endTimePicker.getValue() == null || typeComboBox.getValue() == null) {
				displayAlert();
			}
	    
		else {
			 Appointment newEvent = new Appointment();
			    newEvent = new Appointment();
			    newEvent.setCustomer(CustomerSelectionController.selectedCustomer);
			    newEvent.setTitle(eventTitleTextField.getText());
			    newEvent.setStartDate(startDatePicker.getValue());
			    newEvent.setEndDate(endDatePicker.getValue());
			    newEvent.setStartTime(startTimePicker.getValue());
			    newEvent.setEndTime(endTimePicker.getValue());
			    newEvent.setDescription(descriptionTextArea.getText());   
			    newEvent.setType(typeComboBox.getValue());
			    newEvent.setTimeRange();
			    
			    try {
					SQL_Appointments.insertAppointment(newEvent);
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			    
			    finally {
				    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
				    currentEvent = newEvent;
				    currentEventStart = newEvent.getStartDate();
				    
			    }
		}
	}
	
	
	//Display alert if form fields are not filled out properly
	private void displayAlert()
	{
		Alert alert = new Alert(AlertType.WARNING, "Please fill out all fields before saving this event.", 
				ButtonType.OK);
		alert.showAndWait();
				
	}
	

	/*
	 * When the customer textfield is clicked, a list of customers in the database comes up that the user can 
	 * select from. A customer not in the database must first be added before they can be selected for an 
	 * appointment.
	 */
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

	
		
	
	
	
	//Return the current event 
	public static Appointment getEvent()
	{
		return currentEvent;
	}
	
	//Cancel adding new event - get confirmation then close the window
	@FXML
	private void cancelNewEvent(ActionEvent event)
	{
		if(confirmCancelNewEvent()) {
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		}
	}
	
	//Ask for confirmation before cancelling new event
	private boolean confirmCancelNewEvent()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, 
				"If you cancel, the event will not be saved to your calendar. Do you want to continue?",
				ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.YES) {
			return true;
		}
		return false;
	}
	
	

	
	
	
	
	
	
}
