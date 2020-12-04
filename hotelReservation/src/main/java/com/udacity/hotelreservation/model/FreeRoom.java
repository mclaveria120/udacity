package com.udacity.hotelreservation.model;

public class FreeRoom extends Room{

	public FreeRoom(String roomNumber, Double price, RoomType roomType) {
		super(roomNumber, 0.0, roomType);
	}

	@Override
	public String toString() {
		return "Room Type: "+roomType+ ", Price: Free, Numeber: "+roomNumber;
	}
	
	
}
