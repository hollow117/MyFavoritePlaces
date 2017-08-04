///////////////////////////////////////////////////////////////////////////////
//   
// Title:            My Favorite Places
// Files:            MyFavoritePlaces.java, PlaceList.java, Place.java
// Semester:         CS302 Spring 2016
//
// Author:           Jason Choe
// Email:            choe2@wisc.edu
// CS Login:         choe
// Lecturer's Name:  Jim Williams
// Lab Section:      313
//
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  MyFavoritePlaces.java
// File:             MyFavoritePlaces.java
// Semester:         CS302 Spring 2016
//
// Author:           Jason Choe, choe2@wisc.edu
// CS Login:         choe
// Lecturer's Name:  Jim Williams
// Lab Section:      313
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MyFavoritePlaces class provides a way to store and retrieve lists of places.
 * Each place has a name, address, latitude and longitude. The program can load
 * places from one or more files, the user can add, edit and delete these 
 * places and then save a list of places to a file.The user can display 
 * information about the places. The user can specify a current place and the 
 * places will be listed in order according to increasing distance from the 
 * current place.
 *
 * no known bugs
 *
 * @author Jason Choe
 */

public class MyFavoritePlaces {
	// this field will be used to have MyFavoritePlaces turned on or off.
	private static boolean turnOn = true;
	private static boolean placeExist = false;

	/**
	 * This method runs the MyFavoritePlaces program. User get to choose 
	 * options from add, show, edit, delete, set current place, read and write 
	 * file, and quit program. Depending on user's choice, the program will 
	 * display information about the places.
	 * 
	 * @param args
	 *            - String information from different files
	 * 
	 * @return none
	 */
	public static void main(String[] args) {

		Place aPlace = new Place(); // setting instance of class Place
		PlaceList placeList = new PlaceList(); // setting instance of class
		// PlaceList
		Scanner input = new Scanner(System.in); // setting instance of class
		// Scanner

		do {
			System.out.println("My Favorite Places 2016" + 
		"\n--------------------------");
			if (aPlace.getCurrentPlace() != null) {
				System.out.print("distance from " + 
			aPlace.getCurrentPlace().getName() + "\n");
				for (int i = 0; i < placeList.numOfPlaces(); i++) {
					placeList.sort();
					System.out.print(i + 1 + ") " + 
					placeList.retrieve(i).getName());
					System.out.printf(" (%.2f", placeList.retrieve(i).
							getDifferenceWithCurrentPlace());
					System.out.print(") miles");
					if (i != placeList.numOfPlaces() - 1) {
						System.out.print("\n");
					}

				}
			} else if (placeList.numOfPlaces() > 0) {
				for (int i = 0; i < placeList.numOfPlaces(); i++) {
					{
						placeList.sort();
						System.out.print(i + 1 + ") " + placeList.retrieve(i).
								getName());
						if (i != placeList.numOfPlaces() - 1) {
							System.out.print("\n");
						}
					}

				}
			} else {
				System.out.print("No places loaded.");
			}
			// ----------------------------------------------------------------
			// -----------------------SHOWING OPTIONS--------------------------

			System.out.print("\n--------------------------");
			if (placeList.numOfPlaces() > 0) {
				placeExist = true;
				System.out.print(
						"\nA)dd S)how E)dit D)elete C)urrent R)ead W)rite" + 
				" Q)uit : ");

			} else {
				System.out.print("\nA)dd R)ead Q)uit : ");

			}

			String choice = input.nextLine();

			// this checks to let user to only write a single letter as input
			if (choice.length() > 1) {
				System.out.print("\n");
				continue;
			}
			
			// this checks to let user to only write appropriate option as 
			// input if there is no place stored in the ArrayList
			if (placeList.numOfPlaces() == 0 && !choice.equalsIgnoreCase("A") 
					&& !choice.equalsIgnoreCase("R") 
					&& !choice.equalsIgnoreCase("Q")){
				System.out.print("\n");
				continue;
			}
			
			// this checks to let user to only write appropriate option as 
			// input if there is/are place(s) stored in the ArrayList
			if (placeList.numOfPlaces() > 0 && !choice.equalsIgnoreCase("A") 
					&&!choice.equalsIgnoreCase("S") 
					&&!choice.equalsIgnoreCase("E") 
					&&!choice.equalsIgnoreCase("D") 
					&&!choice.equalsIgnoreCase("C") 
					&&!choice.equalsIgnoreCase("R") 
					&&!choice.equalsIgnoreCase("W") 
					&& !choice.equalsIgnoreCase("Q")) {
				System.out.print("\n");
				continue;
			}

			// ----------------------------ADD---------------------------------
			if (choice.equalsIgnoreCase("A")) {
				System.out.print("Enter the name: ");
				String choice1 = input.nextLine(); // getting place name from
				// user

				System.out.print("Enter the address: ");
				String choice2 = input.nextLine(); // getting place address
				// from user

				try {
					String response = Geocoding.find(choice2);
					GResponse geoResponse = GeocodeResponse.parse(response);
					if (geoResponse.hasAddress()) {
						Place newPlace = new Place(choice1, geoResponse.
								getFormattedAddress(),
								geoResponse.getLatitude(), geoResponse.
								getLongitude());
						if (placeList.areadyExist(newPlace)) {
							// print message saying already exists
							System.out.println("Place " + newPlace.getName() + 
									" already in list.\n");

						} else {
							placeList.add(newPlace);
							System.out.print("\n");
						}

					} else {
						System.out.print("Place not found using address: " + 
					choice1 + "\n");
						System.out.print("Press Enter to continue.");
						input.nextLine();
						System.out.print("\n");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				// this sets placeExist to true so that different options 
				// can be available
				placeExist = true;
					

				// ----------------------------SHOW-----------------------------
			} else if (choice.equalsIgnoreCase("S") && placeExist == true) {
				System.out.print("Enter number of place to Show: ");
				int choice3 = input.nextInt();
				if ((choice3) <= placeList.numOfPlaces()) {
					System.out.println(placeList.retrieve(choice3 - 1).
							getName());
					System.out.println(placeList.retrieve(choice3 - 1).
							getAddress());
					System.out.print(placeList.retrieve(choice3 - 1).
							getLatitude() + ", ");
					System.out.println(placeList.retrieve(choice3 - 1).
							getLongitude());
					try {
						Geocoding.openBrowser(placeList.retrieve(choice3 - 1).
								url());
					} catch (IOException | URISyntaxException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					try {
						System.out.println(placeList.retrieve(choice3 - 1).
								url());
					} catch (UnsupportedEncodingException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					System.out.println("Press Enter to continue.");
					input.nextLine();

				} else {
					System.out.print("Invalid value: " + choice3 + "\n\n");
				}
				input.nextLine();

				// -------------------------EDIT-------------------------------
			} else if (choice.equalsIgnoreCase("E") && placeExist == true) {
				System.out.print("Enter number of place to Edit: ");
				int choice4 = input.nextInt();
				if ((choice4) <= placeList.numOfPlaces()) {
					System.out.print("Current name: " + placeList.
							retrieve(choice4 - 1).getName());
					System.out.print("\nEnter a new name: ");
					input.nextLine();
					String choice4_1 = input.nextLine();
					System.out.print("Current address: " + placeList.
							retrieve(choice4 - 1).getAddress());
					System.out.print("\nEnter a new address: ");
					String choice4_2 = input.nextLine();
					System.out.print("\n");
					
					// the following lines delete place from the file/ memory
					// and add a new place into the file/ memory according to 
					// user's response
					
					placeList.remove(choice4 - 1);
					aPlace.setName(choice4_1);
					aPlace.setAddress(choice4_2);
					try {
						aPlace.setAddress(
								GeocodeResponse.parse(Geocoding.find(aPlace.
										getAddress())).getFormattedAddress());
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					try {
						aPlace.setLatitude(GeocodeResponse.parse(Geocoding.
								find(aPlace.getAddress())).getLatitude());
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					try {
						aPlace.setLongitude(GeocodeResponse.parse(Geocoding.
								find(aPlace.getAddress())).getLongitude());
					} catch (IOException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					placeList.add(new Place(aPlace.getName(), aPlace.
							getAddress(), aPlace.getLatitude(),
							aPlace.getLongitude()));
				} else {
					System.out.print("Invalid value: " + choice4 + "\n\n");
					input.nextLine();
				}
				
				// --------------------------DELETE----------------------------
			} else if (choice.equalsIgnoreCase("D") && placeExist == true) {
				System.out.print("Enter number of place to Delete: ");

				String choice5 = input.nextLine();

				try {
					Integer.parseInt(choice5);
				} catch (NumberFormatException e) {
					System.out.print("Invalid value: " + choice5 + "\n\n");
					continue;
				}
				if (Integer.parseInt(choice5) <= placeList.numOfPlaces()) {
					System.out.println(placeList.retrieve(Integer.
							parseInt(choice5) - 1).getName() + " deleted.");
					placeList.remove(Integer.parseInt(choice5) - 1);
					System.out.println("Press Enter to continue.");
					input.nextLine();

				} else {
					System.out.print("Invalid value: " + choice5 + "\n");
					System.out.print("\n");
				}

				if (placeList.numOfPlaces() == 0) { // Look to see if there
					placeExist = false; 			// is anything saved in
				} 									// ArrayList
					// -----------------------CURRENT--------------------------
			} else if (choice.equalsIgnoreCase("C") && placeExist == true) {
				System.out.print(
						"Enter number of place to be Current place: ");

				String choice6 = input.nextLine();

				try {
					Integer.parseInt(choice6);
				} catch (NumberFormatException e) {
					System.out.print("Invalid value: " + choice6 + "\n\n");
					continue;
				}
				if (Integer.parseInt(choice6) <= placeList.numOfPlaces()) {
					System.out.println(
							placeList.retrieve(Integer.parseInt(choice6) - 1).
							getName() + " set as Current place.");
					aPlace.setCurrentPlace(placeList.retrieve(Integer.
							parseInt(choice6) - 1));
					placeList.sort();
					System.out.println("Press Enter to continue");
					input.nextLine();
				} else {
					System.out.print("Invalid value: " + choice6 + "\n\n");
					input.nextLine();
				}

	
				// ---------------------------READ-----------------------------
			} else if (choice.equalsIgnoreCase("R")) {

				System.out.println("Available Files:");
				placeList.directory();
				System.out.print("\nEnter filename: ");
				String choice7 = input.nextLine();
				File file = new File(choice7);
				if (!file.exists()) {
					System.out.print("Unable to read from file: " + choice7 + 
							"\n\n");
					continue;
				}
					placeList.read(choice7);
			

				if (placeList.numOfPlaces() == 0) { // Look to see if there
					placeExist = false; 			// is anything saved in
				} else {							// ArrayList
					placeExist = true;
				}

				// ---------------------------WRITE----------------------------
			} else if (choice.equalsIgnoreCase("W") && placeExist == true) {
				System.out.print("Enter filename: ");
				String choice7 = input.nextLine();
				System.out.print("\n");

				if (!choice7.equals(null) || !choice.equals("")) {
					placeList.write(choice7);
				} else {
					System.out.println("Unable to write to file:" + choice7);
					System.out.println("\n");
				}
				// ---------------------------QUIT-----------------------------
			} else if (choice.equalsIgnoreCase("Q")) {
				System.out.println(
						"Thank you for using My Favorite Places 2016!");
				MyFavoritePlaces.turnOn = false;
			}
			// Do-while loop will only continue if variable turnOn is true.
		} while (MyFavoritePlaces.turnOn == true); 

		// Scanner closing
		input.close();
	}

}
