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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private Button viewEventButton;
	
	@FXML
	private Button deleteEventButton;
	
	@FXML
	private TableView<Appointment> agendaTable;
	
	@FXML
	private TableColumn<Appointment, String> appointmentColumn;
	
	@FXML
	private TableColumn<Appointment, String> timeColumn;
	
	@FXML
	private AnchorPane popupAnchorPane;
	
	public static Appointment selectedAppointment;


	public Stage stage = null;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		selectedAppointment = null;
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
	private void viewEvent(ActionEvent event) throws SQLException, IOException, ClassNotFoundException
	{
		int appointmentId = agendaTable.getSelectionModel().getSelectedItem().getID();
		//System.out.println(agendaTable.getSelectionModel().getSelectedItem().getID());
		
		selectedAppointment = SQL_Appointments.getAppointment(appointmentId);
		
		
		AnchorPane ap = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/helpers/viewEvent.fxml"));
		popupAnchorPane.getChildren().add(ap);
		
	}
	
	@FXML
	private void deleteEvent(ActionEvent event) throws SQLException, ClassNotFoundException
	{
		Appointment appointment = agendaTable.getSelectionModel().getSelectedItem();
		
		if(confirmDeleteEvent()) {
			SQL_Appointments.deleteAppointment(appointment.getID());
			populateAgendaFeed(agendaTable);
		}
	}
	
	private boolean confirmDeleteEvent()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this event?", 
				ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		
		if(alert.getResult() == ButtonType.YES) {
			return true;
		}
		
		return false;
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
