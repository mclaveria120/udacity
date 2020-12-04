package com.udacity.hotelreservation.model;

import java.util.Date;

public class Reservation {

	private Customer customer;
	private IRoom room;
	private Date checkin;
	private Date checkout;

	public Reservation(Customer customer, IRoom room, Date checkin, Date checkout) {
		super();
		this.customer = customer;
		this.room = room;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public IRoom getRoom() {
		return room;
	}

	public void setRoom(IRoom room) {
		this.room = room;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "Checkin: " + this.checkin + " Checkout: " + this.checkout + this.customer.toString();
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Reservation)) {
			return false;
		}

		Reservation reservation = (Reservation) o;

		return reservation.getCheckin().equals(checkin) && reservation.getCheckout().equals(checkout)
				&& reservation.getCustomer().equals(customer) && reservation.getRoom().equals(room);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + room.hashCode();
		result = 31 * result + checkin.hashCode();
		result = 31 * result + checkout.hashCode();
		result = 31 * result + customer.hashCode();
		return result;
	}

}
