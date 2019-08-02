package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*
 * This is the controller class for the Password Reset FXML screen. It has minimal functionality: its
 * main purpose is to inform the user that an e-mail was successfully sent with instructions to
 * reset their password. It features a "Back to Login" button that, when clicked, will take the user
 * back to the login screen. As with all other screens, it features a back arrow to bring the user
 * back to the previous page. However, since the previous page (password reset page) contains expired
 * data, the user will be shown a "Page Expired" message instead. The back arrow functionality was 
 * included in this page to keep uniformity throughout the app.
 */

public class PasswordResetConfirmationController 
{
	
	@FXML
	private ImageView backArrow; //Back arrow to go back to the previous screen. In the case of password
								//reset, the page will be expired to avoid displaying erroneous or
								//sensitive information and avoid duplicate resets.
	
	@FXML
	private Button backToLoginButton; //Button takes user back to login screen
	

	/*
	 * When the user clicks the "Back to Login" button, they are returned to the login screen.
	 */
	@FXML
	void backToLoginButtonClicked(ActionEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/LogInScreen.fxml"));
		Scene loginScreen = new Scene(parent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(loginScreen);
		stage.show();
	}
	
	
	/*
	 * If the user attempts to go back to the password reset page after successfully sending 
	 * the password reset e-mail, they are taken to an expired page.
	 */
	@FXML
	void backArrowClicked(MouseEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/PageExpired.fxml"));
		Scene forgotPassScreen = new Scene(parent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(forgotPassScreen);
		stage.show();
	}
	
	
	

}
