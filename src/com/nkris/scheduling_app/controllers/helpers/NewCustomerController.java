package com.nkris.scheduling_app.controllers.helpers;

import java.sql.SQLException;

import com.nkris.scheduling_app.database.SQL_Customer;
import com.nkris.scheduling_app.models.Address;
import com.nkris.scheduling_app.models.City;
import com.nkris.scheduling_app.models.Country;
import com.nkris.scheduling_app.models.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCustomerController 
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
	
	
	
	
	@FXML
	private void saveNewCustomer(ActionEvent event)
	{
		try {
			SQL_Customer.insertCustomer(createCustomer());
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

	}
	
	@FXML
	private void cancelNewCustomer(ActionEvent event)
	{
		
	}
	
	
	public Customer createCustomer()
	{
		Customer customer = new Customer();
		
		customer.setName(firstNameTextField.getText()+" "+lastNameTextField.getText());
		
		customer.setAddress(createNewAddress());
		
		return customer;
		
	}
	
	
	public Address createNewAddress()
	{
		Address address = new Address();
		address.setAddress(addressTextField.getText());
		address.setCity(getCity());
		address.setPhoneNumber(phoneTextField.getText());
		address.setZipcode(zipcodeTextField.getText());
		return address;	
	}
	
	
	public City getCity()
	{
		City city = new City();
		city.setCityName(cityTextField.getText());
		city.setCountry(getCountry());
		return city;
				
	}
	
	public Country getCountry()
	{
		Country country = new Country();
		country.setCountryName(countryTextField.getText());
		return country;
	}
	
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	
	
}
