package com.nkris.scheduling_app.controllers.helpers;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.database.SQL_Appointments;
import com.nkris.scheduling_app.models.Appointment;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ScheduleReportController implements Initializable
{
    @FXML
    private TableView<Appointment> agendaTable;

    @FXML
    private TableColumn<Appointment, String> appointmentCol;

    @FXML
    private TableColumn<Appointment, String> timeCol;

    @FXML
    private Label dateLabel;

	
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1)
    {
    	setAgendaCellValues();
    	try {
			populateAgendaFeed(agendaTable);
		}
    	catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
    
    
    
    private void populateAgendaFeed(TableView<Appointment> table) throws ClassNotFoundException, SQLException
    {
    	LocalDate today = LocalDate.now();
		ObservableList<Appointment> appointments = SQL_Appointments.getAppointments(today);
		
		table.getItems().clear();
		table.setItems(appointments);
		setAgendaFeedStyle();
    }
    
    
    private void setAgendaFeedStyle()
    {
    	agendaTable.setStyle("-fx-background-color: #aae6f2;");
		appointmentCol.setStyle("-fx-font-color: #130f94;"+"-fx-font-weight: bold;"+"-fx-font-size: 17.5;");
		timeCol.setStyle("-fx-font-size: 17;");
    }
    
    private void setAgendaCellValues()
    {
    	appointmentCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("timeRange"));
    }
    
    
    
    
}
