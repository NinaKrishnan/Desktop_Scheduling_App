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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
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
	private TableColumn<Customer, String> personalIDColumn;
	
	@FXML
	private TableColumn<Customer, Address> addressIDColumn;
	
	@FXML
	private TableColumn<Customer, Integer> activeColumn;
	
	@FXML
	private ImageView backArrow;
	
	@FXML
	private Button viewCustomerButton;
	
	public static Customer selectedCustomer;
	
	public static int selectedCustomerId;

	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		try {
			setCellValues();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getCellValues();
	
		
	}
	
	
	private void setCellValues() throws ClassNotFoundException
	{
		nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		personalIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
		activeColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("active"));
		addressIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Address>("address"));

        try {
			populateCustomers();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getCellValues()
	{
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        personalIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty
        		(Integer.toString(cellData.getValue().getCustomerID())));
	}
	
	
	private void populateCustomers() throws SQLException, ClassNotFoundException
	{
		customersTable.getItems().clear();
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

	
	public void updateCustomers() throws SQLException, ClassNotFoundException
	{
		customersTable.getItems().clear();
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

	
	@FXML
	private void viewCustomer(ActionEvent event) throws IOException, SQLException
	{
		selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
		if(selectedCustomer == null)
		{
			noSelectionAlert();
		}
		else
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/ViewCustomer.fxml"));
			
			NewCustomerController controller = new NewCustomerController();
			loader.setController(controller);
			
			
			Parent layout;
			try 
			{
				layout = loader.load(getClass().getResource("/com/nkris/scheduling_app/FXML/helpers/ViewCustomer.fxml"));
				Scene scene = new Scene(layout);
				Stage stage = new Stage();
				controller.setStage(stage);
				
				stage.initModality(Modality.WINDOW_MODAL);
				stage.setScene(scene);
				
				stage.showAndWait();
			} 
			catch (Exception e)
			{ 
				e.printStackTrace();
			}
		}
	}
	
	private void noSelectionAlert()
	{
		Alert alert = new Alert(AlertType.ERROR, "You must select a customer to view.", ButtonType.OK);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.OK)
		{
			alert.close();
		}
	}
	
	
	private void getValueAt()
	{
	    TablePosition pos = customersTable.getSelectionModel().getSelectedCells().get(0);
	    int row = pos.getRow();
	    int column = 1;
	    int id = (Integer) customersTable.getColumns().get(column).getCellObservableValue(row).getValue();
	    selectedCustomerId = id;
	}

	

	
	
	
}
