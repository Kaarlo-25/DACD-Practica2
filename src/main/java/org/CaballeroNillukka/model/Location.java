package org.CaballeroNillukka.model;

public class Location {

	//Constructor
	private final String name;
	private final float latitude;
	private final float longitude;
	public Location(String name, float latitude, float longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
	}

	//Methods
	public String toString(){
		return String.format("\n%s", name);
	}
}
