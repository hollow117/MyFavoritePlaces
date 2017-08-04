
///////////////////////////////////////////////////////////////////////////////
//                  
// Main Class File:  MyFavoritePlaces.java
// File:             Place.java
// Semester:         CS302 Spring 2016
//
// Author:           Jason Choe choe2@wisc.edu
// CS Login:         choe
// Lecturer's Name:  Jim Williams
// Lab Section:      313
///////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.net.URLEncoder;

/**
 * This class creates a place object and it gets saved onto the ArrayList in
 * PlaceList class. This class is used to compare different place objects.
 *
 * no known bugs
 *
 * @author Jason Choe
 */

public class Place implements Comparable<Place> {
	private String name;
	private String address;
	private double latitude;
	private double longitude;
	private double distance;
	private static Place currentPlace;
	private final String URL = "https://www.google.com/maps/place/";

	/**
	 * This is a default constructor.
	 * 
	 * @param none
	 * 
	 * @return none
	 */
	public Place() {

	}

	/**
	 * This constructor initializes a new instance of Place at the appropriate
	 * with name, address, latitude and longitude information.
	 * 
	 * @param String
	 *            place, String address, double Latitude, double Longitude
	 * 
	 * @return none
	 */

	public Place(String place, String address, double Latitude, 
			double Longitude) {
		this.name = place;
		this.address = address;
		this.latitude = Latitude;
		this.longitude = Longitude;
	}

	/**
	 * This method is a setter method that initializes the static variable
	 * currentPlace.
	 * 
	 * @param Place
	 *            place - place with name, address, latitude and longitude
	 *            information of the place
	 * 
	 * @return none
	 */

	public void setCurrentPlace(Place place) {
		currentPlace = place;
	}

	/**
	 * This method is a getter method that pulls out currentPlace information.
	 * 
	 * @param none
	 * 
	 * @return none
	 */

	public Place getCurrentPlace() {
		return currentPlace;
	}

	/**
	 * This method is a setter method that initializes the static variable
	 * currentPlace.
	 * 
	 * @param Place
	 *            place - place with name, address, latitude and longitude
	 *            information of the place
	 * 
	 * @return none
	 */

	public double getDifferenceWithCurrentPlace() {
		this.setDistance(currentPlace.getLatitude(), currentPlace.
				getLongitude(), this.getLatitude(), this.getLongitude());

		return Math.abs(this.getDistance());
	}

	/**
	 * This method is a setter method that initializes the name of object's
	 * place.
	 * 
	 * @param String
	 *            place - name of the place
	 * 
	 * @return none
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method is a getter method that returns the name of object's place.
	 * 
	 * @param none
	 * 
	 * @return name of the place
	 */

	public String getName() {
		return name;
	}

	/**
	 * This method is a setter method that initializes the address of object's
	 * place.
	 * 
	 * @param String
	 *            place - address of the place
	 * 
	 * @return none
	 */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method is a getter method that returns the address of object's
	 * place.
	 * 
	 * @param none
	 * 
	 * @return address of the place
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * This method is a setter method that initializes the latitude of object's
	 * place.
	 * 
	 * @param double
	 *            Latitude - latitude of the place
	 * 
	 * @return none
	 */

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * This method is a getter method that returns the latitude of object's
	 * place.
	 * 
	 * @param none
	 * 
	 * @return latitude of the place
	 */

	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * This method is a setter method that initializes the longitude of
	 * object's place.
	 * 
	 * @param double
	 *            Longitude - longitude of the place
	 * 
	 * @return none
	 */

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * This method is a getter method that returns the longitude of object's
	 * place.
	 * 
	 * @param none
	 * 
	 * @return longitude of the place
	 */

	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * This method is a setter method that initializes the distance between
	 * class variable currentPlace and the place that is compared to.
	 * 
	 * @param double
	 *            latitude1 - latitude of currentPlace double longitude1 -
	 *            longitude of currentPlace double latitude2 - latitude of the
	 *            place that is being compared double longitude2 - longitude of
	 *            the place that is being compared
	 * 
	 * @return none
	 */

	public void setDistance(double latitude1, double longitude1, 
			double latitude2, double longitude2) {
		distance = Geocoding.distance(latitude1, longitude1, latitude2, 
				longitude2);
	}

	/**
	 * This method is a getter method that returns the distance between
	 * currentPlace and place that is being compared.
	 * 
	 * @param none
	 * 
	 * @return distance between currentPlace and place that is being compared
	 */

	public double getDistance() {
		return distance;
	}

	/**
	 * This method creates URL that will be used in show option in
	 * MyFavoritePlace.java.
	 * 
	 * @param none
	 * 
	 * @return String of URL of the place
	 */

	public String url() throws UnsupportedEncodingException {
		StringBuffer response = new StringBuffer();
		response.append(URL);
		String replacedString = URLEncoder.encode(address, "UTF-8");
		response.append(replacedString);

		response.append("/@");
		response.append(latitude + "," + longitude + "," + "17z/");

		return response.toString();
	}


	/**
	 * This method overrides equals method that already exist. This on top of
	 * Object's equals method, it will also compare if object is an instance of
	 * Place and if it is, it will compare name and a setter method that
	 * initializes the static variable currentPlace.
	 * 
	 * @param Place
	 *            place - place with name, address, latitude and longitude
	 *            information of the place
	 * 
	 * @return true or false depending on if this place and another place is
	 * 		   equal or not equal
	 */

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else {
			if (o instanceof Place) {
				Place other = (Place) o;
				return (this.name.equals(other.name) && (this.address.
						equals(other.address)));
			} else {
				return false;
			}
		}
	}

	/**
	 * This method overrides compareTo method that already exist. While
	 * currentPlace is not set up, it will sort the list alphabetically. If
	 * currentPlace is set up, it will sort the list according to ascending
	 * order of distance between place and currentPlace.
	 * 
	 * @param Place
	 *            place - place's information that will be compared to
	 *            currentPlace
	 * 
	 * @return negative, 0 or positive value - comparing alphabets of place's
	 *         names or distance between currentPlace and place.
	 */

	@Override
	public int compareTo(Place place) {
		if (currentPlace == null) {
			// if currentPlace is null, this will compare places alphabetically
			// by name of places
			return this.getName().toLowerCase().compareTo(place.getName().
					toLowerCase());
		} else {
			// compare this distance to current to place distance to current
			// to see which is closest to currentPlace
			return (Double.compare(this.getDifferenceWithCurrentPlace(), place.
					getDifferenceWithCurrentPlace()));

		}
	}

}
