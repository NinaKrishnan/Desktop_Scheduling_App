package com.nkris.scheduling_app.controllers.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LoginsReportController implements Initializable
{
    @FXML
    private TableView<String> loginsTable;
    
    @FXML
    private TableColumn<String, String> loginsColumn;

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		loginsTable.getItems().clear();
		try {
	        loginsColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue())); //Wrap data in a simple string property to display in observable list without having to create a separate class

			loginsTable.setItems(getLogins());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private ObservableList<String> getLogins() throws IOException
	{
		ObservableList<String> logins = FXCollections.observableArrayList();
		try {
			String resource = "LoginTracker.txt";
			BufferedReader file = new BufferedReader(new FileReader(resource));
			
			String line;
			while((line = file.readLine()) != null) {
				logins.add("LOGIN: "+line);
			}
			file.close();
			
		} 
	    catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return logins;
	}
	
	
}
