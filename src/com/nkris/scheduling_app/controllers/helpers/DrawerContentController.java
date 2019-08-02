package com.nkris.scheduling_app.controllers.helpers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DrawerContentController 
{
	
	@FXML
	private Button signOutButton;
	
	
	@FXML
	private void signOut(ActionEvent event) throws IOException
	{
		if(getSignOutConfirmation()==true) 
		{
			returnToLoginScreen(event);
		}
		
	}
	
	private boolean getSignOutConfirmation()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to sign out?"
				, ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait();
		
		if(alert.getResult()==ButtonType.YES) 
			{
			return true;
			}
		return false;
	}
	

	/*
	 * Sign user out of account and return to login screen
	 */
	private void returnToLoginScreen(ActionEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/LogInScreen.fxml"));
		
		Scene loginScreen = new Scene(parent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(loginScreen);
		stage.show();
	}
	

}
