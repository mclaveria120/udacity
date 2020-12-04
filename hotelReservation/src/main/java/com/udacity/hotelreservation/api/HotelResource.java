package com.udacity.hotelreservation.api;

import java.util.Date;
import java.util.List;

import com.udacity.hotelreservation.api.services.CustomerService;
import com.udacity.hotelreservation.api.services.ReservationService;
import com.udacity.hotelreservation.model.Customer;
import com.udacity.hotelreservation.model.IRoom;
import com.udacity.hotelreservation.model.Reservation;

public class HotelResource {

	private ReservationService reservationService;
	private CustomerService customerService;
	
	public HotelResource() {
		this.reservationService = ReservationService.Instance();
		this.customerService = CustomerService.Instance();
	}
	
	public Customer getCustomer(String email) {
		return this.customerService.getCustomer(email);
	}
	
	public void createACustomer(String  email,  String firstName, String lastname) {
		this.customerService.addCustomer(email, firstName, lastname);
	}
	
	public IRoom getRoom(String roomNumber) {
		return this.reservationService.getARoom(roomNumber);
	}
	
	public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
		Customer customer = this.customerService.getCustomer(customerEmail);
		return this.reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
	}
	
	public List<Reservation>  getCustomersReservations(String customerEmail){
		return this.reservationService.getCustomersReservation(customerEmail);
		
	}
	
	public List<IRoom> findARoom(Date checkIn, Date checkOut){
		return this.reservationService.findRooms(checkIn, checkOut);
	}
}
