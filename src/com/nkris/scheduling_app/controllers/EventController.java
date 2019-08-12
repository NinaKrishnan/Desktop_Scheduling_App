package com.nkris.scheduling_app.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EventController
{
	@FXML
	private JFXButton newEventButton;
	
	@FXML
	AnchorPane popupAnchorPane;

	private Stage stage = null;
	
	@FXML
	private void createEventPopup(ActionEvent event) throws IOException
	{
		AnchorPane ap = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/helpers/EventPopUp.fxml"));
		popupAnchorPane.getChildren().add(ap);
		
	}
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	

}
