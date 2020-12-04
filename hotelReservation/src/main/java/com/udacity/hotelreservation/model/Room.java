package com.udacity.hotelreservation.model;

public class Room implements IRoom {

	protected String roomNumber;
	protected Double price;
	protected RoomType roomType;

	public Room(String roomNumber, Double price, RoomType roomType) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public boolean isFree() {
		return this.price == 0.0;
	}

	@Override
	public String toString() {
		return "Room Type: " + this.roomType + ", Price: " + this.price + ", Number: " + this.roomNumber;
	}

	public Double getRoomPrice() {
		return this.price;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Room)) {
			return false;
		}
		Room room = (Room) o;

		return room.getRoomNumber().equals(roomNumber);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + roomNumber.hashCode();
		return result;
	}

}
