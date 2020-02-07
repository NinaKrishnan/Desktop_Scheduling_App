package com.nkris.scheduling_app.controllers.helpers;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportsPopUpController
{
	@FXML
	private JFXButton appointmentTypesButon;

	
	@FXML 
	private JFXButton loginsButton;
	
	
	@FXML
	private JFXButton scheduleButton;
	
	
	
	
	@FXML
	private void generateAppointmentsReport(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/MonthlyReports.FXML"));
		
		MonthlyReportController reportController = new MonthlyReportController();
		loader.setController(reportController);
		
		
		Parent layout;
		try 
		{
			layout = loader.load(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/MonthlyReports.FXML"));
			Scene scene = new Scene(layout);
			Stage eventStage = new Stage();
			reportController.setStage(eventStage);
			
			eventStage.initModality(Modality.WINDOW_MODAL); //Create popup window
			eventStage.setScene(scene);
			eventStage.showAndWait();
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
		
	}
	
	
	@FXML
	private void generateLoginsReport(ActionEvent event)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/LoginsReport.FXML"));
		
		MonthlyReportController reportController = new MonthlyReportController();
		loader.setController(reportController);
		
		
		Parent layout;
		try 
		{
			layout = loader.load(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/LoginsReport.FXML"));
			Scene scene = new Scene(layout);
			Stage eventStage = new Stage();
			reportController.setStage(eventStage);
			
			eventStage.initModality(Modality.WINDOW_MODAL); //Create popup window
			eventStage.setScene(scene);
			eventStage.showAndWait();
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void generateScheduleReport(ActionEvent event)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/ScheduleReport.fxml"));
		
		MonthlyReportController reportController = new MonthlyReportController();
		loader.setController(reportController);
		
		
		Parent layout;
		try 
		{
			layout = loader.load(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/ScheduleReport.fxml"));
			Scene scene = new Scene(layout);
			Stage eventStage = new Stage();
			reportController.setStage(eventStage);
			
			eventStage.initModality(Modality.WINDOW_MODAL); //Create popup window
			eventStage.setScene(scene);
			eventStage.showAndWait();
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
	}
	
}
