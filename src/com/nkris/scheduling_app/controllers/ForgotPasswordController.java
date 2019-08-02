package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//******************************************************************************************************//
//******************************************************************************************************//


/*
 * This is the controller class for the "Forgot Password" FXML screen. This screen pops up when a user
 * clicks on the "forgot password?" link on the login screen. The user will be prompted to enter their 
 * e-mail address that is associated with their NSK System username (the one they registered with).
 * After entering their e-mail and clicking on the "reset password" button, their e-mail will be
 * validated. The program will validate three conditions:
 * 		1. Is the e-mail address filled out? The textfield cannot be blank. 
 * 		2. Is the e-mail address in the correct format? Address must be written in the format
 * 			"name@example.org" which is checked against a regex validator.
 * 		3. Is the e-mail address registered with the system? E-mail must be associated with a registered 
 * 			user in order to be valid. MySQL database will be queried to check for e-mail, and instructions
 * 			will be sent to the e-mail address to validate the username associated with it.
 * If e-mail address is valid, the user will be taken to a confirmation screen to let them know that
 * they can check their e-mail for password reset instructions. 
 * 
 * This screen, like all the other system screens, features a back arrow to take the user back to 
 * the previous page.
 * As with all the other Log-In screens, it also provides a language selection drop-down menu, which
 * can be used to change the language to spanish or italian.
 */


//******************************************************************************************************//
//******************************************************************************************************//


public class ForgotPasswordController implements Initializable
{
	
    @FXML
    private MenuButton forgotPasswordLanguageMenuButton; //language selection menu 
    													//on reset password screen

    @FXML
    private ImageView forgotPasswordBackArrow; //back button on reset password screen; 
    									//takes user back to login screen

    @FXML
    private TextField emailTextField; //for users to enter their associated e-mail;
    								//password reset instructions will be sent to e-mail
    								//must be the e-mail address associated with user's account
    
    @FXML
    private Button resetPasswordButton; //reset password button sends e-mail to address entered
    									//[if address is valid] and opens confirmation page
    
    @FXML
    private Label enterValidEmailLabel; //Error text appears if e-mail address is not valid
    
    
    
    //Initialize method; removes default cursor selection from the textfield.
    @Override
	public void initialize(URL url, ResourceBundle rb) 
    {
    	//removes default textfield selection
		emailTextField.setFocusTraversable(false);
	}
    
    
    
    /*
     * Back arrow event listener; brings back login screen when clicked
     */
    @FXML
    void forgotPasswordBackArrowClicked(MouseEvent event) throws IOException
    {
    	Parent parent = FXMLLoader.load(getClass().getResource
    			("/com/nkris/scheduling_app/FXML/LogInScreen.fxml"));
    	Scene loginScreen = new Scene(parent);
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(loginScreen);
    	stage.show();
    }
    
    
    
    
    
    /*
     * Upon clicking this button, the e-mail address that was entered will be validated for 
     * correct format (including the possibility that the user did not enter anything)
     * as well as its existence as a user's e-mail in the database.
     */
    @FXML
    void resetPasswordButtonClicked(ActionEvent event) throws IOException
    {
    	//displays an alert that the e-mail field is empty, and must be filled out.
    	if(emailTextField.getText().isEmpty()) emptyEmailField(); 
    	
    	//displays an alert that the e-mail format is invalid, and must be formatted according
    	//to example.
    	else if(!isValidEmail(emailTextField.getText())) invalidEmailAlert();
    	
    	//e-mail is valid; password reset instructions are e-mailed to the specified address 
    	//and the user is taken to the confirmation screen.
    	else {
    	
	    	Parent parent = FXMLLoader.load(getClass().getResource
	    			("/com/nkris/scheduling_app/FXML/PasswordResetConfirmation.fxml"));
	    	Scene confirmationScreen = new Scene(parent);
	    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	stage.setScene(confirmationScreen);
	    	stage.show();
    	
    	}
    	
    }
    
    
    
    
    /*
     * An alert pops up when the e-mail address in the format: "name@example.org"
     */
    void invalidEmailAlert()
    {
    	highlightEmailTextField();
    	enterValidEmailLabel.setText("*Please enter a valid e-mail address.");//alert label pops up 
																			//above textfield 
		Alert alert = new Alert(AlertType.ERROR, 
				"The e-mail entered is invalid. "
				+ "Please enter an e-mail address in the format "
				+ "\"address@example.org\"" , ButtonType.OK);
		alert.showAndWait();
    }
    
    
    
    /*
     * An alert pops up when the user has left the e-mail address field blank.
     */
    void emptyEmailField() 
    {
    	highlightEmailTextField();
    	enterValidEmailLabel.setText("Please enter your e-mail addresss"); //alert label pops up 
    																	//above textfield 
    	Alert alert = new Alert(AlertType.ERROR, "E-mail field cannot be left blank. "
				+ "Please enter the e-mail address associated with your NKS account in order "
				+ "to reset your password.", ButtonType.OK);
    	alert.showAndWait();
	}
    
    
    
    
    /*
     * *Returns true if the e-mail address matches the format "name@example.org"
     * 
     * *Returns false otherwise
     */
    boolean isValidEmail(String email)
    {
    	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    	return email.matches(regex);
    }
   
    
    
    
    
	/*
	 * highlights the e-mail textfield with a bright red border if the user attempts to submit
	 * password reset request with an invalid e-mail.
	 */
    void highlightEmailTextField()
    {
    	emailTextField.setBorder(new Border(new BorderStroke
    			(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
    	emailTextField.getStyleClass().add("error");
    }

	
	
	
}
