package com.udacity.hotelreservation.api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.udacity.hotelreservation.model.Customer;
import com.udacity.hotelreservation.model.IRoom;
import com.udacity.hotelreservation.model.Reservation;

public class ReservationService {

	private List<Reservation> reservations;
	private List<IRoom> rooms;

	private static ReservationService instance;

	private ReservationService() {
		this.reservations = new ArrayList<Reservation>();
		this.rooms = new ArrayList<IRoom>();
	}

	public static ReservationService Instance() {
		if (instance == null)
			instance = new ReservationService();
		return instance;
	}

	public void addRoom(IRoom room) {
		if (!this.rooms.stream().anyMatch(r -> r.getRoomNumber().equals(room.getRoomNumber()))) {
			this.rooms.add(room);
		}
	}

	public IRoom getARoom(String roomNumber) {
		return this.rooms.stream().filter(room -> roomNumber.equalsIgnoreCase(room.getRoomNumber())).findAny()
				.orElse(null);
	}

	public List<IRoom> getAllRooms() {
		return this.rooms;
	}

	public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		if (isReservationPossible(room, checkInDate, checkOutDate)) {
			Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
			this.reservations.add(reservation);
			return reservation;
		}
		return null;
	}

	public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
		List<IRoom> availableRooms = new ArrayList<IRoom>();
		for (IRoom room : rooms) {
			List<Reservation> reservations = this.findReservation(room.getRoomNumber());
			if (reservations.isEmpty() || !isDateBooked(reservations, checkInDate, checkOutDate)) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}

	private boolean isReservationPossible(IRoom room, Date checkInDate, Date checkOutDate) {
		return this.findRooms(checkInDate, checkOutDate).stream()
				.filter(r -> r.getRoomNumber().equalsIgnoreCase(room.getRoomNumber())).findAny().isPresent();
	}

	private boolean isDateBooked(List<Reservation> reservations, Date checkInDate, Date checkOutDate) {
		for (Reservation reservation : reservations) {
				if(reservation.getCheckin().before(checkOutDate) && checkInDate.before(reservation.getCheckout())){//overlap
					return true;
					
			}
		}
		return false;
	}

	private List<Reservation> findReservation(String roomNumer) {
		return this.reservations.stream()
				.filter(reservation -> reservation.getRoom().getRoomNumber().equalsIgnoreCase(roomNumer))
				.collect(Collectors.toList());
	}

	public List<Reservation> getCustomersReservation(Customer customer) {
		return this.getCustomersReservation(customer.getEmail());

	}

	public List<Reservation> getCustomersReservation(String customerEmail) {
		return this.reservations.stream()
				.filter(reservation -> reservation.getCustomer().getEmail().equals(customerEmail))
				.collect(Collectors.toList());

	}

	public void printAllReservations() {
		this.reservations.forEach(reservation -> System.out.println(reservation.toString()));
	}

}
