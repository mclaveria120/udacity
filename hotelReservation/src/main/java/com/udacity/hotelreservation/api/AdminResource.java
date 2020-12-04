package com.udacity.hotelreservation.api;

import java.util.List;

import com.udacity.hotelreservation.api.services.CustomerService;
import com.udacity.hotelreservation.api.services.ReservationService;
import com.udacity.hotelreservation.model.Customer;
import com.udacity.hotelreservation.model.IRoom;

public class AdminResource {

	
	public Customer getCustomer(String email) {
		return CustomerService.Instance().getCustomer(email);
	}
	
	
	public void addRoom(IRoom room) {
		ReservationService.Instance().addRoom(room);
	}
	
	public List<IRoom> getAllRooms(){
		return ReservationService.Instance().getAllRooms();
	}
	
	public List<Customer> getAllCustomers(){
		return CustomerService.Instance().getAllCustomers();
	}
	
	public void displayAllReservations() {
		 ReservationService.Instance().printAllReservations();
	}
}
