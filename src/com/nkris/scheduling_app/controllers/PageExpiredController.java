package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//******************************************************************************************************//
//******************************************************************************************************//


/*
 * This is the controller class for the "Page Expired" FXML screen. It has very minimal functionality;
 * its only purpose is to prevent users from accessing the reset password screen after they've already 
 * reset their password (via the back arrow from the reset confirmation screen).
 * If they try to go back from the reset password confirmation screen, they are brought to the "Page
 * Expired" screen and given a single button, labeled "Back to Login" that takes them back to the login
 * screen. 
 * It was a deliberate choice to include the back arrow on the confirmation page rather than omit it 
 * completely. 
 */

//******************************************************************************************************//
//******************************************************************************************************//



public class PageExpiredController  
{
	
	@FXML
	private Button returnToLoginButton; //The only button on the screen; takes the user back to the 
										//login page
	

	
	
	/*
	 * This is the only button on the screen; when clicked it takes the user back to the log-in
	 * screen.
	 */
	
	@FXML
	void returnToLoginButtonClicked(ActionEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/LogInScreen.fxml"));
		Scene forgotPassScreen = new Scene(parent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(forgotPassScreen);
		stage.show();
	}
	
}
