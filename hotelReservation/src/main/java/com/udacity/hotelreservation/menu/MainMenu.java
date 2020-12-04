package com.udacity.hotelreservation.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.udacity.hotelreservation.api.AdminResource;
import com.udacity.hotelreservation.api.HotelResource;
import com.udacity.hotelreservation.model.IRoom;
import com.udacity.hotelreservation.model.Reservation;
import com.udacity.hotelreservation.util.MenuInputValidator;

public class MainMenu {

	private HotelResource hotelResource;
	private AdminResource adminResource;

	public MainMenu(HotelResource hotelResource, AdminResource adminResource) {

		this.hotelResource = hotelResource;
		this.adminResource = adminResource;
	}

	public void start() {
		try {

			String userInput;
			Scanner sn = new Scanner(System.in);
			while (true) {

				System.out.println("**** Welcome to HotelReservation application  ****");
				System.out.println("1.- Find and Reserve a room");
				System.out.println("2.- See my reservations");
				System.out.println("3.- Create an account");
				System.out.println("4.- Admin");
				System.out.println("5.- Exit");
				System.out.println("Enter your choice:");

				userInput = sn.next();
				switch (userInput) {
				case "1":
					try {
						String email = MenuInputValidator.stringInput("Enter your email", sn);
						Date checkInDate = MenuInputValidator.dateInput("Enter checkin date(dd-M-yyyy)", sn);
						Date checkOutDate = MenuInputValidator.dateInput("Enter checkout date(dd-M-yyyy)", sn);
						System.out.println("Rooms available: ");
						List<IRoom> rooms = this.hotelResource.findARoom(checkInDate, checkOutDate);
						if(rooms.isEmpty()) {
							System.out.println("No rooms available for those dates.");
							break;
						}
						IntStream.range(0, rooms.size())
								.forEach(index -> System.out.println(index + ":" + rooms.get(index)));

					
						Integer roomSelection = MenuInputValidator.integerInput("Which room do you want: ", sn);

						Reservation reservation = this.hotelResource.bookARoom(email, rooms.get(roomSelection),
								checkInDate, checkOutDate);
						System.out.println(reservation);
					} catch (Exception e) {
						System.out.println("Error while creating a reservation. Please try again");
					}
					break;
				case "2":
					try {
						String email = MenuInputValidator.stringInput("Enter your email", sn);
						this.hotelResource.getCustomersReservations(email).forEach(System.out::println);
					} catch (Exception e) {
						System.out.println("Error while searching a reservation. Please try again");
					}
					break;
				case "3":
					try {
						String email = MenuInputValidator.stringInput("Enter your email", sn);
						String firstName = MenuInputValidator.stringInput("Enter your firstName", sn);
						String lastname = MenuInputValidator.stringInput("Enter your lastname", sn);
						this.hotelResource.createACustomer(email, firstName, lastname);
						System.out.println("Account created");
					} catch (Exception e) {
						System.out.println("Error while creating a customer. Please try again");
					}
					break;
				case "4":
					AdminMenu adminMenu = new AdminMenu(adminResource);
					adminMenu.start();
					break;
				case "5":
					System.out.println("Exiting...");
					System.exit(0);
				default:
					System.out.println("Invalid choice. Read the options carefully...");
				}
			}
		} catch (Exception e) {
			System.out.println("Error!");
			System.out.println("Exiting...");
			System.exit(0);
		}
	}

	public void creassteAccount(String email, String firstName, String lastname) {
		this.hotelResource.createACustomer(email, firstName, lastname);

	}
}
