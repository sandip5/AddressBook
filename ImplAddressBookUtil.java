package com.presensesoft.addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ImplAddressBookUtil implements IAddressBookUtil {
	Scanner scanner = new Scanner(System.in);
	int counter = 0;
	String path = "D:\\java\\AddressBookManagement\\src\\com\\presensesoft\\jsonfiles\\addressbook.json";
	String statename = "";
	static AddressBookModel model = new AddressBookModel();
	static ArrayList<Person> persons = new ArrayList<Person>();

	String search;
	int indexOfPerson;


	public static void PrintPersonDetails(ArrayList<Person> persons, String statename) {
		String str = "";
		str += "Person detail\n";

		System.out.println(str);
		persons.forEach(i -> {
			if (!statename.isEmpty() && statename.equals(i.getAddressObj().getState())) {
				System.out.println(i.getFirstname() + " " + i.getLastname() + " " + i.getAddressObj().getAddressLocal()
						+ " " + i.getAddressObj().getCity() + " " + i.getAddressObj().getState() + " "
						+ i.getAddressObj().getZip() + " " + i.getMobile());
			}

		});
	}

	@Override
	public void readJson() {
		File file = new File(path);
		if (file.exists() && file.length() != 0) {
			try {
				model = (AddressBookModel) JsonUtil.readMapper(path, model);
			} catch (IOException e) {
				e.printStackTrace();
			}
			persons.addAll(model.getPersons());
			counter = persons.size();

		}

	}

	@Override
	public void createNewAddressBook() {
		System.out.println("-----------------------New Address Book-----------------------");
		System.out.println("Enter state name: ");

		statename = scanner.next();

		boolean isFoundState = false;
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAddressObj().getState().equals(statename)) {
				isFoundState = true;
				break;
			}
		}
		if (!isFoundState) {

			System.out.println("->State is added<-");
			boolean close = false;

			while (!close) {
				System.out.println(
						"Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.close");
				int ch = scanner.nextInt();
				switch (ch) {
				case 1:
					
					addPerson();

					break;
				case 2:
					
					editPerson();
					break;
				case 3:
					
					deletePerson();
					break;
				case 4:
					

					sortByLastName();

					break;
				case 5:
					

					sortByZip();

					break;
				case 6:
					// print

					if (counter > 0) {
						System.out.println("Printing all records...");

						ImplAddressBookUtil.PrintPersonDetails(persons, statename);

					} else
						System.out.println("There is no record to print...");

					break;
				case 7:
					// close
					close = true;
					System.out.println("Closing...");
					break;
				default:
					System.out.println("Invalid option");
				}
			}
		} else
			System.out.println("State exist please try again");

		System.out.println("-----------------------New Address Book-----------------------");

	}

	
	@Override
	public void openAddressBook() {
		System.out.println("-----------------------Open Address Book-----------------------");
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < persons.size(); i++) {
			map.put(persons.get(i).getAddressObj().getState(), persons.get(i).getAddressObj().getState());
		}
		System.out.println("states available " + map.keySet());
		System.out.println("Enter state");
		statename = scanner.next();
		boolean isFoundState = false;
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAddressObj().getState().equals(statename)) {
				isFoundState = true;
				break;
			}
		}
		if (isFoundState) {
			System.out.println("->State is found<-");
			boolean close2 = false;

			while (!close2) {
				System.out.println(
						"Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.quit");
				switch (scanner.nextInt()) {
				case 1:					
					addPerson();
					break;
				case 2:					
					editPerson();
					break;
				case 3:
					deletePerson();
					break;
				case 4:
					sortByLastName();
					break;
				case 5:
					sortByZip();
					break;
				case 6:
					// print
					if (counter > 0) {
						System.out.println("Printing all records...");

						ImplAddressBookUtil.PrintPersonDetails(persons, statename);

					} else
						System.out.println("There is no record to print...");

					break;
				case 7:
					// close
					close2 = true;
					System.out.println("Closing...");
					break;
				default:
					System.out.println("Invalid option");
				}
			}

		} else
			System.out.println("Please create new state of that name\nelse try new state name");

		System.out.println("-----------------------Open Address Book-----------------------");

	}

	
	@Override
	public void save() {
		System.out.println("-----------------------Save Address Book-----------------------");

		System.out.println("->Saving address book into json<-");
		model.setPersons(persons);
		try {
			JsonUtil.writeMapper(path, model);
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Writing into file successful....");

		System.out.println("-----------------------Save Address Book-----------------------");

	}

	@Override
	public void saveAs() throws FileNotFoundException {
		System.out.println("-----------------------Save As Address Book-----------------------");

		System.out.println("->Save as<-");
		System.out.println("This option requires path where you want to store file");
		System.out.println("for continue press (y/n)");
		if (scanner.next().charAt(0) == 'y') {
			String pathInput = "D:\\java\\AddressBookManagement\\src\\com\\presensesoft\\jsonfiles\\";
			// checking whether path is valid or not
			System.out.println("Enter filename");
			pathInput += scanner.next();
			pathInput += ".json";
			if (new File(pathInput).exists()) {
				throw new FileNotFoundException("You cannot rewrite existing file");
			} else {
				System.out.println("->Saving address book into file<-");
				model.setPersons(persons);
				try {
					JsonUtil.writeMapper(pathInput, model);
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println("Writing into file successful....");
			}
		}

		System.out.println("-----------------------Save As Address Book-----------------------");


		
	}

	
	@Override
	public void addPerson() {
		System.out.println("Add person details...");
		Person person = new Person();

		System.out.println("Enter mobile");
		Long mobile = scanner.nextLong();
		boolean isMobileTaken = false;
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getMobile() == mobile) {
				isMobileTaken = true;
				break;
			}
		}
		if (isMobileTaken) {
			System.out.println("This mobile number is already taken");
		} else {
			person.setMobile(mobile);
			System.out.println("Enter person first name: ");
			person.setFirstname(scanner.next().toLowerCase());
			System.out.println("Enter person last name: ");
			person.setLastname(scanner.next().toLowerCase());
			System.out.println("Enter address Details: ");
			Address address = new Address();
			System.out.println("Enter address: ");
			address.setAddressLocal(scanner.next());
			System.out.println("Enter city: ");
			address.setCity(scanner.next());
			address.setState(statename);
			System.out.println("Enter zip: ");
			address.setZip(scanner.nextInt());

			person.setAddressObj(address);

			persons.add(person);

			System.out.println();
			System.out.println("Person added");
			counter++;

		}

	}

	
	@Override
	public void editPerson() {
		if (counter > 0) {
			System.out.println("Enter Persons mobile number you want to edit:");
			Long searchMobile = scanner.nextLong();
			indexOfPerson = 0;
			boolean isFoundPerson = false;
			for (int i = 0; i < persons.size(); i++) {
				if (searchMobile == persons.get(i).getMobile()) {
					isFoundPerson = true;
					indexOfPerson = i;
					break;
				}
			}
			if (isFoundPerson) {

				System.out.println("enter new address");
				persons.get(indexOfPerson).getAddressObj().setAddressLocal(scanner.next());
				System.out.println("enter new city name");
				persons.get(indexOfPerson).getAddressObj().setCity(scanner.next());
				System.out.println("enter new zip");
				persons.get(indexOfPerson).getAddressObj().setZip(scanner.nextInt());

				persons.get(indexOfPerson).setMobile(searchMobile);

				System.out.println();
				System.out.println("Edit completed");

			} else
				System.out.println("No person found with this number");
		} else
			System.out.println("There is no record to edit");

	}

	
	@Override
	public void deletePerson() {
		if (counter > 0) {
			System.out.println("Enter Persons mobile number you want to delete:");
			Long Mobilesearch = scanner.nextLong();
			indexOfPerson = 0;
			boolean isFoundPerson = false;
			for (int i = 0; i < persons.size(); i++) {
				if (Mobilesearch == persons.get(i).getMobile()) {
					isFoundPerson = true;
					indexOfPerson = i;
					break;
				}
			}
			if (isFoundPerson) {
				persons.remove(indexOfPerson);
				counter--;
				System.out.println();
				System.out.println("Delete completed");
			} else
				System.out.println("No person found with this number");
		} else
			System.out.println("No records to delete");

	}

	
	@Override
	public void sortByLastName() {
		if (counter > 1) {
			System.out.println("Sorting by Last name is selected");
			Collections.sort(persons, (e1, e2) -> e1.getLastname().compareTo(e2.getLastname()));
			System.out.println("Please wait...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Sorting is completed to see the result select print option");
		} else
			System.out.println("Less records to sort");

		
		
	}

	
	@Override
	public void sortByZip() {
		if (counter > 1) {
			System.out.println("Sorting by zip");
			Collections.sort(persons, (e1, e2) -> String.valueOf(e1.getAddressObj().getZip())
					.compareTo(String.valueOf(e2.getAddressObj().getZip())));
			System.out.println("Please wait...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Sorting is completed to see the result select print option");
		} else
			System.out.println("Less records to sort");

		
	}		
	
}	
	