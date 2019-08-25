package com.nkris.scheduling_app.controllers.helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class DrawerContentController implements Initializable
{
	
	@FXML
	private AnchorPane navDrawer; //Main navigation drawer
	
	
	@FXML
	private JFXButton accountButton; //Account button in main nav drawer
	
	
	@FXML
	private JFXButton settingsButton; //Settings button in main nav drawer
	
	
	@FXML
	private HBox accountHBox; //Container for account button drop-down
	
	
	@FXML
	private HBox settingsHBox; //Container for settings button drop-down
	
	
	@FXML
	private JFXButton dashboardButton; //Dashboard button in main nav drawer
	
	@FXML
	private JFXButton customersButton;
	
	
	
	//TODO
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}

	
	/*
	 * When account button is pushed, a container pops up below it containing two buttons:
	 *    -One button ("Profile") to access user profile 
	 *    -Second button ("Sign Out") to sign out of application; brings back login screen
	 * Container is fetched from a separate FXML file in "helpers" package
	 * **If popup is already open, clicking account button closes it
	 * **If settings popup is open, opening account popup closes it
	 */
	@FXML
	private void showAccountPopup(MouseEvent event) 
	{
		try
		{
			AnchorPane popupContent = FXMLLoader.load(getClass().getResource
					("/com/nkris/scheduling_app/FXML/helpers/AccountPopUp.fxml"));
			if(accountPopupIsShowing())
			{
				hideAccountPopup();
			}
			
			else
			{
				if(settingsPopupIsShowing())
				{
					hideSettingsPopup();
				}
				setAccountPopupSize();
				setAccountPopupPane(popupContent);
			}
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * Add the appropriate anchor pane to account popup container
	 * Change the text on account button to an upward-facing arrow
	 */
	private void setAccountPopupPane(AnchorPane anchorPane)
	{
		accountHBox.getChildren().addAll(anchorPane);
		accountHBox.setMargin(anchorPane, new Insets(0, 0, 10, 0));
		accountButton.setText("Account ⮝");
	}
	
	
	/*
	 * Set account button popup to appropriate size and position 
	 */
	private void setAccountPopupSize()
	{
		accountHBox.setPrefHeight(120);
		accountHBox.setPrefWidth(135);
		accountHBox.setAlignment(Pos.CENTER);
	}
	
	
	//TODO
	/*
	 * When Settings button is clicked, a container pops up below it containing one button
	 * 		-Button ("Choose Color") allows user to select a different color for the dashboard theme
	 * ***If settings popup is already open, clicking button closes it
	 * ***If account popup is open, clicking settings button closes it
	 */
	@FXML
	private void showSettingsPopup(ActionEvent event)
	{
			
		if(accountPopupIsShowing())
		{
			hideAccountPopup();
		}
		if(settingsPopupIsShowing())
		{
			hideSettingsPopup();
		}
		else
		{
			renderSettingsPopup();
			
		}
	
	}
	

	/*
	 * Calls methods to set settings popup to appropriate size and create the color picker button
	 * Changes to a downward-facing arrow on Settings button
	 */
	private void renderSettingsPopup()
	{
		setSettingsPopupSize();
		createChooseColorButton();
		settingsButton.setText("Settings ⮝");
	}
	
	
	/*
	 * Create a choose color button and add it to settings popup container
	 * Add an event handler to bring up the color picker when button is clicked
	 */
	private void createChooseColorButton()
	{
		JFXButton chooseColorButton = new JFXButton("Choose Color: ");
		chooseColorButton.getStyleClass().add("chooseColorBtn");
		settingsHBox.getChildren().addAll(chooseColorButton);
		chooseColorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			chooseColor(new JFXColorPicker(), settingsHBox);
			
		});
	}
	 
	
	/*
	 * Set appropriate size and alignment for settings popup container
	 */
	private void setSettingsPopupSize()
	{
		settingsHBox.setPrefHeight(120);
		settingsHBox.setPrefWidth(120);
		settingsHBox.setAlignment(Pos.CENTER);
	}
	
	
	
	/*
	 * Change the color of main nav drawer when color picker is used
	 */
	private void chooseColor(JFXColorPicker colorpicker, HBox hbox)
	{
		colorpicker.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			 navDrawer.setBackground(new Background(new BackgroundFill
						(Paint.valueOf(colorpicker.getValue().toString()), 
								CornerRadii.EMPTY, Insets.EMPTY)));
		});
		hbox.getChildren().addAll(colorpicker);
	}
	
	
	/*
	 * Checks to see if accoutn popup container is showing; returns true if so, false otherwise
	 */
	private boolean accountPopupIsShowing()
	{
		if(accountHBox.getHeight() > 0)
		{
			return true;
		}
		return false;
	}
	
	
	/*
	 * Clears and hides the Account button popup
	 * Sets Account button arrow to downward-facing
	 */
	private void hideAccountPopup()
	{
	
		accountHBox.getChildren().clear();
		accountHBox.setPrefHeight(0);
		accountButton.setText("Account ⮟");
	}
	
	  
	/*
	 * Checks to see if Settings button popup is showing; returns true if so, false otherwise
	 */
	private boolean settingsPopupIsShowing()
	{
		if(settingsHBox.getHeight() > 0)
		{
			return true;
		}
		return false;
	}
	
	
	/*
	 * Clears and hides Settings button popup
	 * Changes Settings button arrow to downward-facing
	 */
	private void hideSettingsPopup()
	{
		settingsHBox.getChildren().clear();
		settingsHBox.setPrefHeight(0);
		settingsButton.setText("Settings ⮟");
	}

	
	@FXML
	private void openCustomersScreen(ActionEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/CustomersScreen.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	
}
