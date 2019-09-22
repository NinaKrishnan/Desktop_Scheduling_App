package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.controllers.CustomersController;
import com.nkris.scheduling_app.database.SQL_Address;
import com.nkris.scheduling_app.models.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewCustomerController implements Initializable
{
	@FXML
	private TextField firstNameTextField;
	
	@FXML
	private TextField lastNameTextField;
	
	@FXML
	private TextField addressTextField;
	
	@FXML
	private TextField cityTextField;
	
	@FXML
	private TextField stateTextField;
	
	@FXML
	private TextField countryTextField;
	
	@FXML
	private TextField zipcodeTextField;
	
	@FXML
	private TextField phoneTextField;
	
	@FXML
	private Button updateCustomerButton;
	
	@FXML
	private Button cancelUpdateCustomerButton;
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) 
	{
		if(CustomersController.selectedCustomer!=null)
		{
			try {
				fillCustomerFields(CustomersController.selectedCustomer);
				phoneTextField.setText(getZipcode(CustomersController.selectedCustomer));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disableTextFields();
		}
	}
	
	
	
	private void disableTextFields()
	{
		firstNameTextField.setDisable(true);
		lastNameTextField.setDisable(true);
		addressTextField.setDisable(true);
		cityTextField.setDisable(true);
		stateTextField.setDisable(true);
		countryTextField.setDisable(true);
		zipcodeTextField.setDisable(true);
		phoneTextField.setDisable(true);
	}
	
	@FXML
	private void updateCustomer(ActionEvent event)
	{
		if(firstNameTextField.isDisabled())
		{
			firstNameTextField.setDisable(false);
			lastNameTextField.setDisable(false);
			addressTextField.setDisable(false);
			cityTextField.setDisable(false);
			stateTextField.setDisable(false);
			countryTextField.setDisable(false);
			zipcodeTextField.setDisable(false);
			phoneTextField.setDisable(false);
			
			updateCustomerButton.setText("Save Changes");
		}
		else
		{
			
		}
	}
	
	
	@FXML
	private void cancelUpdateCustomer(ActionEvent event)
	{
		if(confirmCancelUpdate())
		{
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

		}
	}


	public void fillCustomerFields(Customer customer) throws ClassNotFoundException, SQLException
	{
		firstNameTextField.setText(getFirstName(customer.getName()));
		lastNameTextField.setText(getLastName(customer.getName()));
		addressTextField.setText(customer.getAddress().getStreetAddress());
		cityTextField.setText(customer.getAddress().getCity().getCityName());
		stateTextField.setText(customer.getAddress().getState());
		countryTextField.setText(customer.getAddress().getCity().getCountry().getCountryName());
		zipcodeTextField.setText(SQL_Address.getZipcode(customer.getAddress().getId()));
		phoneTextField.setText(customer.getAddress().getPhoneNumber());
		zipcodeTextField.setText(getZipcode(customer));
	}
	
	private String getZipcode(Customer customer) throws ClassNotFoundException, SQLException
	{
		String zip = SQL_Address.getZipcode(customer.getAddress().getId());
		return zip;
	}
	
	
	
	public String getFirstName(String name)
	{
		String firstName = "";
		for(int i = 0; i < name.length(); i++)
		{
			if(name.charAt(i) == ' ')
			{
				firstName = name.substring(0, i);
			}
		}
		return firstName;
	}
	
	public String getLastName(String name)
	{
		String lastName = "";
		for(int i = 0; i < name.length(); i++)
		{
			if(name.charAt(i) == ' ')
			{
				lastName = name.substring(i+1, name.length());
			}
		}
		return lastName;
	}

	
	private boolean confirmCancelUpdate()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel? "
				+ "Any changes you made will be lost.", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.YES)
		{
		    return true;
		}
		return false;
		
	}


	
	
	
}
