package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.database.DatabaseHandler;
import com.nkris.scheduling_app.database.SQL_Address;
import com.nkris.scheduling_app.database.SQL_City;
import com.nkris.scheduling_app.database.SQL_Country;
import com.nkris.scheduling_app.database.SQL_Customer;
import com.nkris.scheduling_app.models.Address;
import com.nkris.scheduling_app.models.City;
import com.nkris.scheduling_app.models.Country;
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

public class NewCustomerController implements Initializable
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
	
	private Stage stage = null;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		
	}
	
	private void clearTextFields()
	{
		firstNameTextField.clear();
		lastNameTextField.clear();
		addressTextField.clear();
		cityTextField.clear();
		countryTextField.clear();
		zipcodeTextField.clear();
		phoneTextField.clear();
		stateTextField.clear();
	}
	
	
	@FXML
	private void saveNewCustomer(ActionEvent event)
	{
		if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") 
				|| addressTextField.getText().equals("") || cityTextField.getText().equals("")
				|| countryTextField.getText().equals("") || zipcodeTextField.getText().equals("")
				|| phoneTextField.getText().equals("") || stateTextField.getText().equals("")) {
			displayAlert();
		}
		
		else {
			try {
				Connection connection = DatabaseHandler.getDBconnection();
				Customer customer = createCustomer();
				SQL_Customer.insertCustomer(customer, connection);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		}
	}
	
	
	
	
	
	@FXML
	private void cancelNewCustomer(ActionEvent event)
	{
		if(cancelNewCustomerAlert())
		{
		    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

		}
	}
	
	
	private boolean cancelNewCustomerAlert()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel customer entry?"
				, ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if(alert.getResult() == ButtonType.YES)
		{
			return true;
		}
		return false;
	}
	
	
	public Customer createCustomer() throws ClassNotFoundException, SQLException
	{
		Customer customer = new Customer();
		customer.setCustomerID(SQL_Customer.getLastIndex(DatabaseHandler.getDBconnection()));
		customer.setName(firstNameTextField.getText() + " " + lastNameTextField.getText());
		//customer.setCustomerID();
		Address address = createNewAddress();
		customer.setAddress(address);
		customer.getAddress().setPhoneNumber(phoneTextField.getText());
		
						
		return customer;
		
	}
	

	public Address createNewAddress() throws ClassNotFoundException, SQLException
	{
		Address address = new Address();
		address.setStreetAddress(addressTextField.getText());
		address.setCity(getCity());
		address.setPhoneNumber(phoneTextField.getText());
		address.setZipcode(zipcodeTextField.getText());
		address.setState(stateTextField.getText());
		address.setId(SQL_Address.getLastIndex(DatabaseHandler.getDBconnection()));
		//address.setId();
		return address;	
	}
	
	
	public City getCity() throws ClassNotFoundException, SQLException
	{
		City city = new City();
		city.setCityName(cityTextField.getText());
		city.setCountry(getCountry());
		
		if(!SQL_City.containsCity(city.getCityName())) {
			city.setCityID(SQL_City.getLastIndex(DatabaseHandler.getDBconnection()));
			SQL_City.insertCity(city, DatabaseHandler.getDBconnection());
		}
		
		else {
			city.setCityID(SQL_City.getCityId(city.getCityName(), city.getCountry().getCountryId()));
		}
		return city;
				
	}
	
	public Country getCountry() throws ClassNotFoundException, SQLException
	{
		Country country = new Country();
		country.setCountryName(countryTextField.getText());
		
		if(!SQL_Country.containsCountry(countryTextField.getText())) {
			country.setCountryID(SQL_Country.getLastIndex(DatabaseHandler.getDBconnection()));
			SQL_Country.insertCountry(country, DatabaseHandler.getDBconnection());
		}
		else {
			country.setCountryID(SQL_Country.getCountryId(countryTextField.getText()));
		}
		
		return country;
	}
	
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	
	

	private void displayAlert()
	{
		Alert alert = new Alert(AlertType.WARNING, "Please fill out all fields before saving.", ButtonType.OK);
		
		alert.showAndWait();
	}


	
	
}
