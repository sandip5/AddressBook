package com.presensesoft.addressbook;

import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;



public class AddressBookManager {
	
	public static void main(String[] args)
			throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		
		ImplAddressBookUtil util = new ImplAddressBookUtil();
		util.readJson();

		Scanner scanner = new Scanner(System.in);

		boolean isExitAddressBook = false;
		System.out.println("Address book manager\n");
		while (!isExitAddressBook) {

			System.out.println("Select menu");
			System.out.println("1. New Address Book\n" + "2. Open Address Book\n" + "3. Save Address Book\n"
					+ "4. SaveAs Address Book\n" + "5. Quit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				util.createNewAddressBook();
				break;
			case 2:
				util.openAddressBook();
				break;
			case 3:
				util.save();
				break;
			case 4:
				util.saveAs();
				break;
			case 5:
				System.out.println("-----------------------Quit Address Book-----------------------");
				isExitAddressBook = true;
				System.out.println("Thank you for your time");
				break;
			default:
				System.out.println("Invalid option");
				break;

			}
		}

		scanner.close();
	}


}
