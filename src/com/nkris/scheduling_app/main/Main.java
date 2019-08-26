package com.nkris.scheduling_app.main;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.models.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


//*****************************************************************************************************//
//*****************************************************************************************************//


/*
 * This is the controller class that launches the application. It brings up the login screen.
 */


//*****************************************************************************************************//
//*****************************************************************************************************//

//TODO: external style sheets





public class Main extends Application implements Initializable{
	
	public static User user = new User();
	
	
	
	
	/*
	 * Start method; displays the login screen.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource
					("/com/nkris/scheduling_app/FXML/LogInScreen.fxml")); //Login Screen
			Scene scene = new Scene(root,960,800);
			scene.getStylesheets().add(getClass().getResource
					("/com/nkris/scheduling_app/main/stylesheets/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/* 
	 * Main method; launches the application & establishes connection with database
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		launch(args);
		//CLOSE THE DATABASE 
	}	



	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		
	}
}
