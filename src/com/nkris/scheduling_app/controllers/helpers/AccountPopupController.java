package com.nkris.scheduling_app.controllers.helpers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class AccountPopupController 
{
	@FXML
	private JFXButton signOutButton;
	 
	
	
	@FXML
	private void signOut(ActionEvent event) throws IOException
	{
		if(getSignOutConfirmation())
		{
			returnToLoginScreen(event);
		}
	}
	
	
	
	private boolean getSignOutConfirmation()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to sign out?", ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.YES) return true;
		return false;
	}
	
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
