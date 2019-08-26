package com.nkris.scheduling_app.controllers.helpers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.nkris.scheduling_app.controllers.CustomersController;
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
import javafx.scene.control.Button;
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
		if(CustomersController.selectedCustomer!=null)
		{
			fillCustomerFields(CustomersController.selectedCustomer);
		}
	}
	
	
	
	@FXML
	private void saveNewCustomer(ActionEvent event)
	{
		try {
			Connection connection = DatabaseHandler.getConnection();
			Customer customer = createCustomer();
			SQL_Customer.insertCustomer(customer, connection);
			SQL_Customer.customers.add(customer);
			SQL_Address.insertAddress(customer.getAddress(), connection);
			SQL_City.insertCity(customer.getAddress().getCity(), connection);
			SQL_Country.insertCountry(customer.getAddress().getCountry(), connection);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
				
		customer.setName(firstNameTextField.getText() + " " + lastNameTextField.getText());
		
		Address address = createNewAddress();
		customer.setAddress(address);
		
						
		return customer;
		
	}
	

	public Address createNewAddress()
	{
		Address address = new Address();
		address.setStreetAddress(addressTextField.getText());
		address.setCity(getCity());
		address.setPhoneNumber(phoneTextField.getText());
		address.setZipcode(zipcodeTextField.getText());
		address.setId(this.hashCode());
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
	
	
	public void fillCustomerFields(Customer customer)
	{
		firstNameTextField.setText(getFirstName(customer.getName()));
		lastNameTextField.setText(getLastName(customer.getName()));
		addressTextField.setText(customer.getAddress().getStreetAddress());
		cityTextField.setText(customer.getAddress().getCity().getCityName());
		stateTextField.setText(customer.getAddress().getState());
		countryTextField.setText(customer.getAddress().getCountry().getCountryName());
		zipcodeTextField.setText(customer.getAddress().getZipcode());
		phoneTextField.setText(customer.getAddress().getPhoneNumber());
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







	
	
}
