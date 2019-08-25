package com.nkris.scheduling_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.nkris.scheduling_app.controllers.helpers.NewCustomerController;
import com.nkris.scheduling_app.database.SQL_Customer;
import com.nkris.scheduling_app.models.Address;
import com.nkris.scheduling_app.models.Customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomersController implements Initializable
{
	@FXML
	private JFXButton newCustomerButton;
	
	@FXML
	public TableView <Customer> customersTable;
	
	@FXML
	private TableColumn<Customer, String> nameColumn;
	
	@FXML
	private TableColumn<Address, String> addressColumn;
	
	@FXML
	private TableColumn<Customer, String> personalIDColumn;
	
	@FXML
	private TableColumn<Customer, String> addressIDColumn;
	
	@FXML
	private TableColumn<Customer, String> activeColumn;
	
	@FXML
	private ImageView backArrow;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{

		nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("address"));
		personalIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
		activeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("active"));

        try {
			populateCustomers();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//TODO
	private void test()
	{
		 nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
	     addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
	     addressIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCustomerAddress().getID())));
	     personalIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCustomerID())));
	     activeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getActive())));
	}
	
	
	
	private void populateCustomers() throws SQLException
	{
		customersTable.setItems(SQL_Customer.getCustomers());
	}
	
	
	@FXML
	private void addNewCustomer(ActionEvent event)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/NewCustomer.fxml"));
		
		NewCustomerController controller = new NewCustomerController();
		loader.setController(controller);
		
		
		Parent layout;
		try 
		{
			layout = loader.load(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/NewCustomer.fxml"));
			Scene scene = new Scene(layout);
			Stage stage = new Stage();
			controller.setStage(stage);
			
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			updateCustomers();
		} 
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
	}

	
	public void updateCustomers() throws SQLException
	{
		customersTable.setItems(SQL_Customer.getCustomers());
	}

	@FXML
	private void backToDashboard(MouseEvent event) throws IOException
	{
		Parent parent = FXMLLoader.load(getClass().getResource
				("/com/nkris/scheduling_app/FXML/DashboardUI.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}



	
	
	
	
}
