package com.nkris.scheduling_app.controllers;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.main.Main;

import javafx.event.ActionEvent;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//TODO: ADD PASSWORD NUMBER OF ATTEMPTS CONSTRAINT. 
//TODO: ADD VALIDATION THAT CHECKS FOR USERNAME IN DATABASE
//TODO: ADD VALIDATION THAT CHECKS PASSWORD IN DATABASE
//TODO: LIMIT NUMBER OF LOGIN ATTEMPTS 

//*****************************************************************************************************//
//*****************************************************************************************************//


/*
 * This is the controller class for the Log-In FXML screen. This is always the first screen that a user 
 * will see upon opening the app, thus it is the screen that is launched in the Main Controller's 
 * "start" method. To log in to the app, the user must enter valid credentials in the form of a 
 * username and password. 
 * 
 * When the "Log-In" button is pushed, the program will validate both the username and password using
 * the following credentials:
 * 		1. Have both fields been filled out/not left empty?
 * 		2. Is the username a registered username stored in the database? The database will be queried
 * 			to ensure that the username is in existence.
 * 		3. Is the password entered an exact match for the stored password corresponding to the 
 * 			username entered? Again, the database will be queried to ensure a username/password match.
 * 
 * The number of login attempts is limited to prevent brute-force attacks/dictionary hacking. If number
 * of attempts is exceeded, user will be required to reset password before logging in. 
 * 
 * If username and password are valid, the user will be logged in to their account, where the opening
 * screen will be their dashboard.
 * 
 * The log-in page also has a "forgot password" option in the form of a link, located just under the
 * password textfield. When the user clicks the link, they are taken to the "reset password" screen,
 * where they are instructions for resetting their password.
 * 
 * As with all the other log-in pages, there is a language selection drop-down menu located at the top
 * of the page where the user can choose to change the language to Spanish, Italian, or English.
 * The default language setting is English.
 * 
 */

//*****************************************************************************************************//
//*****************************************************************************************************//




public class LogInController implements Initializable
{
	
    @FXML
    private Label welcomeBackLabel; //Title label, changes languages upon language selection
    
    @FXML
    private Label signInLabel; //Subtitle label, changes languages upon language selection
    
    @FXML
    private MenuButton languageMenuButton; //Language selection drop-down menu; displays current language
		
	@FXML
	private MenuItem englishMenuItem; //Menu item for changing log-in language to English
	
    @FXML
    private MenuItem spanishMenuItem; //Menu item for changing log-in language to Spanish
	
    @FXML
    private TextField usernameTextField; //Username entered here
    
    @FXML
    private TextField passwordTextField; //Password entered here
    
    @FXML
    private Button forgotPasswordButton; //Forgot password button links to a separate screen to reset
    									//password
    
    @FXML
    private Button loginButton; //Login button; validates username and password and logs user into 
    							//system if valid, and displays error message otherwise.
        
    
    //TODO							
    private final int loginAttempLimit = 3; //Maximum number of incorrect login attempts from any one user
    
    
    
    /*
     * Initialize method removes default selection from textfields.
     */
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		
		translatePage();
		
		usernameTextField.setFocusTraversable(false);
		passwordTextField.setFocusTraversable(false);	
		
		DashboardController.loginTime = null;
		
	}
    
	
	
 
    /*
     * Changes the language to English 
     */
    @FXML
    void englishSelected(ActionEvent event)
    {
    	
    }
    
    
    /*
     * Changes the language to spanish
     */
    @FXML
    void spanishSelected(ActionEvent event) 
    {
    	
    	
    }

    
    
    
    void translate(String language)
    {
    	switch(language)
    	{
    	case "en":
        	//change text on language selection menu
    		languageMenuButton.setText("English");
        	
    		//change welcome title to english
    		welcomeBackLabel.setText("Welcome Back, ");
    		
        	//change the login title to english
    		signInLabel.setText("Log in to continue");
    		
        	//change the username/password prompt text to english
    		usernameTextField.setPromptText("Username");
        	passwordTextField.setPromptText("Password");
        	
        	//change "forgot password" button to english
        	forgotPasswordButton.setText("Forgot Password?");
        	
        	//change the login button to english
        	loginButton.setText("Log In");
        	
    	case "es":
    		//change text on language selection menu to 
        	languageMenuButton.setText("Spanish");
        	
        	//change welcome title to spanish
        	welcomeBackLabel.setText("Bienvenido,");
        	
        	//change login title to spanish
        	signInLabel.setText("Inicia sesión \npara continuar");
        	
        	//change username/password prompt text to spanish
        	usernameTextField.setPromptText("Nombre de usario");
        	passwordTextField.setPromptText("Contraseña");
        	
        	//change "forgot password" button to spanish
        	forgotPasswordButton.setText("¿Olvidó contraseña?");
        	
        	//change the login button to spanish
        	loginButton.setText("Iniciar sesión");
    	}
    }
    
    private void translatePage()
    {
    	if(Locale.getDefault().getLanguage().equals("es")) {
    		//change text on language selection menu to 
        	languageMenuButton.setText("Spanish");
        	
        	//change welcome title to spanish
        	welcomeBackLabel.setText("Bienvenido,");
        	
        	//change login title to spanish
        	signInLabel.setText("Inicia sesión \npara continuar");
        	
        	//change username/password prompt text to spanish
        	usernameTextField.setPromptText("Nombre de usario");
        	passwordTextField.setPromptText("Contraseña");
        	
        	//change "forgot password" button to spanish
        	forgotPasswordButton.setText("¿Olvidó contraseña?");
        	
        	//change the login button to spanish
        	loginButton.setText("Iniciar sesión");
    	}
    	
    }
  
      
    
    /*
     * Takes user to the "forgot password" screen when button is clicked
     */
	@FXML
	void forgotPasswordButtonClicked(ActionEvent event) throws IOException
	{
		Parent forgotPassParent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/ForgotPassword.fxml"));
		
		Scene forgotPasswordScreen = new Scene(forgotPassParent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(forgotPasswordScreen);
		stage.show();
	}
	
	
	 
	
	/*
	 * Validates username and password; logs user in if valid and displays error message otherwise.
	 * If login attempts exceed the limit, the user will be taken to a reset password screen and 
	 * instructed to reset their password before the system will log them in.
	 */
	@FXML
	void loginButtonClicked(ActionEvent event) throws IOException, SQLException
	{
		if(isValidUsernameAndPassword(usernameTextField.getText(), passwordTextField.getText())) {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("LoginTracker.txt", true));
			writer.write("User: "+usernameTextField.getText()+", ");
			writer.write("Timestamp: "+LocalDateTime.now().toString());
			writer.newLine();
			writer.close();
			
			setUserName(usernameTextField.getText());
			Parent parent = FXMLLoader.load(getClass().getResource
					("/com/nkris/scheduling_app/FXML/DashboardUI.fxml"));
			Scene homeScreen = new Scene(parent);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			DashboardController.loginTime = LocalTime.now();
			
			stage.setScene(homeScreen);		
			stage.show();
		}
		else {
			displayAlert();
		}
	}

	private void displayAlert()
	{
		Alert alert = new Alert(AlertType.WARNING, "Invalid username and/or password. Please try again."
				, ButtonType.OK);
		alert.showAndWait();
	}
	
	//Determine user's location and translate login screen into language. 
	//****Supported: English & Spanish
	private void translate()
	{
		Locale locale = Locale.getDefault();
		
		String language = locale.getLanguage();
		
		translate(language);
	}
	
	//TODO
	private void setUserName(String userName)
	{
		Main.user.setUserName(userName);
	}
	
	
	

	/*
	 * Queries the database to validate the username
	 */
	boolean isValidUsernameAndPassword(String username, String password) throws SQLException
	{
		Connection connection = DatabaseHandler.getDBconnection();
		
		String query = "SELECT * FROM user WHERE userName = ? AND password = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet set = statement.executeQuery();
		
		if(set.next()) {
			return true;
		}
		
		return false;
	}

	
	
	
	
	
	
	
	
	/*
	 * Tracks the number of consecutive login attempts
	 */
	int getNumberOfLoginAttempts()
	{
		return 0;
	}   
	
	
	@FXML
	private void setPasswordFocus(KeyEvent event)
	{
		passwordTextField.setFocusTraversable(true);
	}
	
	@FXML
	private void changeCursorToHand(MouseEvent event) throws IOException
	{
		
	}
	

	
}
