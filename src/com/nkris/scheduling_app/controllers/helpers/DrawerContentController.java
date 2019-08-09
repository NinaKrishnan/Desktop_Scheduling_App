package com.nkris.scheduling_app.controllers.helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPopup;
import com.nkris.scheduling_app.controllers.DashboardController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.stage.Stage;


public class DrawerContentController implements Initializable
{
	
	@FXML
	private AnchorPane navDrawer;
	
	
	@FXML
	private JFXButton accountButton;
	
	@FXML
	private JFXButton settingsButton;
	
	
	@FXML
	private VBox vBox;
	
	@FXML
	private HBox accountHBox;
	
	@FXML
	private HBox settingsHBox;
	
	@FXML
	private JFXButton dashboardButton;
	

	private ArrayList<JFXButton> buttons = new ArrayList<JFXButton>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		createButtonGroup();
	}

	
	
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
			else {
				accountHBox.setPrefHeight(120);
				accountHBox.setPrefWidth(135);
				accountHBox.setAlignment(Pos.CENTER);
				accountHBox.getChildren().addAll(popupContent);
				accountHBox.setMargin(popupContent, new Insets(0, 0, 10, 0));
				accountButton.setText("Account ⮝");
			}
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
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
	

	
	private void renderSettingsPopup()
	{
		settingsHBox.setPrefHeight(120);
		settingsHBox.setPrefWidth(120);
		settingsHBox.setAlignment(Pos.CENTER);
		JFXButton chooseColorButton = new JFXButton("Choose Color: ");
		settingsHBox.getChildren().addAll(chooseColorButton);
		chooseColorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			chooseColor(new JFXColorPicker(), settingsHBox);
			
		});
		settingsButton.setText("Settings ⮝");
	}
	 
	
	
	private void chooseColor(JFXColorPicker colorpicker, HBox hbox)
	{
		colorpicker.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			 navDrawer.setBackground(new Background(new BackgroundFill
						(Paint.valueOf(colorpicker.getValue().toString()), 
								CornerRadii.EMPTY, Insets.EMPTY)));
			 changeButtonColor(colorpicker.getValue().toString());
		});
		hbox.getChildren().addAll(colorpicker);
	}
	
	
	private boolean accountPopupIsShowing()
	{
		if(accountHBox.getHeight() > 0)
		{
			return true;
		}
		return false;
	}
	
	
	
	private void hideAccountPopup()
	{
	
		accountHBox.getChildren().clear();
		accountHBox.setPrefHeight(0);
		accountButton.setText("Account ⮟");
	}
	
	

	private boolean settingsPopupIsShowing()
	{
		if(settingsHBox.getHeight() > 0)
		{
			return true;
		}
		return false;
	}
	
	
	private void hideSettingsPopup()
	{
		settingsHBox.getChildren().clear();
		settingsHBox.setPrefHeight(0);
		settingsButton.setText("Settings ⮟");
	}
	
	
	private void changeButtonColor(String color)
	{
		for(JFXButton btn : buttons)
		{
			btn.setStyle("-fx-background-color: "+color);
		}
	}
	
	
	private void createButtonGroup()
	{
		for(Node node : navDrawer.getChildren())
		{
			if(node instanceof JFXButton)
			{
				buttons.add((JFXButton) node);
			}
		}
	}
	
	
	
	
	

}
