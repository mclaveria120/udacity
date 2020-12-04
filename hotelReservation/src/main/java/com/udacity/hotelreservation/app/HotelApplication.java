package com.udacity.hotelreservation.app;

import com.udacity.hotelreservation.api.AdminResource;
import com.udacity.hotelreservation.api.HotelResource;
import com.udacity.hotelreservation.menu.MainMenu;

public class HotelApplication {

	
	public static void main(String args[]) {
		MainMenu mainMenu = new MainMenu(new HotelResource(), new AdminResource());
		mainMenu.start();
	}

}
