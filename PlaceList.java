
///////////////////////////////////////////////////////////////////////////////
//                  
// Main Class File:  MyFavoritePlaces.java
// File:             PlaceList.java
// Semester:         CS302 Spring 2016
//
// Author:           Jason Choe choe2@wisc.edu
// CS Login:         choe
// Lecturer's Name:  Jim Williams
// Lab Section:      313
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class stores list of Places into an ArrayList and handles the list
 * appropriately depending on methods used in the main class of
 * MyFavoritePlaces.
 *
 * no known bugs
 *
 * @author Jason Choe
 */

public class PlaceList {
	private ArrayList<Place> list = new ArrayList<>();

	/**
	 * This method loads places found in the file from String input "fileName"
	 * and store them into the ArrayList list.
	 * 
	 * @param fileName
	 *            - this is the name of the file provided by the user
	 * 
	 * @return none
	 */

	public void read(String fileName) {
		File folder = new File(fileName);
		try (Scanner input = new Scanner(folder);) {
			while (input.hasNext()) {
				String line1 = input.nextLine();
				String[] breakDown = line1.split(";");
				String placeName = breakDown[0];
				String placeAddress = breakDown[1];
				double placeLatitude = Double.parseDouble(breakDown[2]);
				double placeLongitude = Double.parseDouble(breakDown[3]);

				Place newPlace = new Place(placeName, placeAddress, 
						placeLatitude, placeLongitude);
				if (this.areadyExist(newPlace)) {
					// print message saying already exists
					System.out.println("Place " + newPlace.getName() + 
							" already in list.");

				} else {
					this.add(newPlace);
				}

			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to read from file: <" + fileName + ">");
			ex.printStackTrace();
		}
		System.out.print("\n");
	}

	/**
	 * This method creates a new file from String input fileName provided by
	 * user.
	 * 
	 * @param fileName
	 *            - this is the name of the new file that will be created
	 * 
	 * @return none
	 */

	public void write(String fileName) {

		File file = new File(fileName);
		try (java.io.PrintWriter output = new java.io.PrintWriter(file);) {
		
			for (int i = 0; i < this.numOfPlaces(); i++) {
				output.print(this.retrieve(i).getName() + ";");
				output.print(this.retrieve(i).getAddress() + ";");
				output.print(this.retrieve(i).getLatitude() + ";");
				output.print(this.retrieve(i).getLongitude());
				output.print("\n");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to read from file: <" + fileName + ">");
			ex.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method prints out file names ending in .mfp in the current program
	 * directory.
	 * 
	 * @param none
	 * 
	 * @return none
	 */

	public void directory() {
		File folder = new File(".");
		for (File file : folder.listFiles()) {
			if (file.getName().endsWith(".mfp")) {
				System.out.print("\t" + file.getName() + "\n");
			}
		}
	}

	/**
	 * This method goes through current directory and checks if there is a file
	 * with the same filename provided String input that exists in the current
	 * directory.
	 * 
	 * @param String
	 *            input - name of the file
	 * 
	 * @return true - if found the same name of file false - if file with the
	 *         same name was not found
	 */

	public boolean fileExists(String input) {
		File folder = new File(".");
		for (File file : folder.listFiles()) {
			if (input.matches(file.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method sorts places in ascending order.
	 * 
	 * @param none
	 * 
	 * @return none
	 */

	public void sort() {
		Collections.sort(this.list);
	}

	/**
	 * This method is a getter method for ArrayList <Place> list where Place(s)
	 * adds onto the existing ArrayList.
	 * 
	 * @param place
	 *            - new place using Place's constructor.
	 * 
	 * @return none
	 */

	public void add(Place place) {
		list.add(place);
	}

	/**
	 * This method is a getter method for ArrayList <Place> list where Place(s)
	 * gets removed from the existing ArrayList.
	 * 
	 * @param index
	 *            - index of the place that is stored in the ArrayList.
	 * 
	 * @return none
	 */

	public void remove(int index) {
		list.remove(index);
	}

	/**
	 * This method is a getter method for ArrayList <Place> list where
	 * information from ArrayList gets pulled depending on the place index of
	 * the ArrayList.
	 * 
	 * @param index
	 *            - index of the place that is stored in the ArrayList.
	 * 
	 * @return Place element information at certain index
	 */

	public Place retrieve(int index) {
		return list.get(index);
	}

	/**
	 * This method checks to see if the ArrayList already contains the same
	 * Place information.
	 * 
	 * @param Place
	 *            lookFor - this is a Place object
	 * 
	 * @return whether list contains the place or not.
	 */
	public boolean areadyExist(Place lookFor) {
		return this.list.contains(lookFor);
	}

	/**
	 * This method is a getter method for ArrayList <Place> list where it
	 * provides size of the ArrayList.
	 * 
	 * @param none
	 * 
	 * @return size of the ArrayList
	 */

	public int numOfPlaces() {
		return list.size();

	}

}
