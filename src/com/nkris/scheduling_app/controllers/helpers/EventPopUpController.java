package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class EventPopUpController implements Initializable
{
	

	
	@FXML
	private Button saveEventButton;
	
	public static boolean save;
	
	@FXML
	private Button cancelEventButton;
	
	private boolean result;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}

	@FXML
	public void saveEvent(ActionEvent event)
	{
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	    

	    
	}
	
	
	public boolean getResult()
	{
		return save;
	}
	
	@FXML
	private void cancelNewEvent(ActionEvent event)
	{
		
	}
	
	public void closeStage(Stage stage)
	{
		stage.close();
	}
	
	
	

	
	
	
	
	
	
}
