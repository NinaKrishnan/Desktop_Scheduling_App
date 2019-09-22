package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.database.SQL_Customer;
import com.nkris.scheduling_app.models.Customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerSelectionController implements Initializable
{
	@FXML
	private  TableView<Customer> customerSelectionTable;
	
	@FXML
	private  TableColumn<Customer, String> customerNameColumn;
	
	@FXML
	private  TableColumn<Customer, String> customerIdColumn;
	
	@FXML
	private static Button selectCustomerButton;
	
	public static Customer selectedCustomer;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		try {
			initializeCustomersTable();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getCellValues();
	}
	
	private void initializeCustomersTable() throws ClassNotFoundException
	{
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
		
		try {
			populateTable();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getCellValues()
	{
        customerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        customerIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty
        		(Integer.toString(cellData.getValue().getCustomerID())));
	}
	
	
	private void populateTable() throws SQLException, ClassNotFoundException
	{
		customerSelectionTable.getItems().clear();
		customerSelectionTable.setItems(SQL_Customer.getCustomers());
	}
	
	
	public static Customer getSelectedCustomer() {
		return selectedCustomer;
	}
	
	
	@FXML
	private void selectCustomer(ActionEvent event)
	{
		selectedCustomer = customerSelectionTable.getSelectionModel().getSelectedItem();
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}


	public static void setSelectedCustomerTextfield(TextField textfield)
	{
		textfield.setText(selectedCustomer.getName());
	}
	
	
}
