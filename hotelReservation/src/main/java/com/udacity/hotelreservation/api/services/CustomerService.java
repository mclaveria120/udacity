package com.udacity.hotelreservation.api.services;

import java.util.ArrayList;
import java.util.List;

import com.udacity.hotelreservation.model.Customer;

public class CustomerService {

	private List<Customer> customers;
	private static CustomerService instance;
	
	private CustomerService() {
		this.customers = new ArrayList<Customer>();
	}
	
	
	public static CustomerService Instance(){
	      if (instance == null)
	    	  instance = new CustomerService();
	      return instance;
	}
	
	public void addCustomer(String email, String firsName, String lastname) {
		if(!this.customers.stream().anyMatch(customer -> customer.getEmail().equals(email))){
			this.customers.add(new Customer(firsName, lastname, email));
		}	
	}
	
	public Customer getCustomer(String email) {
		return this.customers.stream()
				  .filter(customer -> email.equals(customer.getEmail()))
				  .findAny()
				  .orElse(null);
	}	

	
	public List<Customer> getAllCustomers(){
		return this.customers;
	}
}