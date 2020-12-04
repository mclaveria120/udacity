package com.udacity.hotelreservation.menu;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.udacity.hotelreservation.api.AdminResource;
import com.udacity.hotelreservation.model.FreeRoom;
import com.udacity.hotelreservation.model.Room;
import com.udacity.hotelreservation.model.RoomType;
import com.udacity.hotelreservation.util.MenuInputValidator;

public class AdminMenu {

	private AdminResource adminResource;

	public AdminMenu(AdminResource adminResource) {
		this.adminResource = adminResource;
	}

	public void start() {
		boolean activate = true;
		String userInput;
		Scanner sn = new Scanner(System.in);
		while (activate) {

			System.out.println("**** Welcome to Admin Section ****");

			System.out.println("1.- See al customers");
			System.out.println("2.- See all rooms");
			System.out.println("3.- See al reservations");
			System.out.println("4.- Add a room");
			System.out.println("5.- Back to main menu");

			userInput = sn.next();
			switch (userInput) {
			case "1":
				this.adminResource.getAllCustomers().forEach(System.out::println);
				break;
			case "2":
				this.adminResource.getAllRooms().forEach(System.out::println);
				break;
			case "3":
				this.adminResource.displayAllReservations();
				break;
			case "4":
				try {
					String roomNumber = MenuInputValidator.stringInput("Enter room number", sn);
					String roomType = MenuInputValidator.stringInput("Enter room type: Single or Double", sn);
					Double price = MenuInputValidator.doubleInput("Enter price", sn);

					Room room = new Room(roomNumber, price, RoomType.valueOf(roomType.toUpperCase()) );
					this.adminResource.addRoom(room);	
					break;
	
				}catch(Exception e) {
					System.out.println("Error while trying to add a room. Please try again");
				}
			case "5":
				activate = false;
				break;
			default:
				activate = false;
			}
		}
	}
}
