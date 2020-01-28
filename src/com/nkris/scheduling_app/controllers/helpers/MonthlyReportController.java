package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.models.Month;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MonthlyReportController implements Initializable
{
	@FXML
	private Label monthLabel;
	
	@FXML
	private TableView<Month> reportTable;
	
	@FXML
	private TableColumn<Month, String> monthCol;
	
	@FXML
	private TableColumn<Month, String> appointmentCol;
	
	public Stage stage = null;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		try {
			setAppointmentTypes();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	private void setAppointmentTypes() throws SQLException
	{
		List<Month> months = new ArrayList<Month>();
		for(int i = 1; i <= 12; i++) {
			Month month = new Month(i);
			months.add(month);
		}
		
		ObservableList<Month> monthList = FXCollections.observableArrayList(months);
		
		monthCol.setCellValueFactory(new PropertyValueFactory<Month, String>("name"));
		appointmentCol.setCellValueFactory(new PropertyValueFactory<Month, String>("appointmentTypes"));
		
		reportTable.getItems().clear();
		reportTable.setItems(monthList);
	}

	
	public void setStage (Stage stage)
	{
		this.stage = stage;
	}
	
	
}
