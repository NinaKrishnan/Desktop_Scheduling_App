package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EventController implements Initializable
{
	
	
	@FXML
	private JFXButton newEventButton;
	
	@FXML
	private TableView<Appointment> agendaTable;
	
	@FXML
	private TableColumn<Appointment, String> appointmentColumn;
	
	@FXML
	private TableColumn<Appointment, String> timeColumn;
	
	@FXML
	private AnchorPane popupAnchorPane;
	
	


	public Stage stage = null;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		setAgendaFeedCellValues();
		try {
			populateAgendaFeed(agendaTable);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void populateAgendaFeed(TableView<Appointment> table) throws ClassNotFoundException, SQLException
	{
		LocalDate date = DashboardController.currentEventStart;
		ObservableList<Appointment> appointments = SQL_Appointments.getAppointments(date);
		
		table.getItems().clear();
		table.setItems(appointments);
		setAgendaFeedStyle();
	}
	
	private void setAgendaFeedStyle()
	{
		agendaTable.setStyle("-fx-background-color: #aae6f2;");
		appointmentColumn.setStyle("-fx-font-color: #130f94;"+"-fx-font-weight: bold;"+"-fx-font-size: 17.5;");
		timeColumn.setStyle("-fx-font-size: 17;");
	}
	
	
	private void setAgendaFeedCellValues()
	{
		appointmentColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("timeRange"));

	}
	 
	 
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
