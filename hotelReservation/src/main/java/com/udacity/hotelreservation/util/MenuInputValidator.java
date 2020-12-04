package com.udacity.hotelreservation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MenuInputValidator {

	
	public static Integer integerInput(String titleName, Scanner sacanner) {
		System.out.println(titleName);
		try{
			return Integer.parseInt(sacanner.next());
		}catch(Exception e) {
			System.out.println("Invalid Number. Please try again");	
			throw e;
		}
	}
	
	public static String stringInput(String titleName, Scanner sacanner) {
		System.out.println(titleName);
		return sacanner.next();
	}
	
	public static Double doubleInput(String titleName, Scanner sacanner) {
		System.out.println(titleName);
		try{
			return Double.parseDouble(sacanner.next());
		}catch(Exception e) {
			System.out.println("Invalid Number. Please try again");	
			throw e;
		}
	}

	public static Date dateInput(String titleName, Scanner sacanner) throws Exception  {
		System.out.println(titleName);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
		try {
			return formatter.parse(sacanner.next());
		} catch (Exception e) {
			System.out.println("Invaldd Date. Please try again");	
			throw e;
		}
	}
}
